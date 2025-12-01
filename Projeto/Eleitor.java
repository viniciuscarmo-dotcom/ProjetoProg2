package Projeto;

import java.util.ArrayList;
import java.util.List;

public class Eleitor extends Pessoa{
	static int numDeEleitores;
	static List<Eleitor> listaDeEleitores = new ArrayList<Eleitor>();	
	String numeroDeInscricao;
	
	boolean jaVotou;
	

	public Eleitor(String nome, String dataDeNascimento, int idade, String cpf, String numeroDeInscricao) {
		super(nome, dataDeNascimento, idade, cpf);
		this.numeroDeInscricao = numeroDeInscricao;
		Eleitor.numDeEleitores++;
		Eleitor.listaDeEleitores.add(this);
	}
}