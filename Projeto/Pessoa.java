package Projeto;

public abstract class Pessoa {
    int anoDeNascimento;
    String dataDeNascimento;
    String nome;
    String cpf;

    public Pessoa (String nome, String dataDeNascimento, int idade, String cpf){
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.anoDeNascimento = idade;
    }
}