package paedS2.taules;

import paedS2.LeerDataset;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ControlTaules {

    private TaulaNom taulaNom;

    private String ruta = "dataset/tablesM.paed";
    Scanner sc;

    public ControlTaules(){

        taulaNom = LeerDataset.taulaNom(ruta);
        sc  = new Scanner(System.in);
    }


    private void opcionA(){
        System.out.print("Entra el nom del pirata a afegir: ");
        String nom = sc.nextLine();
        System.out.print("Entra l'edat del pirata a afegir: ");
        int edat = Integer.parseInt(sc.nextLine());
        System.out.print("Entra el rol del pirata a afegir: ");
        String rol = sc.nextLine();
        taulaNom.afegirPirata(new Pirata(nom, edat,rol));
        System.out.println("\nEl pirata s'ha afegit correctament a la tripulació.");
    }

    private void opcionB(){
        System.out.print("Entra el nom del pirata a eliminar: ");
        String nom = sc.nextLine();
        taulaNom.eliminarPirata(nom);
        System.out.println("\nEl pirata s'ha eliminat correctament de la tripulació. F.");
    }
    private void opcionC(){
        System.out.print("Entra el nom del pirata a consultar: ");
        String nom = sc.nextLine();
        Pirata p = taulaNom.consultarPirata(nom);
        if (p==null){
            System.out.println("\nNo existeix el pirata");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("\n\tNom: ");
            sb.append(p.getNom());
            sb.append("\n\tEdat: ");
            sb.append(p.getEdat());
            sb.append("\n\tRol: ");
            sb.append(p.getRol());
            System.out.println(sb);
        }
    }
    private void opcionD(){
        System.out.println("\nGenerant histograma...");
        int[] edats = taulaNom.getEdats();
        //Visual
        new JFHistograma(edats);
        //Consola
        /*for (int i = 0; i < edats.length; i++) {
            if (edats[i]!=0) {
                System.out.println("\t Hi ha " + edats[i] + " pirates amb " + i + " anys");
            }
        }*/
    }

    public void menuPrincipal() {


        System.out.println("\n\tA. Afegir pirata");
        System.out.println("\tB. Eliminar pirata");
        System.out.println("\tC. Consultar pirata");
        System.out.println("\tD. Histograma per edats\n");
        System.out.println("\tE. Tornar enrere");


        System.out.print("\n\tEscull una opció: ");
        String opc = sc.nextLine();

        switch (opc) {
            case "A":
                opcionA();
                break;

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
                return;

            default:
                System.out.println("Aquesta opció no existeix");
                break;
        }

        menuPrincipal();

    }
}
