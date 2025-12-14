package prog2.projeto;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Eleicao {

    static public void contagemDeVotos() {

        System.out.println("\nRESULTADO DA ELEIÇÃO");
        System.out.println("Compareceram " + Eleitor.getNumDeVotantes() + " eleitores.");

        // Calcula o resultado cargo por cargo
        for (Cargo cargoAtual : Cargo.values()) {

            // Filtrar candidatos por cargo
            List<Candidato> candidatosDoCargo = new ArrayList<>();
            for (Candidato c : Candidato.getListaDeCandidatos()) {
                if (c.getCargo() == cargoAtual) {
                    candidatosDoCargo.add(c);
                }
            }

            // Se não houver candidatos para esse cargo, pula para o próximo
            if (candidatosDoCargo.isEmpty()) {
                continue;
            }

            // Apuração de cada cargo
            int votosValidosCargo = 0;
            int maiorVotacaoCargo = 0;
            List<Candidato> vencedoresCargo = new ArrayList<>();
            String criterioDesempate = null;

            // Contagem de votos
            for (Candidato candidato : candidatosDoCargo) {
                votosValidosCargo += candidato.getNumDeVotos();

                if (candidato.getNumDeVotos() > maiorVotacaoCargo) {
                    maiorVotacaoCargo = candidato.getNumDeVotos();
                    vencedoresCargo.clear();
                    vencedoresCargo.add(candidato);
                } else if (candidato.getNumDeVotos() == maiorVotacaoCargo) {
                    vencedoresCargo.add(candidato);
                }
            }

            // Caso não haja nenhum voto válido
            if (vencedoresCargo.isEmpty() || maiorVotacaoCargo == 0) {
                System.out.println("\n-------------------------------------------");
                System.out.println("CARGO: " + cargoAtual);
                System.out.println("-------------------------------------------");
                System.out.println("Sem votos válidos computados.");
                continue; // Pula para o próximo cargo
            }

            Candidato vencedorFinal;

            // Desempate por idade
            if (vencedoresCargo.size() == 1) {
                vencedorFinal = vencedoresCargo.get(0);
            } else {
                // Empate
                vencedorFinal = vencedoresCargo.get(0);
                for (int i = 1; i < vencedoresCargo.size(); i++) {
                    vencedorFinal = Candidato.quemEhMaisVelho(vencedorFinal, vencedoresCargo.get(i));
                }
                criterioDesempate = "pelo critério de idade.";
            }

            // Resultado
            double porcentagem = 0.0;
            if (votosValidosCargo > 0) {
                porcentagem = (100.0 * maiorVotacaoCargo / votosValidosCargo);
            }

            System.out.println("\n-------------------------------------------");
            System.out.println("CARGO: " + cargoAtual);
            System.out.println("-------------------------------------------");
            System.out.println("Votos Nulos/Brancos: " + Candidato.getVotosNulos(cargoAtual));
            System.out.println("Vencedor: " + vencedorFinal.getNome());
            System.out.println("Partido/Número: " + vencedorFinal.getNumeroDoCandidato());
            System.out.println("Votos: " + maiorVotacaoCargo + " (" + String.format("%.2f", porcentagem) + "% dos válidos)");
            if (criterioDesempate != null) {
                System.out.println(criterioDesempate);
            }
        }

        // Arquivo
        List<String> relatorios = gerarArquivoDaEleicao();
        System.out.println("\n-------------------------------------------");
        if (!relatorios.isEmpty()) {
            System.out.println("Relatórios gerados com sucesso:");
            for (String nome : relatorios) {
                System.out.println(" -> " + nome);
            }
        } else {
            System.out.println("Nenhum relatório foi gerado.");
        }
    }

    static private List<String> gerarArquivoDaEleicao() {
        List<String> arquivosGerados = new ArrayList<>();

        try {
            // Ajeita data
            ZonedDateTime dataAgora = ZonedDateTime.now();
            String dataStr = dataAgora.toString().substring(0,19).replace(".", "").replace(":", "-").replace("T", " ");

            // Ordena a lista
            Collections.sort(Candidato.getListaDeCandidatos());

            // Para cada cargo, cria um arquivo diferente
            for (Cargo cargo : Cargo.values()) {

                List<Candidato> candidatosDoCargo = new ArrayList<>();
                for (Candidato c : Candidato.getListaDeCandidatos()) {
                    if (c.getCargo() == cargo) {
                        candidatosDoCargo.add(c);
                    }
                }

                if (candidatosDoCargo.isEmpty()) {
                    continue; // Pula para o próximo cargo se não houver candidatos
                }

                String nomeDoArquivo = "Relatório " + cargo + " " + dataStr + ".txt";

                try (FileWriter arquivo = new FileWriter(nomeDoArquivo)) {
                    PrintWriter gravarArquivo = new PrintWriter(arquivo);

                    gravarArquivo.printf("RELATÓRIO DE ELEIÇÃO - %s\n", cargo);
                    gravarArquivo.printf("--------------------------------------------------\n");

                    int nulosDoCargo = Candidato.getVotosNulos(cargo);
                    int totalVotantes = Eleitor.getNumDeVotantes();
                    double porcNulos = (totalVotantes > 0) ? (100.0 * nulosDoCargo / totalVotantes) : 0;

                    gravarArquivo.printf("Votos Nulos/Brancos (%s): %d (%.2f%% do total de votantes)\n", cargo, nulosDoCargo, porcNulos);
                    gravarArquivo.printf("--------------------------------------------------\n");
                    gravarArquivo.printf("Nome - Número; Votos\n");

                    for (Candidato c : candidatosDoCargo) {
                        gravarArquivo.printf("%s; %d votos\n", c, c.getNumDeVotos());
                    }

                    arquivosGerados.add(nomeDoArquivo);
                }
            }

            return arquivosGerados;

        } catch (Exception e) {
            System.out.println("Erro ao gerar arquivos: " + e);
            return new ArrayList<>();
        }
    }

    static public void cadastrarEleitores(int num) {
        Scanner leitor = new Scanner(System.in);
        String nome, cpf;

        for (int i = 0; i < num; i++){
            System.out.printf("\nNome: ");
            nome = leitor.nextLine();
            System.out.printf("CPF: ");
            cpf = leitor.nextLine();

            // Só avança para criar o eleitor quando verifica que não tem um eleitor com o mesmo cpf
            while (Eleitor.verificarCPFValido(cpf)) {
                System.out.printf("CPF inválido. Digite novamente: ");
                cpf = leitor.nextLine();
            }
            
            new Eleitor(nome, cpf);
        }
    }

    static public void cadastrarCandidatos() {
        Scanner leitor = new Scanner(System.in);
        String nome, data;
        int numero;
        int cont;
        String[] cargos = {"PRESIDENTE", "PREFEITO", "VEREADOR"};

        for (int j = 0; j < 3; j++) {
            System.err.println("\nCadastro de " + cargos[j]);
            System.out.printf("Digite quantos candidatos quer cadastrar: ");
            cont = leitor.nextInt();
            leitor.nextLine();

            for (int i = 0; i < cont; i++) {
                System.out.printf("\nDigite o nome do candidato: ");
                nome = leitor.nextLine();
                System.out.printf("Digite a data de nascimento do candidato (ex.: 01/01/2000): ");
                data = leitor.nextLine();
                System.out.printf("Digite o número do candidato (2 p/ presidente e prefeito, 5 p/ vereador): ");
                numero = leitor.nextInt();
                leitor.nextLine();

                // Só avança para criar o candidato quando verifica que não tem um candidato com o mesmo número
                while (Candidato.getCadastroDeCandidatos().containsKey(Cargo.valueOf(cargos[j])+"-"+numero)) { 
                    System.out.printf("Digite um número de candidato que não esteja sendo usado: ");
                    numero = leitor.nextInt();
                    leitor.nextLine();
                }

                new Candidato(nome, data, numero, Cargo.valueOf(cargos[j]));
            }
        }

        
    }
}