package prog2.projeto;

public class Urna {
    public static void votar(Eleitor eleitor, int numeroVoto, Cargo cargoAtual) {
        if (!eleitor.jaVotouPara(cargoAtual)) {

            String chave = Candidato.gerarChave(cargoAtual, numeroVoto);
            Candidato candidato = Candidato.getCadastroDeCandidatos().get(chave);

            if (candidato != null) {
                candidato.adicionaNumDeVotos("123456789");
                System.out.println("Voto confirmado para " + cargoAtual + ": " + candidato.getNome());
            } else {
                Candidato.adicionaVotoNulo(cargoAtual, "123456789");

                System.out.println("Voto Nulo/Branco computado para " + cargoAtual + ".");
            }

            eleitor.registrarVoto(cargoAtual, "123456789");
            Eleitor.adicionaNumDeVotantes("123456789");
        } else {
            System.out.println("Eleitor já votou para " + cargoAtual);
        }
    }
}