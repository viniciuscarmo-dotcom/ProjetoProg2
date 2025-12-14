import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import prog2.projeto.Candidato;
import prog2.projeto.Eleicao;
import prog2.projeto.Eleitor;
import prog2.projeto.Urna;

public class EleicaoTeste {
    @Test
    public void TestEleicao() {
        Candidato candidato1 = new Candidato("Jose", "18/10/1997", "619898159", "ABC", 99);
        Candidato candidato2 = new Candidato("Ana", "10/10/1997", "165156516", "EFG", 98);
        Candidato candidato3 = new Candidato("Mateus", "01/10/1997", "871895956", "HOJ", 97);

        Eleitor eleitor1 = new Eleitor("Joao", "10/10/2000", "519582985", "646516584");
        Eleitor eleitor2 = new Eleitor("Jose", "01/01/2001", "591952915", "645198151");
        Eleitor eleitor3 = new Eleitor("Maria", "20/12/1998", "619898159", "646651684");
        Eleitor eleitor4 = new Eleitor("Pedro", "13/09/1999", "519519591", "444516584");
        Eleitor eleitor5 = new Eleitor("Jose", "13/09/1999", "719595819", "668686584");
        Eleitor eleitor6 = new Eleitor("Ana", "13/09/1999", "519719529", "646519994");
        Eleitor eleitor7 = new Eleitor("Mateus", "13/09/1999", "798519516", "646666584");

        Urna.votar(eleitor1, 99);
        Urna.votar(eleitor2, 98);
        // Repetido para testar se alguém consegue votar novamente
        Urna.votar(eleitor2, 98);
        Urna.votar(eleitor4, 44);
        Urna.votar(eleitor5, 97);
        Urna.votar(eleitor6, 98);
        Urna.votar(eleitor7, 98);

        Eleicao.contagemDeVotos();

        // Votos que o candidato de número 97 recebeu
        assertEquals(1, candidato3.getNumDeVotos());
        // Votos que o candidato de número 98 recebeu
        assertEquals(3, candidato2.getNumDeVotos());
        // Votos que o candidato de número 99 recebeu
        assertEquals(1, candidato1.getNumDeVotos());
        // Votos nulos ou brancos
        assertEquals(1, Candidato.getVotosNulosOuBrancos());
        // Número de eleitores cadastrados
        assertEquals(7, Eleitor.getNumDeEleitores());
        // Número de eleitores que compareceram
        assertEquals(6, Eleitor.getNumDeVotantes());
        // Votos válidos
        assertEquals(5, Eleicao.getNumDeVotosValidos());
        // Vencedor(a) da eleição
        assertEquals(candidato2, Eleicao.getVencedor().get(0));
        // Verificar se o eleitor1 votou
        assertTrue(eleitor1.eleitorJaVotou());
        // Verificar se o eleitor3 votou
        assertFalse(eleitor3.eleitorJaVotou());
    }
}
