package cr.ac.ulead.principal;

import cr.ac.ulead.Logic.Logic;
import cr.ac.ulead.presentacion.Presentacion;

public class Main {

    public static void main(String[] args) throws Exception {

        Presentacion presentacion = new Presentacion();
        Logic logic = new Logic();
        int opcion = 0;

        do {
            opcion = presentacion.menu();
            logic.runPrograma(opcion);
        } while (opcion != 5);
    }
}
