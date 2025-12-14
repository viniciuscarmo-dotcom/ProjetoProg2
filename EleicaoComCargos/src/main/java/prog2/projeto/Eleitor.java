package prog2.projeto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Eleitor extends Pessoa {
	private static int numDeEleitores;
	private static int numDeVotantes;
	private static List<Eleitor> listaDeEleitores = new ArrayList<>();
	private static String autenticacao = "123456789";
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

	// Retorna número de inscrição do eleitor
    public String getNumeroDeInscricao() {
        return numeroDeInscricao;
    }

	// Retorna se o eleitor já votou em determinado cargo
    public boolean jaVotouPara(Cargo cargo) {
        return votosComputados.contains(cargo);
    }

	// Retorna se o eleitor já votou em todos os cargos
    public boolean jaVotouEmTudo() {
        return !votosComputados.isEmpty();
    }

	// Registra que o eleitor votou em determinado cargo, precisa de autenticacao
    public void registrarVoto(Cargo cargo, String autenticacao) {
		if (Eleitor.autenticacao.equals(autenticacao)) {
			this.votosComputados.add(cargo);
		}
    }
	
	// Retorna o número de eleitores que compareceram a eleição
	public static int getNumDeVotantes() {
        return numDeVotantes;
    }
	
	// Adiciona mais um ao número de eleitores que compareceram, precisa de autenticação
	public static void adicionaNumDeVotantes(String autenticacao) {
		if (Eleitor.autenticacao.equals(autenticacao)) {
			Eleitor.numDeVotantes++;
		}
    }

	// Retorna número de eleitores
    public static int getNumDeEleitores() {
        return numDeEleitores;
    }
	
	// Retorna a lista com todos os eleitores
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

	// Reinicia os eleitores
    public static void zerar() {
        numDeEleitores = 0;
        numDeVotantes = 0;
        listaDeEleitores.clear();
    }
}