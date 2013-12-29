package test.com.adagio.language.figures;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.adagio.language.figures.Figure;

public class FigureTest {

	Figure testFigure;
	
	@Test
	public void DurationTest(){
		double delta = 0.00001;
		
		testFigure = new Figure(4,1);
		assertEquals(1.5,testFigure.duration(),delta);
		
		testFigure = new Figure(2,2);
		assertEquals(3.5,testFigure.duration(),delta);
		
		testFigure = new Figure(32,0);
		assertEquals(0.125,testFigure.duration(),delta);
		
		testFigure = new Figure(64,2);
		assertEquals(0.109375,testFigure.duration(),delta);
	}
	

	@Test
	public void DurationConstructorTest() {
		testFigure = new Figure(1.5);
		assertEquals(4,testFigure.getShape().intValue());
		assertEquals(1,testFigure.getDots().length);
		
		testFigure = new Figure(3.5);
		assertEquals(2,testFigure.getShape().intValue());
		assertEquals(2,testFigure.getDots().length);
		
		testFigure = new Figure(0.125);
		assertEquals(32,testFigure.getShape().intValue());
		assertEquals(0,testFigure.getDots().length);
		
		testFigure = new Figure(0.109375);
		assertEquals(64,testFigure.getShape().intValue());
		assertEquals(2,testFigure.getDots().length);
		
	}
	
	
}
