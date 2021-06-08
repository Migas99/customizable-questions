package Test;

import Questions.Question;
import interfaces.controller.ITestStatistics;
import interfaces.models.IQuestion;

/*
* Nome: José Miguel Ribeiro da Cunha
* Número: 8170479
* Turma: 2
 */
public class TestStatistics implements ITestStatistics {

    private IQuestion[] questions;

    public TestStatistics(IQuestion[] questions) {
        this.questions = questions;
    }

    /**
     * Método que retorna a média de tempo gasto por questão
     *
     * @return - média de tempo gasto por questão
     */
    @Override
    public double meanTimePerAnswer() {
        return ((this.questions[0].getQuestion_metadata().getTimestamp_start() - this.questions[this.questions.length - 1].getQuestion_metadata().getTimestamp_finish()) / this.questions.length);
    }

    @Override
    public double standardDeviationTimePerAnsewer() {
        double desvPad = 0;
        for (int i = 0; i < this.questions.length; i++) {
            double valor = ((this.questions[i].getQuestion_metadata().getTimestamp_finish() - this.questions[i].getQuestion_metadata().getTimestamp_start()) - this.meanTimePerAnswer());
            desvPad = desvPad + (valor * valor);
        }
        return Math.sqrt(desvPad / this.questions.length);
    }

    /**
     * Método que retorna a percentagem de respostas corretas
     *
     * @return - percentagem de respostas corretas
     */
    @Override
    public double correctAnswerPecentage() {
        return ((this.correctAnswer() * 100) / this.questions.length);
    }

    /**
     * Método que retorna a percentagem de respostas erradas
     *
     * @return - percentagem de respostas erradas
     */
    @Override
    public double incorrectAnswerPecentage() {
        return ((this.incorrectAnswer() * 100) / this.questions.length);
    }

    /**
     * Método que retorna o número de respostas corretas
     *
     * @return - número de respostas corretas
     */
    @Override
    public int correctAnswer() {
        int contador = 0;
        for (int i = 0; i < this.questions.length; i++) {
            if (this.questions[i].evaluateAnswer() == true) {
                ++contador;
            }
        }
        return contador;
    }

    /**
     * Método que retorna o número de respostas erradas
     *
     * @return - número de respostas erradas
     */
    @Override
    public int incorrectAnswer() {
        int contador = 0;
        for (int i = 0; i < this.questions.length; i++) {
            if (this.questions[i].evaluateAnswer() == false) {
                ++contador;
            }
        }
        return contador;
    }

    /**
     * Método que retorna um array de questões contendo apenas aquelas que o
     * utilizador errou
     *
     * @return - array de questões contendo apenas aquelas que o utilizador
     * errou
     */
    @Override
    public IQuestion[] incorrectAnswers() {
        IQuestion[] newIncorrect = new Question[this.incorrectAnswer()];
        if (this.incorrectAnswer() > 0) {
            for (int i = 0; i < this.questions.length; i++) {
                if (this.questions[i].evaluateAnswer() == false) {
                    for (int j = 0; j < newIncorrect.length; j++) {
                        if (newIncorrect[j] == null) {
                            newIncorrect[j] = this.questions[i];
                            break;
                        }
                    }
                }
            }
        }
        return newIncorrect;
    }

    /**
     * Método que retorna um array de questões contendo apenas aquelas que o
     * utilizador acertou
     *
     * @return - array de questões contendo apenas aquelas que o utilizador
     * acertou
     */
    @Override
    public IQuestion[] correctAnswers() {
        IQuestion[] newCorrect = new Question[this.correctAnswer()];
        if (this.correctAnswer() > 0) {
            for (int i = 0; i < this.questions.length; i++) {
                if (this.questions[i].evaluateAnswer() == true) {
                    for (int j = 0; j < newCorrect.length; j++) {
                        if (newCorrect[j] == null) {
                            newCorrect[j] = this.questions[i];
                            break;
                        }
                    }
                }
            }
        }
        return newCorrect;
    }

}
