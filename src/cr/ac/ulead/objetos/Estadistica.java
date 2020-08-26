package cr.ac.ulead.objetos;

import java.util.ArrayList;

public class Estadistica {

    public double promedioTramite(ArrayList<Integer> promedioTramite) {

        double sumaTramite = 0;
        double promedio = 0;
        int cantidadTramites = promedioTramite.size();
        for (int i = 0; i < promedioTramite.size(); i++) {
            sumaTramite = sumaTramite + promedioTramite.get(i);
        }
        promedio = sumaTramite / cantidadTramites;
        return promedio;
    }
}
