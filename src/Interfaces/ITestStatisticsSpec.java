package Interfaces;

/*
* Nome: José Miguel Ribeiro da Cunha
* Número: 8170479
* Turma: 2
 */
public interface ITestStatisticsSpec {
    
    /**
     * Método que retorna a percentagem de respostas corretas do tipo MultipleChoiceAnswer
     *
     * @return - percentagem de respostas corretas do tipo MultipleChoiceAnswer
     */
    public abstract double correctMultipleChoiceAnswerPercentage();
    
    /**
     * Método que retorna a percentagem de respostas erradas do tipo MultipleChoiceAnswer
     *
     * @return - percentagem de respostas erradas do tipo MultipleChoiceAnswer
     */
    public abstract double incorrectMultipleChoiceAnswerPercentage();
    
    /**
     * Método que retorna a percentagem de respostas corretas do tipo NumericAnswer
     *
     * @return - percentagem de respostas corretas do tipo NumericAnswer
     */
    public abstract double correctNumericAnswerPercentage();
    
    /**
     * Método que retorna a percentagem de respostas erradas do tipo NumericAnswer
     *
     * @return - percentagem de respostas erradas do tipo NumericAnswer
     */
    public abstract double incorrectNumericAnswerPercentage();
    
    /**
     * Método que retorna a percentagem de respostas corretas do tipo YesNo
     *
     * @return - percentagem de respostas corretas do tipo YesNo
     */
    public abstract double correctYesNoAnswerPercentage();
    
    /**
     * Método que retorna a percentagem de respostas erradas do tipo YesNo
     *
     * @return - percentagem de respostas erradas do tipo YesNo
     */
    public abstract double incorrectYesNoAnswerPercentage();
}
