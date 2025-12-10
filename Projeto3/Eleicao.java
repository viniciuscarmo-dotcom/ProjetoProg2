package Projeto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Eleicao {
    static double numDeVotantes;
    static double numDeVotosValidos;
    static double maiorVotacao;
    static List<Candidato> vencedor = new ArrayList<>();;
    static double a;

    static Candidato maisVelho;

    static public void contagemDeVotos() {
        Map<String, Integer> registroDeVotos = new HashMap<String, Integer>();

        registroDeVotos.put("Votos Nulos ou Brancos", Candidato.votosNulosOuBrancos);
        numDeVotantes = Candidato.votosNulosOuBrancos;

        // Cria um mapa com o nome do candidato e número de votos (incluindo os votos brancos ou nulos)
        // e adiciona um por um nesse mapa, ao mesmo tempo que checa quem foi o(s) vencedor(es)
        for (Candidato candidato : Candidato.listaDeCandidatos) {
            registroDeVotos.put(candidato.nome, candidato.numDeVotos);
            numDeVotantes += candidato.numDeVotos;
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
        if (vencedor.size() > 1) {
            maisVelho = vencedor.get(0);

            for (int i = 1; i < vencedor.size(); i++) {
                maisVelho = Candidato.quemEhMaisVelho(maisVelho, vencedor.get(i));
            }

            vencedor.clear();
            vencedor.add(maisVelho);
        }
        
        a = (100*maiorVotacao/numDeVotosValidos);

        System.out.println("O vencedor da eleição foi: " + vencedor.get(0) + " com " + maiorVotacao + " votos, com " + a + "% dos votos válidos.");
        System.out.println("Compareceram " + (int) numDeVotantes + " dos " + Eleitor.numDeEleitores + " dos eleitores cadastrados.");
    }
}
