package Questions;

import interfaces.models.IQuestionMultipleChoice;

/*
* Nome: José Miguel Ribeiro da Cunha
* Número: 8170479
* Turma: 2
 */
public class QuestionMultipleChoice extends Question implements IQuestionMultipleChoice {

    private String options[];
    private String correct_anwser;
    private String user_answer;

    /**
     * Método construtor da classe QuestionMultipleChoice
     *
     * @param title - Título da questão
     * @param question_description - descrição da questão
     * @param mark - valor da questão
     * @param options - array que contem as várias opções de resposta
     * @param correct_answer - a resposta correta à questão
     */
    public QuestionMultipleChoice(String title, String question_description, float mark, String options[], String correct_answer) {
        super(title, question_description, mark);
        this.options = options;
        this.correct_anwser = correct_answer;
        this.user_answer = new String();
    }

    /**
     * Método que retorna o array de opções
     *
     * @return - retorna o array de opções
     */
    @Override
    public String[] getOptions() {
        return this.options;
    }

    /**
     * Método que define o array de opções
     *
     * @param strings - array de opções
     */
    @Override
    public void setOptions(String[] strings) {
        this.options = strings;
    }

    /**
     * Método que retorna a resposta correta à questão
     *
     * @return - retorna a resposta correta à questão
     */
    @Override
    public String getCorrect_answer() {
        return this.correct_anwser;
    }

    /**
     * Método que define a resposta correta à questão
     *
     * @param string - resposta correta à questão
     */
    @Override
    public void setCorrect_answer(String string) {
        this.correct_anwser = string;
    }

    /**
     * Método que retorna a resposta do utilizador à questão
     *
     * @return - retorna a resposta utilizador à questão
     */
    @Override
    public String getUser_answer() {
        return this.user_answer;
    }

    /**
     * Método que define a resposta do utilizador à questão
     *
     * @param string - resposta do utilizador à questão
     */
    @Override
    public void setUser_answer(String string) {
        this.user_answer = string;
    }

    /**
     * Método que define a resposta do utilizador
     *
     * @param user_answer - resposta do utilizador
     */
    @Override
    public void answer(String user_answer) {
        this.setUser_answer(user_answer);
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
        return getUser_answer().equals(getCorrect_answer());
    }

}
