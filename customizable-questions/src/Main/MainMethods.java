package Main;

import Interfaces.ITestStatisticsSpec;
import Questions.Question;
import Test.ScoreStrategy;
import Test.Test;
import Test.TestMultipleChoiceOnly;
import Test.TestStatisticsSpec;
import interfaces.controller.ITest;
import interfaces.exceptions.TestException;
import interfaces.models.IQuestion;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import views.TestWindow;

/*
* Nome: José Miguel Ribeiro da Cunha
* Número: 8170479
* Turma: 2
 */
public class MainMethods {

    /**
     * Método que cálcula outras estatísticas extras
     *
     * @param demoTest - recebe como parâmetro a instância de um Teste
     * @throws TestException - se numa dada posição do array de questões a
     * questão estiver com valor null
     */
    public void testStatisticsSpec(ITest demoTest) throws TestException {
        IQuestion[] questions = new Question[demoTest.numberQuestions()];
        for (int i = 0; i < demoTest.numberQuestions(); i++) {
            questions[i] = demoTest.getQuestion(i);
        }
        ITestStatisticsSpec testStatSpec = new TestStatisticsSpec(questions);
        System.out.println("Percentagem de questões do tipo MultipleChoice corretas: " + testStatSpec.correctMultipleChoiceAnswerPercentage());
        System.out.println("Percentagem de questões do tipo MultipleChoice erradas: " + testStatSpec.incorrectMultipleChoiceAnswerPercentage());
        System.out.println("Percentagem de questões do tipo Numeric corretas: " + testStatSpec.correctNumericAnswerPercentage());
        System.out.println("Percentagem de questões do tipo Numeric erradas: " + testStatSpec.incorrectNumericAnswerPercentage());
        System.out.println("Percentagem de questões do tipo YesNo corretas: " + testStatSpec.correctYesNoAnswerPercentage());
        System.out.println("Percentagem de questões do tipo YesNo erradas: " + testStatSpec.incorrectYesNoAnswerPercentage());
    }

    /**
     * Método que inicia um teste do tipo normal
     *
     * @param testWindow - instância do tipo TestWindow
     * @param JSONFile - Path da localização do ficheiro JSON que irá ser lido
     * para a criação das perguntas
     * @param min - definição da nota mínima possível de ser tirada neste teste
     * @param max - definição da nota máxima possível de ser tirada neste teste
     * @throws TestException - se não conseguir armazenar uma certa questão
     * @throws java.io.IOException - se tiver algum erro ao carregar o ficheiro
     * com as perguntas do teste
     */
    public void beginTest(TestWindow testWindow, String JSONFile, int min, int max) throws TestException, IOException {
        ITest demoTest = new Test();
        demoTest.setScoreStrategy(new ScoreStrategy(min, max));
        System.out.println("Inicio de Teste!");
        System.out.println("Hora em que começou o teste: " + LocalTime.now());
        demoTest.loadFromJSONFile(JSONFile);
        testWindow.startTest(demoTest);
        System.out.println("Teste Efetuado!");
        System.out.println("Hora em que acabou o teste: " + LocalTime.now());
        System.out.println(demoTest.toString());
        this.testStatisticsSpec(demoTest);
    }

    /**
     * Método que inicia um teste do tipo MultipleChoiceOnly
     *
     * @param testWindow - instância do tipo TestWindow
     * @param JSONFile - Path da localização do ficheiro JSON que irá ser lido
     * para a criação das perguntas
     * @param min - definição da nota mínima possível de ser tirada neste teste
     * @param max - definição da nota máxima possível de ser tirada neste teste
     * @throws TestException - se não conseguir armazenar uma certa questão
     * @throws java.io.IOException - se tiver algum erro ao carregar o ficheiro
     * com as perguntas do teste
     */
    public void beginTestMultipleChoice(TestWindow testWindow, String JSONFile, int min, int max) throws TestException, IOException {
        ITest demoTestMultipleChoiceOnly = new TestMultipleChoiceOnly();
        demoTestMultipleChoiceOnly.setScoreStrategy(new ScoreStrategy(min, max));
        System.out.println("Inicio de Teste!");
        System.out.println("Hora em que começou o teste: " + LocalTime.now());
        demoTestMultipleChoiceOnly.loadFromJSONFile(JSONFile);
        testWindow.startTest(demoTestMultipleChoiceOnly);
        System.out.println("Teste Efetuado!");
        System.out.println("Hora em que acabou o teste: " + LocalTime.now());
        System.out.println(demoTestMultipleChoiceOnly.toString());
        this.testStatisticsSpec(demoTestMultipleChoiceOnly);
    }

    /**
     * Método que carrega um teste em formato txt e o apresenta em formato de
     * SystemOut as suas características
     *
     * @param path - localização do ficheiro
     * @param fileName - nome do teste guardado em formato txt
     * @throws FileNotFoundException - caso o ficheiro não tenha sido encontrado
     * @throws IOException - caso haja um erro na leitura do arquivo
     */
    public void readTest(String path, String fileName) throws FileNotFoundException, IOException {
        FileReader arq = new FileReader(path + "/" + fileName + ".txt");
        BufferedReader lerArq = new BufferedReader(arq);

        String st;
        while ((st = lerArq.readLine()) != null) {
            System.out.println(st);
        }
        lerArq.close();
    }

    /**
     * Método que carrega um teste em formato txt e o apresenta em formato de
     * SystemOut as suas características
     *
     * @param pasta - indicação do nome da pasta onde os resultados estão
     * armazenados
     * @throws interfaces.exceptions.TestException - caso a path não exista
     * @throws FileNotFoundException - caso o ficheiro não tenha sido encontrado
     * @throws IOException - caso haja um erro na leitura do arquivo
     */
    public void sortByNumberCorrectAnswers(String pasta) throws TestException, FileNotFoundException, IOException {
        String path = ("results/" + pasta);
        File dir = new File(path);
        if (dir.exists() == false) {
            throw new TestException("A path inserida não existe!");
        }
        File[] files = dir.listFiles();
        int[] ncorrect = new int[dir.listFiles().length];

        for (int i = 0; i < files.length; i++) {
            FileReader arq = new FileReader(path + "/" + files[i].getName());
            BufferedReader lerArq = new BufferedReader(arq);
            String st;
            while ((st = lerArq.readLine()) != null) {
                if (st.contains("NumRespCor")) {
                    String value = st.replace("NumRespCor: ", "");
                    ncorrect[i] = Integer.valueOf(value);
                }
            }
            lerArq.close();
        }

        for (int i = 0; i < files.length; i++) {
            for (int j = 0; j < files.length; j++) {
                if (ncorrect[i] > ncorrect[j]) {
                    int n = ncorrect[i];
                    File f = files[i];

                    ncorrect[i] = ncorrect[j];
                    files[i] = files[j];

                    ncorrect[j] = n;
                    files[j] = f;

                    j = 0;
                }
            }
        }

        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].getName());
            System.out.println("Número respostas correctas: " + ncorrect[i]);
        }
    }

}
