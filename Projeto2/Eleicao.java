package Projeto;

import java.util.HashMap;
import java.util.Map;

public class Eleicao {
    static double numDeVotantes;
    static double numDeVotosValidos;
    static double maiorVotacao;
    static String vencedor;
    static double a;

    static public void contagemDeVotos() {
        Map<String, Integer> registroDeVotos = new HashMap<String, Integer>();

        registroDeVotos.put("Votos Nulos ou Brancos", Candidato.votosNulosOuBrancos);
        numDeVotantes = Candidato.votosNulosOuBrancos;

        for (Candidato candidato : Candidato.listaDeCandidatos) {
            registroDeVotos.put(candidato.nome, candidato.numDeVotos);
            numDeVotantes += candidato.numDeVotos;
            numDeVotosValidos += candidato.numDeVotos;
            
            if (candidato.numDeVotos > maiorVotacao) {
                maiorVotacao = candidato.numDeVotos;
                vencedor = candidato.nome;
            }
        }
        
        a = (100*maiorVotacao/numDeVotosValidos);

        System.out.println("O vencedor da eleição foi: " + vencedor + " com " + maiorVotacao + " votos, com " + a + "% dos votos válidos.");
        System.out.println("Compareceram " + numDeVotantes + " dos " + Eleitor.numDeEleitores + " dos eleitores cadastrados.");
    }
}
