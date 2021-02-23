import java.util.ArrayList;
import java.util.Arrays;

public class Vertice {
    private int id;
    private String nombre;
    private String tipo;
    private int posicion;
    private static float[][] relaciones;
    private static int lastPos=0;

    public Vertice(int id, String nombre, String tipo) {
        this.id = id;
        posicion=lastPos;
        lastPos++;
        this.nombre = nombre;
        this.tipo = tipo;
        //relaciones = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getPosicion() {
        return posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public static float[][] getRelaciones() {
        return relaciones;
    }

    public static void initRelaciones (int num){
        relaciones = new float[num][num];
    }

    public static void anadirRelacion (int origen, int destino, float distancia){
        relaciones[origen][destino] = distancia;
        relaciones[destino][origen] = distancia;
    }


    @Override
    public String toString() {
        return "Vertice{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", posicion=" + posicion +
                '}';
    }
}
