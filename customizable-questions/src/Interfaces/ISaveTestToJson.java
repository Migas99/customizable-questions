package Interfaces;

import interfaces.exceptions.TestException;
import interfaces.models.IQuestionMultipleChoice;
import interfaces.models.IQuestionNumeric;
import interfaces.models.IQuestionYesNo;
import java.io.IOException;

/*
* Nome: José Miguel Ribeiro da Cunha
* Número: 8170479
* Turma: 2
 */
public interface ISaveTestToJson {
    
    /**
     * Método que adiciona uma questão a um JsonArray
     *
     * @param question - questão que irá ser guardada
     * @param fileName - nome do teste
     * @throws TestException - caso a path inserida não exista
     * @throws IOException - caso haja um erro ao escrever ou fechar o arquivo
     */
    public abstract void saveQuestionMultipleChoice(IQuestionMultipleChoice question, String fileName) throws TestException, IOException;
    
    /**
     * Método que adiciona uma questão a um JsonArray
     *
     * @param question - questão que irá ser guardada
     * @param fileName - nome do teste
     * @throws TestException - caso a path inserida não exista
     * @throws IOException - caso haja um erro ao escrever ou fechar o arquivo
     */
    public abstract void saveQuestionNumeric(IQuestionNumeric question, String fileName) throws TestException, IOException;
    
    /**
     * Método que adiciona uma questão a um JsonArray
     *
     * @param question - questão que irá ser guardada
     * @param fileName - nome do teste
     * @throws TestException - caso a path inserida não exista
     * @throws IOException - caso haja um erro ao escrever ou fechar o arquivo
     */
    public abstract void saveQuestionYesNo(IQuestionYesNo question, String fileName) throws TestException, IOException;
    
    /**
     * Método que guarda um teste em formato Json
     *
     * @param fileName - nome do teste
     * @throws IOException - caso haja um erro ao escrever ou fechar o arquivo
     */
    public void writeToJson(String fileName) throws IOException;
}
