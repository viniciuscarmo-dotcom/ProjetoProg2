import java.util.*;

public class Votacao {
    static Scanner sc = new Scanner(System.in);
    static Set<Eleitor> eleitores = new HashSet<>();
    static int cont = 0;
    static int nulos = 0;

    public static void main(String[] args) {

        cadastroEleitores();

        /*
        Eleitor eleitor = new Eleitor("Joao", "10/10/2000", "619898159", "646516584");
        Eleitor eleitor2 = new Eleitor("Jose", "01/01/2001", "9819519851", "645198151");
        Eleitor eleitor3 = new Eleitor("Maria", "20/12/1998", "619898159", "646516584");
        Eleitor eleitor4 = new Eleitor("Pedro", "13/09/1999", "619898159", "646516584");
        Eleitor eleitor5 = new Eleitor("Jose", "13/09/1999", "619898159", "646516584");
        Eleitor eleitor6 = new Eleitor("Ana", "13/09/1999", "619898159", "646516584");
        Eleitor eleitor7 = new Eleitor("Mateus", "13/09/1999", "619898159", "646516584");
        */

        int cadCandidatos;
        System.out.printf("O que deseja: 1 - Cadastrar candidatos | (outro número) - Eleição com candidato já cadastrados? ");
        cadCandidatos = sc.nextInt();
        sc.nextLine();
        
        if (cadCandidatos == 1) {
            System.out.printf("Digite o número de candidatos que quer cadastrar: ");
            cadCandidatos = sc.nextInt();
            sc.nextLine();
            Eleicao.cadastrarCandidatos(cadCandidatos);

        } else {
            Candidato candidato1 = new Candidato("Jose", "17/10/1997", "619898159", "ABC", 99);
            Candidato candidato2 = new Candidato("Ana", "10/10/1997", "619898159", "EFG", 98);
            Candidato candidato3 = new Candidato("Mateus", "01/10/1997", "619898159", "HOJ", 97);
        }
        
        menuDeVotacao();

        /*
        Urna.votar(eleitor, 99);
        Urna.votar(eleitor2, 98);
        Urna.votar(eleitor2, 98);
        Urna.votar(eleitor4, 98);
        Urna.votar(eleitor5, 97);
        Urna.votar(eleitor6, 98);
        Urna.votar(eleitor7, 98);
        */

        Eleicao.contagemDeVotos();
    }

    private static void cadastroEleitores() {
        int qtd;
        String nome;
        String cpf;

        System.out.printf("Quantos eleitores: ");
        qtd = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < qtd; i++){
            System.out.printf("Nome: ");
            nome = sc.nextLine();
            System.out.printf("CPF: ");
            cpf = sc.nextLine();

            while (Eleitor.verificarCPFValido(cpf)) {
                System.out.printf("CPF inválido. Digite novamente: ");
                cpf = sc.nextLine();
            }

            Eleitor obj = new Eleitor(nome, cpf);
            eleitores.add(obj);
        }
    }

    private static void menuDeVotacao() {
        int variavel = 0;
        while (variavel != 2 && cont < Eleitor.getNumDeEleitores()){
            System.out.println("O que deseja: ");
            System.out.println("1 - Votar");
            System.out.println("2 - Encerrar votação");
            variavel = sc.nextInt();
            sc.nextLine();
            switch (variavel) {
                case 1:
                    votacao();
                    break;
                default:
                    break;
            }
        }
    }

    private static void imprimirCandidatos() {
        for (Candidato candidato : Candidato.getListaDeCandidatos()){
            System.out.println(candidato);
        }
    }

    private static void votacao() {
        String cpf;
        int voto;
        System.out.printf("Eleitor, insira seu CPF: ");
        cpf = sc.nextLine();
        for (Eleitor eleitor : eleitores){
            if (eleitor.getCPF().equals(cpf)){
                System.out.println("Bem-vindo, " + eleitor.getNome());
                imprimirCandidatos();
                System.out.printf("Escolha seu candidato: ");
                voto = sc.nextInt();

                try {
                    Urna.votar(eleitor, voto);
                    cont++;
                } catch (NullPointerException e) {
                    nulos++;
                    cont++;
                }
            }
        }
    }
}   
