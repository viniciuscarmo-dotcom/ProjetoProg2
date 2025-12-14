package prog2.projeto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Eleitor extends Pessoa {
	private static int numDeEleitores;
	private static int numDeVotantes;
	private static List<Eleitor> listaDeEleitores = new ArrayList<>();
	private String numeroDeInscricao;
    private Set<Cargo> votosComputados = new HashSet<>();
	
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

    public boolean jaVotouPara(Cargo cargo) {
        return votosComputados.contains(cargo);
    }

    public boolean jaVotouEmTudo() {
        return !votosComputados.isEmpty();
    }

    public void registrarVoto(Cargo cargo) {
        this.votosComputados.add(cargo);
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

    public static void zerar() {
        numDeEleitores = 0;
        numDeVotantes = 0;
        listaDeEleitores.clear();
    }
}