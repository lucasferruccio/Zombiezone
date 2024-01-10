package jogo.gui;

import java.awt.Window;
import java.util.LinkedList;

import jogo.controle.Ator;
import jogo.controle.Tiro;
import jogo.controle.Zumbi;
import jplay.Scene;

public class ControleTiros {
	
	LinkedList<Tiro> tiros = new LinkedList<>();
	
	//Cria os tiros
	public void criarTiro(double x, double y, int caminho, Scene cena) {
		Tiro tiro = new Tiro(x,y, caminho);
		tiros.addFirst(tiro);
	}
	
	//Movimenta o disparo
	public void run(Window janela, Ator inimigo) {
		//Desenha os tiros na tela
		for (int j = 0; j < tiros.size(); j++) {
			tiros.get(j).draw();
		}
		
		for (int i = 0; i < tiros.size(); i++) {
			Tiro tiro = tiros.removeFirst();
			tiro.mover();
			tiros.addLast(tiro);
			
			//Checa se o tiro pegou em algum inimigo
			if (inimigo != null && tiro.collided(inimigo)) {
				//Termina o loop para evitar erros
				i = tiros.size();
				//Remove o tiro do array
				tiros.remove(tiro);
				//Efetua o dano no inimigo
				((Zumbi) inimigo).atacado(250);
			}
			//Caso o tiro chegue no limite da tela ele e removido
			else if (tiro.getY() > janela.getHeight() | tiro.getY() < 0) {
				i = tiros.size();
				tiros.remove(tiro);
			} else if (tiro.getX() > janela.getWidth() | tiro.getX() < 0) {
				i = tiros.size();
				tiros.remove(tiro);
			}
		}
		 
		
	}

}
