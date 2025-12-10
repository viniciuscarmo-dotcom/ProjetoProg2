package Projeto;

public class Urna {
	public static void votar(Eleitor eleitor, String voto) {
		if (!eleitor.jaVotou) {
			eleitor.jaVotou = true;
			try {
				Candidato.cadastroDeCandidatos.get(voto).numDeVotos++;
			} catch (NullPointerException e){
				Candidato.votosNulosOuBrancos++;
			}

            System.out.println("Sucesso!");
		} else {
			System.out.println("Eleitor já votou.");
		}
	}
}