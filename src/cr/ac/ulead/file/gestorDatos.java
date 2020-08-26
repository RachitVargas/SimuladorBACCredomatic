package cr.ac.ulead.file;

import cr.ac.ulead.objetos.EstadoPersona;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class gestorDatos {

    public void cargarDatos(ArrayList<EstadoPersona> estadoPersona) throws FileNotFoundException {

        Scanner fileReader = new Scanner(new File("C:\\Users\\User\\IdeaProjects\\LastProject" +
                "\\src\\cr\\ac\\ulead\\file\\simulacion.csv"));

        System.out.println("Cargando simulación...");
        while (fileReader.hasNextLine()) {
            String currentLine = fileReader.nextLine();
            String datos[] = currentLine.split(",");
            EstadoPersona estadoPersona1 = new EstadoPersona();
            estadoPersona1.setNormal(Integer.parseInt(datos[0]));
            estadoPersona1.setPrioridad(Integer.parseInt(datos[1]));

            estadoPersona.add(estadoPersona1);
        }
        System.out.println("Simulación cargada con exito");
        fileReader.close();
    }
}