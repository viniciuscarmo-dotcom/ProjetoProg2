package prog2.projeto;

import java.util.Scanner;

public class Votacao {
    private static Scanner sc = new Scanner(System.in);
    private static int cont = 0;
    private static int nulos = 0;

    public static void main(String[] args) {
        cadastroDeEleitores();

        cadastroDeCandidatos();
        
        menuDeVotacao();

        Eleicao.contagemDeVotos();
    }

    private static void cadastroDeEleitores() {
        int qtd;

        System.out.printf("Quantos eleitores: ");
        qtd = sc.nextInt();
        sc.nextLine();

        Eleicao.cadastrarEleitores(qtd);
    }

    private static void menuDeVotacao() {
        int variavel = 0;
        while (variavel != 2 && cont < Eleitor.getNumDeEleitores()){
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
        for (Candidato candidato : Candidato.getListaDeCandidatos()){
            System.out.println(candidato);
        }
    }

    private static void votacao() {
        String cpf;
        int voto;
        System.out.printf("Eleitor, insira seu CPF: ");
        cpf = sc.nextLine();
        for (Eleitor eleitor : Eleitor.getListaDeEleitores()){
            if (eleitor.getCPF().equals(cpf)){
                System.out.println("Bem-vindo, " + eleitor.getNome());
                imprimirCandidatos();
                System.out.printf("Escolha seu candidato: ");
                voto = sc.nextInt();

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

    private static void cadastroDeCandidatos() {
        int cadCandidatos;
        System.out.printf("O que deseja: 1 - Cadastrar candidatos | (outro número) - Eleição com candidato já cadastrados? ");
        cadCandidatos = sc.nextInt();
        sc.nextLine();
        
        if (cadCandidatos == 1) {
            System.out.printf("Quantos candidatos: ");
            cadCandidatos = sc.nextInt();
            sc.nextLine();
            Eleicao.cadastrarCandidatos(cadCandidatos);
        } else {
            Candidato candidato1 = new Candidato("Jose", "17/10/1997", "619898159", "ABC", 99);
            Candidato candidato2 = new Candidato("Ana", "10/10/1997", "619898159", "EFG", 98);
            Candidato candidato3 = new Candidato("Mateus", "01/10/1997", "619898159", "HOJ", 97);
        }
    }
}   
