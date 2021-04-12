package paedS2.arbres;

import paedS2.grafs.Vertice;

import java.util.LinkedList;
import java.util.Queue;

public class ArbolTesoro {

    private Tesoro tesoroOrigen;
    private Tesoro tesoroAux;
    private boolean tesoroTrobat;

    public ArbolTesoro(Tesoro tesoroOrigen) {
        this.tesoroOrigen = tesoroOrigen;
    }

    public void inserirNodo(Tesoro tesoro) {
        inserirNodo(tesoro, tesoroOrigen);
    }

    private void inserirNodo(Tesoro tesoro, Tesoro padre) {
        if (padre.getValor() < tesoro.getValor()) {
            if(padre.getHijoMayor() == null) {
                padre.setHijoMayor(tesoro);
            } else {
                inserirNodo(tesoro, padre.getHijoMayor());
            }
        } else if (padre.getValor() > tesoro.getValor()) {
            if(padre.getHijoMenor() == null) {
                padre.setHijoMenor(tesoro);
            } else {
                inserirNodo(tesoro, padre.getHijoMenor());
            }
        }
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
        } else  {
            padre = tesoroAux.getPadre();
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
                Tesoro tmp = tesoroAux.getHijoMayor();
                while (tmp.getHijoMenor()!=null){
                    tmp = tmp.getHijoMenor();
                }

                if (tmp.getHijoMayor()!=null){
                    tmp.getPadre().setHijoMenor(tmp.getHijoMayor());
                }

                if (padre.getHijoMenor() == tesoroAux) {
                    padre.setHijoMenor(tmp);
                } else if (padre.getHijoMayor() == tesoroAux) {
                    padre.setHijoMayor(tmp);
                }
                tmp.setHijoMayor(tesoroAux.getHijoMayor());
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
            System.out.println("\n\t" + t.toString());
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



}
