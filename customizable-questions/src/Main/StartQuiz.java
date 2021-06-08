package Main;

import interfaces.exceptions.TestException;
import java.io.IOException;
import java.util.Scanner;
import views.TestWindow;

/*
* Nome: José Miguel Ribeiro da Cunha
* Número: 8170479
* Turma: 2
 */
public class StartQuiz {

    public static void main(String[] args) throws TestException, IOException {

        TestWindow testWindow = new TestWindow();
        MainMethods methods = new MainMethods();
        Scanner scanResp = new Scanner(System.in);
        int resposta;
        int tipoTeste;
        boolean loop = false;

        /*
        * Este ciclo irá se repetir enquanto não for atribuido um valor aceitável à variàvel resposta.
        * As opções possíveis são a de realização de um teste ou o carregamento de um já realizado em formato de um ficheiro txt.
         */
        System.out.println("Deseja ler os resultados de um teste já realizado ou realizar um teste?\n(Introduza 1 - Carregar um teste)\n(Introduza 2 - Realizar um teste)");
        do {
            resposta = scanResp.nextInt();
            if (resposta == 1) {
                System.out.println("Escolheu a opção de ler os resultados de um teste.");
                loop = true;
            } else {
                if (resposta == 2) {
                    System.out.println("Escolheu a opção de realizar um teste.");
                    loop = true;
                } else {
                    System.out.println("Introduziu uma resposta inválida.");
                }
            }
        } while (loop = false);

        loop = false;

        /*
        * Dependendo da resposta, irá ser realizado um teste ou irá ser carregado um teste já realizado.
        * Caso tenha sido escolhido a opção de carregar um teste, a próxima questão será qual é o tipo
        * de teste que estará prestes a ser carregado.
        * Caso tenha sido escolhido a opção de realizar um teste, a próxima questão será se o utilizador
        * pretende realizar um teste normal ou um teste de apenas de escolha múltipla.
         */
        if (resposta == 1) {
            System.out.println("Insira o nome do teste: ");
            Scanner scanFileName = new Scanner(System.in);
            String fileName = scanFileName.nextLine();
            System.out.println("Pretende ler os resultados de um teste normal ou um teste de só escolhas múltiplas?\n(Introduza 1 - Um teste normal)\n(Introduza 2 - Um teste só de escolha múltipla)");
            do {
                tipoTeste = scanResp.nextInt();
                if (tipoTeste == 1) {
                    loop = true;
                    System.out.println("Escolheu carregar um teste normal");
                    methods.readTest("results/test", fileName);
                    System.exit(0);
                } else {
                    if (tipoTeste == 2) {
                        loop = true;
                        System.out.println("Escolheu carregar um teste do tipo MultipleChoiceOnly");
                        methods.readTest("results/testMultipleChoiceOnly", fileName);
                        System.exit(0);
                    } else {
                        System.out.println("Introduziu uma resposta inválida.\nIntroduze 1 ou 2, consoante o tipo do teste.");
                    }
                }
            } while (loop = false);
        } else {
            if (resposta == 2) {
                System.out.println("Pretende realizar um teste normal ou um teste de só escolhas múltiplas?\n(Introduza 1 - Um teste normal)\n(Introduza 2 - Um teste só de escolha múltipla)");
                do {
                    tipoTeste = scanResp.nextInt();
                    if (tipoTeste == 1) {
                        System.out.println("Escolheu realizar um teste normal");
                        loop = true;
                        methods.beginTest(testWindow, "data/teste_A.json", 0, 20);
                    } else {
                        if (tipoTeste == 2) {
                            System.out.println("Escolheu realizar um teste do tipo MultipleChoiceOnly");
                            loop = true;
                            methods.beginTestMultipleChoice(testWindow, "data/teste_A.json", 0, 10);
                        } else {
                            System.out.println("Introduziu uma resposta inválida.\nIntroduze 1 ou 2, consoante o tipo do teste.");
                        }
                    }
                } while (loop = false);
            }
        }
    }
}
