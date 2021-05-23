package Main;

import Interfaces.ISaveTestToJson;
import Questions.QuestionMultipleChoice;
import Questions.QuestionNumeric;
import Questions.QuestionYesNo;
import Test.ScoreStrategy;
import Test.Test;
import interfaces.controller.ITest;
import interfaces.exceptions.TestException;
import interfaces.models.IQuestionMultipleChoice;
import interfaces.models.IQuestionNumeric;
import interfaces.models.IQuestionYesNo;
import java.io.IOException;
import views.TestWindow;

/*
* Nome: José Miguel Ribeiro da Cunha
* Número: 8170479
* Turma: 2
 */
public class Testing {

    public static void main(String[] args) throws TestException, IOException {
        ITest demoTest = new Test();
        TestWindow testWindow = new TestWindow();
        ISaveTestToJson save = new SaveTestToJson();
        MainMethods methods = new MainMethods();
        String fileName = "CulturaGeral";
        demoTest.setScoreStrategy(new ScoreStrategy(0, 15));

        String[] options1 = {"Monte Everest", "Montanha do Pico", "Serra da Estrela", "Alpes"};

        IQuestionMultipleChoice question1 = new QuestionMultipleChoice("Questão 1", "Qual é a montanha mais alta do Mundo?", 5, options1, "Monte Everest");
        IQuestionYesNo question2 = new QuestionYesNo("Questão 2", "Foi no ano 1143 que Portugal foi oficialmente reconhecido?", 5, "YES");
        IQuestionNumeric question3 = new QuestionNumeric("Questão 3", "Qual é a raiz quadrada de 36?", 5, 6);

        /*demoTest.addQuestion(question1);
        demoTest.addQuestion(question2);
        demoTest.addQuestion(question3);
        demoTest.removeQuestion(question1);
        System.out.println(demoTest.getQuestion(0).getQuestion_description());*/
        
        /*save.saveQuestionMultipleChoice(question1, fileName);
        save.saveQuestionNumeric(question3, fileName);
        save.saveQuestionYesNo(question2, fileName);
        save.writeToJson(fileName);*/
       
        methods.beginTest(testWindow, "TestesRealizados/" + fileName + ".json", 0, 15);
   
        methods.sortByNumberCorrectAnswers("test");
    }

}
