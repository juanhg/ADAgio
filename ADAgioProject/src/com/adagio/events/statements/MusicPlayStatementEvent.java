package com.adagio.events.statements;

import com.adagio.language.chords.Chord;
import com.adagio.language.chords.ChordIdentifier;
import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.AlteredNoteName;
import com.adagio.language.musicnotes.BasicNoteName;
import com.adagio.language.musicnotes.notealterations.Alteration;
import com.adagio.language.musicnotes.notealterations.DoubleFlatAlteration;
import com.adagio.language.musicnotes.notealterations.FlatAlteration;
import com.adagio.language.musicnotes.notealterations.SharpAlteration;

import java.util.EventObject;
import java.util.Vector;

import org.modelcc.types.IntegerModel;



public class MusicPlayStatementEvent extends EventObject {
	
	private static final long serialVersionUID = 1L;
	
	// Este evento se lanza cuando se ha generado música.
	private  Vector<Chord> chords;

	public MusicPlayStatementEvent(Object source, Vector<Chord> chords) {
		super(source);
		this.chords = chords;
	}

	public Vector<Chord> getChords() {
		return chords;
	}
	
	/**
	 * Used for Test
	 * Add to the vector 3 default chords.
	 * 	0CM (<<c e g>)
	 *  3E#M (<eis''' gisis''' bis'''>)
	 *  4GbM/4Cbb (<ges'''' bes'''' des''''' ceses''''>)
	 *  @pre Needs that the listener has been initialized first.
	 */
	public void testInitilizer() {
		
		ChordIdentifier M;
		AbsoluteMusicNote fundamental, bass;
		BasicNoteName bNoteName;
		Chord chord;
		Alteration alteration;

		M = new ChordIdentifier("M");
		bass = null;
		fundamental = null;
		chord = null;
		bNoteName = null;
		alteration = null;

		// 0CM (<<c e g>)
		fundamental = new AbsoluteMusicNote(0, "C");
		chord = new Chord(fundamental, M, bass);
		this.chords.add(chord);

		// 3E#M (<eis''' gisis''' bis'''>)
		bNoteName = new BasicNoteName("E");
		alteration = new SharpAlteration(true);
		fundamental = new AbsoluteMusicNote(new IntegerModel(3),
				new AlteredNoteName(bNoteName, alteration));
		chord = new Chord(fundamental, M, bass);
		this.chords.add(chord);

		// 4GbM/4Cbb (<ges'''' bes'''' des''''' ceses''''>)
		bNoteName = new BasicNoteName("C");
		alteration = new DoubleFlatAlteration(true);
		bass = new AbsoluteMusicNote(new IntegerModel(4), new AlteredNoteName(
				bNoteName, alteration));
		bNoteName = new BasicNoteName("G");
		alteration = new FlatAlteration(true);
		fundamental = new AbsoluteMusicNote(new IntegerModel(4),
				new AlteredNoteName(bNoteName, alteration));
		chord = new Chord(fundamental, M, bass);
		this.chords.add(chord);
	}


	
	
}
