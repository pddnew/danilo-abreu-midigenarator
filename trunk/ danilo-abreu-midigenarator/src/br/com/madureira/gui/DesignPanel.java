package br.com.madureira.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/** 
 * @author Danilo
 */
public class DesignPanel extends JPanel{    

    private static boolean msg = false;

    public JPanel createPanel(){
        return this;
    }

    public void repaint(boolean bool){
        msg = bool;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        if(msg){
            super.paintComponents(g);

            Graphics2D g2 = (Graphics2D)g;
            int red = (int) (Math.random() * 250 );
            int green = (int) (Math.random() * 250 );
            int blue = (int) (Math.random() * 250);

            g2.setColor(new Color(red, green, blue));

            int height = (int) ((Math.random() * 120) + 10);
            int width = (int) ((Math.random() * 120) +10);

            int x = (int) ((Math.random() * 40) + 10);
            int y = (int) ((Math.random() * 30) + 10);

            g2.drawOval(x * 3, y * 3, width, height);
            msg = false;
        }            
    }

}
