package test.com.adagio.language.instruments.features;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.types.IntegerModel;

import com.adagio.language.instruments.features.Register;
import com.adagio.language.musicnotes.AbsoluteMusicNote;
import com.adagio.language.musicnotes.AlteredNoteName;
import com.adagio.language.musicnotes.BasicNoteName;
import com.adagio.language.musicnotes.notealterations.Alteration;
import com.adagio.language.musicnotes.notealterations.DoubleFlatAlteration;
import com.adagio.language.musicnotes.notealterations.SharpAlteration;

public class RegisterTest {

	AbsoluteMusicNote A5Sharp, A2Sharp, A4bb, A3bb; 
	AbsoluteMusicNote A5,A3, A2, C4,B2, B3,C2, C3, G3; 
	AbsoluteMusicNote Am1, Cm1, Dm1, Am5, Cm5, Gm5, Bm4, Cm3;
	BasicNoteName bNoteName;
	Alteration alteration;
	Register r1, r2, r3, r4, r5;

	@Before
	public void setUp() throws Exception {
		bNoteName = new BasicNoteName("A");
		alteration = new SharpAlteration(true);
		A5Sharp = new AbsoluteMusicNote(new IntegerModel(5), new AlteredNoteName(bNoteName, alteration));

		bNoteName = new BasicNoteName("A");
		alteration = new SharpAlteration(true);
		A2Sharp = new AbsoluteMusicNote(new IntegerModel(2), new AlteredNoteName(bNoteName, alteration));
		
		bNoteName = new BasicNoteName("A");
		alteration = new DoubleFlatAlteration(true);
		A4bb = new AbsoluteMusicNote(new IntegerModel(4), new AlteredNoteName(bNoteName, alteration));

		bNoteName = new BasicNoteName("A");
		alteration = new DoubleFlatAlteration(true);
		A3bb = new AbsoluteMusicNote(new IntegerModel(3), new AlteredNoteName(bNoteName, alteration));

		A5 = new AbsoluteMusicNote(5, "A");
		G3 = new AbsoluteMusicNote(3,"G");
		A3 = new AbsoluteMusicNote(3,"A");
		A2 = new AbsoluteMusicNote(2,"A");
		C2 = new AbsoluteMusicNote(2,"C");
		C4 = new AbsoluteMusicNote(4,"C");
		B3 = new AbsoluteMusicNote(3, "B");
		B2 = new AbsoluteMusicNote(2, "B");
		C3 = new AbsoluteMusicNote(3, "C");

		Am1 = new AbsoluteMusicNote(-1,"A");
		Cm1 = new AbsoluteMusicNote(-1,"C");
		Dm1 = new AbsoluteMusicNote(-1,"D");
		Am5 = new AbsoluteMusicNote(-5,"A");
		Cm5 = new AbsoluteMusicNote(-5,"C");
		Gm5 = new AbsoluteMusicNote(-5,"G");
		Bm4 = new AbsoluteMusicNote(-4,"B");
		Cm3 = new AbsoluteMusicNote(-3,"C");
		
		r1 = new Register();
		r2 = new Register(Cm3, A3);
		r3 = new Register(Cm1, C4);
		r4 = new Register(A2, A5Sharp);
		r5 = new Register(A2,B2);
	}
	
	@Test
	public void noteToRegisterTest(){
		assertEquals(true,A2.equals(r1.aNoteToRegister(A2)));
		assertEquals(true,A5Sharp.equals(r1.aNoteToRegister(A5Sharp)));
		assertEquals(true,Cm1.equals(r3.aNoteToRegister(Cm5)));
		assertEquals(true,Cm3.equals(r2.aNoteToRegister(Cm5)));
		assertEquals(true,B2.equals(r2.aNoteToRegister(B3)));
		assertEquals(true,A3bb.equals(r2.aNoteToRegister(A4bb)));
		assertEquals(true, C2.equals(r5.aNoteToRegister(C3)));
		assertEquals(true, A2Sharp.equals(r5.aNoteToRegister(A5Sharp)));
	}
	
}
