import exceptions.IncorrectAmountOfQuestionsException;
import impl.ExaminerServiceImpl;
import impl.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionService questionService = new JavaQuestionService();

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private static List<Question> QUESTIONS = List.of(
            new Question("q1", "a1"),
            new Question("q2", "a2"),
            new Question("q3", "a3"),
            new Question("q4", "a4"),
            new Question("q5", "a5")
    );



    @ParameterizedTest
    @MethodSource("getQuestionsNegativeParams")
    public void getQuestionsNegative(int incorrectAmount) {
        assertThrows(IncorrectAmountOfQuestionsException.class, () -> examinerService.getQuestions(incorrectAmount));
    }

    @Test
    public void getQuestionsPositive() {
        when(questionService.getRandomQuestion()).thenReturn(
                QUESTIONS.get(1),
                QUESTIONS.get(2),
                QUESTIONS.get(1),
                QUESTIONS.get(3),
                QUESTIONS.get(0)
        );
        assertThat(examinerService.getQuestions(4)).containsExactly(
                QUESTIONS.get(1),
                QUESTIONS.get(2),
                QUESTIONS.get(3),
                QUESTIONS.get(0)
        );
        when(questionService.getRandomQuestion()).thenReturn(
                QUESTIONS.get(3),
                QUESTIONS.get(3),
                QUESTIONS.get(3),
                QUESTIONS.get(2)
        );
        assertThat(examinerService.getQuestions(2)).containsExactly(
                QUESTIONS.get(3),
                QUESTIONS.get(2)

        );
    }

    public static Stream<Arguments> getQuestionsNegativeParams() {
        return Stream.of(
                Arguments.of(-1),
                Arguments.of(QUESTIONS.size() + 1),
                Arguments.of(QUESTIONS.size() + 100)
        );
    }