package paedS2.taules;

import paedS2.LeerDataset;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ControlTaules {

    private TaulaNom taulaNom;

    private String ruta = "dataset/tablesXS.paed";
    private Scanner sc;
    private long initialReadTime;
    public ControlTaules(){
        initialReadTime = System.currentTimeMillis();
        taulaNom = LeerDataset.taulaNom(ruta);
        initialReadTime = System.currentTimeMillis()-initialReadTime;
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
        System.out.println("\nEl pirata s'ha afegit correctament a la tripulaci√≥.");
    }

    private void opcionB(){
        System.out.print("Entra el nom del pirata a eliminar: ");
        String nom = sc.nextLine();
        taulaNom.eliminarPirata(nom);
        System.out.println("\nEl pirata s'ha eliminat correctament de la tripulaci√≥. F.");
    }
    private void opcionC(){
        System.out.print("Entra el nom del pirata a consultar: ");
        String nom = sc.nextLine();
        long tmpTemps = System.currentTimeMillis();
        Pirata p = taulaNom.consultarPirata(nom);
        tmpTemps = System.currentTimeMillis()-tmpTemps;

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

        System.out.println("S'ha tardat tants " + tmpTemps + " millis en consultar el pirata");
    }
    private void opcionD(){
        System.out.println("\nGenerant histograma...");
        int[] edats = taulaNom.getEdats();
        //Visual
        long tmpTemps = System.currentTimeMillis();
        new JFHistograma(edats);
        tmpTemps = System.currentTimeMillis()-tmpTemps;

        System.out.println("S'ha tardat tants " + tmpTemps + " millis en generar el histograma");
        //Consola
        /*for (int i = 0; i < edats.length; i++) {
            if (edats[i]!=0) {
                System.out.println("\t Hi ha " + edats[i] + " pirates amb " + i + " anys");
            }
        }*/
    }

    public void menuPrincipal() {

        System.out.println("\n\tHe taradat " + initialReadTime + " millis a llegir el dataset");
        System.out.println("\n\tA. Afegir pirata");
        System.out.println("\tB. Eliminar pirata");
        System.out.println("\tC. Consultar pirata");
        System.out.println("\tD. Histograma per edats\n");
        System.out.println("\tE. Tornar enrere");


        System.out.print("\n\tEscull una opci√≥: ");
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
                System.out.println("Aquesta opci√≥ no existeix");
                break;
        }

        menuPrincipal();

    }
}
