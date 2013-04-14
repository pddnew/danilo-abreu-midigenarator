package br.com.madureira.sound;

import br.com.madureira.util.MadureiraException;
import java.util.EventListener;
import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

/**
 * @author Danilo
 */
public class SoundCreator {

    EventListener win;
    Sequencer sequencer;

    public SoundCreator(EventListener e){
        win = e;
    }

    public void create() throws MadureiraException{
        
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.addControllerEventListener((ControllerEventListener)win, new int[] {127});
            sequencer.addMetaEventListener((MetaEventListener)win);
            sequencer.open();
            Sequence sequence = new Sequence(Sequence.PPQ, 4);

            Track track = sequence.createTrack();

            // Array com a divisão temporal
            int div[] = {  4, 4, 4, 4, 4, 4, 4, 4,
                            4, 4, 4, 4, 4, 6, 2, 8,
                            4, 4, 4, 4, 4, 4, 4, 4,
                            4, 4, 4, 4, 6, 2, 8,
                            4, 4, 4, 4, 4, 2, 2, 4, 4,
                            4, 2, 2, 4, 4, 4, 4, 8,
                            4, 4, 4, 4, 4, 4, 4, 4,
                            4, 4, 4, 4, 6, 2, 8, 2 };


            // Array valores das notas
            int notes[] = { 64, 64, 65, 67, 67, 65, 64, 62,
                            60, 60, 62, 64, 64, 62, 62,
                            64, 64, 65, 67, 67, 65, 64, 62,
                            60, 60, 62, 64, 62, 60, 60,
                            62, 62, 64, 60, 62, 64, 65, 64, 60,
                            62, 64, 65, 64, 62, 60, 62, 59,
                            64, 64, 65, 67, 67, 65, 64, 62,
                            60, 60, 62, 64, 62, 60, 60, 72 };

            int div2[] = { 4, 16, 16, 16, 8, 8,
                            16, 16, 16, 8, 8,
                            8, 8, 8, 8, 8, 8, 8, 8,
                            16, 16, 16, 8, 8 };

            int notes2[] = { 48, 47, 45, 43, 43,
                             48, 47, 45, 43, 48,
                             43, 48, 43, 48, 43, 45, 41, 43,
                             48, 47, 45, 43, 48 };

            int div3[] = { 4, 16, 16, 16, 8, 8,
            16, 16, 16, 8, 8,
            8, 8, 8, 8, 8, 8, 8, 8,
            16, 16, 16, 8, 8 };

            int notes3[] = { 55, 55, 52, 52, 50,
            55, 55, 52, 50, 55,
            50, 55, 50, 55, 50, 52, 48, 50,
            55, 55, 52, 50, 55 };


            track = produceSound(track, div, notes, 100);
            track = produceSound(track, div2, notes2, 26);
            track = produceSound(track, div3, notes3, 28);

            sequencer.setSequence(sequence);
            sequencer.start();
            sequencer.setTempoInBPM(120);

        } catch (MidiUnavailableException ex) {
            throw new MadureiraException("[MidiUnavailableException] : " +
                    ex.getMessage());
        } catch(InvalidMidiDataException ex){
            throw new MadureiraException("[InvalidMidiDataException] : " +
                    ex.getMessage());
        }
    }

    private Track produceSound(Track track, int[] div, int[] notes, int intensity){
        int acum = 0;

        for (int i = 0; i < div.length - 1; i++) {
            acum += div[i];
            track.add(createEvent(ShortMessage.NOTE_ON,
                            1, notes[i], intensity, acum ));

                track.add(createEvent(ShortMessage.CONTROL_CHANGE,
                        1, 127, 0, acum));

                track.add(createEvent(ShortMessage.NOTE_OFF,
                            1, notes[i], intensity, acum + div[i+1]));
        }
        return track;
    }

    private MidiEvent createEvent(int cmd, int channel,  int note,
                                                int intensity, int moment){

        MidiEvent event = null;

        try{
            ShortMessage message = new ShortMessage();
            message.setMessage(cmd, channel, note, intensity);
            event = new MidiEvent(message, moment);
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return event;
    }
}