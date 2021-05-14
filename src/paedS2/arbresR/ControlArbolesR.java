package paedS2.arbresR;

import paedS2.LeerDataset;
import paedS2.arbres.ArbolTesoro;
import paedS2.arbres.Tesoro;

import java.util.Scanner;

public class ControlArbolesR {

    String ruta = "dataset/r-treeXS.paed";

    private ArbolR arbolR;

    public ControlArbolesR() {
        arbolR = LeerDataset.tesoroR(ruta);
    }

    public void menuPrincipal() {

        Scanner scInt = new Scanner(System.in);
        int nodeOrigen;

        System.out.println("\n\tA. Afegir tresor");
        System.out.println("\tB. Eliminar tresor");
        System.out.println("\tC. Visualizar");
        System.out.println("\tD. Cerca de àrea");
        System.out.println("\tE. Cerca per proximitat\n");
        System.out.println("\tF. Tornar enrere");


        Scanner sc = new Scanner(System.in);
        System.out.print("\n\tQuina funcionalitat vols executar?");
        String opc = sc.nextLine();

        switch (opc) {
            case "A":
                opcionA();
                return;

            case "B":
                opcionB();
                break;
            case "C":
                opcionC();
                break;

            case "D":
                opcionD();
                break;

            case "E":
                opcionE();
                break;

            case "F":
                return;

            default:
                System.out.println("Aquesta opció no existeix");
                break;
        }

        menuPrincipal();

    }
    private void opcionA() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEntra el nom del tresor a afegir: ");
        String nom = sc.nextLine();
        System.out.print("Entra la coordenada X de la posicio del tesoro a afegir: ");
        Long corX = Long.parseLong(sc.nextLine());
        System.out.print("Entra la coordenada Y de la posicio del tesoro a afegir: ");
        Long corY = Long.parseLong(sc.nextLine());

        System.out.println("\nEl tresor s'ha afegit correctament al botí.");
    }

    private void opcionB() {
    }

    private void opcionC() {
        arbolR.mostrarArbol();
    }

    private void opcionD() {
    }

    private void opcionE() {
    }
}
