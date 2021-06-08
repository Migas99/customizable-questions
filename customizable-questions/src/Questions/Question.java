package Questions;

import interfaces.exceptions.QuestionException;
import interfaces.models.IQuestion;
import interfaces.models.IQuestionMetadata;

/*
* Nome: José Miguel Ribeiro da Cunha
* Número: 8170479
* Turma: 2
 */
public abstract class Question implements IQuestion {

    private IQuestionMetadata questionMetadata;
    private String title;
    private String question_description;
    private boolean done = false;
    private float mark;

    /**
     * Método construtor da classe Question
     *
     * @param title - Título da questão
     * @param question_description - descrição da questão
     * @param mark - valor da questão
     */
    public Question(String title, String question_description, float mark) {
        this.title = title;
        this.question_description = question_description;
        this.mark = mark;
        this.questionMetadata = new QuestionMetadata();
    }

    /**
     * Método que retorna o título da questão
     *
     * @return - retorna o título da questão
     */
    @Override
    public String getTitle() {
        return this.title;
    }

    /**
     * Método que define o título da questão
     *
     * @param string - Titulo da questão
     * @throws QuestionException - se o input for null
     */
    @Override
    public void setTitle(String string) throws QuestionException {
        if (string != null) {
            this.title = string;
        } else {
            throw new QuestionException("O parâmetro de entrada é null");
        }
    }

    /**
     * Método que retorna a descrição da questão
     *
     * @return - retorna a descrição da questão
     */
    @Override
    public String getQuestion_description() {
        return this.question_description;
    }

    /**
     * Método que define a descrição da questão
     *
     * @param string - Descrição da questão
     * @throws QuestionException - se o input for null
     */
    @Override
    public void setQuestion_description(String string) throws QuestionException {
        if (string != null) {
            this.question_description = string;
        } else {
            throw new QuestionException("O parâmetro de entrada é null");
        }
    }

    /**
     * Método que retorna a QuestionMetadata
     *
     * @return - retorna a instância de QuestionMetadata
     */
    @Override
    public IQuestionMetadata getQuestion_metadata() {
        return this.questionMetadata;
    }

    /**
     * Método que define QuestionMetadata
     *
     * @param iqm - Instância de QuestionMetadata
     */
    @Override
    public void setQuestion_metadata(IQuestionMetadata iqm) {
        this.questionMetadata = iqm;
    }

    /**
     * Método que retorna o valor (true ou false) de se a questão foi respondida
     *
     * @return - retorna se a questão foi respondida
     */
    @Override
    public boolean isDone() {
        return this.done;
    }

    /**
     * Método que define se a questão foi resolvida
     *
     * @param bln - se foi resolvida (true) ou não foi resolvida (false)
     */
    @Override
    public void setDone(boolean bln) {
        this.done = bln;
    }

    /**
     * Método que define a resposta do utilizador
     *
     * @param user_answer - resposta do utilizador
     */
    @Override
    public abstract void answer(String user_answer);

    /**
     * Método que avalia a resposta do utilizador
     *
     * @return : True - se a resposta está correcta | False - se a resposta está
     * errada
     */
    @Override
    public abstract boolean evaluateAnswer();

    /**
     * Método que define o valor de uma questão
     *
     * @param f - valor da questão
     */
    @Override
    public void setMark(float f) {
        this.mark = f;
    }

    /**
     * Método que retorna a cotação de uma questão
     *
     * @return - retorna o valor da questão
     */
    @Override
    public float getMark() {
        return this.mark;
    }

}
