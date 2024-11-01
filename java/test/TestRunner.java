package tudai.tdv.java.test;

import org.junit.runner.JUnitCore;

public class TestRunner {

    public static void main(String args[]) {
        System.out.println("Test runner -> comienzo del Runner");

        // Esta línea es la clave. Le dice a JUnitCore que ejecute todas las pruebas definidas en la clase TestSuite

        JUnitCore.runClasses(TestSuite.class);
        System.out.println("Test runner -> Fin del Runner");
    }

}