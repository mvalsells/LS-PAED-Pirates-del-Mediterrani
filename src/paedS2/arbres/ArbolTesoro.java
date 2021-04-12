package paedS2.arbres;

import paedS2.grafs.Vertice;

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
                if (padre.getHijoMenor() == tesoroAux) {
                    padre.setHijoMenor(null);
                } else if (padre.getHijoMayor() == tesoroAux) {
                    padre.setHijoMayor(null);
                }
            } else if (tesoroAux.getHijoMenor() == null) {
                //Si hi ha fill major
                if (padre.getHijoMenor() == tesoroAux) {
                    padre.setHijoMenor(tesoroAux.getHijoMayor());
                } else if (padre.getHijoMayor() == tesoroAux) {
                    padre.setHijoMayor(tesoroAux.getHijoMayor());
                }
            } else if (tesoroAux.getHijoMayor() == null) {
                //Si hi ha fill menor
                if (padre.getHijoMenor() == tesoroAux) {
                    padre.setHijoMenor(tesoroAux.getHijoMenor());
                } else if (padre.getHijoMayor() == tesoroAux) {
                    padre.setHijoMayor(tesoroAux.getHijoMenor());
                }
            } else {

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





}
