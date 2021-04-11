package paedS2.arbres;

import paedS2.LeerDataset;

import java.util.Scanner;

public class ControlArbolesBinarios {

    String ruta = "dataset/treeXXS.paed";

    private ArbolTesoro arbolTesoro;

    public ControlArbolesBinarios() {
        arbolTesoro = LeerDataset.tesoro(ruta);
    }
    private void opcionA (){
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEntra el nom del tresor a afegir: ");
        String nom = sc.nextLine();
        System.out.print("Entra el valor del tresor a afegir: ");
        Long valor = Long.parseLong(sc.nextLine());
        arbolTesoro.inserirNodo(new Tesoro(nom, valor));
        System.out.println("\nEl tresor s'ha afegit correctament al botí.");
    }

    private void opcionB (){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEntra el nom del tresor a eliminar: ");
        String nom = sc.nextLine();
        arbolTesoro.buscarNodo(nom);
    }

    private void opcionC () {

        System.out.println("\n\t I. Preordre");
        System.out.println("\t II. Postordre");
        System.out.println("\t III. Inordre");
        System.out.println("\t IV. Per nivells");

        Scanner sc = new Scanner(System.in);
        System.out.print("\tQuin recorregut vols fer servir? ");
        String recorregut = sc.nextLine();
        switch (recorregut) {
            case "I":
                break;
            case "II":
                break;
            case "III":
                arbolTesoro.inOrdre();
                break;
            case "IV":
                break;

            default:
                System.out.println("Aquest cas no existeix");
        }
    }






    public void menuPrincipal() {

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
                opcionA();
                return;

            case "B":
                opcionB();
                return;

            case "C":
                opcionC();
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
