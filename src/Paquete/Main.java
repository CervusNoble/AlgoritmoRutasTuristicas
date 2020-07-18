package Paquete;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {

    public static ArrayList<Ruta> lista_rutas = new ArrayList<Ruta>();
    public static int n_poi=0;
    public static String [] poi;
    public static String[][] matriz ;
    public static ArrayList<Conexion> poi_conectados = new ArrayList<Conexion>();        //Guarda las columnas eficientes de cada POI.

    public static void main(String[] args) {
        n_poi=Integer.parseInt(JOptionPane.showInputDialog(null,"Digite la cantidad de puntos de interes"));
        poi=new String[n_poi];
        matriz=new String[n_poi][n_poi];
        for(int a=0;a<n_poi;a++){

          poi[a]=JOptionPane.showInputDialog(null,"Digite el nombre del punto de interes");

        }
        for(int b=0;b<matriz.length;b++){
        for(int c=0;c<matriz.length;c++){

            if(b==c){
                matriz[b][c]="-1";
            } else if(b!=c){
                matriz[b][c]=JOptionPane.showInputDialog(null,"Digite los datos de la fila "+poi[b]+" y la columna "+poi[c]);

            }


        }

        }

        for(int i = 0; i < matriz.length; i++) {
            Conexion poi_c = new Conexion();
            for (int j = 0; j < matriz.length; j++) {
                if (!matriz[i][j].equals("-1")) {                             //Se añaden las columnas de los puntos de interes mas eficientes en pos_admitidas.
                    poi_c.setConexiones(j);
                    poi_c.setValor(matriz[i][j]);
                }
            }
            poi_conectados.add(poi_c);
        }

        formar_posi(new ArrayList<Integer>(),new ArrayList<String>(), 0, "0" );            //Se enviar el POI de inicio (siempre empieza por 0) a poi_anterior.
        mostrar_rutas();
        rutas_aptas();
    }

    public static void formar_posi(ArrayList<Integer> recorrido, ArrayList<String> valor, int poi_anterior, String val_anterior){
        recorrido.add(poi_anterior);                            //Se agrega el POI siguiente al recorrido.
        valor.add(val_anterior);
        if(recorrido.size()==n_poi){
            BigDecimal res = new BigDecimal("0");
            Ruta r = new Ruta();
            for (int iterador:recorrido){
                r.setRuta(poi[iterador]);                //Guarda las rutas validas en el objeto r.
            }
            for (String iterador:valor) {
                res = res.add(new BigDecimal(iterador));
                r.setPuntaje(iterador);
            }
            r.setRes(res);
            lista_rutas.add(r);                                 //Guarda cada ruta valida el ArrayList lista_rutas.
        }else{
            for (int i = 0; i< poi_conectados.get(recorrido.get(recorrido.size()-1)).getConexiones().size(); i++) {              //Limite del for es la cantidad maxima de posibilidades en un POI. Se obtiene con la cantidad de posibilidades admitidas del último POI recorrido.
                if(!recorrido.contains(poi_conectados.get(recorrido.get(recorrido.size() - 1)).getConexiones().get(i))) {      //Es verdadero si en las rutas de "recorrido" contiene las posibilidades del último POI recorrido. Esto se hace para no repetir el mismo POI. ! para ejecutar la instrucción en el caso de que no la contenga.
                    formar_posi((ArrayList<Integer>) recorrido.clone(), (ArrayList<String>) valor.clone(), poi_conectados.get(recorrido.get(recorrido.size() - 1)).getConexiones().get(i), poi_conectados.get(recorrido.get(recorrido.size() - 1)).getValor().get(i)); //Se envía el recorrido, la ubicación del POI siguiente en pos_admitidas y el valor sumado hasta ahora.
                }
            }
        }

    }

    public static void mostrar_rutas(){
        String reco="Rutas validas";
        for(Ruta iterador_rutas:lista_rutas){
            reco=reco+"\n"+(iterador_rutas.getRuta()+" Valor: "+iterador_rutas.getPuntaje()+" Resultado: "+iterador_rutas.getRes());                  //Imprime las rutas validas/optimas con su valor.
        }
        JOptionPane.showMessageDialog(null,reco);
    }

    public static void rutas_aptas(){
        BigDecimal res_ant=new BigDecimal("9999");
        ArrayList<Integer> index = new ArrayList<Integer>();
        int cont=0;
        for(Ruta iterador_rutas:lista_rutas){
            if(res_ant.compareTo(iterador_rutas.getRes())==1){
                index.clear();
                index.add(cont);
                res_ant=iterador_rutas.getRes();
            }else if(res_ant.compareTo(iterador_rutas.getRes())==0){
                index.add(cont);
            }
            cont++;
        }
        String ruta="\nRutas más aptas:";
        for (int iterador:index) {
            ruta=ruta+"\n"+(lista_rutas.get(iterador).getRuta()+"Valor: "+lista_rutas.get(iterador).getRes());
        }
        JOptionPane.showMessageDialog(null,""+ruta);
    }

}