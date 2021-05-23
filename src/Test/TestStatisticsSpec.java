package Test;

import Interfaces.ITestStatisticsSpec;
import Questions.QuestionMultipleChoice;
import Questions.QuestionNumeric;
import Questions.QuestionYesNo;
import interfaces.models.IQuestion;

/*
* Nome: José Miguel Ribeiro da Cunha
* Número: 8170479
* Turma: 2
 */
public class TestStatisticsSpec implements ITestStatisticsSpec {

    private final IQuestion[] questions;

    public TestStatisticsSpec(IQuestion[] questions) {
        this.questions = questions;
    }

    /**
     * Método que retorna a percentagem de respostas corretas do tipo
     * MultipleChoiceAnswer
     *
     * @return - percentagem de respostas corretas do tipo MultipleChoiceAnswer
     */
    @Override
    public double correctMultipleChoiceAnswerPercentage() {
        int contadorCor = 0;
        int contadorTotal = 0;
        for (int i = 0; i < this.questions.length; i++) {
            if (this.questions[i] instanceof QuestionMultipleChoice) {
                if (this.questions[i].evaluateAnswer() == true) {
                    ++contadorCor;
                }
                ++contadorTotal;
            }
        }
        
        if(contadorTotal == 0){
            return 0;
        }
        
        return ((contadorCor * 100) / contadorTotal);
    }

    /**
     * Método que retorna a percentagem de respostas erradas do tipo
     * MultipleChoiceAnswer
     *
     * @return - percentagem de respostas erradas do tipo MultipleChoiceAnswer
     */
    @Override
    public double incorrectMultipleChoiceAnswerPercentage() {
        int contadorErr = 0;
        int contadorTotal = 0;
        for (int i = 0; i < this.questions.length; i++) {
            if (this.questions[i] instanceof QuestionMultipleChoice) {
                if (this.questions[i].evaluateAnswer() == false) {
                    ++contadorErr;
                }
                ++contadorTotal;
            }
        }
        
        if(contadorTotal == 0){
            return 0;
        }
        
        return ((contadorErr * 100) / contadorTotal);
    }

    /**
     * Método que retorna a percentagem de respostas corretas do tipo
     * NumericAnswer
     *
     * @return - percentagem de respostas corretas do tipo NumericAnswer
     */
    @Override
    public double correctNumericAnswerPercentage() {
        int contadorCor = 0;
        int contadorTotal = 0;
        for (int i = 0; i < this.questions.length; i++) {
            if (this.questions[i] instanceof QuestionNumeric) {
                if (this.questions[i].evaluateAnswer() == true) {
                    ++contadorCor;
                }
                ++contadorTotal;
            }
        }
        
        if(contadorTotal == 0){
            return 0;
        }
        
        return ((contadorCor * 100) / contadorTotal);
    }

    /**
     * Método que retorna a percentagem de respostas erradas do tipo
     * NumericAnswer
     *
     * @return - percentagem de respostas erradas do tipo NumericAnswer
     */
    @Override
    public double incorrectNumericAnswerPercentage() {
        int contadorErr = 0;
        int contadorTotal = 0;
        for (int i = 0; i < this.questions.length; i++) {
            if (this.questions[i] instanceof QuestionNumeric) {
                if (this.questions[i].evaluateAnswer() == false) {
                    ++contadorErr;
                }
                ++contadorTotal;
            }
        }
        
        if(contadorTotal == 0){
            return 0;
        }
        
        return ((contadorErr * 100) / contadorTotal);
    }

    /**
     * Método que retorna a percentagem de respostas corretas do tipo YesNo
     *
     * @return - percentagem de respostas corretas do tipo YesNo
     */
    @Override
    public double correctYesNoAnswerPercentage() {
        int contadorCor = 0;
        int contadorTotal = 0;
        for (int i = 0; i < this.questions.length; i++) {
            if (this.questions[i] instanceof QuestionYesNo) {
                if (this.questions[i].evaluateAnswer() == true) {
                    ++contadorCor;
                }
                ++contadorTotal;
            }
        }
        
        if(contadorTotal == 0){
            return 0;
        }
        
        return ((contadorCor * 100) / contadorTotal);
    }

    /**
     * Método que retorna a percentagem de respostas erradas do tipo YesNo
     *
     * @return - percentagem de respostas erradas do tipo YesNo
     */
    @Override
    public double incorrectYesNoAnswerPercentage() {
        int contadorErr = 0;
        int contadorTotal = 0;
        for (int i = 0; i < this.questions.length; i++) {
            if (this.questions[i] instanceof QuestionYesNo) {
                if (this.questions[i].evaluateAnswer() == false) {
                    ++contadorErr;
                }
                ++contadorTotal;
            }
        }
        
        if(contadorTotal == 0){
            return 0;
        }
        
        return ((contadorErr * 100) / contadorTotal);
    }

}
