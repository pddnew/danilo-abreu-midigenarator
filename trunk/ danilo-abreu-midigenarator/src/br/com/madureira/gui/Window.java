package br.com.madureira.gui;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.ShortMessage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Window é a classe responsável de criar a Tela.
 * @author Danilo 30-05-2010
 */
public class Window extends JFrame implements ControllerEventListener,
            MetaEventListener{

    JPanel panel;
    private static final int END_OF_TRACK = 47;

    public Window(){
        super(".::.MIDI SOUND GENERATE.::.");

        panel = new DesignPanel().createPanel();

        setContentPane(panel);
        setBounds(25, 32, 298, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**     
     * @param event - Parametro ShortMessage
     */
    @Override
    public void controlChange(ShortMessage event) {
        DesignPanel p = (DesignPanel) panel;
        p.repaint(true);
    }

    /**     
     * @param meta Parametro MetaMessage
     */
    @Override
    public void meta(MetaMessage meta) {
        if(meta.getType() == END_OF_TRACK)
                //JOptionPane.showMessageDialog(null, "THE END");
                JOptionPane.showMessageDialog(null, 
                        "THE END!\n THIS WINDOW WILL BE CLOSED.", "END OF SOUND", 
                        JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
    }
}
