package prog2.projeto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Candidato extends Pessoa implements Comparable<Candidato> {
    private Cargo cargo;
    private static Map<Cargo, Integer> votosNulosPorCargo = new HashMap<>();
	private static int quantidadeDeCandidatos;
    private static Map<String, Candidato> cadastroDeCandidatos = new HashMap<>();
    private static List<Candidato> listaDeCandidatos = new ArrayList<>();
    private static String autenticacao = "123456789";
    private String partido;
    private int numeroDoCandidato;
    private int numDeVotos;

    public Candidato (String nome, String dataDeNascimento, int numero, Cargo cargo) {
        super(nome, dataDeNascimento, "1111");
        this.numeroDoCandidato = numero;
        this.cargo = cargo;

        String chaveUnica = cargo.name() + "-" + numero;

        Candidato.cadastroDeCandidatos.put(chaveUnica, this);
        Candidato.listaDeCandidatos.add(this);
    }

    public Cargo getCargo() {
        return cargo;
    }

    public static String gerarChave(Cargo cargo, int numero) {
        return cargo.name() + "-" + numero;
    }

    @Override
    public String toString(){
        return String.format("[%s] %s - %d", this.cargo, this.getNome(), this.numeroDoCandidato);
    }

    // Implementado para ordernar a lista de candidatos pelo número de votos
    @Override
    public int compareTo(Candidato outroCandidato) {
        return outroCandidato.numDeVotos - this.numDeVotos;
    }
    
    public int getNumeroDoCandidato() {
        return numeroDoCandidato;
    }

    // Muda o número do candidato, caso o número não esteja sendo usado
    public void setNumeroDoCandidato(int numeroDoCandidato) {
        if (!cadastroDeCandidatos.containsKey(numeroDoCandidato)) {
            this.numeroDoCandidato = numeroDoCandidato;
        }
    }
    
    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    // Retorna o número de votos do candidato
    public int getNumDeVotos() {
        return this.numDeVotos;
    }

    // Adiciona um voto ao candidato, precisa de autenticacao
    public void adicionaNumDeVotos(String autenticacao) {
        if (Candidato.autenticacao.equals(autenticacao)) {
            this.numDeVotos++;
        } 
    }

    // Verifica qual dos candidatos é mais velho, caso tenham a mesma idade sorteia um deles
    public static Candidato quemEhMaisVelho(Candidato candidato1, Candidato candidato2) {
        // Verifica ano, mês e dia de nascimento para dizer quem é mais velho
        if (candidato1.getAnoDeNascimento() < candidato2.getAnoDeNascimento()) {
            return candidato1;

        } else if (candidato2.getAnoDeNascimento() < candidato1.getAnoDeNascimento()) {
            return candidato2;

        } else {
            if (candidato1.getMesDeNascimento() < candidato2.getMesDeNascimento()) {
                return candidato1;

            } else if (candidato2.getMesDeNascimento() < candidato1.getMesDeNascimento()) {
                return candidato2;

            } else {
                if (candidato1.getDiaDeNascimento() < candidato2.getDiaDeNascimento()) {
                    return candidato1;
                    
                } else if (candidato2.getDiaDeNascimento() < candidato1.getDiaDeNascimento()) {
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


    // Retorna um mapa com os números dos candidatos e os candidatos
    public static Map<String, Candidato> getCadastroDeCandidatos() {
        return Candidato.cadastroDeCandidatos;
    }
    
    // Retorna uma lista com todos os candidatos
    public static List<Candidato> getListaDeCandidatos() {
        return Candidato.listaDeCandidatos;
    }
    
    // Retorna o número de votos nulos ou brancos
    public static int getVotosNulos(Cargo cargo) {
        return votosNulosPorCargo.getOrDefault(cargo, 0);
    }


    // Adiciona voto nulo ou branco, precisa de autenticação
    public static void adicionaVotoNulo(Cargo cargo, String autenticacao) {
        if (Candidato.autenticacao.equals(autenticacao)) {
            votosNulosPorCargo.put(cargo, getVotosNulos(cargo) + 1);
        }
    }

    // Retorna a quantidade de candidatos
    public static int getQuantidadeDeCandidatos() {
        return Candidato.quantidadeDeCandidatos;
    }

    // Reinicia os candidatos
    public static void zerar() {
        quantidadeDeCandidatos = 0;
        votosNulosPorCargo.clear();
    }
}