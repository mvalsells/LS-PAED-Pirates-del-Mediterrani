package paedS2.arbresR;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;

public class JPArbolR extends JPanel {
    private ArbolR arbolR;
    private Graphics2D graphics2D;
    private int graphX;
    private int graphY;
    private static final int escalat = 7;
    public JPArbolR(ArbolR arbolR){
        this.arbolR=arbolR;
    }

    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

/*
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

        graphics2D = (Graphics2D) graphics;

        RectanguloR root = arbolR.getRoot();
        ArrayList<Float> pos= root.posicio();
        graphX = (int) ((root.posicio().get(0)-root.posicio().get(2))*escalat+1);
        graphY = (int) ((root.posicio().get(1)-root.posicio().get(3))*escalat+1);

        setBackground(Color.YELLOW);
        setSize(new Dimension(graphX,graphY));

        for (ElementoR er:root.getHijos()){
            mostrarElementoR(er);
        }
    }

    private void mostrarElementoR(ElementoR er) {
        ArrayList<Float> pos = er.posicio();
        ArrayList<Float> posRoot = arbolR.getRoot().posicio();
        if (pos.size()==4){
            //És rectangle
            float high = (pos.get(1)-pos.get(3))*escalat;
            float width = (pos.get(0)- pos.get(2))*escalat;
            float topLeftX = (pos.get(2)-posRoot.get(2))*escalat;
            float topLeftY = graphY-(pos.get(1)-posRoot.get(3))*escalat;
            graphics2D.draw(new Rectangle2D.Float(topLeftX,topLeftY,width,high));
            //graphics2D.drawRect((int)topLeftX,(int)topLeftY,(int)width,(int) high);
            for (ElementoR erHijo:er.getHijos()){
                mostrarElementoR(erHijo);
            }
        } else if (pos.size()==2){
            //És punt
            int x = (int)((pos.get(0)-posRoot.get(2))*escalat);
            int y = (int)(graphY-(pos.get(1)-posRoot.get(3))*escalat);
            graphics2D.drawOval(x-3, y-3, 6,6);
        }

        /*
        pos.add(maxX);
        pos.add(maxY);
        pos.add(minX);
        pos.add(minY);
         */


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
