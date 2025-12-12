package Projeto;

import java.util.ArrayList;
import java.util.List;

public class Eleitor extends Pessoa {
	static int numDeEleitores;
	static int numDeVotantes;
	static List<Eleitor> listaDeEleitores = new ArrayList<>();	
	String numeroDeInscricao;
	boolean jaVotou;
	
	/*
	public Eleitor(String nome, String dataDeNascimento, int idade, String cpf, String numeroDeInscricao) {
		super(nome, dataDeNascimento, idade, cpf);
		this.numeroDeInscricao = numeroDeInscricao;
		Eleitor.numDeEleitores++;
		Eleitor.listaDeEleitores.add(this);
	}
	*/

	public Eleitor(String nome, String cpf){
		super(nome, cpf);
		Eleitor.numDeEleitores++;
		Eleitor.listaDeEleitores.add(this);
	}
}