package com.adagio.io;

import java.io.PrintWriter;

import com.adagio.events.MusicEventListener;
import com.adagio.language.MusicPiece;

public abstract class MusicPieceWriter implements MusicEventListener {
	 public abstract void write(MusicPiece m,PrintWriter out);
}
