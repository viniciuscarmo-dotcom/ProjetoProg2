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
        Map<String, Integer> registroDeVotos = new HashMap<>();
        String nomeDoArquivo;
        String empate = ".";

        // Adiciona o número de votos nulos e brancos ao número total de votantes
        registroDeVotos.put("Votos Nulos ou Brancos", Candidato.getVotosNulosOuBrancos());

        // Cria um mapa com o nome do candidato e número de votos (incluindo os votos brancos ou nulos)
        // e adiciona um por um nesse mapa, ao mesmo tempo que checa quem foi o(s) vencedor(es)
        for (Candidato candidato : Candidato.getListaDeCandidatos()) {
            registroDeVotos.put(candidato.getNome(), candidato.getNumDeVotos());
            numDeVotosValidos += candidato.getNumDeVotos();

            if (candidato.getNumDeVotos() == maiorVotacao) {
                vencedor.add(candidato);
            }
            else if (candidato.getNumDeVotos() > maiorVotacao) {
                maiorVotacao = candidato.getNumDeVotos();
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
            
            empate = ", pelo critério da idade.";

            vencedor.clear();
            vencedor.add(maisVelho);
        }
        
        porcentagemVencedor = (100 * (double) maiorVotacao / numDeVotosValidos);
        nomeDoArquivo = gerarArquivoDaEleicao(registroDeVotos);

        System.out.println("O vencedor da eleição foi: " + vencedor.get(0) + " com " + maiorVotacao + " votos, com " + String.format("%.2f", porcentagemVencedor) + "% dos votos válidos" + empate);
        System.out.println("Compareceram " + Eleitor.getNumDeVotantes() + " dos " + Eleitor.getNumDeEleitores() + " dos eleitores cadastrados.");
        System.out.println("Nome do relatório da eleição gerado: " + nomeDoArquivo);
    }

    // Criação do arquivo da eleição ordenada pela quantidade de votos do com os votos Brancos/Nulos no começo
    static private String gerarArquivoDaEleicao(Map<String, Integer> registroDeVotos) {
        try {
            ZonedDateTime dataAgora = ZonedDateTime.now();
            // Transforma dataAgora em string, pega só a parte da string até o horário (posição 19), tira os pontos e substitui todos os ":" da string pra "-"
            String data = dataAgora.toString().substring(0,19).replace(".", "").replace(":", "-").replace("T", " ");
            String nomeDoArquivo = "Relatório da Eleição " + data + ".txt";

            try (FileWriter arquivo = new FileWriter(nomeDoArquivo)) {
                PrintWriter gravarArquivo = new PrintWriter(arquivo);
                double porcentagem;
                
                //Ordenação da lista de candidatos pelo número de votos
                Collections.sort(Candidato.getListaDeCandidatos());
                
                porcentagem = (100 * (double) Candidato.getVotosNulosOuBrancos() / Eleitor.getNumDeVotantes());
                gravarArquivo.printf("Nome do Candidato - Número; Número de Votos; Porcentagem\n");
                gravarArquivo.printf("Votos Nulos ou Brancos; " + Candidato.getVotosNulosOuBrancos() + "; " + String.format("%.2f", porcentagem) + "\n");
                
                for (int i = 0; i < Candidato.getListaDeCandidatos().size(); i++) {
                    porcentagem = (100.0 * (double) Candidato.getListaDeCandidatos().get(i).getNumDeVotos() / Eleitor.getNumDeVotantes());
                    gravarArquivo.printf(Candidato.getListaDeCandidatos().get(i) + "; " + Candidato.getListaDeCandidatos().get(i).getNumDeVotos() + "; " + String.format("%.2f", porcentagem) + "\n");
                }
            }

            return nomeDoArquivo;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // Cadastro de um certo número de candidatos
    static public void cadastrarCandidatos(int num) {
        Scanner leitor = new Scanner(System.in);
        String a, b;
        int c;

        for (int i = 0; i < num; i++) {
            System.out.printf("Digite o nome do candidato: ");
            a = leitor.nextLine();
            System.out.printf("Digite a data de nascimento do candidato (ex.: 01/01/2000): ");
            b = leitor.nextLine();
            System.out.printf("Digite o número do candidato: ");
            c = leitor.nextInt();
            leitor.nextLine();

            // Só avança para criar o candidato quando verifica que não tem um candidato com o mesmo número
            while (Candidato.getCadastroDeCandidatos().containsKey(c)) { 
                System.out.printf("Digite um número de candidato que não esteja sendo usado: ");
                c = leitor.nextInt();
                leitor.nextLine();
            }

            new Candidato(a, b, c);
        }
    }
}
