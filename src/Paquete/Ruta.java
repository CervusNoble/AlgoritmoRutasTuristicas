package Paquete;

import java.util.ArrayList;
import java.util.List;

public class Ruta {
    private ArrayList<String> ruta = new ArrayList<String>();
    private double puntaje = 0;

    public ArrayList<String> getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta.add(ruta);
    }

    public double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }
}