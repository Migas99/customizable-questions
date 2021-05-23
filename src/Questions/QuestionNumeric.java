package Questions;

import interfaces.models.IQuestionNumeric;

/*
* Nome: José Miguel Ribeiro da Cunha
* Número: 8170479
* Turma: 2
 */
public class QuestionNumeric extends Question implements IQuestionNumeric {

    private double correct_anwser;
    private double user_answer;

    /**
     * Método construtor da classe QuestionNumeric
     *
     * @param title - Título da questão
     * @param question_description - descrição da questão
     * @param mark - valor da questão
     * @param correct_answer - resposta correta à questão
     */
    public QuestionNumeric(String title, String question_description, float mark, double correct_answer) {
        super(title, question_description, mark);
        this.correct_anwser = correct_answer;
    }

    /**
     * Método que retorna a resposta correta à questão
     *
     * @return - retorna a resposta correta à questão
     */
    @Override
    public double getCorrect_anwser() {
        return this.correct_anwser;
    }

    /**
     * Método que define a resposta correta à questão
     *
     * @param d - resposta correta à questão
     */
    @Override
    public void setCorrect_anwser(double d) {
        this.correct_anwser = d;
    }

    /**
     * Método que retorna a resposta do utilizador à questão
     *
     * @return - retorna a resposta utilizador à questão
     */
    @Override
    public double getUser_answer() {
        return this.user_answer;
    }

    /**
     * Método que define a resposta do utilizador à questão
     *
     * @param d - resposta do utilizador à questão
     */
    @Override
    public void setUser_answer(double d) {
        this.user_answer = d;
    }

    /**
     * Método que define a resposta do utilizador
     *
     * @param user_answer - resposta do utilizador
     */
    @Override
    public void answer(String user_answer) {
        this.setUser_answer(Double.parseDouble(user_answer));
        super.setDone(true);
    }

    /**
     * Método que avalia a resposta do utilizador
     *
     * @return : True - se a resposta está correcta | False - se a resposta está
     * errada
     */
    @Override
    public boolean evaluateAnswer() {
        return this.user_answer == this.correct_anwser;
    }

}
