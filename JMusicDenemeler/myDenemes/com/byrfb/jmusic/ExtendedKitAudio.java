package com.byrfb.jmusic;

import inst.SimpleSampleInst;
import jm.JMC;
import jm.music.data.*;
import jm.music.tools.*;
import jm.util.*;
import jm.audio.*;
import jm.audio.io.*;

/**
 * A short example which generates a drum kit pattern
 * and writes to a MIDI file called ExtendedKit.mid
 * @author Andrew Brown
 */
public final class ExtendedKitAudio implements JMC{
	/////////////
	//Attributes
	/////////////
	// audio instruments
	private static SimpleSampleInst kickInst = new SimpleSampleInst("Kick.au", FRQ[36], true);
	private static SimpleSampleInst snareInst = new SimpleSampleInst("Snare.au", FRQ[38], true);
	private static SimpleSampleInst hatsInst = new SimpleSampleInst("Hats.au", FRQ[42], true);
	private static SimpleSampleInst openHatsInst = new SimpleSampleInst("HHOpen.au", FRQ[46], false);
	private static Instrument[] drumKit = {kickInst, snareInst, hatsInst, openHatsInst};
	// data containers
	private static Score pattern1 = new Score("JMDemo - Kit"); 
	private static Part drumsBD = new Part("Kick", 0, 9); // 9 = MIDI channel 10
	private static Part drumsSD = new Part("Snare", 1, 9); // 9 = MIDI channel 10
	private static Part drumsHHC = new Part("Hats Closed", 2, 9); // 9 = MIDI channel 10
	private static Part drumsHHO = new Part("Hats Open", 3, 9); // 9 = MIDI channel 10
	private static Part drumsCY = new Part("Cymbal", 3, 9); // 9 = MIDI channel 10
	private static Phrase phrBD = new Phrase(0.0);
	private static Phrase phrSD = new Phrase(0.0);
	private static Phrase phrHHC = new Phrase(0.0);
	private static Phrase phrHHO = new Phrase(0.0); 
	private static Phrase end = new Phrase(64.0);
	//define often used rests
	private static Note restSQ = new Note(REST, SEMI_QUAVER); //rest
	private static Note restC = new Note(REST, CROTCHET); //rest
	
	////////////
	//main
	////////////	
	public static void main(String[] args){
		//create an instance of the drum rhythm
		
		ExtendedKitAudio ek = new ExtendedKitAudio();
		
		// set tempo
		pattern1.setTempo(100);
		Play.midi(pattern1);
		// normalise the jMusic score dynamics
		Mod.normalise(pattern1);
		
		//write an audio file of the score using the drumKit instruments
//		Write.au(pattern1, "ExtendedDrums.au", drumKit);

		
		
	}
	//////////////
	//constructor
	//////////////
	public ExtendedKitAudio() {		
		//Let us know things have started
		System.out.println("Creating drum patterns . . .");
		
		// make bass drum
		this.doBassDrum();
		
		//snare drum
		this.doSnare();
		
		// make hats
		this.doHiHats();
		
		//crash at the end
		Note crashSB = new Note(49, 8.0); // crash
		end.addNote(crashSB);
		
		//Assemble the score
		this.doScore();
	}	
	////////////
	//Methods
	////////////
	private void doBassDrum() {
		//Create basic kick pattern
		System.out.println("Doing kick drum. . .");
		for(int r=0;r<8;r++){ // repeat 8 times
    		for(int i=0;i<4;i++){
    			Note note = new Note(36, CROTCHET, 127);
    			phrBD.addNote(note);
    			phrBD.addNote(restC);
    		}
    	}
	}
		
	private void doSnare() {
	// make snare drum
		System.out.println("Doing snare. . .");
		for(int j=0;j<16;j++){	//repeat for 16 bars of 4/4
			phrSD.addNote(restC);
			Note snareC = new Note(38, CROTCHET, 127);
			phrSD.addNote(snareC);
			phrSD.addNote(restC);
			for(int i=0;i<3;i++){	//vary the last crotchet beat each bar
				int rand = (int)(Math.random()*3);
				if (rand > 1) {
					Note snareSEMI_QUAVER = new Note(38, SEMI_QUAVER);
					phrSD.addNote(snareSEMI_QUAVER);
				} else {
					phrSD.addNote(restSQ);
				}
			}	
			phrSD.addNote(restSQ);
		}
	}
	
	private void doHiHats() {
		System.out.println("Doing Hi Hats. . .");
		for(int r = 0; r < 8; r++){ //repeat for 8 two bar cycles
			Note note1 = new Note(42, SEMI_QUAVER, (int)(Math.random()*80+45)); //start with closed hat
			phrHHC.addNote(note1);
			phrHHO.addNote(restSQ); // add a rest to the other HH part so they align
			for(int i=0;i<30;i++){ 
			double rand = Math.random();
				if (rand < 0.1) { //select occasional open hi hat
					Note note5 = new Note(46, SEMI_QUAVER, (int)(Math.random()*80+45));
					phrHHO.addNote(note5);
					phrHHC.addNote(restSQ);
				} else {
					Note note2 = new Note(42, SEMI_QUAVER, (int)(Math.random()*80+45));
					phrHHC.addNote(note2);
					phrHHO.addNote(restSQ);
				}
			}
			Note note6 = new Note(46, SEMI_QUAVER, 60); // open hi hat at the end of the pattern
			phrHHO.addNote(note6);
			phrHHC.addNote(restSQ);
			// accent every other beat of the closed hats
			Mod.accents(phrHHC, 2.0);
			
		}
	}
	private void doScore() {
		// add phrases to the parts
		System.out.println("Assembling. . .");
		drumsBD.addPhrase(phrBD);
		drumsSD.addPhrase(phrSD);
		drumsHHC.addPhrase(phrHHC);
		drumsHHO.addPhrase(phrHHO);
		drumsCY.addPhrase(end);
		
		// add the drum parts to a score.
		pattern1.addPart(drumsBD);
		pattern1.addPart(drumsSD);
		pattern1.addPart(drumsHHC);
		pattern1.addPart(drumsHHO);
		pattern1.addPart(drumsCY);
	}
}