package com.adagio.language.figures;

import org.modelcc.Constraint;
import org.modelcc.IModel;
import org.modelcc.Multiplicity;
import org.modelcc.Optional;
import org.modelcc.types.IntegerModel;


public class Figure implements IModel {
	
	IntegerModel shape;

	/**
	 * @return True is duration is pow of 2. False in other case.
	 */
	@Constraint
	boolean validShape() {
		int result = 0;
		boolean isValid = false;
		
		if(shape.intValue() >= 0) {
			for (int i = 0; result < shape.intValue(); i++) {
				result = (int) Math.pow(2.0, i);
				if (result == shape.intValue()) {
					isValid = true;
				}
			}
		}
		
		if(isValid == false){
			System.err.println("Error 11: \"" + shape.intValue() +"\" it's not a valid figure shape.\n");
			System.exit(11);
		}
		return isValid;
	}

	@Optional
	@Multiplicity(minimum = 1)
	FigureDot [] dots;
	
	/**
	 * Default constructor.
	 */
	public Figure(){}
	
	/**
	 * Attributes constructor.
	 * @param shape duration of the shape 
	 * @param dots Vector of dots
	 */
	public Figure(IntegerModel shape, FigureDot [] dots){
		this.shape = shape;
		if(this.dots != null){
			this.dots= dots.clone();
		}
	}
	
	/**
	 * Primitive constructor.
	 * @param shape int value duration of the shape
	 * @param dotsNum Number of dots
	 */
	public Figure(int shape, int dotsNum) {

		this.shape = new IntegerModel(shape);
		this.dots = new FigureDot[dotsNum];
		if (!this.validShape()) {
			System.err.println("Error 11: \"" + shape + "\" it's not a valid shape duration.\n");
			System.exit(11);
		}
	}
	

	/**
	 * If duration is valid, it will create a figure with the indicated duration.
	 * If duration is not valid, it will create a figure with null attributes;
	 * @param duration Figure's duration. (4 whole note, 2 half note, ...)
	 */
	public Figure(double duration){
		double figDur = Double.MAX_VALUE;
		int dotsNum = 0;
		Figure figure = null;
		
		for(int i = 0; figDur > duration; i++){
			figure = (new Figure((int)Math.pow(2.0, i),0));
			figDur = figure.duration();
		}
		
		double originalFigDur = figDur;
		for(int i = 0; figDur < duration; i++){
			figDur += originalFigDur / (Math.pow(2.0, i+1));
			dotsNum++;
		}
		
		if(figDur == duration){
			this.shape = figure.shape;
			this.dots = new FigureDot[dotsNum];
		}
		else{
			this.shape = null;
			this.dots = null;
			System.err.println("Warning 12: \"" + duration+ "\" it's not a valid duration");
		}
	}
	
	/**
	 * Calculates the figure's duration (4 whole note, 2 half note, ...)
	 * @return
	 */
	public double duration(){
		double baseDuration = 4.0/this.shape.intValue();
		double duration = baseDuration;
		
		if(this.dots != null){
			for(int i = 0; i < this.dots.length;i++){
				duration += baseDuration/(Math.pow(2.0, i+1));
			}
		}
		return duration;
	}
	
	/**
	 * Checks if a duration is a valid one
	 * @param duration Figure's duration. (4 whole note, 2 half note, ...)
	 * @return True if is valid. False in other case.
	 */
 	static public boolean validDuration(double duration){
 		double figDur = Double.MAX_VALUE;
		
		for(int i = 0; figDur > duration; i++){
			figDur = (new Figure((int)Math.pow(2.0, i),0)).duration();
		}
		
		double originalFigDur = figDur;
		for(int i = 0; figDur < duration; i++){
			figDur += originalFigDur / (Math.pow(2.0, i+1));
		}
		
		if(figDur == duration){
			return false;
		}
		else{
			return true;
		}
	}
	
	public IntegerModel getShape() {
		return shape;
	}

	public void setShape(IntegerModel shapeDuration) {
		this.shape = shapeDuration;
	}

	public FigureDot[] getDots() {
		return dots;
	}

	public void setDots(FigureDot[] dots) {
		this.dots = dots;
	}
	
	public Figure clone(){
		Figure cloned = new Figure(this.shape,this.dots);
		return cloned;
	}
	
}

