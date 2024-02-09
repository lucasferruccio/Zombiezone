package jogo.gui;

import java.util.LinkedList;
import jogo.controle.ControleBala;

public class InterfaceTiros {
	
	public static void  desenharTiros(LinkedList<ControleBala> tiros) {
		for (int j = 0; j < tiros.size(); j++) {
			tiros.get(j).draw();
		}
	}
}
