package cr.ac.ulead.objetos;

public class CajaRegistradora {

    private boolean disponible = true;
    private int tiempoTramite;

    public int getTiempoTramite() {
        return tiempoTramite;
    }

    public void setTiempoTramite(int tiempoTramite) {
        this.tiempoTramite = tiempoTramite;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int calcularMinutos() {

        int minuto = 0;
        double random = calcularRandom();

        if (random >= 0 && random < 0.3) {
            minuto = 1;
        } else if (random >= 0.3 && random < 0.4) {
            minuto = 2;
        } else if (random >= 0.4 && random < 0.6) {
            minuto = 3;
        } else if (random >= 0.6 && random < 0.8) {
            minuto = 5;
        } else if (random >= 0.8 && random < 0.9) {
            minuto = 8;
        } else if (random >= 0.9 && random < 0.95) {
            minuto = 13;
        } else if (random >= 0.95) {
            minuto = 13 + (int) (13 * Math.random());
        }

        return minuto;
    }

    public double calcularRandom() {
        return Math.random();
    }

}
