import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.ZonedDateTime;
import java.util.*;

public class Eleicao {
    static int numDeVotosValidos;
    static int maiorVotacao;
    static List<Candidato> vencedor = new ArrayList<>();;
    static double porcentagemVencedor;
    static Candidato maisVelho;

    static public void contagemDeVotos() {
        Map<String, Integer> registroDeVotos = new HashMap<String, Integer>();
        String nomeDoArquivo;

        // Adiciona o número de votos nulos e brancos ao número total de votantes
        registroDeVotos.put("Votos Nulos ou Brancos", Candidato.votosNulosOuBrancos);

        // Cria um mapa com o nome do candidato e número de votos (incluindo os votos brancos ou nulos)
        // e adiciona um por um nesse mapa, ao mesmo tempo que checa quem foi o(s) vencedor(es)
        for (Candidato candidato : Candidato.listaDeCandidatos) {
            registroDeVotos.put(candidato.nome, candidato.numDeVotos);
            numDeVotosValidos += candidato.numDeVotos;

            if (candidato.numDeVotos == maiorVotacao) {
                vencedor.add(candidato);
            }
            else if (candidato.numDeVotos > maiorVotacao) {
                maiorVotacao = candidato.numDeVotos;
                vencedor.clear();
                vencedor.add(candidato);
            }
        }

        // Caso ocorra de duas ou mais pessoas receber a mesma quantidade de votos, ganha o mais velho 
        // (ou o sorteado, caso os mais velhos tenham exatamente a mesma idade)
        if (vencedor.size() > 1) {
            maisVelho = vencedor.get(0);

            for (int i = 1; i < vencedor.size(); i++) {
                maisVelho = Candidato.quemEhMaisVelho(maisVelho, vencedor.get(i));
            }

            vencedor.clear();
            vencedor.add(maisVelho);
        }
        
        porcentagemVencedor = (100 * (double) maiorVotacao / numDeVotosValidos);
        nomeDoArquivo = gerarArquivoDaEleicao(registroDeVotos);

        System.out.println("O vencedor da eleição foi: " + vencedor.get(0) + " com " + maiorVotacao + " votos, com " + String.format("%.2f", porcentagemVencedor) + "% dos votos válidos.");
        System.out.println("Compareceram " + (int) Eleitor.numDeVotantes + " dos " + Eleitor.numDeEleitores + " dos eleitores cadastrados.");
        System.out.println("Nome do relatório da eleição gerado: " + nomeDoArquivo);
    }

    // Criação do arquivo da eleição ordenada pela quantidade de votos do com os votos Brancos/Nulos no começo
    static private String gerarArquivoDaEleicao(Map<String, Integer> registroDeVotos) {
        try {
            ZonedDateTime dataAgora = ZonedDateTime.now();
            // Transforma dataAgora em string, pega só a parte da string antes do ponto (posição 18) e retira todos os ":" da string
            String data = dataAgora.toString().substring(0,18).replace(":", "");
            System.out.println(data);
            String nomeDoArquivo = "Relatório da Eleição " + data + ".txt";
            
            try (FileWriter arquivo = new FileWriter(nomeDoArquivo)) {
                PrintWriter gravarArquivo = new PrintWriter(arquivo);
                double porcentagem;
                
                //Ordenação da lista de candidatos pelo número de votos
                Collections.sort(Candidato.listaDeCandidatos);
                
                porcentagem = (100 * (double) Candidato.votosNulosOuBrancos / Eleitor.numDeVotantes);
                gravarArquivo.printf("Nome do Candidato - Número; Número de Votos; Porcentagem\n");
                gravarArquivo.printf("Votos Nulos ou Brancos; " + Candidato.votosNulosOuBrancos + "; " + String.format("%.2f", porcentagem) + "\n");
                
                for (int i = 0; i < Candidato.listaDeCandidatos.size(); i++) {
                    porcentagem = (100.0 * (double) Candidato.listaDeCandidatos.get(i).numDeVotos / Eleitor.numDeVotantes);
                    gravarArquivo.printf(Candidato.listaDeCandidatos.get(i) + "; " + Candidato.listaDeCandidatos.get(i).numDeVotos + "; " + String.format("%.2f", porcentagem) + "\n");
                }
            }

            return nomeDoArquivo;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
