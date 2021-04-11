package paedS2.arbres;

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
        System.out.println(tesoro.toString());
        if (tesoro.getHijoMayor() != null) {
            inOrdre(tesoro.getHijoMayor());
        }
    }

    public void preOrdre() {preOrdre(tesoroOrigen);}

    private void preOrdre(Tesoro tesoro) {
        System.out.println(tesoro.toString());
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
        System.out.println(tesoro.toString());
    }





}
