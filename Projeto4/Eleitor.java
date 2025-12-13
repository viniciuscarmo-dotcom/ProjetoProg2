import java.util.*;

public class Eleitor extends Pessoa {
	private static int numDeEleitores;
	private static int numDeVotantes;
	private static List<Eleitor> listaDeEleitores = new ArrayList<>();
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

    public boolean eleitorJaVotou() {
        return jaVotou;
    }

    public void setJaVotou(boolean jaVotou) {
        this.jaVotou = jaVotou;
    }
	
	public static int getNumDeVotantes() {
        return numDeVotantes;
    }
	
	public static void adicionaNumDeVotantes() {
        Eleitor.numDeVotantes++;
    }

    public static int getNumDeEleitores() {
        return numDeEleitores;
    }
	
	public static List<Eleitor> getListaDeEleitores() {
		return listaDeEleitores;
	}

	// Verificar se o cpf do eleitor já está sendo utilizado
	static public boolean verificarCPFValido(String cpf) {
		for (int i = 0; i < listaDeEleitores.size(); i++) {
			if (listaDeEleitores.get(i).getCPF().equals(cpf)) {
				return true;
			}
		}

		return false;
	}
}