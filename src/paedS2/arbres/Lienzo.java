/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paedS2.arbres;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author daniel
 */
public class Lienzo extends JPanel implements Scrollable {
    private Tesoro objArbol;
    public static final int DIAMETRO = 30;
    public static final int RADIO = DIAMETRO / 2;
    public static final int ANCHO = 40;

    public void setObjArbol(Tesoro objArbol) {
        this.objArbol = objArbol;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        pintar(g, getWidth() / 2, 40, objArbol);
    }
    
    private void pintar(Graphics g, int x, int y, Tesoro n) {
        if (n == null)
        {}
        else {
            int EXTRA = n.getAltura() * (ANCHO / 1) * 2;
            g.drawOval(x, y, 150, DIAMETRO);
            g.drawString(n.getValor()+"", x + 12, y + 18);
            if (n.getHijoMenor() != null) {
                g.drawLine(x + RADIO, y + RADIO, x + ANCHO - EXTRA + RADIO, y + ANCHO + RADIO);
                pintar(g, x - ANCHO - EXTRA, y + ANCHO, n.getHijoMenor());
            }
            if (n.getHijoMayor() != null) {
                g.drawLine(x + RADIO, y + RADIO, x + ANCHO + EXTRA + RADIO, y + ANCHO + RADIO);
                pintar(g, x + ANCHO + EXTRA, y + ANCHO, n.getHijoMayor());
            }
        }
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return new Dimension(512, 256);
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 128;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 128;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return getPreferredSize().width
                <= getParent().getSize().width;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return getPreferredSize().height
                <= getParent().getSize().height;
    }
}
