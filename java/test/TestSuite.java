package tudai.tdv.java.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Indica que JUnit se encargará de ejecutar las pruebas.
@RunWith(Suite.class)

// El parámetro Suite.class especifica que se trata de un conjunto de pruebas.
@Suite.SuiteClasses({
        DemoPrecedencia.class,
        PersonaTest.class,
        PadronTest.class
})

//Lista de clases de prueba
public class TestSuite {
    //Clase vacia
}
