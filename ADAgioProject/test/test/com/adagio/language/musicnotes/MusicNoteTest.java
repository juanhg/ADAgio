package test.com.adagio.language.musicnotes;

import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.AlteredNoteName;
import com.adagio.language.musicnotes.BasicNoteName;
import com.adagio.language.musicnotes.MusicNote;
import com.adagio.language.musicnotes.MusicNoteName;
import com.adagio.language.musicnotes.RelativeMusicNote;
import com.adagio.language.musicnotes.notealterations.Alteration;
import com.adagio.language.musicnotes.notealterations.DoubleFlatAlteration;
import com.adagio.language.musicnotes.notealterations.DoubleSharpAlteration;
import com.adagio.language.musicnotes.notealterations.SharpAlteration;
import com.adagio.language.musicnotes.octavealterations.DownOctaveAlteration;
import com.adagio.language.musicnotes.octavealterations.OctaveAlteration;
import com.adagio.language.musicnotes.octavealterations.UpOctaveAlteration;

import static org.junit.Assert.*;
import static org.modelcc.test.ModelAssert.*;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.io.java.JavaModelReader;
import org.modelcc.metamodel.Model;
import org.modelcc.parser.Parser;
import org.modelcc.parser.ParserFactory;

public class MusicNoteTest { 

  Model model;
  Parser<MusicNote> parser;

  @SuppressWarnings("unchecked")
@Before
  public void setUp() throws Exception {
    model = JavaModelReader.read(MusicNote.class);
    parser = ParserFactory.create(model,ParserFactory.WHITESPACE);
  }

  // Comprobamos que las siguientes notas son válidas y se leen sin ambigüedades.
  @Test
  public void musicNoteValidTest() {
    assertAmbiguityFree(parser,"-2D");
    assertAmbiguityFree(parser,"4C");
    assertAmbiguityFree(parser,"2Ebb");
    assertAmbiguityFree(parser,"Ebb");
    assertAmbiguityFree(parser,"3D#");
    assertAmbiguityFree(parser,"G");
    assertAmbiguityFree(parser,"3Dx");
    assertAmbiguityFree(parser,"4Gx");
    assertAmbiguityFree(parser,"-3Ab");
    assertAmbiguityFree(parser,"1Dx");
    assertAmbiguityFree(parser,"B");
    assertAmbiguityFree(parser,"D#");
    assertAmbiguityFree(parser,"Fbb");
    assertAmbiguityFree(parser,"-1C");
    assertAmbiguityFree(parser,"1Fx");
    assertAmbiguityFree(parser,"A#");
    assertAmbiguityFree(parser,"4Ax");
    assertAmbiguityFree(parser,"A''");
    assertAmbiguityFree(parser,"B,");
  }

  // Comprobamos que las siguientes notas son inválidas (por la octava, restricción custom con @Constraint).
  @Test
  public void musicNoteInvalidOctaveTest() {
    assertInvalid(parser,"-6D");
    assertInvalid(parser,"6D");
    assertInvalid(parser,"-7A");
    assertInvalid(parser,"7A");
    assertInvalid(parser,"-6C");
    assertInvalid(parser,"6F");
  }

  // Comprobamos que las siguientes notas son inválidas (por el nombre de las notas).
  @Test
  public void musicNoteInvalidNoteTest() {
    assertInvalid(parser,"H");
    assertInvalid(parser,"I");
    assertInvalid(parser,"J");
    assertInvalid(parser,"K");
    assertInvalid(parser,"L");
    assertInvalid(parser,"M");
    assertInvalid(parser,"N");
    assertInvalid(parser,"Ñ");
    assertInvalid(parser,"I");
    assertInvalid(parser,"J");
    assertInvalid(parser,"K");
    assertInvalid(parser,"L");
    assertInvalid(parser,"O");
    assertInvalid(parser,"P");
    assertInvalid(parser,"Q");
    assertInvalid(parser,"R");
    assertInvalid(parser,"S");
    assertInvalid(parser,"T");
    assertInvalid(parser,"U");
    assertInvalid(parser,"V");
    assertInvalid(parser,"W");
    assertInvalid(parser,"X");
    assertInvalid(parser,"Y");
    assertInvalid(parser,"Z");
    
    assertInvalid(parser,"a");
    assertInvalid(parser,"b");
    assertInvalid(parser,"c");
    assertInvalid(parser,"d");
    assertInvalid(parser,"e");
    assertInvalid(parser,"f");
    assertInvalid(parser,"g");
    assertInvalid(parser,"h");
    
  }  

  // Comprobamos si la información leída de una nota es correcta:
  @Test
  public void musicNoteReadTest1() throws Exception {
    
    assertAmbiguityFree(parser,"-2D"); // Nos garantiza que es válido y no hay ambigüedades
    MusicNote note = parser.parse("-2D");

    assertTrue(note instanceof AbsoluteMusicNote);
    AbsoluteMusicNote aNote = (AbsoluteMusicNote)note;

    assertEquals(-2,aNote.getOctave().intValue()); 

    assertTrue(aNote.getMusicNoteName() instanceof BasicNoteName); //Idem, tienes que implementar getNoteName()
    BasicNoteName bNoteName = (BasicNoteName) aNote.getMusicNoteName();

    assertEquals("D",bNoteName.getValue()); //Implementa ese getValue también, así es independiente del toString (que da la traducción a lilypond y que podría cambiar en un futuro).
  }
  
  @Test
  public void musicNoteReadTest2() throws Exception {
    
    assertAmbiguityFree(parser,"3Dx"); // Nos garantiza que es válido y no hay ambigüedades
    MusicNote note = parser.parse("3Dx");

    AbsoluteMusicNote aNote = (AbsoluteMusicNote)note;

    assertEquals(3,aNote.getOctave().intValue()); 

    assertTrue(aNote.getMusicNoteName() instanceof AlteredNoteName); //Idem, tienes que implementar getNoteName()
    AlteredNoteName altNoteName = (AlteredNoteName) aNote.getMusicNoteName();

    BasicNoteName bNoteName = altNoteName.getBasicNoteName();
    assertEquals("D",bNoteName.getValue()); //Implementa ese getValue también, así es independiente del toString (que da la traducción a lilypond y que podría cambiar en un futuro).
    
    Alteration alteration = altNoteName.getAlteration();
    assertTrue(alteration instanceof DoubleSharpAlteration);
    DoubleSharpAlteration dSharpAlteration = (DoubleSharpAlteration) alteration;
    assertEquals("x",dSharpAlteration.getValue());
  }
  
  @Test
  public void musicNoteReadTest3() throws Exception {
    
    assertAmbiguityFree(parser,"2Ebb"); // Nos garantiza que es válido y no hay ambigüedades
    MusicNote note = parser.parse("2Ebb");

    assertTrue(note instanceof AbsoluteMusicNote);
    AbsoluteMusicNote aNote = (AbsoluteMusicNote)note;

    assertEquals(2,aNote.getOctave().intValue()); 

    assertTrue(aNote.getMusicNoteName() instanceof AlteredNoteName); //Idem, tienes que implementar getNoteName()
    AlteredNoteName altNoteName = (AlteredNoteName) aNote.getMusicNoteName();

    BasicNoteName bNoteName = altNoteName.getBasicNoteName();
    assertEquals("E",bNoteName.getValue()); //Implementa ese getValue también, así es independiente del toString (que da la traducción a lilypond y que podría cambiar en un futuro).
    
    Alteration alteration = altNoteName.getAlteration();
    assertTrue(alteration instanceof DoubleFlatAlteration);
    DoubleFlatAlteration dSharpAlteration = (DoubleFlatAlteration) alteration;
    assertEquals("bb",dSharpAlteration.getValue());
  }
  
  @Test
  public void musicNoteReadTest4() throws Exception {
    
    assertAmbiguityFree(parser,"A#"); // Nos garantiza que es válido y no hay ambigüedades
    MusicNote note = parser.parse("A#");

    assertTrue(note instanceof RelativeMusicNote);
    RelativeMusicNote rNote = (RelativeMusicNote)note;
    
    assertNull(rNote.getOctave());
    
    MusicNoteName mNoteName = rNote.getMusicNoteName();
    
    assertTrue(mNoteName instanceof AlteredNoteName);
    AlteredNoteName altNoteName = (AlteredNoteName) mNoteName;
    
    assertEquals("A",altNoteName.getBasicNoteName().getValue());
    
   Alteration alteration = altNoteName.getAlteration();
    assertTrue(alteration instanceof SharpAlteration);
    SharpAlteration sharpAlteration = (SharpAlteration) alteration;
    assertEquals("#",sharpAlteration.getValue());
  }
  
  @Test
  public void musicNoteReadTest5() throws Exception {
    
    assertAmbiguityFree(parser,"A''"); // Nos garantiza que es válido y no hay ambigüedades
    MusicNote note = parser.parse("A''");

    assertTrue(note instanceof RelativeMusicNote);
    RelativeMusicNote rNote = (RelativeMusicNote)note;
    
    //Check the alteration
    assertNotNull(rNote.getOctave());
    OctaveAlteration oAlteration = rNote.getOctave();
    assertTrue(oAlteration instanceof UpOctaveAlteration);
    UpOctaveAlteration uOAlteration = (UpOctaveAlteration) oAlteration;
    assertEquals("''", uOAlteration.getValue());
    
    MusicNoteName mNoteName = rNote.getMusicNoteName();
    assertTrue(mNoteName instanceof BasicNoteName);
    BasicNoteName bNoteName = (BasicNoteName) mNoteName;
    
    assertEquals("A",bNoteName.getValue());
  }
  
  @Test
  public void musicNoteReadTest6() throws Exception {
    
    assertAmbiguityFree(parser,"B,"); // Nos garantiza que es válido y no hay ambigüedades
    MusicNote note = parser.parse("B,");

    assertTrue(note instanceof RelativeMusicNote);
    RelativeMusicNote rNote = (RelativeMusicNote)note;
    
    //Check the alteration
    assertNotNull(rNote.getOctave());
    OctaveAlteration oAlteration = rNote.getOctave();
    assertTrue(oAlteration instanceof DownOctaveAlteration);
    DownOctaveAlteration dOAlteration = (DownOctaveAlteration) oAlteration;
    assertEquals(",", dOAlteration.getValue());
    
    MusicNoteName mNoteName = rNote.getMusicNoteName();
    assertTrue(mNoteName instanceof BasicNoteName);
    BasicNoteName bNoteName = (BasicNoteName) mNoteName;
    
    assertEquals("B",bNoteName.getValue());
  }
  
  @Test
  public void musicNoteReadTest7() throws Exception {
    
    assertAmbiguityFree(parser,"G"); // Nos garantiza que es válido y no hay ambigüedades
    MusicNote note = parser.parse("G");

    assertTrue(note instanceof RelativeMusicNote);
    RelativeMusicNote rNote = (RelativeMusicNote)note;
    
    //Check the alteration
    assertNull(rNote.getOctave());
    
    MusicNoteName mNoteName = rNote.getMusicNoteName();
    assertTrue(mNoteName instanceof BasicNoteName);
    BasicNoteName bNoteName = (BasicNoteName) mNoteName;
    
    assertEquals("G",bNoteName.getValue());
  }
  
}

