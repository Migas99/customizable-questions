package Main;

import Interfaces.ISaveTestToJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import interfaces.exceptions.TestException;
import interfaces.models.IQuestionMultipleChoice;
import interfaces.models.IQuestionNumeric;
import interfaces.models.IQuestionYesNo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
* Nome: José Miguel Ribeiro da Cunha
* Número: 8170479
* Turma: 2
 */
public class SaveTestToJson implements ISaveTestToJson {

    private JsonArray test = new JsonArray();

    /**
     * Método que adiciona uma questão a um JsonArray
     *
     * @param question - questão que irá ser guardada
     * @param fileName - nome do teste
     * @throws TestException - caso a path inserida não exista
     * @throws IOException - caso haja um erro ao escrever ou fechar o arquivo
     */
    @Override
    public void saveQuestionMultipleChoice(IQuestionMultipleChoice question, String fileName) throws TestException, IOException {
        File dir = new File("tests_done");
        if (dir.exists() == false) {
            throw new TestException("A path inserida não existe!");
        }

        String[] array = question.getOptions();

        JsonObject obj = new JsonObject();
        JsonObject elements = new JsonObject();
        JsonArray options = new JsonArray();

        for (int i = 0; i < array.length; i++) {
            options.add(array[i]);
        }

        obj.addProperty("type", "MultipleChoice");
        elements.addProperty("title", question.getTitle());
        elements.addProperty("question_description", question.getQuestion_description());
        elements.addProperty("mark", question.getMark());
        elements.add("options", options);
        elements.addProperty("correct_answer", question.getCorrect_answer());
        obj.add("question", elements);
        this.test.add(obj);

    }

    /**
     * Método que adiciona uma questão a um JsonArray
     *
     * @param question - questão que irá ser guardada
     * @param fileName - nome do teste
     * @throws TestException - caso a path inserida não exista
     * @throws IOException - caso haja um erro ao escrever ou fechar o arquivo
     */
    @Override
    public void saveQuestionNumeric(IQuestionNumeric question, String fileName) throws TestException, IOException {
        File dir = new File("tests_done");
        if (dir.exists() == false) {
            throw new TestException("A path inserida não existe!");
        }

        JsonObject obj = new JsonObject();
        JsonObject elements = new JsonObject();

        obj.addProperty("type", "Numeric");
        elements.addProperty("title", question.getTitle());
        elements.addProperty("question_description", question.getQuestion_description());
        elements.addProperty("mark", question.getMark());
        elements.addProperty("correct_answer", question.getCorrect_anwser());
        obj.add("question", elements);
        this.test.add(obj);

    }

    /**
     * Método que adiciona uma questão a um JsonArray
     *
     * @param question - questão que irá ser guardada
     * @param fileName - nome do teste
     * @throws TestException - caso a path inserida não exista
     * @throws IOException - caso haja um erro ao escrever ou fechar o arquivo
     */
    @Override
    public void saveQuestionYesNo(IQuestionYesNo question, String fileName) throws TestException, IOException {
        File dir = new File("tests_done");
        if (dir.exists() == false) {
            throw new TestException("A path inserida não existe!");
        }

        JsonObject obj = new JsonObject();
        JsonObject elements = new JsonObject();

        obj.addProperty("type", "YesNo");
        elements.addProperty("title", question.getTitle());
        elements.addProperty("question_description", question.getQuestion_description());
        elements.addProperty("mark", question.getMark());
        elements.addProperty("correct_answer", question.getCorrect_answer());
        obj.add("question", elements);
        this.test.add(obj);

    }

    /**
     * Método que guarda um teste em formato Json
     *
     * @param fileName - nome do teste
     * @throws IOException - caso haja um erro ao escrever ou fechar o arquivo
     */
    @Override
    public void writeToJson(String fileName) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter arq = new FileWriter("tests_done/" + fileName + ".json", true);
        arq.write(gson.toJson(this.test));
        arq.close();
        System.out.println("Guardada com sucesso!");
    }

}
