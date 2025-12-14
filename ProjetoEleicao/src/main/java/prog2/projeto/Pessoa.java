package prog2.projeto;

public abstract class Pessoa {
    private int diaDeNascimento;
    private int mesDeNascimento;
    private int anoDeNascimento;
    private String dataDeNascimento;
    private String nome;
    private String cpf;

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

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public int getDiaDeNascimento() {
        return diaDeNascimento;
    }

    public void setDiaDeNascimento(int diaDeNascimento) {
        this.diaDeNascimento = diaDeNascimento;
    }

    public int getMesDeNascimento() {
        return mesDeNascimento;
    }

    public void setMesDeNascimento(int mesDeNascimento) {
        this.mesDeNascimento = mesDeNascimento;
    }

    public int getAnoDeNascimento() {
        return anoDeNascimento;
    }

    public void setAnoDeNascimento(int anoDeNascimento) {
        this.anoDeNascimento = anoDeNascimento;
    }
}