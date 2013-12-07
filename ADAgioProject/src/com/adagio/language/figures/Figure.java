package com.adagio.language.figures;

import org.modelcc.Constraint;
import org.modelcc.IModel;
import org.modelcc.Multiplicity;
import org.modelcc.Optional;
import org.modelcc.types.IntegerModel;


public class Figure implements IModel {
	
	IntegerModel shapeDuration;

	/**
	 * @return True is duration is pow of 2. False in other case.
	 */
	@Constraint
	boolean validShapeDuration() {
		int result = 1;
		boolean isValid = false;
		
		if(shapeDuration.intValue() >= 0) {
			for (int i = 0; result < shapeDuration.intValue(); i++) {
				result = (int) Math.pow(2.0, i);
				if (result == shapeDuration.intValue()) {
					isValid = true;
				}
			}
		}
		
		if(isValid == false){
			System.err.println("Error 11: \"" + shapeDuration.intValue() +"\" it's not a valid shape duration.\n");
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
	public Figure(IntegerModel shapeDuration, FigureDot [] dots){
		this.shapeDuration = shapeDuration;
		this.dots= dots.clone();
	}
	
	/**
	 * Primitive constructor.
	 * @param shape int value duration of the shape
	 * @param dotsNum Number of dots
	 */
	public Figure(int shapeDuration, int dotsNum) {

		this.shapeDuration = new IntegerModel(shapeDuration);
		this.dots = new FigureDot[dotsNum];
		if (!this.validShapeDuration()) {
			System.err.println("Error 11: \"" + shapeDuration + "\" it's not a valid shape duration.\n");
			System.exit(11);
		}
	}
	
	/**
	 * Duration constructor.
	 * Construct the figure that have the indicated total duration
	 * (RECIPROCAL DURATION RESPECT THE Whole Note)
	 * 2 - Half Note
	 * 4 - Quarter Note
	 * 8 - Eight Note
	 * etc
	 * @param finalDuration RECIPROCAL DURATION VALUE RESPECT THE WHOLE NOTE
	 */
	public Figure(double finalDuration){
		int powResult = 0;
		int shape = 0;
		int dots= 0;
		double dotResult = 0.0;
		boolean isValidDuration = false;
		
		if(finalDuration > 0) {
			for (int i = 0; powResult < finalDuration; i++) {
				powResult = (int) Math.pow(2.0, i);
			}
			if(finalDuration != shape){
				dotResult = powResult;
				for(int j = 1; dotResult > finalDuration; j++){
					dotResult -= (powResult/2)/Math.pow(2.0, j); 
					dots++;
				}
				if(dotResult == finalDuration){
					isValidDuration = true;
				}
			}
			else{
				isValidDuration = true;
			}
		}
		
		if(isValidDuration){
			this.shapeDuration = new IntegerModel(powResult);
			this.dots = new FigureDot[dots];
		}
		else{
			System.err.println("Warning 12: \"" + finalDuration+ "\" it's not a valid total duration."
					+ " Closest figure generated.");
			this.shapeDuration = new IntegerModel(powResult);
			this.dots = new FigureDot[dots];
		}
	}
	
	/**
	 * Obtains the reciprocal duration value respect the whole note
	 * @return The duration value of the note repect the whole note
	 */
	public double duration(){
		double duration = this.shapeDuration.intValue();
		double firstDuration = duration;
		
		if(this.dots != null){
			for(int i = 1; i <= this.dots.length; i++){
				duration -= (firstDuration/2)/Math.pow(2.0, i);
			}
		}
		
		return duration;
	}

	public IntegerModel getShapeDuration() {
		return shapeDuration;
	}

	public void setShapeDuration(IntegerModel shapeDuration) {
		this.shapeDuration = shapeDuration;
	}

	public FigureDot[] getDots() {
		return dots;
	}

	public void setDots(FigureDot[] dots) {
		this.dots = dots;
	}
	
	public Figure clone(){
		Figure cloned = new Figure(this.shapeDuration,this.dots);
		return cloned;
	}
	
	
	
}

