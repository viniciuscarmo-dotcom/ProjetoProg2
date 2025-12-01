package Projeto;

import java.util.HashMap;
import java.util.Map;

public class Eleicao {
    double numDeVotantes;
    double numDeVotosValidos;
    double maiorVotacao;
    String vencedor;
    double a;

    public void contagemDeVotos() {
        Map<String, Integer> registroDeVotos = new HashMap<String, Integer>();

        registroDeVotos.put("Votos Nulos ou Brancos", Candidato.votosNulosOuBrancos);
        this.numDeVotantes += Candidato.votosNulosOuBrancos;

        for (Candidato candidato : Candidato.listaDeCandidatos) {
            registroDeVotos.put(candidato.nome, candidato.numDeVotos);
            this.numDeVotantes += candidato.numDeVotos;
            this.numDeVotosValidos += candidato.numDeVotos;
            
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
