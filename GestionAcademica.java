import java.util.ArrayList;
import java.util.Locale;

/**
 * Administra la coleccion de estudiantes: registro, busqueda,
 * listados e indicadores del rendimiento academico.
 */
public class GestionAcademica {

    private ArrayList<Estudiante> estudiantes;

    public GestionAcademica() {
        estudiantes = new ArrayList<>();
    }

    // ---------------------------------------------------------------
    // Validaciones y registro
    // ---------------------------------------------------------------

    /** Texto valido: no vacio (ni solo espacios) y sin punto y coma. */
    public static boolean textoValido(String texto) {
        return texto != null && !texto.trim().isEmpty() && !texto.contains(";");
    }

    public static boolean notaValida(int nota) {
        return nota >= 0 && nota <= 20;
    }

    public boolean existeCodigo(String codigo) {
        return buscarPorCodigo(codigo) != null;
    }

    /**
     * Registra un estudiante si sus datos son validos y el codigo no existe.
     * @return true si se registro; false en caso contrario.
     */
    public boolean registrar(Estudiante e) {
        if (e == null || !textoValido(e.getCodigo()) || !textoValido(e.getNombre())) {
            return false;
        }
        for (int nota : e.getNotas()) {
            if (!notaValida(nota)) {
                return false;
            }
        }
        if (existeCodigo(e.getCodigo())) {
            return false;
        }
        estudiantes.add(e);
        return true;
    }

    /** Reemplaza todos los registros (usado al recuperar desde archivo). */
    public void cargarRegistros(ArrayList<Estudiante> recuperados) {
        estudiantes.clear();
        for (Estudiante e : recuperados) {
            registrar(e); // evita duplicados si el archivo los trae
        }
    }

    // ---------------------------------------------------------------
    // Consulta
    // ---------------------------------------------------------------

    public Estudiante buscarPorCodigo(String codigo) {
        if (codigo == null) {
            return null;
        }
        String buscado = codigo.trim();
        for (Estudiante e : estudiantes) {
            if (e.getCodigo().equalsIgnoreCase(buscado)) {
                return e;
            }
        }
        return null;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return new ArrayList<>(estudiantes);
    }

    public int cantidad() {
        return estudiantes.size();
    }

    public boolean estaVacia() {
        return estudiantes.isEmpty();
    }

    // ---------------------------------------------------------------
    // Indicadores
    // ---------------------------------------------------------------

    public int contarAprobados() {
        int contador = 0;
        for (Estudiante e : estudiantes) {
            if (e.estaAprobado()) {
                contador++;
            }
        }
        return contador;
    }

    public int contarReprobados() {
        return estudiantes.size() - contarAprobados();
    }

    /** Promedio general del curso (0 si no hay estudiantes: evita dividir entre cero). */
    public double promedioGeneral() {
        if (estudiantes.isEmpty()) {
            return 0.0;
        }
        double acumulador = 0;
        for (Estudiante e : estudiantes) {
            acumulador += e.calcularPromedio();
        }
        return acumulador / estudiantes.size();
    }

    public double porcentajeAprobados() {
        if (estudiantes.isEmpty()) {
            return 0.0;
        }
        return contarAprobados() * 100.0 / estudiantes.size();
    }

    /** En caso de empate devuelve el primero registrado. */
    public Estudiante mejorPromedio() {
        if (estudiantes.isEmpty()) {
            return null;
        }
        Estudiante mejor = estudiantes.get(0);
        for (int i = 1; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).calcularPromedio() > mejor.calcularPromedio()) {
                mejor = estudiantes.get(i);
            }
        }
        return mejor;
    }

    /** En caso de empate devuelve el primero registrado. */
    public Estudiante peorPromedio() {
        if (estudiantes.isEmpty()) {
            return null;
        }
        Estudiante peor = estudiantes.get(0);
        for (int i = 1; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).calcularPromedio() < peor.calcularPromedio()) {
                peor = estudiantes.get(i);
            }
        }
        return peor;
    }

    public ArrayList<Estudiante> bajoPromedioGeneral() {
        ArrayList<Estudiante> resultado = new ArrayList<>();
        double general = promedioGeneral();
        for (Estudiante e : estudiantes) {
            if (e.calcularPromedio() < general) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    /** Copia ordenada de mayor a menor promedio (seleccion); no altera el orden de registro. */
    public ArrayList<Estudiante> ordenadosPorPromedio() {
        ArrayList<Estudiante> copia = new ArrayList<>(estudiantes);
        for (int i = 0; i < copia.size() - 1; i++) {
            int posMayor = i;
            for (int j = i + 1; j < copia.size(); j++) {
                if (copia.get(j).calcularPromedio() > copia.get(posMayor).calcularPromedio()) {
                    posMayor = j;
                }
            }
            if (posMayor != i) {
                Estudiante aux = copia.get(i);
                copia.set(i, copia.get(posMayor));
                copia.set(posMayor, aux);
            }
        }
        return copia;
    }

    // ---------------------------------------------------------------
    // Presentacion
    // ---------------------------------------------------------------

    public static String encabezadoTabla() {
        return String.format("%-6s %-22s %3s %3s %3s   %6s  %3s %3s  %s",
                "COD", "NOMBRE", "N1", "N2", "N3", "PROM", "MAX", "MIN", "ESTADO");
    }

    public String generarReporte() {
        if (estudiantes.isEmpty()) {
            return "No hay estudiantes registrados. No es posible generar el reporte.";
        }
        StringBuilder sb = new StringBuilder();
        Locale l = Locale.US;
        sb.append("========== REPORTE ACADEMICO ==========\n");
        sb.append("Limite de aprobacion (Variante C): ").append((int) Estudiante.LIMITE_APROBACION).append("\n");
        sb.append("Total de estudiantes : ").append(cantidad()).append("\n");
        sb.append("Aprobados            : ").append(contarAprobados()).append("\n");
        sb.append("Reprobados           : ").append(contarReprobados()).append("\n");
        sb.append(String.format(l, "Promedio general     : %.2f%n", promedioGeneral()));
        sb.append(String.format(l, "Porcentaje aprobados : %.2f%%%n", porcentajeAprobados()));
        Estudiante mejor = mejorPromedio();
        Estudiante peor = peorPromedio();
        sb.append(String.format(l, "Promedio mas alto    : %s (%s) - %.2f%n",
                mejor.getNombre(), mejor.getCodigo(), mejor.calcularPromedio()));
        sb.append(String.format(l, "Promedio mas bajo    : %s (%s) - %.2f%n",
                peor.getNombre(), peor.getCodigo(), peor.calcularPromedio()));

        sb.append("\n--- Estudiantes con promedio inferior al general ---\n");
        ArrayList<Estudiante> bajos = bajoPromedioGeneral();
        if (bajos.isEmpty()) {
            sb.append("(ninguno)\n");
        } else {
            for (Estudiante e : bajos) {
                sb.append(String.format(l, "%s - %s - %.2f%n", e.getCodigo(), e.getNombre(), e.calcularPromedio()));
            }
        }

        sb.append("\n--- Listado general (mayor a menor promedio) ---\n");
        sb.append(encabezadoTabla()).append("\n");
        for (Estudiante e : ordenadosPorPromedio()) {
            sb.append(e.toString()).append("\n");
        }
        return sb.toString();
    }
}
