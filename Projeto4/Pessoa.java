package Projeto;

public abstract class Pessoa{
    int diaDeNascimento;
    int mesDeNascimento;
    int anoDeNascimento;
    String dataDeNascimento;
    String nome;
    String cpf;


    public Pessoa (String nome, String dataDeNascimento, String cpf){
        this.nome = nome;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        separarDataDeNascimento();
    }

    public Pessoa (String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }

    public void separarDataDeNascimento() {
        // Divide a string pelo caractere '/'
        String[] partes = this.dataDeNascimento.split("/");

        // Converte cada parte para inteiro
        this.diaDeNascimento = Integer.parseInt(partes[0]);
        this.mesDeNascimento = Integer.parseInt(partes[1]);
        this.anoDeNascimento = Integer.parseInt(partes[2]);
    }
}