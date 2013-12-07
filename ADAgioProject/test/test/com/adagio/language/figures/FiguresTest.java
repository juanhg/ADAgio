package test.com.adagio.language.figures;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.adagio.language.figures.Figure;

public class FiguresTest {

	Figure testFigure;
	
	@Test
	public void DurationTest(){
		double delta = 0.00001;
		
		testFigure = new Figure(4,1);
		assertEquals(3.0,testFigure.duration(),delta);
		
		testFigure = new Figure(2,2);
		assertEquals(1.25,testFigure.duration(),delta);
		
		testFigure = new Figure(32,0);
		assertEquals(32.0,testFigure.duration(),delta);
		
		testFigure = new Figure(64,4);
		assertEquals(34,testFigure.duration(),delta);
	}
	

	@Test
	public void DurationConstructorTest() {
		testFigure = new Figure(4);
		assertEquals(4,testFigure.getShapeDuration().intValue());
		assertEquals(0,testFigure.getDots().length);
		
		testFigure = new Figure(1);
		assertEquals(1,testFigure.getShapeDuration().intValue());
		assertEquals(0,testFigure.getDots().length);
		
		testFigure = new Figure(1.5);
		assertEquals(2,testFigure.getShapeDuration().intValue());
		assertEquals(1,testFigure.getDots().length);
		
		testFigure = new Figure(3);
		assertEquals(4,testFigure.getShapeDuration().intValue());
		assertEquals(1,testFigure.getDots().length);
		
		testFigure = new Figure(2.5);
		assertEquals(4,testFigure.getShapeDuration().intValue());
		assertEquals(2,testFigure.getDots().length);
		
		testFigure = new Figure(4.5);
		assertEquals(8,testFigure.getShapeDuration().intValue());
		assertEquals(3,testFigure.getDots().length);
		
		testFigure = new Figure(6);
		assertEquals(8,testFigure.getShapeDuration().intValue());
		assertEquals(1,testFigure.getDots().length);
		
		testFigure = new Figure(3.3);
		assertEquals(4,testFigure.getShapeDuration().intValue());
		assertEquals(1,testFigure.getDots().length);
		
		testFigure = new Figure(7.5);
	
	}
}
