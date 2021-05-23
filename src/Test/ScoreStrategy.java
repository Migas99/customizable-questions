package Test;

import interfaces.controller.IScoreStrategy;
import interfaces.exceptions.TestException;
import interfaces.models.IQuestion;

/*
* Nome: José Miguel Ribeiro da Cunha
* Número: 8170479
* Turma: 2
 */
public class ScoreStrategy implements IScoreStrategy {

    private double sf, sfb, exc;

    public ScoreStrategy (double min, double max) throws TestException{
        if(max>min){
            this.sf = ((max+min)*0.5);
            this.sfb = ((max+min)*0.7);
            this.exc = ((max+min)*0.85);
        }else{
            throw new TestException("O valor mínimo é maior que o valor máximo");
        }
    }

    /**
     * Método que calcula a classificação do teste
     *
     * @param iqs - Array com as questões do teste
     * @return - retorna a classificação do teste
     */
    @Override
    public String CalculateScore(IQuestion[] iqs) {
        double nota = 0;
        String classificação = null;
        for (IQuestion iq : iqs) {
            if (iq.evaluateAnswer() == true) {
                nota = nota + iq.getMark();
            }
        }
        if (nota < this.sf) {
            classificação = "Não satisfaz";
        } else {
            if (nota < this.sfb) {
                classificação = "Satisfaz";
            } else {
                if (nota < this.exc) {
                    classificação = "Satisfaz bastante";
                } else {
                    if (nota >= this.exc) {
                        classificação = "Excelente";
                    }
                }
            }
        }
        return classificação;
    }
}
