package paedS2.arbres;

import paedS2.LeerDataset;

import java.util.Scanner;

public class ControlArbolesBinarios {

    String ruta = "dataset/treeXXS.paed";

    private ArbolTesoro arbolTesoro;

    public ControlArbolesBinarios() {
        arbolTesoro = LeerDataset.tesoro(ruta);
    }
    private static void opcionA (){

    }























    public static void menuPrincipal() {

        Scanner scInt = new Scanner(System.in);
        int nodeOrigen;

        System.out.println("\n\tA. Afegir tresor");
        System.out.println("\tB. Eliminar tresor");
        System.out.println("\tC. Llistar botí");
        System.out.println("\tD. Cerca per valor (exacte)");
        System.out.println("\tE. Cerca valor (rang)\n");
        System.out.println("\tF. Tornar enrere");


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

            case "F":
                return;

            default:
                System.out.println("Aquesta opció no existeix");
                break;
        }

        menuPrincipal();

    }
}