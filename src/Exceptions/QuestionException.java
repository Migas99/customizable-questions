package Exceptions;

/*
* Nome: José Miguel Ribeiro da Cunha
* Número: 8170479
* Turma: 2
 */
public class QuestionException extends Throwable {

    private String message;

    public QuestionException() {

    }

    public QuestionException(String message) {
        this.message = message;
        System.out.println(this.message);
    }
}
