package paedS2.arbres;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class AVLTree {

    private Tesoro root;
    private Tesoro tesoroAux;
    private boolean tesoroTrobat;
    private ArrayList<Tesoro> tesorosRang;

    public Tesoro getRoot() {
        return root;
    }

    public AVLTree(Tesoro root){
        this.root=root;
    }

    void updateHeight(Tesoro n) {
        n.setAltura(1 + max(height(n.getHijoMenor()), height(n.getHijoMayor())));
    }

    int height(Tesoro N) {
        if (N == null)
            return 0;

        return N.getAltura();
    }

    int getBalance(Tesoro n) {
        return (n == null) ? 0 : height(n.getHijoMenor()) - height(n.getHijoMayor());
    }
    private Tesoro rotateRight(Tesoro tesoro) {
        Tesoro x = tesoro.getHijoMenor();
        Tesoro z = x.getHijoMayor();

        x.setHijoMayor(tesoro);
        tesoro.setHijoMenor(z);

        updateHeight(tesoro);
        updateHeight(x);
        return x;
    }

    private Tesoro rotateLeft(Tesoro tesoro) {
        Tesoro x = tesoro.getHijoMayor();
        Tesoro z = x.getHijoMenor();

        x.setHijoMenor(tesoro);
        tesoro.setHijoMayor(z);

        updateHeight(tesoro);
        updateHeight(x);
        return x;
    }


    Tesoro rebalance(Tesoro z) {
        updateHeight(z);
        int balance = getBalance(z);
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
        return z;
    }
/*
    public Tesoro insert(Tesoro nodePadre, Tesoro inserir) {
        if (nodePadre == null) {
            return inserir;
        } else if (nodePadre.getValor() > inserir.getValor()) {
            nodePadre.setHijoMenor(insert(nodePadre.getHijoMenor(), inserir));
        } else if (nodePadre.getValor() < inserir.getValor()) {
            nodePadre.setHijoMayor(insert(nodePadre.getHijoMayor(), inserir));
        } else {
            throw new RuntimeException("duplicate Key!");
        }
        return rebalance(nodePadre);
    }

 */
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    public void insert(long valor, String nombre) {
        root = insert(root, valor, nombre);
    }

    public Tesoro insert(Tesoro tesoro, long key, String nombre){
        if (tesoro == null)
            return (new Tesoro(nombre, key));

        if (key < tesoro.getValor())
            tesoro.setHijoMenor(insert(tesoro.getHijoMenor(), key, nombre));
        else if (key > tesoro.getValor())
            tesoro.setHijoMayor(insert(tesoro.getHijoMayor(), key, nombre));
        else // Duplicate
            return tesoro;

        tesoro.setAltura(1 + max(height(tesoro.getHijoMenor()), height(tesoro.getHijoMayor())));

        int balance = getBalance(tesoro);

        if (balance > 1 && key < tesoro.getHijoMenor().getValor())
            return rotateRight(tesoro);

        if (balance < -1 && key > tesoro.getHijoMayor().getValor())
            return rotateLeft(tesoro);

        if (balance > 1 && key > tesoro.getHijoMenor().getValor()) {
            tesoro.setHijoMenor(rotateLeft(tesoro.getHijoMenor()));
            return rotateRight(tesoro);
        }

        if (balance < -1 && key < tesoro.getHijoMayor().getValor()) {
            tesoro.setHijoMayor(rotateRight(tesoro.getHijoMayor()));
            return rotateLeft(tesoro);
        }

        return tesoro;
    }

    public void buscarNodo(String nom) {
        tesoroAux = null;
        tesoroTrobat = false;
        buscarNodo(nom, root);
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
/*
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
                    root = null;

                } else if (tesoroAux.getHijoMenor() == null) {
                    //Si hi ha fill major
                    root = tesoroAux.getHijoMayor();
                    root.setPadre(null);

                } else if (tesoroAux.getHijoMayor() == null) {
                    //Si hi ha fill menor
                    root = tesoroAux.getHijoMenor();
                    root.setPadre(null);

                } else {
                    //Si te dos fills
                    Tesoro tresorDesplacar = tesoroAux.getHijoMayor();
                    while (tresorDesplacar.getHijoMenor() != null) {
                        tresorDesplacar = tresorDesplacar.getHijoMenor();
                    }

                    root = tresorDesplacar;

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

                    root.setPadre(null);
                }

            }

        }
    }
*/

    public void eliminarTesoro(String s){
        buscarNodo(s);
        System.out.println("Tesoro: " + tesoroAux.toString());
        eliminarTesoro(root, tesoroAux);
    }

    public Tesoro eliminarTesoro(Tesoro tesoro, Tesoro tesoroEliminado){
        if (tesoro == null)
            return tesoro;


        //Esto de aqui es para encontrar la posicion del tesoro eliminado en relacion a tesoro, esto se hace sobre
        // todo para cuadno tenemos dos hijos en el tesoro que queremos eliminar
        if (tesoroEliminado.getValor() < tesoro.getValor())
            tesoro.setHijoMenor(eliminarTesoro(tesoro.getHijoMenor(), tesoroEliminado));
        else if (tesoroEliminado.getValor() > tesoro.getValor())
            tesoro.setHijoMayor(eliminarTesoro(tesoro.getHijoMayor(), tesoroEliminado));
        else { //Cuando tesoro y tesoroEliminado son lo mismo
            // Si el tesoro eliminado tiene un hijo o zero hijos
            if ((tesoro.getHijoMenor() == null) || (tesoro.getHijoMayor() == null)){
                Tesoro aux = null;
                if (aux == tesoro.getHijoMenor())
                    aux = tesoro.getHijoMayor();
                else
                    aux = tesoro.getHijoMenor();

                // Si no tiene hijos, lo sustituimos por null
                if (aux == null) {
                    tesoro = null;
                } else // Caso con solo un hijo, cambiamos el tesoro por su unico hijo
                    tesoro = aux;
            } else { // Caso donde tiene dos hijos

                // Primero buscaremos el hijo mas pequeño de la rama del hijo mayor del tesoro eliminado
                Tesoro tesoroMin = tesoro.getHijoMayor();

                while (tesoroMin.getHijoMenor() != null)
                    tesoroMin = tesoroMin.getHijoMenor();

                // Sustituimos lso valores del Tesoror, para no tener que cambiar sus hijos actuales
                tesoro.setValor(tesoroMin.getValor());
                tesoro.setName(tesoroMin.getName());

                // Una vez cambiando solo eliminamos el tesoro que hemos duplicado
                tesoro.setHijoMayor(eliminarTesoro(tesoro.getHijoMayor(), tesoroMin));
            }
        }
        if (tesoro == null)
            return tesoro;

        //Actualizamos la altura del tesoro actual y hacemos AVL
        updateHeight(tesoro);


        int balance = getBalance(tesoro);


        if (balance > 1 && getBalance(tesoro.getHijoMenor()) >= 0)
            return rotateRight(tesoro);
        if (balance > 1 && getBalance(tesoro.getHijoMenor()) < 0){
            tesoro.setHijoMenor(rotateLeft(tesoro.getHijoMenor()));
            return rotateRight(tesoro);
        }

        if (balance < -1 && getBalance(tesoro.getHijoMayor()) <= 0)
            return rotateLeft(tesoro);
        if (balance < -1 && getBalance(tesoro.getHijoMayor()) > 0){
            tesoro.setHijoMayor(rotateRight(tesoro.getHijoMayor()));
            return rotateLeft(tesoro);
        }

        return tesoro;
    }


    public void inOrdre() {
        inOrdre(root);
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

    public void preOrdre() {preOrdre(root);}

    private void preOrdre(Tesoro tesoro) {
        System.out.println("\n\t" + tesoro.toString());
        if (tesoro.getHijoMenor() != null) {
            preOrdre(tesoro.getHijoMenor());
        }
        if (tesoro.getHijoMayor() != null) {
            preOrdre(tesoro.getHijoMayor());
        }
    }

    public void postOrdre() {postOrdre(root);}

    private void postOrdre(Tesoro tesoro) {
        if (tesoro.getHijoMenor() != null) {
            postOrdre(tesoro.getHijoMenor());
        }
        if (tesoro.getHijoMayor() != null) {
            postOrdre(tesoro.getHijoMayor());
        }
        System.out.println("\n\t" + tesoro.toString());
    }

    public void nivells() {nivells(root);}

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
        cercaValorExacte(valor, root);
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
        cercaValorRang(valorMin, valorMax, root);
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

    public void balanceigArbol(Tesoro tesoro){
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

    public void dibujarArbol(){
        Lienzo lienzo = new Lienzo();
        lienzo.setObjArbol(root);

        JFrame ventana = new JFrame();

        ventana.setDefaultCloseOperation(3);

        ventana.setLayout(new BorderLayout());
        ventana.add(new JScrollPane(lienzo));

        ventana.setSize(1000, 1000);
        ventana.setVisible(true);
    }

}
