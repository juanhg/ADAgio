package test.com.adagio.language.statements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.modelcc.test.ModelAssert.assertAmbiguityFree;
import static org.modelcc.test.ModelAssert.assertInvalid;

import org.junit.Before;
import org.junit.Test;
import org.modelcc.io.java.JavaModelReader;
import org.modelcc.metamodel.Model;
import org.modelcc.parser.Parser;
import org.modelcc.parser.ParserFactory;

import com.adagio.language.channels.ChannelIdentifier;
import com.adagio.language.channels.channeloptions.ChannelOption;
import com.adagio.language.channels.channeloptions.DestroyCommand;
import com.adagio.language.statements.simple.ChannelStatement;

public class ChannelStatementTest {

	  Model model;
	  Parser<ChannelStatement> parser;

	  @SuppressWarnings("unchecked")
	@Before
	  public void setUp() throws Exception {
	    model = JavaModelReader.read(ChannelStatement.class);
	    parser = ParserFactory.create(model,ParserFactory.WHITESPACE);
	  }
	
	@Test
	public void playSentenceValidtest() {
		assertAmbiguityFree(parser,"CHANNEL GUITARRA ENABLE VOLUME=19");
		assertAmbiguityFree(parser,"CHANNEL GUITARRA ENABLE DISABLE VOLUME=19");
		assertAmbiguityFree(parser,"CHANNEL GUITARRA ENABLE DISABLE VOLUME=19 INSTRUMENT=PIANO");
		assertAmbiguityFree(parser,"channel violin instrument=violin");
		assertAmbiguityFree(parser,"CHANNEL PIANO");
		assertAmbiguityFree(parser,"CHANNEL ENABLE");
		assertAmbiguityFree(parser,"channel destroy");
		assertAmbiguityFree(parser,"CHANNEL a volume = 100");
		assertAmbiguityFree(parser,"CHANNEL VIOLIN VOLUME=0");
		assertAmbiguityFree(parser,"channel piano destroy");
		assertAmbiguityFree(parser, "Channel voz1 melody instrument=voice");
		assertAmbiguityFree(parser, "Channel piano harmony");
		assertAmbiguityFree(parser,"CHANNEL GUITARRA ENABLE DISABLE VOLUME=19 INSTRUMENT=PIANO harmony");
		assertAmbiguityFree(parser,"CHANNEL GUITARRA ENABLE DISABLE VOLUME=19 melody INSTRUMENT=PIANO");
	}
	
	@Test
	public void playSentenceInvalidtest() {
		assertInvalid(parser,"CHANNEL");
		assertInvalid(parser,"CHANNEL PIANO DESTROY ENABLE");
		assertInvalid(parser,"CHANNEL a volume = 101");
		assertInvalid(parser,"CHANNEL VIOLIN VOLUME=-3");
	}
	
	@Test
	public void ChannelStatementReadTest1() throws Exception {
		assertAmbiguityFree(parser, "CHANNEL GUITARRA ");
		ChannelStatement cStat = parser.parse("CHANNEL GUITARRA DESTROY ");
		
		ChannelIdentifier identifier = cStat.getId().clone();
		assertEquals("GUITARRA", identifier.getValue());
		
		ChannelOption [] options = cStat.getOptions();
		assertTrue(options != null);
		
		assertTrue(cStat.getOptions()[0] instanceof DestroyCommand);
	}
}
