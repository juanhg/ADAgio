package com.adagio.language.tempos;

import org.modelcc.IModel;
import org.modelcc.Prefix;
import org.modelcc.types.IntegerModel;

import com.adagio.language.figures.Figure;


public class Tempo implements IModel{
	
	Figure figure;
	@Prefix("=")
	IntegerModel bps;
	
	public Tempo(){}
	
	/**
	 * Attributes constructor
	 * @param figure 
	 * @param bps
	 */
	public Tempo(Figure figure, IntegerModel bps){
		this.figure = (Figure) figure.clone();
		this.bps = bps;
	}
	
	public Tempo(Figure figure, int bps){
		this.figure = figure.clone();
		this.bps = new IntegerModel(bps);
	}
	

	public Figure getFigure() {
		return figure;
	}

	public void setFigure(Figure figure) {
		this.figure = figure;
	}

	public IntegerModel getBps() {
		return bps;
	}

	public void setBps(IntegerModel bps) {
		this.bps = bps;
	}
	
	public Tempo clone(){
		Tempo cloned = new Tempo();
		cloned.figure = this.figure.clone();
		cloned.bps = this.bps;
		return cloned;
	}
}
