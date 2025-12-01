package Projeto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Candidato extends Pessoa{
    static int votosNulosOuBrancos;
	static int quantidadeDeCandidatos;
	static Map<String, Candidato> cadastroDeCandidatos = new HashMap<String, Candidato>();
    static List<Candidato> listaDeCandidatos = new ArrayList<>();
    String partido;
    String numeroDoCandidato;
    int numDeVotos;

    public Candidato (String nome, String dataDeNascimento, int idade, String cpf, String partido, String numeroDoCandidato) {
        super(nome, dataDeNascimento, idade, cpf);
        this.partido = partido;
        this.numeroDoCandidato = numeroDoCandidato;
        Candidato.quantidadeDeCandidatos++;
        Candidato.cadastroDeCandidatos.put(numeroDoCandidato, this);
        Candidato.listaDeCandidatos.add(this);
    }
}