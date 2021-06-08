package Test;

import Questions.Question;
import Questions.QuestionMultipleChoice;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;
import interfaces.controller.IScoreStrategy;
import interfaces.controller.ITest;
import interfaces.controller.ITestStatistics;
import interfaces.exceptions.TestException;
import interfaces.models.IQuestion;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/*
* Nome: José Miguel Ribeiro da Cunha
* Número: 8170479
* Turma: 2
 */
public class TestMultipleChoiceOnly implements ITest {

    private IScoreStrategy scoreStrat;
    private ITestStatistics testStat;
    private IQuestion[] questions = new Question[0];

    /**
     * Método que adiciona uma questão ao array de questões
     *
     * @param iq - Questão que queremos adicionar ao array de Questões
     * @return : True - se a questão foi adicionada com sucesso | False - se a
     * questão não foi adicionada com sucesso
     * @throws TestException - se a questão que recebemos como parâmetro for
     * null ou não é uma questão de escolha múltipla
     */
    @Override
    public boolean addQuestion(IQuestion iq) throws TestException {
        if (iq != null && (iq instanceof QuestionMultipleChoice) == true) {
            IQuestion[] trans = Arrays.copyOf(this.questions, this.questions.length + 1);
            for (int i = 0; i < trans.length; i++) {
                if (trans[i] == null) {
                    trans[i] = iq;
                    this.questions = trans;
                    return true;
                }
            }
        } else {
            throw new TestException("A questão passada como parâmetro de entrada é null ou não é uma questão de escolha múltipla");
        }
        return false;
    }

    /**
     * Método que retorna uma questão do array de questões
     *
     * @param i - Posição da questão no array de questões
     * @return - retorna a questão naquela posição do array de questões
     * @throws TestException - se a questão naquela posição for null
     */
    @Override
    public IQuestion getQuestion(int i) throws TestException {
        if (this.questions[i] != null) {
            return this.questions[i];
        } else {
            throw new TestException("A questão nessa posição do array é null");
        }
    }

    /**
     * Método que remove uma questão do array de questões
     *
     * @param i - Posição no array de questões da questão que queremos remover
     * @return : True - se a questão foi removida com sucesso | False - se a
     * questão não foi removida com sucesso
     */
    @Override
    public boolean removeQuestion(int i) {
        if (i >= 0 && i < this.questions.length && this.questions[i] != null) {
            for (int x = i; x < this.questions.length; x++) {
                if (x == this.questions.length - 1) {
                    this.questions[x] = null;
                } else {
                    this.questions[x] = this.questions[++x];
                    --x;
                }
            }
            IQuestion[] trans = Arrays.copyOf(this.questions, this.questions.length - 1);
            this.questions = trans;
            return true;
        }
        return false;
    }

    /**
     * Método que remove uma questão do array de questões
     *
     * @param iq - Questão que queremos remover do array de questões
     * @return : True - se a questão foi removida com sucesso | False - se a
     * questão não foi removida com sucesso
     */
    @Override
    public boolean removeQuestion(IQuestion iq) {
        if (iq != null) {
            int position = -1;
            for (int i = 0; i < this.questions.length; i++) {
                if (this.questions[i] != null && this.questions[i] == iq) {
                    position = i;
                }
            }
            if (position >= 0) {
                for (int x = position; x < this.questions.length; x++) {
                    if (x == this.questions.length - 1) {
                        this.questions[x] = null;
                    } else {
                        this.questions[x] = this.questions[++x];
                        --x;
                    }
                }
                IQuestion[] trans = Arrays.copyOf(this.questions, this.questions.length - 1);
                this.questions = trans;
                return true;
            }
        }
        return false;
    }

    /**
     * Método que retorna o número de questões no array de questões
     *
     * @return - Número de questões no array de questões
     */
    @Override
    public int numberQuestions() {
        return this.questions.length;
    }

    /**
     * Método que verifica se todas as questões foram respondidas
     *
     * @return : True - se foram todas respondidas | False - se não foram todas
     * respondidas
     */
    @Override
    public boolean isComplete() {
        for (IQuestion question : this.questions) {
            if (question != null && question.isDone() == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * Método que retorna TestStatistics
     *
     * @return - Esta instância de TestStatistics
     */
    @Override
    public ITestStatistics getTestStatistics() {
        this.testStat = new TestStatistics(this.questions);
        return this.testStat;
    }

    /**
     * Método que instância no array de questões, questões contidas num ficheiro
     * de formato JSon
     *
     * @param string - Indica a localização do ficheiro Json
     * @return : True - se o ficheiro foi carregado com sucesso | False - se o
     * ficheiro não foi carregado com sucesso
     * @throws TestException - se não foi encontrado nenhum ficheiro naquela
     * localização
     */
    @Override
    public boolean loadFromJSONFile(String string) throws TestException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(string));
            JsonStreamParser p = new JsonStreamParser(reader);
            JsonArray array = (JsonArray) p.next();
            int array_size = array.size();
            for (int i = 0; i < array_size; i++) {
                JsonElement arrayElement = array.get(i);
                JsonObject obj = arrayElement.getAsJsonObject();
                if (obj.has("type") && obj.has("question")) {
                    String type = obj.get("type").getAsString();
                    JsonObject question = obj.get("question").getAsJsonObject();
                    if ("MultipleChoice".equals(type)) {
                        JsonArray questionArray = question.get("options").getAsJsonArray();
                        String[] options = new String[questionArray.size()];
                        for (int j = 0; j < options.length; j++) {
                            if (options[j] == null && questionArray.get(j) != null) {
                                options[j] = questionArray.get(j).getAsString();
                            }
                        }
                        this.addQuestion(new QuestionMultipleChoice(
                                question.get("title").getAsString(),
                                question.get("question_description").getAsString(),
                                question.get("mark").getAsFloat(),
                                options,
                                question.get("correct_answer").getAsString()
                        ));
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            throw new TestException("O ficheiro não foi encontrado");
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                throw new TestException("Erro ao fechar o Reader");
            }
        }
        return true;
    }

    /**
     * Método que define ScoreStrategy
     *
     * @param iss - instância do ScoreStrategy
     */
    @Override
    public void setScoreStrategy(IScoreStrategy iss) {
        this.scoreStrat = (ScoreStrategy) iss;
    }

    /**
     * Método que retorna ScoreStrategy
     *
     * @return - Esta instância de ScoreStrategy
     */
    @Override
    public IScoreStrategy getScoreStrategy() {
        return this.scoreStrat;
    }

    /**
     * Método que retorna o resultado do teste
     *
     * @return - Resultado do teste
     */
    @Override
    public String calculateScore() {
        return scoreStrat.CalculateScore(this.questions);
    }

    /**
     * Método que guarda os resultados dos testes num ficheiro de texto
     *
     * @return False - se o ficheiro não foi guardado com sucesso
     */
    @Override
    public String toString() {
        String var = "---";

        for (IQuestion question : this.questions) {
            var = var
                    + " \nTitle: " + question.getTitle()
                    + " \nQuestion Description: " + question.getQuestion_description()
                    + " \nMark: " + question.getMark()
                    + " \nAcertou? " + question.evaluateAnswer()
                    + "\n---";
        }
        var = var
                + " \nClassificação: " + this.calculateScore()
                + " \nMédia de tempo por pergunta: " + this.getTestStatistics().meanTimePerAnswer()
                + " \nMédia de desvio por pergunta: " + this.getTestStatistics().standardDeviationTimePerAnsewer()
                + " \nNúmero de respostas corretas: " + this.getTestStatistics().correctAnswer()
                + " \nNúmero de respostas erradas: " + this.getTestStatistics().incorrectAnswer()
                + " \nPercentagem de respostas certas: " + this.getTestStatistics().correctAnswerPecentage()
                + " \nPercentagem de respostas erradas: " + this.getTestStatistics().incorrectAnswerPecentage()
                + "\n---";

        return var;
    }

    /**
     * Método que guarda os resultados dos testes num ficheiro de texto
     *
     * @return : True - se o ficheiro foi guardado com sucesso | False - se o
     * ficheiro não foi guardado com sucesso
     * @throws TestException - se não conseguir armazenar uma certa questão
     */
    @Override
    public boolean saveTestResults() throws TestException {
        File dir = new File("results/testMultipleChoiceOnly");
        int num;
        if (dir.exists() == true) {
            num = dir.listFiles().length + 1;
        } else {
            throw new TestException("A path inserida não existe!");
        }
        FileWriter arq = null;
        try {
            arq = new FileWriter("results/testMultipleChoiceOnly" + "/Test" + String.valueOf(num) + ".txt", true);
        } catch (IOException ex) {
            throw new TestException("Erro ao escrever no ficheiro!");
        }
        PrintWriter gravarArq = new PrintWriter(arq);
        for (IQuestion question : this.questions) {
            gravarArq.println("Title: " + question.getTitle());
            gravarArq.println("Question: " + question.getQuestion_description());
            gravarArq.println("Mark: " + question.getMark());
            gravarArq.println("Acertou: " + question.evaluateAnswer());
        }
        gravarArq.println("Classificação: " + this.calculateScore());
        gravarArq.println("MedTempPorPerg: " + this.getTestStatistics().meanTimePerAnswer());
        gravarArq.println("MedDesvPorPerg: " + this.getTestStatistics().standardDeviationTimePerAnsewer());
        gravarArq.println("NumRespCor: " + this.getTestStatistics().correctAnswer());
        gravarArq.println("NumRespErr: " + this.getTestStatistics().incorrectAnswer());
        gravarArq.println("PercRespCor: " + this.getTestStatistics().correctAnswerPecentage());
        gravarArq.println("PercRespErr: " + this.getTestStatistics().incorrectAnswerPecentage());
        try {
            arq.close();
        } catch (IOException ex) {
            throw new TestException("Erro ao fechar o ficheiro!");
        }
        return true;
    }

}
