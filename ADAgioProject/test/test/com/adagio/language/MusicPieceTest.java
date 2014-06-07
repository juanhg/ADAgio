package test.com.adagio.language;

import static org.modelcc.test.ModelAssert.assertAmbiguityFree;

import java.nio.charset.StandardCharsets;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.io.java.JavaModelReader;
import org.modelcc.metamodel.Model;
import org.modelcc.parser.Parser;
import org.modelcc.parser.ParserFactory;

import test.com.adagio.InitTest;

import com.adagio.AdagioPreprocessor;
import com.adagio.language.MusicPiece;

public class MusicPieceTest extends InitTest {
	
	Model model;
	Parser<MusicPiece> parser;
	String path = ".\\input_examples";
	String program0 =  path + "\\0_DefaultChannelProgram.adg";
	String program1 = path + "\\1_TempoProgram.adg";
	String program2 = path + "\\2_EmeProgram.adg";
	String program3 = path +  "\\3_MultiplicityProgram.adg";
	String program4 = path + "\\4_BarsProgram.adg";
	String program5 = path + "\\5_TimesProgram.adg";
	String program6 = path + "\\6_SiAmanecieraProgram.adg";
	String program7 = path + "\\7_FallsApartProgram.adg";
	String program8 = path + "\\8_GuitarraProgram.adg";
	String program9 = path + "\\9_SMProgram.adg";
	String program10 = path + "\\10_GuitarProgram.adg";
	
	String test0, test1, test2, test3, test4, test5, test6, test7, test8, test9, test10;
	

	  @SuppressWarnings("unchecked")
	@Before
	  public void setUp() throws Exception {
		
		super.setUp();
	    model = JavaModelReader.read(MusicPiece.class);
	    parser = ParserFactory.create(model,ignore);
	    test0 = AdagioPreprocessor.fileToString(program0, StandardCharsets.UTF_8);
	    test1 = AdagioPreprocessor.fileToString(program1, StandardCharsets.UTF_8);
	    test2 = AdagioPreprocessor.fileToString(program2, StandardCharsets.UTF_8);
	    test3 = AdagioPreprocessor.fileToString(program3, StandardCharsets.UTF_8);
	    test4 = AdagioPreprocessor.fileToString(program4, StandardCharsets.UTF_8);
	    test5 = AdagioPreprocessor.fileToString(program5, StandardCharsets.UTF_8);
	    test6 = AdagioPreprocessor.fileToString(program6, StandardCharsets.UTF_8);
	    test7 = AdagioPreprocessor.fileToString(program7, StandardCharsets.UTF_8);
	    test8 = AdagioPreprocessor.fileToString(program8, StandardCharsets.UTF_8);
	    test10 = AdagioPreprocessor.fileToString(program10, StandardCharsets.UTF_8);
	  }

	@Test
	public void MusicPieceValidTest() {
		assertAmbiguityFree(parser, test0);
		assertAmbiguityFree(parser, test1);
		assertAmbiguityFree(parser, test2);
		assertAmbiguityFree(parser, test3);
		assertAmbiguityFree(parser, test4);
		assertAmbiguityFree(parser, test5);
		assertAmbiguityFree(parser, test6);
		assertAmbiguityFree(parser, test7);
		assertAmbiguityFree(parser, test8);
		assertAmbiguityFree(parser, test10);
		
	}

}
