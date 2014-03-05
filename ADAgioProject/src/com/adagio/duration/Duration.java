package com.adagio.duration;

import com.adagio.language.figures.Figure;

public class Duration {
	private Figure figure;
	
	public Duration(){
		figure = new Figure();
	}
	
	public Duration(Figure figure){
		this.figure = figure;
	}
	
	public Duration(int shape, int numDots){
		figure = new Figure(shape, numDots);
	}

	public double duration(){
		return figure.duration();
	}
	
	public Figure getFigure() {
		return figure;
	}

	public void setFigure(Figure figure) {
		this.figure = figure;
	}
	
	@Override
	public String toString(){
		return figure.toString();
	}
	
	public Duration clone(){
		Duration duration = new Duration();
		duration.figure = this.figure.clone();
		
		return duration;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Duration other = (Duration) obj;
		if (figure == null) {
			if (other.figure != null)
				return false;
		} else if (!figure.equals(other.figure))
			return false;
		return true;
	}
	
	
	
}
