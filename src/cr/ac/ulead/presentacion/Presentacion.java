package cr.ac.ulead.presentacion;

import cr.ac.ulead.objetos.CajaRegistradora;

import java.io.PrintStream;
import java.util.Scanner;

public class Presentacion {

    private Scanner input;
    private PrintStream output;

    public Presentacion() {
        this.input = new Scanner(System.in);
        this.output = new PrintStream(System.out);
    }

    public int menu() {

        output.println("Bienvenido al simulador de BAC Credomatic");
        output.println("Elija una opción por favor:");
        output.println("1. Cargar simulación");
        output.println("2. Establencer número de cajas");
        output.println("3. Correr simulación");
        output.println("4. Mostrar resultados de la simulación");
        output.println("5. Salir");

        return input.nextInt();

    }

    public CajaRegistradora[] cajas() {

        output.println("Por favor especifíque el número");
        int NcajasNormales = input.nextInt();
        CajaRegistradora[] cajas = new CajaRegistradora[NcajasNormales];
        for (int i = 0; i < NcajasNormales; i++) {
            cajas[i] = new CajaRegistradora();
        }
        System.out.println("Cajas genereadas con exito");
        return cajas;
    }

    public void error() {
        System.out.println("¡Lo sentimos, ha ocurrido un error!");
    }

    public int cajaNueva() {
        System.out.println("¿Quiere que una caja sea nueva en su puesto? (elija NUMERO)");
        System.out.println("1. Si");
        System.out.println("2. No");
        int opcion = input.nextInt();
        System.out.println("Se creo la opción");
        return opcion;
    }
}
