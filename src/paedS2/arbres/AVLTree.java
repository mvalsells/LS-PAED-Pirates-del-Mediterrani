package paedS2.arbres;

import java.util.LinkedList;
import java.util.Queue;

public class AVLTree {

    private Tesoro root;

    public AVLTree(Tesoro root){
        this.root=root;
    }

    void updateHeight(Tesoro n) {
        n.setAltura(1 + Math.max(height(n.getHijoMenor()), height(n.getHijoMayor())));
    }

    int height(Tesoro n) {
        return n == null ? -1 : n.getAltura();
    }

    int getBalance(Tesoro n) {
        return (n == null) ? 0 : height(n.getHijoMayor()) - height(n.getHijoMenor());
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
}
