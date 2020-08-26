package cr.ac.ulead.Logic;

import cr.ac.ulead.datosAbstractos.ColaNormal;
import cr.ac.ulead.file.gestorDatos;
import cr.ac.ulead.objetos.CajaRegistradora;
import cr.ac.ulead.objetos.Estadistica;
import cr.ac.ulead.objetos.EstadoPersona;
import cr.ac.ulead.objetos.Persona;
import cr.ac.ulead.presentacion.Presentacion;

import java.util.ArrayList;

public class Logic {

    Presentacion presentacion;
    gestorDatos gestorDatos;
    CajaRegistradora cajas[];
    boolean validacion = false;
    boolean validacionCajas = false;
    ArrayList<EstadoPersona> estadoPersona;
    ColaNormal colaNormal;
    ColaNormal colaDePrioridad;
    Persona persona;
    int gastoCola = 1;
    int tramitePasado = 0;
    ArrayList<Integer> promedioTramite;
    int cajaNueva = 0;

    public Logic() {
        this.presentacion = new Presentacion();
        this.gestorDatos = new gestorDatos();
        this.persona = new Persona();
        this.estadoPersona = new ArrayList<>();
        this.colaNormal = new ColaNormal();
        this.colaDePrioridad = new ColaNormal();
        this.promedioTramite = new ArrayList<>();

    }

    public void runPrograma(int opcion) throws Exception {

        switch (opcion) {

            case 1:
                if (!validacion) {
                    gestorDatos.cargarDatos(this.estadoPersona);
                    this.validacion = true;
                } else {
                    System.out.println("Lo sentimos, la simulación ya se encuentra cargada");
                }
                break;

            case 2:
                if (!this.validacionCajas && this.validacion) {
                    this.cajas = presentacion.cajas();
                    cajaNueva = presentacion.cajaNueva();
                    this.validacionCajas = true;
                } else {
                    System.out.println("Lo sentimos, ya estableciste el número de cajas, o NO has cargado la simulación");
                }
                break;

            case 3:
                if (this.validacionCajas) {
                    int minuto = 0;
                    while (minuto < 480) {
                        // System.out.println("----------- " + " minuto: " + minuto + " -----------"); -- > QUITAR COMENTARIO PARA DESPLEGAR
                        meterPersonaNCola(minuto);
                        meterPersonaNPrioridad(minuto);
                        //GESTION DE PERSONAS DE PRIORIDAD
                        for (int i = 0; i < this.cajas.length; i++) {
                            if (i < this.estadoPersona.get(minuto).getPrioridad()) {
                                if (this.cajas[i].getDisponible()) {
                                    Persona persona = AtenderPersonaPrioridad(this.colaDePrioridad.dequeue(), minuto, i);
                                    this.promedioTramite.add(persona.getTiempoEnCola());
                                    //        System.out.println(persona.toStringPrioridad()); -- > QUITAR COMENTARIO PARA DESPLEGAR
                                }
                            }
                            //GESTION DE PERSONAS NORMAL
                            if (this.cajas[i].getDisponible()) {
                                Persona persona = AtenderPersona(this.colaNormal.dequeue(), minuto, i);
                                this.promedioTramite.add(persona.getTramite());
                                //   System.out.println(persona.toStringNormal()); -- > QUITAR COMENTARIO PARA DESPLEGAR
                            }
                        }
                        liberarCaja();
                        minuto++;
                    }
                } else {
                    System.out.println("Cargue primero el número de cajas!");
                }
                break;

            case 4:
                Estadistica estadistica = new Estadistica();
                CajaRegistradora cajaRegistradora = new CajaRegistradora();
                double promedioTramite = estadistica.promedioTramite(this.promedioTramite);
                System.out.println("El promedio de tramite de todas las personas es de: "
                        + promedioTramite + " minutos");
                int cantidadDePersonasAfueraN = this.colaNormal.personasEncola();
                int cantidadDePersonasAfueraP = this.colaDePrioridad.personasEncola();
                System.out.println("Personas que quedaron en la cola normal (sin ser atendidas): "
                        + (cantidadDePersonasAfueraN + cantidadDePersonasAfueraP));
                double promedioTiempoEsperaEnColaN = this.colaNormal.promedioColaTramite(cajaRegistradora);
                double promedioTiempoEsperaEnColaP = this.colaDePrioridad.promedioColaTramite(cajaRegistradora);
                System.out.println("El promedio de las personas que se quedaron en la cola (sin atender) es: " +
                        (promedioTiempoEsperaEnColaN + promedioTiempoEsperaEnColaP) + " minutos");
                break;

            case 5:
                System.out.println("¡Muchas gracias!");
                break;

            default:
                this.presentacion.error();
                break;
        }
    }

    private void meterPersonaNPrioridad(int minuto) {
        for (int i = 0; i < this.estadoPersona.get(minuto).getPrioridad(); i++) {
            this.persona.setTiempoEnCola(0);
            this.persona.setTramite(0);
            this.colaDePrioridad.enqueue(this.persona);
        }
    }

    private Persona AtenderPersonaPrioridad(Persona dequeue, int minuto, int i) {
        CajaRegistradora cajaRegistradora = new CajaRegistradora();

        if (cajaNueva == 1 && i == 0) {
            int minutoTramite = cajaRegistradora.calcularMinutos();
            dequeue.setTramite(minutoTramite + 1);
            this.cajas[i].setTiempoTramite(minutoTramite + 1);
            dequeue.setTiempoEnCola(minuto);
            this.cajas[i].setDisponible(false);
            return dequeue;
        } else {
            int minutoTramite = cajaRegistradora.calcularMinutos();
            dequeue.setTramite(minutoTramite);
            this.cajas[i].setTiempoTramite(minutoTramite);
            dequeue.setTiempoEnCola(minuto);
            this.cajas[i].setDisponible(false);
            return dequeue;

        }
    }

    private void liberarCaja() {
        for (int i = 0; i < this.cajas.length; i++) {
            if (!this.cajas[i].getDisponible()) {
                this.cajas[i].setTiempoTramite(this.cajas[i].getTiempoTramite() - gastoCola);
                if (this.cajas[i].getTiempoTramite() == 0) {
                    this.cajas[i].setDisponible(true);
                }
            }
        }
    }

    private Persona AtenderPersona(Persona dequeue, int minuto, int i) {

        if (cajaNueva == 1 && i == 0) {
            CajaRegistradora cajaRegistradora = new CajaRegistradora();
            int minutoTramite = cajaRegistradora.calcularMinutos();
            dequeue.setTramite(minutoTramite + 1);
            this.cajas[i].setTiempoTramite(minutoTramite + 1);
            dequeue.setTiempoEnCola(minuto);
            this.cajas[i].setDisponible(false);
            return dequeue;
        } else {
            CajaRegistradora cajaRegistradora = new CajaRegistradora();
            int minutoTramite = cajaRegistradora.calcularMinutos();
            dequeue.setTramite(minutoTramite);
            this.cajas[i].setTiempoTramite(minutoTramite);
            dequeue.setTiempoEnCola(minuto);
            this.cajas[i].setDisponible(false);
            return dequeue;
        }
    }

    private void meterPersonaNCola(int minuto) {
        for (int i = 0; i < this.estadoPersona.get(minuto).getNormal(); i++) {
            this.persona.setTiempoEnCola(0);
            this.persona.setTramite(0);
            this.colaNormal.enqueue(this.persona);
        }
    }
}