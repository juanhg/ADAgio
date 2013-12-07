package com.adagio.language.times;

import org.modelcc.IModel;
import org.modelcc.Prefix;
import org.modelcc.types.IntegerModel;

import com.adagio.language.figures.Figure;

public class Time implements IModel {
	IntegerModel beats;
	@Prefix("/")
	Figure figure;
	
	public Time(){}
	
	public Time(IntegerModel beats, Figure figure){
		this.beats = beats;
		this.figure = figure.clone();
	}
	
	public Time(int beats, double figureDuration){
		this.beats = new IntegerModel(beats);
		figure = new Figure(figureDuration);
	}
	
	/**
	 * Gets the default duration 
	 * @return If figure duration and beats are divisible returns figureDuration/beats. In other
	 *         case returns figure's shape duration
	 */
	public int defaultDuration() {

		double figureDuration = figure.duration();

		if (0 == (figureDuration % beats.intValue())) {
			if (figureDuration >= beats.intValue()) {
				return (int) figureDuration/beats.intValue();
			} else {
				return 1;
			}
		} else {
			//TODO Should return figure.getShapeDuration() + ...
			return	figure.getShapeDuration().intValue();
		}
	}

	public IntegerModel getBeats() {
		return beats;
	}

	public void setBeats(IntegerModel beats) {
		this.beats = beats;
	}

	public Figure getFigure() {
		return figure;
	}

	public void setFigure(Figure figure) {
		this.figure = figure;
	}
}
