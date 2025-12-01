package Projeto;

import java.util.*;

public class Votacao {
    static Scanner sc = new Scanner(System.in);
    static Set<Eleitor> eleitores = new HashSet<>();
    static int cont = 0;
    static int nulos = 0;

    public static void main(String[] args) {

        cadastroEleitores();

        /*
        Eleitor eleitor = new Eleitor("Joao", "10102000", 20, "619898159", "646516584");
        Eleitor eleitor2 = new Eleitor("Jose", "01012001", 18, "9819519851", "645198151");
        Eleitor eleitor3 = new Eleitor("Maria", "20121998", 64, "619898159", "646516584");
        Eleitor eleitor4 = new Eleitor("Pedro", "13091999", 49, "619898159", "646516584");
        Eleitor eleitor5 = new Eleitor("Jose", "13091999", 49, "619898159", "646516584");
        Eleitor eleitor6 = new Eleitor("Ana", "13091999", 49, "619898159", "646516584");
        Eleitor eleitor7 = new Eleitor("Mateus", "13091999", 49, "619898159", "646516584");

         */

        Candidato candidato1 = new Candidato("Jose", "17101997", 28, "619898159", "ABC", "99");
        Candidato candidato2 = new Candidato("Ana", "10101997", 28, "619898159", "EFG", "98");
        Candidato candidato3 = new Candidato("Mateus", "01101997", 28, "619898159", "HOJ", "97");

        menuDeVotacao();

        /*
        urna.votar(eleitor, "99");
        urna.votar(eleitor2, "98");
        urna.votar(eleitor2, "98");
        urna.votar(eleitor4, "98");
        urna.votar(eleitor5, "97");
        urna.votar(eleitor6, "98");
        urna.votar(eleitor7, "98");
        */

        Eleicao.contagemDeVotos();
    }

    private static void cadastroEleitores() {
        int qtd;

        System.out.print("Quantos eleitores: ");
        qtd = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < qtd; i++){
            System.out.println("Nome: ");
            String nome = sc.nextLine();
            System.out.println("CPF: ");
            String cpf = sc.nextLine();

            Eleitor obj = new Eleitor(nome, cpf);
            eleitores.add(obj);
        }
    }

    private static void menuDeVotacao() {
        int variavel = 0;
        while (variavel != 2 && cont < Eleitor.numDeEleitores){
            System.out.println("O que deseja: ");
            System.out.println("1 - Votar");
            System.out.println("2 - Encerrar votação");
            variavel = sc.nextInt();
            sc.nextLine();
            switch (variavel) {
                case 1:
                    votacao();
                    break;
                default:
                    break;
            }
        }
    }

    private static void imprimirCandidatos() {
        for (Candidato candidato : Candidato.listaDeCandidatos){
            System.out.println(candidato);
        }
    }

    private static void votacao() {
        String cpf;
        String voto;
        System.out.println("Eleitor, insira seu CPF: ");
        cpf = sc.nextLine();
        for (Eleitor eleitor : eleitores){
            if (eleitor.cpf.equals(cpf)){
                System.out.println("Bem-vindo, " + eleitor.nome);
                imprimirCandidatos();
                System.out.println("Escolha seu candidato: ");
                voto = sc.nextLine();

                try {
                    Urna.votar(eleitor, voto);
                    cont++;
                } catch (NullPointerException e) {
                    nulos++;
                    cont++;
                }
            }
        }
    }
}   
