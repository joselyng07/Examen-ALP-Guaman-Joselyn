import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Sistema AulaLogic - aplicacion de consola (Variante C: aprobacion >= 13).
 */
public class Main {

    private static final String RUTA_ARCHIVO = "datos/estudiantes.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestionAcademica gestion = new GestionAcademica();
        GestorArchivos gestor = new GestorArchivos(RUTA_ARCHIVO);

        // Recuperacion automatica al iniciar
        gestion.cargarRegistros(gestor.cargar());
        System.out.println("Registros recuperados: " + gestion.cantidad());

        int opcion = 0;
        try {
            do {
                mostrarMenu();
                opcion = leerOpcion(sc);
                switch (opcion) {
                    case 1:
                        registrarEstudiante(sc, gestion);
                        break;
                    case 2:
                        listarEstudiantes(gestion);
                        break;
                    case 3:
                        buscarEstudiante(sc, gestion);
                        break;
                    case 4:
                        System.out.println();
                        System.out.println(gestion.generarReporte());
                        break;
                    case 5:
                        if (gestor.guardar(gestion.getEstudiantes())) {
                            System.out.println("Registros guardados en " + gestor.getRuta());
                        }
                        break;
                    case 6:
                        System.out.println("Guardando registros antes de salir...");
                        break;
                    default:
                        System.out.println("Opcion incorrecta. Elija un numero del 1 al 6.");
                }
            } while (opcion != 6);
        } catch (NoSuchElementException ex) {
            // Fin de la entrada (por ejemplo, entrada redirigida): se sale de forma ordenada
            System.out.println();
            System.out.println("Fin de la entrada. Guardando registros antes de salir...");
        }

        // Guardado automatico al salir
        if (gestor.guardar(gestion.getEstudiantes())) {
            System.out.println("Registros guardados. Hasta pronto.");
        }
        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("=====================================");
        System.out.println("        SISTEMA AULALOGIC");
        System.out.println("=====================================");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Listar estudiantes");
        System.out.println("3. Buscar estudiante");
        System.out.println("4. Generar reporte academico");
        System.out.println("5. Guardar registros");
        System.out.println("6. Salir");
        System.out.println("=====================================");
        System.out.print("Seleccione una opcion: ");
    }

    /** Lee la opcion del menu; devuelve -1 si no es un entero (se trata como opcion incorrecta). */
    private static int leerOpcion(Scanner sc) {
        String texto = sc.nextLine().trim();
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    private static void registrarEstudiante(Scanner sc, GestionAcademica gestion) {
        System.out.print("Codigo del estudiante: ");
        String codigo = sc.nextLine().trim();
        if (!GestionAcademica.textoValido(codigo)) {
            System.out.println("Error: el codigo no puede estar vacio ni contener ';'.");
            return;
        }
        if (gestion.existeCodigo(codigo)) {
            System.out.println("Error: el codigo '" + codigo + "' ya esta registrado.");
            return;
        }

        String nombre;
        do {
            System.out.print("Nombre completo: ");
            nombre = sc.nextLine().trim();
            if (!GestionAcademica.textoValido(nombre)) {
                System.out.println("Error: el nombre no puede estar vacio ni contener ';'.");
            }
        } while (!GestionAcademica.textoValido(nombre));

        int[] notas = new int[Estudiante.CANTIDAD_NOTAS];
        for (int i = 0; i < notas.length; i++) {
            notas[i] = leerNota(sc, i + 1);
        }

        Estudiante e = new Estudiante(codigo, nombre, notas);
        if (gestion.registrar(e)) {
            System.out.println("Estudiante registrado correctamente.");
            System.out.printf(java.util.Locale.US, "Promedio: %.2f - Estado: %s%n",
                    e.calcularPromedio(), e.obtenerEstado());
        } else {
            System.out.println("No se pudo registrar al estudiante.");
        }
    }

    /** Solicita una nota hasta que sea un entero entre 0 y 20. */
    private static int leerNota(Scanner sc, int numero) {
        while (true) {
            System.out.print("Calificacion " + numero + " (0-20): ");
            String texto = sc.nextLine().trim();
            try {
                int nota = Integer.parseInt(texto);
                if (GestionAcademica.notaValida(nota)) {
                    return nota;
                }
                System.out.println("Error: la calificacion debe estar entre 0 y 20.");
            } catch (NumberFormatException ex) {
                System.out.println("Error: ingrese un numero entero entre 0 y 20.");
            }
        }
    }

    private static void listarEstudiantes(GestionAcademica gestion) {
        System.out.println();
        if (gestion.estaVacia()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        System.out.println(GestionAcademica.encabezadoTabla());
        ArrayList<Estudiante> lista = gestion.getEstudiantes();
        for (Estudiante e : lista) {
            System.out.println(e);
        }
    }

    private static void buscarEstudiante(Scanner sc, GestionAcademica gestion) {
        System.out.print("Codigo a buscar: ");
        String codigo = sc.nextLine().trim();
        Estudiante e = gestion.buscarPorCodigo(codigo);
        if (e == null) {
            System.out.println("No existe un estudiante con el codigo '" + codigo + "'.");
            return;
        }
        int[] n = e.getNotas();
        System.out.println();
        System.out.println("Codigo       : " + e.getCodigo());
        System.out.println("Nombre       : " + e.getNombre());
        System.out.println("Calificaciones: " + n[0] + ", " + n[1] + ", " + n[2]);
        System.out.printf(java.util.Locale.US, "Promedio     : %.2f%n", e.calcularPromedio());
        System.out.println("Nota maxima  : " + e.obtenerNotaMaxima());
        System.out.println("Nota minima  : " + e.obtenerNotaMinima());
        System.out.println("Estado       : " + e.obtenerEstado());
    }
}
