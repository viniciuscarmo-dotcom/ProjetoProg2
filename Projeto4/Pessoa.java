public abstract class Pessoa{
    protected int diaDeNascimento;
    protected int mesDeNascimento;
    protected int anoDeNascimento;
    protected String dataDeNascimento;
    protected String nome;
    protected String cpf;

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

    // Separa o dia, mês e ano da data de nascimento
    public void separarDataDeNascimento() {
        // Divide a string pelo caractere '/'
        String[] partes = this.dataDeNascimento.split("/");

        // Converte cada uma das partes para números inteiros
        this.diaDeNascimento = Integer.parseInt(partes[0]);
        this.mesDeNascimento = Integer.parseInt(partes[1]);
        this.anoDeNascimento = Integer.parseInt(partes[2]);
    }
}