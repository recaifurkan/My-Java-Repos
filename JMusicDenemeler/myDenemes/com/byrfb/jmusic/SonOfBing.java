package com.byrfb.jmusic;
import inst.SawtoothInst;
import jm.JMC;
import jm.music.data.*;
import jm.util.*;
import jm.audio.*;
 
public final class SonOfBing implements JMC{

    public static void main(String[] args){

        Score score = new Score(new Part(new Phrase(new Note(C4, MINIM))));
        Write.midi(score);
        Instrument inst = new SawtoothInst(44100);
        Write.au(score, inst);
//        Play.audio(score, inst);
                

    }

}