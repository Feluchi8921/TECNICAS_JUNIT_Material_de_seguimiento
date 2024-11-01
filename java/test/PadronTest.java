package tudai.tdv.java.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import tudai.tdv.java.Padron;
import tudai.tdv.java.Persona;
import tudai.tdv.java.PersonaNoEncontradaException;

public class PadronTest {
    static Padron padron = new Padron();

    public PadronTest() {
    }

    @BeforeClass
    public static void cargarPadronEjemplo() throws Exception {


        Persona p1 = new Persona("Juan", "26.150.235", "1979-01-01", 42, true);
        Persona p2 = new Persona("Pedro", "27.280.234", "1980-02-01", 41, true);
        Persona p3 = new Persona("Maria", "28.184.259", "1981-03-01", 47, true);
        Persona p4 = new Persona("Cecilia", "32.234.528", "1983-04-01", 38, true);
        Persona p5 = new Persona("Carlos", "33.124.235", "1985-04-01", 36, true);
        Persona p6 = new Persona("Jose", "35.345.534", "1987-04-01", 34, true);

        padron.AddVotante(p1);
        padron.AddVotante(p2);
        padron.AddVotante(p3);
        padron.AddVotante(p4);
        padron.AddVotante(p5);
        padron.AddVotante(p6);
    }

    // Cuento la cant de votantes actualmente y luego de agregarlo cuento nuevamente
    @Test
    public void testAgregarVotante() {
        int cantVotantesAntes = padron.getPartipantes().size();
        padron.AddVotante(new Persona("Fermin", "45.321.456", "2002-10-10", 22, true));
        int cantVotantesDespues = padron.getPartipantes().size();
        Assert.assertEquals(cantVotantesAntes + 1, cantVotantesDespues);
    }


    // Se verifica si la persona recuperada es exactamente la misma que la que se agregó.
    // El método assertSame de JUnit asegura que ambos objetos son la misma instancia en memoria.
    // Podria mejorarse comparando atributos
    @Test
    public void testAgregaVotanteExacto() {
        Persona p = new Persona("Fermin", "45.321.456", "2002-10-10", 22, true);
        padron.AddVotante(p);
        Persona pGuardada = padron.getPersona("45.321.456");
        Assert.assertSame("No se recibio la misma persona que se esperaba", p, pGuardada);
    }


    // Verifica si se lanza la excepción PersonaNoEncontradaException cuando se intenta registrar el voto de una persona que no existe en el padrón electoral.
    @Test
    @DisplayName("Intenta votar una persona que no existe")
    public void testExpectedPersonaNoEncontradaException() {
        Assertions.assertThrows(PersonaNoEncontradaException.class, () -> {
            Persona p = new Persona("Mickey", "2.234.528", "1983-04-01", 38, true);
            padron.setVoto(p.getDNI());
        });
    }
}
