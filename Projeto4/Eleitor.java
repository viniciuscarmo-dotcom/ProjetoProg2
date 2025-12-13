import java.util.*;

public class Eleitor extends Pessoa {
	static int numDeEleitores;
	static int numDeVotantes;
	static List<Eleitor> listaDeEleitores = new ArrayList<>();	
	private String numeroDeInscricao;
	private boolean jaVotou;
	
	public Eleitor(String nome, String dataDeNascimento, String cpf, String numeroDeInscricao) {
		super(nome, dataDeNascimento, cpf);
		this.numeroDeInscricao = numeroDeInscricao;
		Eleitor.numDeEleitores++;
		Eleitor.listaDeEleitores.add(this);
	}

	public Eleitor(String nome, String cpf){
		super(nome, cpf);
		Eleitor.numDeEleitores++;
		Eleitor.listaDeEleitores.add(this);
	}

    public String getNumeroDeInscricao() {
        return numeroDeInscricao;
    }

    public void setNumeroDeInscricao(String numeroDeInscricao) {
        this.numeroDeInscricao = numeroDeInscricao;
    }

    public boolean isJaVotou() {
        return jaVotou;
    }

    public void setJaVotou(boolean jaVotou) {
        this.jaVotou = jaVotou;
    }
}