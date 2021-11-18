
import java.io.Serializable;

public class Question implements Serializable {

    private String questionStatement;
    private String options[];
    private String category;
    private int correctOption;

    public Question(String questionStatement, String[] options, String category, int correctOption) {
        this.questionStatement = questionStatement;
        this.options = options;
        this.category = category;
        this.correctOption = correctOption;
    }

    public String getQuestionStatement() {
        return questionStatement;
    }

    public String[] getOptions() {
        return options;
    }

    public String getCategory() {
        return category;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public String toString() {
        String question = questionStatement + "\n";

        for (int i = 0; i < 4; i++) {
            question += (i + 1) + ") " + options[i] + "\n";
        }
        return question;
    }

}
