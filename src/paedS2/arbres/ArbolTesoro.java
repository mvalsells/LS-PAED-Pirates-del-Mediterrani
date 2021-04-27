package paedS2.arbres;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ArbolTesoro {

    private Tesoro tesoroOrigen;
    private Tesoro tesoroAux;
    private boolean tesoroTrobat;
    private ArrayList<Tesoro> tesorosRang;

    public ArbolTesoro(Tesoro tesoroOrigen) {
        this.tesoroOrigen = tesoroOrigen;
    }

    public void inserirNodo(Tesoro tesoro) {
        //alturaArbol();
        inserirNodo(tesoro, tesoroOrigen);

    }

    private void inserirNodo(Tesoro tesoro, Tesoro padre) {
        if (padre.getValor() < tesoro.getValor()) {
            if(padre.getHijoMayor() == null) {
                tesoro.setPadre(padre);
                padre.setHijoMayor(tesoro);
            } else {
                inserirNodo(tesoro, padre.getHijoMayor());
            }
        } else if (padre.getValor() > tesoro.getValor()) {
            if(padre.getHijoMenor() == null) {
                tesoro.setPadre(padre);
                padre.setHijoMenor(tesoro);
            } else {
                inserirNodo(tesoro, padre.getHijoMenor());
            }
        }

        rebalance(tesoro);
    }

    public void buscarNodo(String nom) {
        tesoroAux = null;
        tesoroTrobat = false;
        buscarNodo(nom, tesoroOrigen);
    }

    private void buscarNodo(String nom, Tesoro tesoro) {
        if (!tesoroTrobat) {
            if (nom.equals(tesoro.getName())) {
                tesoroAux = tesoro;
                tesoroTrobat = true;
            } else {
                if (tesoro.getHijoMayor() != null) {
                    buscarNodo(nom, tesoro.getHijoMayor());
                }
                if (tesoro.getHijoMenor() != null) {
                    buscarNodo(nom, tesoro.getHijoMenor());
                }
            }
        }
    }

    public void eliminarNodo(String nom) {
        buscarNodo(nom);
        Tesoro padre;
        if (tesoroAux == null) {
            System.out.println("Aquest tresor no existeix");
        } else {
            padre = tesoroAux.getPadre();
            if (padre != null) {
                if (tesoroAux.getHijoMenor() == null && tesoroAux.getHijoMayor() == null) {
                    //Si no te cap fill
                    System.out.println("cap fill");
                    if (padre.getHijoMenor() == tesoroAux) {
                        padre.setHijoMenor(null);
                    } else if (padre.getHijoMayor() == tesoroAux) {
                        padre.setHijoMayor(null);
                    }
                } else if (tesoroAux.getHijoMenor() == null) {
                    //Si hi ha fill major
                    System.out.println("1 fill major");
                    if (padre.getHijoMenor() == tesoroAux) {
                        padre.setHijoMenor(tesoroAux.getHijoMayor());
                    } else if (padre.getHijoMayor() == tesoroAux) {
                        padre.setHijoMayor(tesoroAux.getHijoMayor());
                    }
                } else if (tesoroAux.getHijoMayor() == null) {
                    //Si hi ha fill menor
                    System.out.println("1 fill menor");
                    if (padre.getHijoMenor() == tesoroAux) {
                        padre.setHijoMenor(tesoroAux.getHijoMenor());
                    } else if (padre.getHijoMayor() == tesoroAux) {
                        padre.setHijoMayor(tesoroAux.getHijoMenor());
                    }
                } else {
                    //Te dos fills
                    System.out.println("2 fills");
                    Tesoro tresorDesplacar = tesoroAux.getHijoMayor();
                    while (tresorDesplacar.getHijoMenor() != null) {
                        tresorDesplacar = tresorDesplacar.getHijoMenor();
                    }

                    //S'indica el nou fill al pare del node a eliminar
                    if (padre.getHijoMenor() == tesoroAux) {
                        padre.setHijoMenor(tresorDesplacar);
                    } else if (padre.getHijoMayor() == tesoroAux) {
                        padre.setHijoMayor(tresorDesplacar);
                    }

                    //Es passa al fill menor del eliminar al desplaçar
                    tresorDesplacar.setHijoMenor(tesoroAux.getHijoMenor());

                    //Miro si el node a substituir és fill del a eliminar
                    if (tesoroAux.getHijoMayor() != tresorDesplacar) {
                        //En cas de que no sigui fill actualitzem el pare i el fill major
                        if (tresorDesplacar.getHijoMayor() != null) {
                            tresorDesplacar.getPadre().setHijoMenor(tresorDesplacar.getHijoMayor());
                            tresorDesplacar.getHijoMayor().setPadre(tresorDesplacar.getPadre());
                        } else {
                            tresorDesplacar.getPadre().setHijoMenor(null);
                        }

                        tresorDesplacar.setHijoMayor(tesoroAux.getHijoMayor());

                    }
                }
            } else {
                //Sin padre
                if (tesoroAux.getHijoMenor() == null && tesoroAux.getHijoMayor() == null) {
                    //Si no te cap fill
                    tesoroOrigen = null;

                } else if (tesoroAux.getHijoMenor() == null) {
                    //Si hi ha fill major
                    tesoroOrigen = tesoroAux.getHijoMayor();
                    tesoroOrigen.setPadre(null);

                } else if (tesoroAux.getHijoMayor() == null) {
                    //Si hi ha fill menor
                    tesoroOrigen = tesoroAux.getHijoMenor();
                    tesoroOrigen.setPadre(null);

                } else {
                    //Si te dos fills
                    Tesoro tresorDesplacar = tesoroAux.getHijoMayor();
                    while (tresorDesplacar.getHijoMenor() != null) {
                        tresorDesplacar = tresorDesplacar.getHijoMenor();
                    }

                    tesoroOrigen = tresorDesplacar;

                    //Es passa al fill menor del eliminar al desplaçar
                    tresorDesplacar.setHijoMenor(tesoroAux.getHijoMenor());

                    //Miro si el node a substituir és fill del a eliminar
                    if (tesoroAux.getHijoMayor() != tresorDesplacar) {
                        //En cas de que no sigui fill actualitzem el pare i el fill major
                        if (tresorDesplacar.getHijoMayor() != null) {
                            tresorDesplacar.getPadre().setHijoMenor(tresorDesplacar.getHijoMayor());
                            tresorDesplacar.getHijoMayor().setPadre(tresorDesplacar.getPadre());
                        } else {
                            tresorDesplacar.getPadre().setHijoMenor(null);
                        }

                        tresorDesplacar.setHijoMayor(tesoroAux.getHijoMayor());

                    }

                    tesoroOrigen.setPadre(null);
                }

            }

        }
    }

    public void inOrdre() {
        inOrdre(tesoroOrigen);
    }

    private void inOrdre (Tesoro tesoro) {
        if (tesoro.getHijoMenor() != null) {
            inOrdre(tesoro.getHijoMenor());
        }
        System.out.println("\n\t" + tesoro.toString());
        if (tesoro.getHijoMayor() != null) {
            inOrdre(tesoro.getHijoMayor());
        }
    }

    public void preOrdre() {preOrdre(tesoroOrigen);}

    private void preOrdre(Tesoro tesoro) {
        System.out.println("\n\t" + tesoro.toString());
        if (tesoro.getHijoMenor() != null) {
            preOrdre(tesoro.getHijoMenor());
        }
        if (tesoro.getHijoMayor() != null) {
            preOrdre(tesoro.getHijoMayor());
        }
    }

    public void postOrdre() {postOrdre(tesoroOrigen);}

    private void postOrdre(Tesoro tesoro) {
        if (tesoro.getHijoMenor() != null) {
            postOrdre(tesoro.getHijoMenor());
        }
        if (tesoro.getHijoMayor() != null) {
            postOrdre(tesoro.getHijoMayor());
        }
        System.out.println("\n\t" + tesoro.toString());
    }

    public void nivells() {nivells(tesoroOrigen);}

    private void nivells(Tesoro tesoro) {
        Queue<Tesoro> cua = new LinkedList<>();
        cua.offer(tesoro);

        while (!cua.isEmpty()) {
            Tesoro t = cua.poll();
            System.out.println("\t" + t.toString());
            if (t.getHijoMenor() != null) {
                cua.offer(t.getHijoMenor());
            }
            if (t.getHijoMayor() != null) {
                cua.offer(t.getHijoMayor());
            }
        }
    }


    public String cercaValorExacte (long valor) {
        tesoroAux = null;
        tesoroTrobat = false;
        cercaValorExacte(valor, tesoroOrigen);
        if (tesoroTrobat) {
            return tesoroAux.getName();
        } else {
            return "Tresor no trobat";
        }
    }

    private void cercaValorExacte(long valor, Tesoro tesoro) {
        if (!tesoroTrobat) {
            if (tesoro.getValor() == valor) {
                tesoroTrobat = true;
                tesoroAux = tesoro;
            } else {
                if (tesoro.getValor() < valor) {
                    cercaValorExacte(valor, tesoro.getHijoMayor());
                } else {
                    cercaValorExacte(valor, tesoro.getHijoMenor());
                }
            }
        }
    }

    public ArrayList<Tesoro> cercaValorRang (long valorMin, long valorMax) {
        tesorosRang = new ArrayList<>();
        cercaValorRang(valorMin, valorMax,tesoroOrigen);
        return tesorosRang;
    }

    private void cercaValorRang(long valorMin, long valorMax,Tesoro tesoro) {
        if(tesoro != null) {
            if (tesoro.getValor() >= valorMin && tesoro.getValor() <= valorMax) {
                tesorosRang.add(tesoro);
                cercaValorRang(valorMin, valorMax, tesoro.getHijoMenor());
                cercaValorRang(valorMin, valorMax, tesoro.getHijoMayor());
            } else {
                if (tesoro.getValor() > valorMax) {
                    cercaValorRang(valorMin, valorMax, tesoro.getHijoMenor());
                } else if (tesoro.getValor() < valorMin) {
                    cercaValorRang(valorMin, valorMax, tesoro.getHijoMenor());
                }
            }
        }
    }

    public void alturaArbol() {

        alturaArbol(tesoroOrigen);
        //balanceigArbol(tesoroOrigen);
    }

    private void alturaArbol(Tesoro tesoro){
        if (tesoro != null){
            alturaArbol(tesoro.getHijoMenor());
            alturaArbol(tesoro.getHijoMayor());

            if (tesoro.getHijoMenor() == null && tesoro.getHijoMayor() == null){
                tesoro.setAltura(0);
            }else if (tesoro.getHijoMenor() == null){
                tesoro.setAltura(tesoro.getHijoMayor().getAltura() + 1);
            }else if (tesoro.getHijoMayor() == null){
                tesoro.setAltura(tesoro.getHijoMenor().getAltura() + 1);
            }else{
                if (tesoro.getHijoMayor().getAltura() < tesoro.getHijoMenor().getAltura()) {
                    tesoro.setAltura(tesoro.getHijoMenor().getAltura() + 1);
                } else {
                    tesoro.setAltura(tesoro.getHijoMayor().getAltura() + 1);
                }
            }
        }
    }

    private void balanceigArbol(Tesoro tesoro){
        if (tesoro != null){
            balanceigArbol(tesoro.getHijoMenor());
            balanceigArbol(tesoro.getHijoMayor());

            if (tesoro.getHijoMenor() == null && tesoro.getHijoMayor() == null){
                tesoro.setFactorBalanceig(0);
            }else if (tesoro.getHijoMenor() == null){
                tesoro.setFactorBalanceig(-tesoro.getHijoMayor().getAltura() - 1);
            }else if (tesoro.getHijoMayor() == null){
                tesoro.setFactorBalanceig(tesoro.getHijoMenor().getAltura() + 1);
            }else{
                tesoro.setFactorBalanceig(tesoro.getHijoMenor().getAltura() - tesoro.getHijoMayor().getAltura());
            }
        }
    }

    public void AVL(){
        tesoroOrigen = rebalance(tesoroOrigen);

    }
    private void updateHeight(Tesoro n) {
        n.setAltura(1 + Math.max(height(n.getHijoMenor()), height(n.getHijoMayor())));
    }

    private int height(Tesoro n) {
        return n == null ? -1 : n.getAltura();
    }

    private int getBalance(Tesoro n) {
        return (n == null) ? 0 : height(n.getHijoMayor()) - height(n.getHijoMenor());
    }

    private Tesoro rebalance(Tesoro z) {
        updateHeight(z);
        int balance = getBalance(z);
        //while (balance < -1 || balance > 1) {
        //    updateHeight(z);
        //    balance = getBalance(z);
            if (balance > 1) {
                if (height(z.getHijoMayor().getHijoMayor()) > height(z.getHijoMayor().getHijoMenor())) {
                    z = rotateLeft(z);
                } else {
                    z.setHijoMayor(rotateRight(z.getHijoMayor()));
                    z = rotateLeft(z);
                }
            } else if (balance < -1) {
                if (height(z.getHijoMenor().getHijoMenor()) > height(z.getHijoMenor().getHijoMayor()))
                    z = rotateRight(z);
                else {
                    z.setHijoMenor(rotateLeft(z.getHijoMenor()));
                    z = rotateRight(z);
                }
            }
        //}
        return z;
    }

    private Tesoro rotateRight(Tesoro y) {
        Tesoro x = y.getHijoMenor();
        Tesoro z = x.getHijoMayor();
        x.setHijoMayor(y);
        y.setHijoMenor(z);
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Tesoro rotateLeft(Tesoro y) {
        Tesoro x = y.getHijoMayor();
        Tesoro z = x.getHijoMenor();
        x.setHijoMenor(y);
        y.setHijoMayor(z);
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    public void AVL(Tesoro tesoro){
        Tesoro temporal= null;
        while (tesoro.getFactorBalanceig() < -1 || tesoro.getFactorBalanceig() > 1) {

            if (tesoro.getHijoMenor() != null){
                if (tesoro.getFactorBalanceig() > 1 && tesoro.getValor() < valor(tesoro.getHijoMenor())) {
                    tesoro = rightRotate(tesoro);
                }
                if (tesoro.getFactorBalanceig() > 1 && tesoro.getValor() > valor(tesoro.getHijoMenor())) {
                    tesoro.setHijoMenor(leftRotate(tesoro.getHijoMenor()));
                    tesoro = rightRotate(tesoro);
                }
            }

            if (tesoro.getHijoMayor() != null){
                if (tesoro.getFactorBalanceig() < -1 && tesoro.getValor() > valor(tesoro.getHijoMayor())) {
                    tesoro = leftRotate(tesoro);
                }
                if (tesoro.getFactorBalanceig() < -1 && tesoro.getValor() < valor(tesoro.getHijoMayor())) {
                    tesoro.setHijoMayor(rightRotate(tesoro.getHijoMayor()));
                    tesoro = leftRotate(tesoro);
                }
            }

            if (tesoro.equals(temporal)){
                break;
            }else{
                temporal = tesoro;
            }

            /*if (tesoro.getFactorBalanceig() > 1 && tesoro.getValor() < valor(tesoro.getHijoMenor())) {
                tesoro = rightRotate(tesoro);
            } else if (tesoro.getFactorBalanceig() < -1 && tesoro.getValor() > valor(tesoro.getHijoMayor())) {
                tesoro = leftRotate(tesoro);
            } else if (tesoro.getFactorBalanceig() > 1 && tesoro.getValor() > valor(tesoro.getHijoMenor())) {
                tesoro.setHijoMenor(leftRotate(tesoro.getHijoMenor()));
                tesoro = rightRotate(tesoro);
            } else if (tesoro.getFactorBalanceig() < -1 && tesoro.getValor() < valor(tesoro.getHijoMayor())) {
                tesoro.setHijoMayor(rightRotate(tesoro.getHijoMayor()));
                tesoro = leftRotate(tesoro);
            }*/
        }

        if (tesoro.getHijoMenor() != null){
            AVL(tesoro.getHijoMenor());
        }
        if (tesoro.getHijoMayor() != null){
            AVL(tesoro.getHijoMayor());
        }


    }

    int height2(Tesoro N) {
        if (N == null)
            return 0;

        return N.getAltura();
    }

    long valor(Tesoro N) {
        if (N == null)
            return 0;

        return N.getValor();
    }

    private Tesoro rightRotate(Tesoro y) {
        Tesoro x = y.getHijoMenor();
        Tesoro T2 = x.getHijoMayor();

        // Perform rotation
        x.setHijoMayor(y);
        y.setHijoMenor(T2);

        // Update heights
        y.setAltura(Math.max(height(y.getHijoMenor()), height(y.getHijoMayor())) + 1);
        x.setAltura( Math.max(height(x.getHijoMenor()), height(x.getHijoMayor())) + 1);

        // Return new root
        return x;
    }

    private Tesoro leftRotate(Tesoro x) {
        Tesoro y = x.getHijoMayor();
        Tesoro T2 = y.getHijoMenor();

        // Perform rotation
        y.setHijoMenor(x);
        x.setHijoMayor(T2);

        //  Update heights
        x.setAltura(Math.max(height(x.getHijoMenor()), height(x.getHijoMayor())) + 1);
        y.setAltura(Math.max(height(y.getHijoMenor()), height(y.getHijoMayor())) + 1);

        // Return new root
        return y;
    }

    public void dibujarArbol(){
        Lienzo lienzo = new Lienzo();
        lienzo.setObjArbol(tesoroOrigen);

        JFrame ventana = new JFrame();

        ventana.setDefaultCloseOperation(3);

        ventana.setLayout(new BorderLayout());
        ventana.add(new JScrollPane(lienzo));

        ventana.setSize(1000, 1000);
        ventana.setVisible(true);
    }



}
