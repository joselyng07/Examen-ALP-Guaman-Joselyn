import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Persistencia en texto plano. Formato por linea:
 * codigo;nombre;nota1;nota2;nota3
 */
public class GestorArchivos {

    private String ruta;

    public GestorArchivos(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }

    /**
     * Sobrescribe el archivo completo con los registros recibidos
     * (no duplica datos entre guardados).
     * @return true si se guardo correctamente.
     */
    public boolean guardar(ArrayList<Estudiante> estudiantes) {
        File archivo = new File(ruta);
        File carpeta = archivo.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, StandardCharsets.UTF_8, false))) {
            for (Estudiante e : estudiantes) {
                int[] n = e.getNotas();
                bw.write(e.getCodigo() + ";" + e.getNombre() + ";" + n[0] + ";" + n[1] + ";" + n[2]);
                bw.newLine();
            }
            return true;
        } catch (IOException ex) {
            System.out.println("Error al guardar el archivo: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Lee el archivo y reconstruye los objetos Estudiante.
     * Si el archivo no existe devuelve una lista vacia.
     * Las lineas con formato incorrecto se omiten con un aviso.
     */
    public ArrayList<Estudiante> cargar() {
        ArrayList<Estudiante> lista = new ArrayList<>();
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            System.out.println("Aviso: no existe '" + ruta + "'. Se inicia sin registros.");
            return lista;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(archivo, StandardCharsets.UTF_8))) {
            String linea;
            int numero = 0;
            while ((linea = br.readLine()) != null) {
                numero++;
                if (linea.trim().isEmpty()) {
                    continue;
                }
                Estudiante e = convertirLinea(linea);
                if (e == null) {
                    System.out.println("Aviso: linea " + numero + " invalida, se omite -> " + linea);
                } else {
                    lista.add(e);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo: " + ex.getMessage());
        }
        return lista;
    }

    /** Convierte una linea a Estudiante; devuelve null si es invalida. */
    private Estudiante convertirLinea(String linea) {
        String[] partes = linea.split(";");
        if (partes.length != 5) {
            return null;
        }
        try {
            int[] notas = new int[3];
            for (int i = 0; i < 3; i++) {
                notas[i] = Integer.parseInt(partes[i + 2].trim());
                if (!GestionAcademica.notaValida(notas[i])) {
                    return null;
                }
            }
            String codigo = partes[0].trim();
            String nombre = partes[1].trim();
            if (!GestionAcademica.textoValido(codigo) || !GestionAcademica.textoValido(nombre)) {
                return null;
            }
            return new Estudiante(codigo, nombre, notas);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
