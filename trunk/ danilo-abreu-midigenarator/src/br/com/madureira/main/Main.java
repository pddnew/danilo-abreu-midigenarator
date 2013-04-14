package br.com.madureira.main;

import br.com.madureira.gui.Window;
import br.com.madureira.sound.SoundCreator;
import br.com.madureira.util.MadureiraException;
import javax.swing.JOptionPane;
/** 
 * @author Danilo
 */
public class Main {

    static Window win = new Window();


    /**
     * @param args Argumentos da linha de comando
     */
    public static void main(String[] args) {
        try{
            SoundCreator c = new SoundCreator(win);
            c.create();
        }catch(MadureiraException ex){
            JOptionPane.showMessageDialog(win, ex.getMessage());
        }catch(Exception ex){
            JOptionPane.showMessageDialog(win, ex.getMessage());
        }
    }
}