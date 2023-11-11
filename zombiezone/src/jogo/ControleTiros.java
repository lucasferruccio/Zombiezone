package jogo;

import java.util.LinkedList;

import jplay.Scene;

public class ControleTiros {
	
	LinkedList<Tiro> tiros = new LinkedList<>();
	
	public void adicionaTiro(double x, double y, int caminho, Scene cena) {
		Tiro tiro = new Tiro(x,y, caminho);
		tiros.addFirst(tiro);
		cena.addOverlay(tiro);;
	}
	
	//movimenta o disparo
	public void run() {
		for (int i = 0; i < tiros.size(); i++) {
			Tiro tiro = tiros.removeFirst();
			tiro.mover();
			tiros.addLast(tiro);
			
		}
	}

}
