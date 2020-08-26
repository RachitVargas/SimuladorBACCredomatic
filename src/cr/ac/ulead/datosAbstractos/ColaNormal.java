package cr.ac.ulead.datosAbstractos;

import cr.ac.ulead.objetos.CajaRegistradora;
import cr.ac.ulead.objetos.Persona;

public class ColaNormal {

    Persona[] data;
    int head = 0;
    int tail = 0;
    int max = 0;

    public ColaNormal() {
        max = 10000;
        data = new Persona[10000];
    }

    public void enqueue(Persona newValue) {

        if (tail == max) {
            System.out.println("¡Lo sentimos, Cola llena!");
        } else {
            int posicion = (head + tail) % max;
            this.data[posicion] = newValue;
            this.tail++;
        }
    }

    public Persona dequeue() {
        Persona dato = (data[head]);
        data[head] = null;
        head = (head + 1) % max;
        if (tail != 0) {
            tail--;
        }
        return dato;
    }

    public int personasEncola() {

        int contador = 0;
        for (int i = 0; i < data.length; i++) {
            if (this.data[i] != null) {
                contador++;
            }
        }
        return contador;
    }

    public double promedioColaTramite(CajaRegistradora cajaRegistradora) {

        double contador = 0;
        double suma = 0;
        for (int i = 0; i < data.length; i++) {
            if (this.data[i] != null) {
                Persona persona = dequeue();
                suma = suma + cajaRegistradora.calcularMinutos();
                contador++;
            }
        }
        double promedio = suma / contador;
        return promedio;
    }
}