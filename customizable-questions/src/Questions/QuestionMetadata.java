package Questions;

import interfaces.models.IQuestionMetadata;

/*
* Nome: José Miguel Ribeiro da Cunha
* Número: 8170479
* Turma: 2
 */
public class QuestionMetadata implements IQuestionMetadata {

    private long start;
    private long finish;
    
    /**
     * Método que retorna quando a contagem do tempo começou
     *
     * @return - retorna quando a contagem do tempo começou
     */
    @Override
    public long getTimestamp_start() {
        return this.start;
    }

    /**
     * Método que define quando a contagem do tempo começou
     *
     * @param l - quando a contagem do tempo começou
     */
    @Override
    public void setTimestamp_start(long l) {
        this.start = l;
    }

    /**
     * Método que retorna quando a contagem do tempo acabou
     *
     * @return - retorna quando a contagem do tempo acabou
     */
    @Override
    public long getTimestamp_finish() {
        return this.finish;
    }

    /**
     * Método que define quando a contagem do tempo acabou
     *
     * @param l - quando a contagem do tempo acabou
     */
    @Override
    public void setTimestamp_finish(long l) {
        this.finish = l;
    }

}
