import exceptions.QuestionAlreadyExistsException;
import exceptions.QuestionNotExistException;
import impl.Question;
import impl.QuestionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



public class JavaQuestionServiceTest {

    private final QuestionService questionService = new JavaQuestionService();

    @BeforeEach
    public void setUp() {}

    @AfterEach
    public void cleanUp() {}

    @Test
    public void addNegativeTest() {
        Question task = new Question("WHAT");
        questionService.add(task);
        assertThrows(QuestionAlreadyExistsException.class, () -> questionService.add(task));
    }

    @Test
    public void addPositiveTest() {
        Question task = new Question("WHAT");
        assertEquals(task, questionService.add(task));
    }

    @Test
    public void removeNegativeTest() {
        Question task = new Question("WHAT");
        assertThrows(QuestionNotExistException.class, () -> questionService.remove(task.getQuestion()));
    }

    @Test
    public void removePositiveTest() {
        Question task = new Question("WHAT");
        questionService.add(task);
        assertEquals(task, questionService.remove(task.getQuestion()));
    }

    @Test
    public void getRandomQuestionNegativeTest() {
        for (int i = 1; i <= 5; i++) {
            questionService.add(new Question("q" + i, "a" + i));
        }
        assertThat(questionService.getRandomQuestion()).isIn(questionService.getAll());
    }

    @Test
    public void getRandomQuestionPositiveTest() {
        assertThat(questionService.getAll()).isEmpty();
        assertThat(questionService.getRandomQuestion()).isNull();
    }

    private class JavaQuestionService implements QuestionService {
        @Override
        public Question add(String question, String answer) {
            return null;
        }

        @Override
        public Question add(Question task) {
            return null;
        }

        @Override
        public Question remove(String task) {
            return null;
        }

        @Override
        public Collection<Question> getAll() {
            return null;
        }

        @Override
        public Question getRandomQuestion() {
            return null;
        }
    }}