import java.util.*;

public class Candidato extends Pessoa implements Comparable<Candidato>{
    private static int votosNulosOuBrancos;
	private static int quantidadeDeCandidatos;
	private static Map<Integer, Candidato> cadastroDeCandidatos = new HashMap<>();
    private static List<Candidato> listaDeCandidatos = new ArrayList<>();
    private String partido;
    private int numeroDoCandidato;
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
                this.getNome() + " - " + numeroDoCandidato
                );
    } 

    // Implementado para ordernar a lista de candidatos pelo número de votos
    @Override
    public int compareTo(Candidato outroCandidato) {
        return outroCandidato.numDeVotos - this.numDeVotos;
    }
    
    public int getNumeroDoCandidato() {
        return numeroDoCandidato;
    }

    public void setNumeroDoCandidato(int numeroDoCandidato) {
        this.numeroDoCandidato = numeroDoCandidato;
    }
    
    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public int getNumDeVotos() {
        return this.numDeVotos;
    }

    // Adiciona um voto ao candidato
    public void adicionaNumDeVotos() {
        this.numDeVotos++;
    }

    // Verifica qual dos candidatos é mais velho, caso tenham a mesma idade sorteia um deles
    public static Candidato quemEhMaisVelho(Candidato candidato1, Candidato candidato2) {
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

    public static Map<Integer, Candidato> getCadastroDeCandidatos() {
        return Candidato.cadastroDeCandidatos;
    }
    
    public static List<Candidato> getListaDeCandidatos() {
        return Candidato.listaDeCandidatos;
    }
    
    public static int getVotosNulosOuBrancos() {
        return Candidato.votosNulosOuBrancos;
    }

    public static void adicionaVotosNulosOuBrancos() {
        Candidato.votosNulosOuBrancos++;
    }

    public static int getQuantidadeDeCandidatos() {
        return Candidato.quantidadeDeCandidatos;
    }    
}