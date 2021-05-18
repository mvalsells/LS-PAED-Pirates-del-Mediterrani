package paedS2.arbresR;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class JPArbolR extends JPanel {
    ArbolR arbolR;
    public JPArbolR(ArbolR arbolR){
        this.arbolR=arbolR;
    }

    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);


        Graphics2D graphics2D = (Graphics2D) graphics;

        int graphWidth = (int)(getWidth()*0.8);
        int graphHigh = (int)(getHeight()*0.8);
        double width_inicial = getWidth()/2.0 - graphWidth/2.0;
        double high_inicial = getHeight()/2.0 - graphHigh/2.0;

        graphics.setColor(Color.RED);
        graphics2D.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
        ArrayList<Float> pos = arbolR.getRoot().posicio();
        float high = pos.get(1)-pos.get(3);
        float width = pos.get(0)- pos.get(2);
        float topLeftX = pos.get(2);
        float topLeftY = pos.get(1);
        graphics2D.draw(new Rectangle2D.Double(topLeftX+width_inicial,topLeftY+high_inicial,width,high));

        //marge
        graphics.setColor(Color.gray);
        graphics2D.setStroke(new BasicStroke(2.8f));
        graphics.drawRect(getWidth()/2 - graphWidth/2,getHeight()/2 - graphHigh/2, graphWidth, graphHigh);

        //Llegenda
        /*for(int i = 0; i<24; i+=2) {
            drawRotate(graphics2D, i + width_inicial, high + high_inicial + 10, String.valueOf(i));
        }*/
    }

    private void drawRotate(Graphics2D g2d, double x, double y, String text)
    {
        g2d.translate((float)x,(float)y);
        g2d.rotate(Math.toRadians(45));
        g2d.drawString(text,0,0);
        g2d.rotate(-Math.toRadians(45));
        g2d.translate(-(float)x,-(float)y);
    }
}
