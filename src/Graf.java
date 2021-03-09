import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Graf {
    private static ArrayList<Vertice> vertices;
    private static ArrayList<Vertice> interest;
    private static ArrayList<Vertice> danger;
    public static void init(){
        vertices = LeerDataset.grafs("dataset/graphXXS.paed");
    }

    public static void opcionA(int origen){
        Vertice nodo = null;
        interest = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            vertices.get(i).setVisitado(false);
            if (vertices.get(i).getId()==origen) {
                nodo = vertices.get(i);
            }
        }

        if (nodo==null){
            //TODO Mirar al hacer el menu
            System.out.println("Nodo no existente");
        } else {
            dfs(nodo);

            for (Vertice v:vertices){
                if (!v.isVisitado()){
                    dfs(v);
                }
            }

            System.out.println("\nAmb DFS s'han trobat els següents llocs d'interès: \n");

            for(Vertice v:interest){
                System.out.println("\t" + v.getNombre() + " - " + v.getTipo());
            }
        }
    }

    private static void dfs(Vertice nodo){
        nodo.setVisitado(true);
        float[][] relaciones = Vertice.getRelaciones();
        if (nodo.getTipo().equals("INTEREST")){
            interest.add(nodo);
        }
        for (int i = 0; i < vertices.size(); i++) {
            Vertice adj = vertices.get(i);
            float dist = relaciones[nodo.getPosicion()][i];
            if (dist!=0.0){
                if (!adj.isVisitado()){
                    dfs(adj);
                }
            }
        }
    }

    public static void opcionB(int origen){
        Vertice nodo = null;
        danger = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            vertices.get(i).setVisitado(false);
            if (vertices.get(i).getId()==origen) {
                nodo = vertices.get(i);
            }
        }
        if (nodo==null){
            //TODO Mirar al hacer el menu
            System.out.println("Nodo no existente");
        } else {
            bfs(nodo);

            for (Vertice v:vertices){
                if (!v.isVisitado()){
                    bfs(v);
                }
            }

            System.out.println("\nAmb BFS s'han trobat els següents llocs perillosos: \n");
            for(Vertice v:danger){
                System.out.println("\t" + v.getNombre() + " - " + v.getTipo());
            }
        }
    }

    private static void bfs(Vertice nodo) {
        PriorityQueue<Vertice> cua = new PriorityQueue<>();
        cua.offer(nodo);
        nodo.setVisitado(true);
        while (!cua.isEmpty()){
            Vertice siguiente = cua.poll();
            //Logica
            if (siguiente.getTipo().equals("DANGER")){
                danger.add(siguiente);
            }
            float[][] relaciones = Vertice.getRelaciones();
            for (int i = 0; i < vertices.size(); i++) {
                Vertice adj = vertices.get(i);
                float dist = relaciones[siguiente.getPosicion()][i];
                if (dist!=0.0){
                    if (!adj.isVisitado()){
                        cua.offer(adj);
                        adj.setVisitado(true);
                    }
                }
            }
        }
    }

    private static void opcionC () {
        System.out.println("Obtenint el MST...");
        mst();
    }

    private static void mst() {
        Vertice parents[] = new Vertice[vertices.size()];
        float key[] = new float[vertices.size()];
        float relaciones[][] = Vertice.getRelaciones();

        for (int i = 0; i < vertices.size(); i++) {
            vertices.get(i).setVisitado(false);
            key[i] = Float.MAX_VALUE;
        }

        key[0] = 0.0f;
        parents[0] = null;

        for (int i = 0; i < vertices.size() - 1; i++) {
            float minDist = Float.MAX_VALUE;
            Vertice vertMin = null;

            for (int j = 0; j < vertices.size(); j++) {
                if(!vertices.get(j).isVisitado() && key[j] < minDist) {
                    minDist = key[j];
                    vertMin = vertices.get(j);

                }
            }

            vertMin.setVisitado(true);

            for (int j = 0; j < vertices.size(); j++) {
                float rel = relaciones[vertMin.getPosicion()][vertices.get(j).getPosicion()];
                if(rel != 0 && !vertices.get(j).isVisitado() && rel < key[j]) {
                    parents[j] = vertMin;
                    key[j] = rel;
                }
            }

        }

        for (int i = 1; i < vertices.size(); i++) {
            System.out.println(parents[i].toString() + "-----" + relaciones[i][parents[i].getPosicion()]);
        }


    }

    private static void opcionD() {
        Scanner sc = new Scanner(System.in);
        int nodoOrigen, nodoDestino;

        System.out.print("Entra identificador nodo origen: ");
        nodoOrigen = sc.nextInt();

        System.out.print("Entra identificador nodo destino: ");
        nodoDestino = sc.nextInt();

        Vertice vertOrigen = null, vertDestino = null;
        for (Vertice v : vertices){
            if (v.getId() == nodoOrigen) {
                vertOrigen = v;
            } else if (v.getId() == nodoDestino){
                vertDestino = v;
            }
        }

        if (vertOrigen != null && vertDestino != null){
            dijkstra(vertOrigen,vertDestino);
        }


    }

    private static void dijkstra(Vertice nodoOrigen, Vertice nodoDestino) {
        float dist[] = new float[vertices.size()];
        float graf[][] = Vertice.getRelaciones();
        for (int i = 0; i < vertices.size(); i++) {
            dist[i] = Float.MAX_VALUE;
            vertices.get(i).setVisitado(false);
        }
        dist[nodoOrigen.getPosicion()] = 0;
        nodoOrigen.setVisitado(true);
        Vertice actual = nodoOrigen;

        while (!nodoDestino.isVisitado()) {
            for (Vertice adj : vertices){
                float peso = graf[actual.getPosicion()][adj.getPosicion()];
                if (peso != 0 && !adj.isVisitado()){
                    float nova = dist[actual.getPosicion()] + peso;
                    if (dist[adj.getPosicion()] > nova){
                        dist[adj.getPosicion()] = nova;
                        //camins que ara s'esvaeixen
                    }
                }
            }
            actual.setVisitado(true);


            float minDist = Float.MAX_VALUE;
            Vertice vertMin = null;

            for (int j = 0; j < vertices.size(); j++) {
                if(!vertices.get(j).isVisitado() && dist[j] < minDist) {
                    minDist = dist[j];
                    vertMin = vertices.get(j);
                }
            }
            actual = vertMin;


        }

        for (int i = 0; i < dist.length; i++) {
            System.out.print("dist: " + dist[i]);
        }
    }

    public static void menuPrincipal() {

        Scanner scInt = new Scanner(System.in);
        int nodeOrigen;

        System.out.println("\n\tA. Cercar llocs d'interès (DFS)");
        System.out.println("\tB. Cercar llocs perillosos (BFS)");
        System.out.println("\tC. Mostrar la Carta Nàutica Universal (MST)");
        System.out.println("\tD. Trobar la ruta òptima (Dijkstra) \n");
        System.out.println("\tE. Tornar enrere");


        Scanner sc = new Scanner(System.in);
        System.out.print("\n\tEscull una opció:");
        String opc = sc.nextLine();

        switch (opc) {
            case "A":

                System.out.print("\nEntra l'identificador del node origen: ");
                nodeOrigen = scInt.nextInt();

                opcionA(nodeOrigen);

                return;

            case "B":

                System.out.print("\nEntra l'identificador del node origen: ");
                nodeOrigen = scInt.nextInt();

                opcionB(nodeOrigen);

                return;

            case "C":

                opcionC();
                return;

            case "D":
                opcionD();
                return;

            case "E":
                return;

            default:
                System.out.println("Aquesta opció no existeix");
                break;
        }

        menuPrincipal();

    }
}
