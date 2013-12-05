package com.adagio.language.times;

import org.modelcc.IModel;
import org.modelcc.Prefix;
import org.modelcc.types.IntegerModel;

import com.adagio.language.figures.Figure;

public class Time implements IModel {
	IntegerModel beats;
	@Prefix("/")
	Figure figure;
}
