package com.adagio.language.statements;

import org.modelcc.IModel;
import org.modelcc.Prefix;
import org.modelcc.Separator;

import com.adagio.events.MusicEventListener;
import com.adagio.language.bars.Bar;

@Prefix( {"(?i)Test", "[ |\n|\t|\r]+"} )
public class TestStatement extends Statement implements IModel {


	    @Separator("\\|")
		Bar [] bars ;

		@Override
		public void run(MusicEventListener listener) {
			// TODO Auto-generated method stub
			
		}
	
}
