import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LeerDataset {
    public static ArrayList<Vertice> grafs (String ruta) {
        ArrayList<Vertice> arrayV = new ArrayList<>();
        try {
            FileReader fr = new FileReader(ruta);
            BufferedReader br = new BufferedReader(fr);
            int numVert = Integer.parseInt(br.readLine());

            for (int i =0; i<numVert; i++){
                String linia = br.readLine();
                String[] split = linia.split(",");
                int id = Integer.parseInt(split[0]);
                Vertice v = new Vertice(id, split[1],split[2]);
                arrayV.add(v);
            }

            Vertice.initRelaciones(numVert);
            int numRel = Integer.parseInt(br.readLine());

            for (int i = 0; i < numRel; i++) {
                String linia = br.readLine();
                String[] split = linia.split(",");
                int idOrigen = Integer.parseInt(split[0]);
                int idDestinacion = Integer.parseInt(split[1]);
                float distancia = Float.parseFloat(split[2]);

                int posOrigen=0;
                int posDestination=0;
                boolean origen=false;
                boolean destination=false;
                for (Vertice v: arrayV){
                    if (v.getId() == idOrigen) {
                        posOrigen=v.getPosicion();
                        origen = true;
                    } else if (v.getId() == idDestinacion){
                        posDestination=v.getPosicion();
                        destination=true;
                    } else if ( destination && origen){
                        break;
                    }
                }

                Vertice.anadirRelacion(posOrigen,posDestination,distancia);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayV;
    }
}
