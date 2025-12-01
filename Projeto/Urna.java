package Projeto;

public class Urna {
	public void votar(Eleitor eleitor, String voto) {
		if (!eleitor.jaVotou) {
			eleitor.jaVotou = true;
            String nome = Candidato.cadastroDeCandidatos.get(voto).nome;
			Candidato.cadastroDeCandidatos.get(voto).numDeVotos++;
            System.out.println("Sucesso!");
		} else {
			System.out.println("Eleitor já votou.");
		}
	}
}