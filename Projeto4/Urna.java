public class Urna {
	public static void votar(Eleitor eleitor, int voto) {
		if (!eleitor.isJaVotou()) {
			eleitor.setJaVotou(true);
			Eleitor.numDeVotantes++;
			try {
				Candidato.cadastroDeCandidatos.get(voto).adicionaNumDeVotos();
			} catch (NullPointerException e){
				Candidato.votosNulosOuBrancos++;
			}

            System.out.println("Sucesso!");
		} else {
			System.out.println("Eleitor já votou.");
		}
	}
}