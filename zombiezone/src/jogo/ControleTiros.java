package jogo;

import java.util.LinkedList;

import java.Scene;
import Jplay.URL;

public class ControleTiros {
	
	LinkedList<Tiro> tiros = new LinkedList<>();
	
	public void adicionaTiro(double x, double y, int caminho, Scene cena) {
		Tiro tiro = new Tiro(x,y, caminho);
		tiros.addFirst(tiro);
		cena.addOverlay(tiro);
	}
	
	//movimenta o disparo
	public void run() {
		for (int i = 0; i < tiro.size(); i++) {
			Tiro tiro = tiro.removeFist();
			tiro.mover();
			tiros.addLast(tiro);
		}
	}

}
