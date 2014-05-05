package com.adagio.language.statements.simple;

import org.modelcc.IModel;
import org.modelcc.Suffix;

import com.adagio.language.statements.Statement;

@Suffix(";")
public abstract class SimpleStatement extends Statement implements IModel {}
