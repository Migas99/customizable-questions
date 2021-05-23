package Exceptions;

/*
* Nome: José Miguel Ribeiro da Cunha
* Número: 8170479
* Turma: 2
*/
public class TestException extends Throwable {

    private String message;

    public TestException() {

    }

    public TestException(String message) {
        this.message = message;
        System.out.println(this.message);
    }
    
}
