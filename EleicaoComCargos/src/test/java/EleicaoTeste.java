import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import prog2.projeto.Candidato;
import prog2.projeto.Cargo;
import prog2.projeto.Eleicao;
import prog2.projeto.Eleitor;
import prog2.projeto.Urna;

public class EleicaoTeste {

    @Before
    public void setUp() {
        Candidato.zerar();
        Eleitor.zerar();
    }

    @Test
    public void TestEleicao() {
        Candidato candidato1 = new Candidato("Jose", "18/10/1997", 99, Cargo.PREFEITO);
        Candidato candidato2 = new Candidato("Ana", "10/10/1997", 98, Cargo.PREFEITO);
        Candidato candidato3 = new Candidato("Mateus", "01/10/1997", 97, Cargo.PREFEITO);

        Eleitor eleitor1 = new Eleitor("Joao", "10/10/2000", "519582985", "646516584");
        Eleitor eleitor2 = new Eleitor("Jose", "01/01/2001", "591952915", "645198151");
        Eleitor eleitor3 = new Eleitor("Maria", "20/12/1998", "619898159", "646651684");
        Eleitor eleitor4 = new Eleitor("Pedro", "13/09/1999", "519519591", "444516584");
        Eleitor eleitor5 = new Eleitor("Jose", "13/09/1999", "719595819", "668686584");
        Eleitor eleitor6 = new Eleitor("Ana", "13/09/1999", "519719529", "646519994");
        Eleitor eleitor7 = new Eleitor("Mateus", "13/09/1999", "798519516", "646666584");

        Urna.votar(eleitor1, 99, Cargo.PREFEITO); // Voto válido (Jose)
        Urna.votar(eleitor2, 98, Cargo.PREFEITO); // Voto válido (Ana)
        Urna.votar(eleitor2, 98, Cargo.PREFEITO); // Tentativa duplicada (Ignorado)
        Urna.votar(eleitor4, 44, Cargo.PREFEITO); // Candidato não existe (Nulo)
        Urna.votar(eleitor5, 97, Cargo.PREFEITO); // Voto válido (Mateus)
        Urna.votar(eleitor6, 98, Cargo.PREFEITO); // Voto válido (Ana)
        Urna.votar(eleitor7, 98, Cargo.PREFEITO); // Voto válido (Ana)

        Eleicao.contagemDeVotos();

        Assert.assertEquals(1L, (long)candidato3.getNumDeVotos()); // Mateus
        Assert.assertEquals(3L, (long)candidato2.getNumDeVotos()); // Ana (Vencedora)
        Assert.assertEquals(1L, (long)candidato1.getNumDeVotos()); // Jose

        Assert.assertEquals(1L, (long)Candidato.getVotosNulos(Cargo.PREFEITO));

        Assert.assertEquals(7L, (long)Eleitor.getNumDeEleitores());


        Assert.assertEquals(6L, (long)Eleitor.getNumDeVotantes());

        long votosValidos = candidato1.getNumDeVotos() + candidato2.getNumDeVotos() + candidato3.getNumDeVotos();
        Assert.assertEquals(5L, votosValidos);

        Assert.assertTrue(candidato2.getNumDeVotos() > candidato1.getNumDeVotos());
        Assert.assertTrue(candidato2.getNumDeVotos() > candidato3.getNumDeVotos());

        Assert.assertTrue(eleitor1.jaVotouPara(Cargo.PREFEITO));
        Assert.assertFalse(eleitor3.jaVotouPara(Cargo.PREFEITO)); // Maria não votou
    }
}