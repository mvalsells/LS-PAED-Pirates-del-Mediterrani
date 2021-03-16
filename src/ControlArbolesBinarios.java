import java.util.Scanner;

public class ControlArbolesBinarios {

    private static void opcionA (){

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

                return;

            case "B":


                return;

            case "C":


                return;

            case "D":

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
