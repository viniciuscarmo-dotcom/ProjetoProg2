import java.util.*;

public class Candidato extends Pessoa implements Comparable<Candidato>{
    static int votosNulosOuBrancos;
	static int quantidadeDeCandidatos;
	static Map<Integer, Candidato> cadastroDeCandidatos = new HashMap<>();
    static List<Candidato> listaDeCandidatos = new ArrayList<>();
    String partido;
    int numeroDoCandidato;
    private int numDeVotos;

    public Candidato (String nome, String dataDeNascimento, String cpf, String partido, int numeroDoCandidato) {
        super(nome, dataDeNascimento, cpf);
        this.partido = partido;
        this.numeroDoCandidato = numeroDoCandidato;
        Candidato.quantidadeDeCandidatos++;
        Candidato.cadastroDeCandidatos.put(numeroDoCandidato, this);
        Candidato.listaDeCandidatos.add(this);
    }

    public Candidato (String nome, String dataDeNascimento,  int numeroDoCandidato) {
        super(nome, dataDeNascimento, "1111");
        this.numeroDoCandidato = numeroDoCandidato;
        Candidato.quantidadeDeCandidatos++;
        Candidato.cadastroDeCandidatos.put(numeroDoCandidato, this);
        Candidato.listaDeCandidatos.add(this);
    }

    @Override
    public String toString(){
        return (
                nome + " - " + numeroDoCandidato
                );
    }

    // Verifica qual dos candidatos é mais velho, caso tenham a mesma idade sorteia um deles
    static Candidato quemEhMaisVelho(Candidato candidato1, Candidato candidato2) {
        // Verifica ano, mês e dia de nascimento para dizer quem é mais velho
        if (candidato1.anoDeNascimento < candidato2.anoDeNascimento) {
            return candidato1;

        } else if (candidato2.anoDeNascimento < candidato1.anoDeNascimento) {
            return candidato2;

        } else {
            if (candidato1.mesDeNascimento < candidato2.mesDeNascimento) {
                return candidato1;

            } else if (candidato2.mesDeNascimento < candidato1.mesDeNascimento) {
                return candidato2;

            } else {
                if (candidato1.diaDeNascimento < candidato2.diaDeNascimento) {
                    return candidato1;

                } else if (candidato2.mesDeNascimento < candidato1.diaDeNascimento) {
                    return candidato2;
                }
            }      
        }

        // Caso as pessoas tenham exatamente a mesma idade, é decidido no sorteio
        List<Candidato> pessoas = new ArrayList<>();
        pessoas.add(candidato1);
        pessoas.add(candidato2);
        Collections.shuffle(pessoas);

        return pessoas.get(0);
    }

    // Implementado para ordernar a lista de candidatos pelo número de votos
    @Override
    public int compareTo(Candidato outroCandidato) {
        return outroCandidato.numDeVotos - this.numDeVotos;
    }

    public int getNumDeVotos() {
        return numDeVotos;
    }

    // Adiciona um voto ao candidato
    public void adicionaNumDeVotos() {
        this.numDeVotos++;
    }
}