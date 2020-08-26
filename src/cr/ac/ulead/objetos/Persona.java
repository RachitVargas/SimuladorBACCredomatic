package cr.ac.ulead.objetos;

public class Persona {

    private int tramite;
    private int tiempoEnCola;

    public int getTramite() {
        return tramite;
    }

    public void setTramite(int tramite) {
        this.tramite = tramite;
    }

    public int getTiempoEnCola() {
        return tiempoEnCola;
    }

    public void setTiempoEnCola(int tiempoEnCola) {
        this.tiempoEnCola = tiempoEnCola;
    }

    public String toStringNormal() {
        return "Persona Normal{" +
                "tramite=" + tramite +
                ", tiempoEnCola=" + tiempoEnCola +
                '}';
    }
    public String toStringPrioridad() {
        return "Persona Prioridad{" +
                "tramite=" + tramite +
                ", tiempoEnCola=" + tiempoEnCola +
                '}';
    }
}
