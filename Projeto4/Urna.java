public class Urna {
	public static void votar(Eleitor eleitor, int voto) {
		if (!eleitor.eleitorJaVotou()) {
			eleitor.setJaVotou(true);
			Eleitor.adicionaNumDeVotantes();
			
			try {
				Candidato.getCadastroDeCandidatos().get(voto).adicionaNumDeVotos();
			} catch (NullPointerException e){
				Candidato.adicionaVotosNulosOuBrancos();
			}

            System.out.println("Sucesso!");
		} else {
			System.out.println("Eleitor já votou.");
		}
	}
}