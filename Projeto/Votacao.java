package Projeto;

public class Votacao {
    public static void main(String[] args) {
        Eleitor eleitor = new Eleitor("Joao", "10102000", 20, "619898159", "646516584");
        Eleitor eleitor2 = new Eleitor("Jose", "01012001", 18, "9819519851", "645198151");
        Eleitor eleitor3 = new Eleitor("Maria", "20121998", 64, "619898159", "646516584");
        Eleitor eleitor4 = new Eleitor("Pedro", "13091999", 49, "619898159", "646516584");
        Eleitor eleitor5 = new Eleitor("Jose", "13091999", 49, "619898159", "646516584");
        Eleitor eleitor6 = new Eleitor("Ana", "13091999", 49, "619898159", "646516584");
        Eleitor eleitor7 = new Eleitor("Mateus", "13091999", 49, "619898159", "646516584");

        Candidato candidato1 = new Candidato("Jose", "17101997", 28, "619898159", "ABC", "99");
        Candidato candidato2 = new Candidato("Ana", "10101997", 28, "619898159", "EFG", "98");
        Candidato candidato3 = new Candidato("Mateus", "01101997", 28, "619898159", "HOJ", "97");


        Urna urna = new Urna();
        urna.votar(eleitor, "99");
        urna.votar(eleitor2, "98");
        urna.votar(eleitor2, "98");
        urna.votar(eleitor4, "98");
        urna.votar(eleitor5, "97");
        urna.votar(eleitor6, "98");
        urna.votar(eleitor7, "98");


        Eleicao eleicao = new Eleicao();
        eleicao.contagemDeVotos();
    }
}   
