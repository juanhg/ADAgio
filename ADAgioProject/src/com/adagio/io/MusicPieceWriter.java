package com.adagio.io;

import java.io.PrintWriter;
import com.adagio.language.MusicPiece;

public abstract class MusicPieceWriter {
	 public abstract void write(MusicPiece m,PrintWriter out);
}
