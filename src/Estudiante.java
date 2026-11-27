/**
 * Representa a un estudiante y sus tres calificaciones.
 * Variante C: promedio minimo de aprobacion = 13.
 */
public class Estudiante {

    /** Promedio minimo para aprobar (Variante C). */
    public static final double LIMITE_APROBACION = 13.0;
    public static final int CANTIDAD_NOTAS = 3;

    private String codigo;
    private String nombre;
    private int[] notas;

    public Estudiante(String codigo, String nombre, int[] notas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.notas = new int[CANTIDAD_NOTAS];
        for (int i = 0; i < CANTIDAD_NOTAS; i++) {
            this.notas[i] = notas[i];
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int[] getNotas() {
        return notas.clone(); // copia: protege el encapsulamiento
    }

    /** Promedio de las tres notas, sin division entera. */
    public double calcularPromedio() {
        int suma = 0;
        for (int i = 0; i < notas.length; i++) {
            suma += notas[i];
        }
        return suma / (double) notas.length;
    }

    public int obtenerNotaMaxima() {
        int max = notas[0];
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] > max) {
                max = notas[i];
            }
        }
        return max;
    }

    public int obtenerNotaMinima() {
        int min = notas[0];
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] < min) {
                min = notas[i];
            }
        }
        return min;
    }

    /** APROBADO si el promedio es mayor o igual al limite configurado. */
    public String obtenerEstado() {
        return estaAprobado() ? "APROBADO" : "REPROBADO";
    }

    public boolean estaAprobado() {
        return calcularPromedio() >= LIMITE_APROBACION;
    }

    @Override
    public String toString() {
        return String.format("%-6s %-22s %3d %3d %3d   %6.2f  %3d %3d  %s",
                codigo, nombre, notas[0], notas[1], notas[2],
                calcularPromedio(), obtenerNotaMaxima(), obtenerNotaMinima(),
                obtenerEstado());
    }
}
