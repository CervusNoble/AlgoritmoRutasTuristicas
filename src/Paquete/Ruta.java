package Paquete;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Ruta {
    private ArrayList<String> ruta = new ArrayList<String>();
    private ArrayList<BigDecimal> puntaje = new ArrayList<BigDecimal>();
    private BigDecimal res;

    public BigDecimal getRes() {
        return res;
    }

    public void setRes(BigDecimal res) {
        this.res = res;
    }

    public ArrayList<String> getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta.add(ruta);
    }

    public ArrayList<BigDecimal> getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(String puntaje) {
        this.puntaje.add(new BigDecimal(puntaje));
    }
}