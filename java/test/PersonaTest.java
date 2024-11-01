package tudai.tdv.java.test;

import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.testng.asserts.Assertion;
import tudai.tdv.java.Persona;
import tudai.tdv.java.PersonaNoEncontradaException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class PersonaTest {
    static Persona ejemplos[];
    static Persona casoActual;
    static Persona buCasoActual;

    @BeforeClass
    public static void cargarEjemplos() throws Exception {
        ejemplos= new Persona[6];
        ejemplos[0] = new Persona("Juan","26.150.235","1980-01-01",44,true);
        ejemplos[1]  = new Persona("Pedro","27.280.234","1980-02-01",44,true);
        ejemplos[2]  = new Persona("Maria","28.184.259","1981-03-01",43,true);
        ejemplos[3]  = new Persona("Cecilia","32.234.528","1970-04-01",54,true);
        ejemplos[4] = new Persona("Carlos","33.124.235","1984-04-01",40,true);
        ejemplos[5]  = new Persona("Jose","35.345.534","1989-04-01",35,true);
    }


    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("Bye byte ");
    }


    // Elige una persona aleatoriamente: Caso actual
    @Before
    public void elegirUno() throws Exception {
        Random generadorAleatorios = new Random();
        int numeroAleatorio = generadorAleatorios.nextInt(PersonaTest.ejemplos.length);
        casoActual=ejemplos[numeroAleatorio];

    }

    // Verifica que el método isHabilitadoParaVotar() de la clase Persona esté implementando correctamente
    // La lógica para determinar si una persona elegida aleatoriamente puede votar según la edad mínima establecida (16 años en este caso)
    @Test
    public void tesHabilitadoParaVotar() {
        boolean obtenido = casoActual.isHabilitadoParaVotar();
        boolean esperado = casoActual.getEdad()>16;
        Assert.assertEquals(obtenido, esperado);
    }

    @Test
    public void testSetEdad() {
        casoActual.setFechaNacimiento("2000-10-08");
        casoActual.setEdad(24);
        int edadReportada = casoActual.getEdad();
        int edadReal= getEdad(casoActual.getFechaNacimiento());
        Assert.assertTrue("La edad reportada no es igual a la edad real",edadReportada == edadReal);
    }

//Esta es una funcion auxiliar que no tienen ninguna anotacion
    public int getEdad(String fechaNacimiento) {
        try {
            // Crea un objeto SimpleDateFormat para parsear la fecha de nacimiento.
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // Parsea la fecha de nacimiento.
            Date fechaNac = sdf.parse(fechaNacimiento);

            // Obtiene la fecha actual.
            Date fechaActual = new Date();

            // Crea un objeto Calendar para calcular la diferencia entre las fechas.
            Calendar calNac = Calendar.getInstance();
            Calendar calActual = Calendar.getInstance();
            calNac.setTime(fechaNac);
            calActual.setTime(fechaActual);

            // Calcula la diferencia de años.
            int edad = calActual.get(Calendar.YEAR) - calNac.get(Calendar.YEAR);

            // Verifica si el cumpleaños ya pasó este año.
            if (calNac.get(Calendar.MONTH) > calActual.get(Calendar.MONTH) ||
                    (calNac.get(Calendar.MONTH) == calActual.get(Calendar.MONTH) &&
                            calNac.get(Calendar.DAY_OF_MONTH) > calActual.get(Calendar.DAY_OF_MONTH))) {
                edad--;
            }

            return edad;
        } catch (ParseException e) {
            e.printStackTrace();
            return -1; // Manejo de errores
        }
    }

    @Test
    @DisplayName("Verificando varias caracteristicas a la vez")
    public void testVariasCaracteristicas(){
        Persona p = new Persona("Mickey","32.235.365", "1987-04-01", 38,true);
        Assertions.assertAll("Probando que se cumplen varias caracteristicas",
                ()->Assert.assertEquals("No se almacena el dni", p.getDNI(), "32.235.365"),
                ()->Assert.assertEquals("Error al almacenar la edad", p.getEdad(), 38)
        );
    }


}
