package prog2.projeto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Votacao {
    static Scanner sc = new Scanner(System.in);
    static List<Cargo> eleicoesDeHoje = List.of(Cargo.PRESIDENTE, Cargo.PREFEITO, Cargo.VEREADOR);
    static String caminho;

    public static void main(String[] args) {

        int opcao = 0;

        do {
            System.out.println("\nCarregando...");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

            System.out.println("\n--- URNA JAVAI ---");
            System.out.println("1 - Upload de eleitores");
            System.out.println("2 - Upload de candidatos");
            System.out.println("3 - Upload padrão");
            System.out.println("4 - Exibir eleitores");
            System.out.println("5 - Exibir candidatos");
            System.out.println("6 - Iniciar votação");
            System.out.println("7 - Limpar dados");
            System.out.println("8 - Finalizar Eleição");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                String entrada = sc.nextLine();
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Digite o caminho do arquivo de eleitores: ");
                    caminho = sc.nextLine();
                    carregarEleitores(caminho);
                    break;
                case 2:
                    System.out.print("Digite o caminho do arquivo de candidatos: ");
                    caminho = sc.nextLine();
                    carregarCandidatos(caminho);
                    break;
                case 3:
                    carregarEleitores("eleitores.txt");
                    carregarCandidatos("candidatos.txt");
                    break;
                case 4:
                    exibirEleitores();
                    break;
                case 5:
                    exibirCandidatos();
                    break;
                case 6:
                    iniciarVotacao();
                    break;
                case 7:
                    limparDados();
                    break;
                case 8:
                    System.out.println();
                    if (Eleitor.getNumDeVotantes() > 0) {
                        Eleicao.contagemDeVotos();
                        return;
                    } else {
                        System.out.println("Ainda não houve eleição.");
                    }
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Não trabalhamos com essa opção");
            }

        } while (opcao != 0);

        sc.close();
    }

    private static void carregarEleitores(String caminho) {
        try (Scanner fileScanner = new Scanner(new File(caminho))) {
            int count = 0;
            while (fileScanner.hasNextLine()) {
                String linha = fileScanner.nextLine(); // Nome - CPF
                String[] dados = linha.trim().split(";");

                if (dados.length >= 2) {
                    String nome = dados[0].trim();
                    String cpf = dados[1].trim();

                    if (!Eleitor.verificarCPFValido(cpf)) {
                        new Eleitor(nome, cpf);
                        count++;
                    }
                }
            }
            System.out.println();
            System.out.println(count + " eleitores carregados com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println();
            System.out.println("Arquivo não encontrado.");
        }
    }

    private static void carregarCandidatos(String caminho) {
        try (Scanner fileScanner = new Scanner(new File(caminho))) {
            int count = 0;
            while (fileScanner.hasNextLine()) {
                String linha = fileScanner.nextLine(); // Nome; DataNascimento; Numero; Cargo

                String[] dados = linha.trim().split(";");

                if (dados.length >= 4) {
                    String nome = dados[0].trim();
                    String dataNasc = dados[1].trim();

                    try {
                        int numero = Integer.parseInt(dados[2].trim());
                        String cargoStr = dados[3].trim().toUpperCase();
                        Cargo cargo = Cargo.valueOf(cargoStr);

                        String chave = Candidato.gerarChave(cargo, numero);

                        if (!Candidato.getCadastroDeCandidatos().containsKey(chave)) {
                            new Candidato(nome, dataNasc, numero, cargo);
                            count++;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro na linha: " + linha + " -> Cargo inválido ou número incorreto.");
                    }
                }
            }
            System.out.println();
            System.out.println(count + " candidatos carregados com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("\nArquivo não encontrado.");
        }
    }

    private static void exibirEleitores() {
        System.out.println("\n--- Lista de Eleitores ---");
        if (Eleitor.getListaDeEleitores().isEmpty()) {
            System.out.println();
            System.out.println("Nenhum eleitor cadastrado.");
            return;
        }
        for (Eleitor e : Eleitor.getListaDeEleitores()) {
            System.out.println("Nome: " + e.getNome() + " | CPF: " + e.getCPF() + " | Já votou em tudo? " + (e.jaVotouEmTudo() ? "Sim" : "Não"));
        }
        System.out.println("Pressione ENTER para continuar...");
        sc.nextLine();
    }

    private static void exibirCandidatos() {
        System.out.println("\n--- Lista de Candidatos ---");
        if (Candidato.getListaDeCandidatos().isEmpty()) {
            System.out.println("\nNenhum candidato cadastrado.");
            return;
        }
        for (Cargo cargoAtual : Cargo.values()) {
            boolean cargoImpresso = false;

            for (Candidato c : Candidato.getListaDeCandidatos()) {

                if (c.getCargo() == cargoAtual) {
                    if (!cargoImpresso) {
                        System.out.println("\n---------------- " + cargoAtual + " ----------------");
                        cargoImpresso = true;
                    }

                    System.out.println(c);
                }
            }
        }

        System.out.println("\nPressione ENTER para continuar...");
        sc.nextLine();
    }

    private static void iniciarVotacao() {
        if (Eleitor.getListaDeEleitores().isEmpty() || Candidato.getListaDeCandidatos().isEmpty()) {
            System.out.println("\nErro: Carregue eleitores e candidatos antes.");
            return;
        }

        System.out.println("\n--- URNA ABERTA ---");
        System.out.println("Digite 'SAIR' no CPF para encerrar.");
        boolean primeiraRodada = false;

        while (true) {
            if (primeiraRodada) {
                System.out.println("Digite 'SAIR' no CPF para encerrar.\n");
            } else {
                primeiraRodada = true;
                System.out.println();
            }

            System.out.print("Digite o CPF do eleitor: ");
            String cpf = sc.nextLine();

            if (cpf.equalsIgnoreCase("SAIR")) break;
            
            Eleitor eleitorAtual = null;
            for (Eleitor e : Eleitor.getListaDeEleitores()) {
                if (e.getCPF().equals(cpf)) {
                    eleitorAtual = e;
                    break;
                }
            }

            if (eleitorAtual == null) {
                System.out.println("Eleitor não encontrado.");
                continue;
            }
            
            boolean votouNessaSessao = false;

            for (Cargo cargoAtual : eleicoesDeHoje) {
                if (eleitorAtual.jaVotouPara(cargoAtual)) {
                    continue;
                }

                System.out.println("\n--- Voto para " + cargoAtual + " ---");
                System.out.print("Digite o número do candidato: ");

                try {
                    String entrada = sc.nextLine();
                    int numero = Integer.parseInt(entrada);
                    
                    Urna.votar(eleitorAtual, numero, cargoAtual);
                    votouNessaSessao = true;

                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida! Voto anulado para este cargo.");
                    Urna.votar(eleitorAtual, 999999, cargoAtual);
                }
            }

            if (votouNessaSessao) {
                System.out.println("\nFIM! Obrigado pelo voto, " + eleitorAtual.getNome());
                Eleitor.adicionaNumDeVotantes("123456789");
                System.out.println("Pressione ENTER para o próximo eleitor...");
                sc.nextLine();
            } else {
                System.out.println("Já votou em todos os cargos disponíveis.");
            }
        }
    }

    private static void limparDados() {
        Eleitor.getListaDeEleitores().clear();
        Candidato.getListaDeCandidatos().clear();
        Candidato.getCadastroDeCandidatos().clear();
        Eleitor.zerar();
        Candidato.zerar();

        System.out.println("Tudo limpo!");
    }
}