package jogo.gui;

import java.awt.Window;
import java.time.LocalTime;
import java.util.LinkedList;
import jogo.controle.*;
import jogo.controle.TiroFuzil;
import jogo.controle.TiroPistola;
import jogo.controle.ControleTiros;
import jplay.Scene;

public class Tiros {
	
	LinkedList<ControleTiros> tiros = new LinkedList<>();
	static LocalTime tempoAtual;
	static LocalTime tempoParaRecarregar = LocalTime.now();
	static long tempoRecarga;
	
	//Cria os tiros
	public void criarTiro(double x, double y, int caminho, Scene cena,int arma) {
		int municoes;
		ControleTiros tiro;
		
		if (arma == 2) {
			tiro = new TiroFuzil(x,y, caminho);
			municoes = TiroFuzil.getMunicoes();
		} else if (arma == 3) {
			tiro = new TiroEspingarda(x,y, caminho);
			municoes = TiroEspingarda.getMunicoes();
		}
		else {
			tiro = new TiroPistola(x,y, caminho);
			municoes = TiroPistola.getMunicoes();
		}
		if (municoes > 0) {
			tempoAtual = LocalTime.now();
			if(tempoAtual.isAfter(tempoParaRecarregar)) {
				if (arma == 2) {
					tiro = new TiroFuzil(x,y, caminho);
					TiroFuzil.setMunicoes(municoes - 1);
					
				} else if (arma == 3){
					tiro = new TiroEspingarda(x,y, caminho);
					TiroEspingarda.setMunicoes(municoes - 1);
				} else {
					tiro = new TiroPistola(x,y, caminho);
					TiroPistola.setMunicoes(municoes - 1);
				}
				tiros.addFirst(tiro);
			} 
		}
	}
	
	public static void recarga(int arma) {
		tempoParaRecarregar = LocalTime.now().plusSeconds(2);
		if (arma == 1) {
			TiroPistola.setMunicoes(12);
		} else if (arma == 2){
			TiroFuzil.setMunicoes(30);
		} else if (arma == 3) {
			TiroEspingarda.setMunicoes(6);
		}
	}
	
	//Movimenta o disparo
	public void run(Window janela, Ator inimigo) {
		//Desenha os tiros na tela
		for (int j = 0; j < tiros.size(); j++) {
			tiros.get(j).draw();
		}
		
		for (int i = 0; i < tiros.size(); i++) {
			ControleTiros tiro = tiros.removeFirst();
			tiro.mover();
			tiros.addLast(tiro);
			
			//Checa se o tiro pegou em algum inimigo
			if (inimigo != null && tiro.collided(inimigo)) {
				//Termina o loop para evitar erros
				i = tiros.size();
				//Remove o tiro do array
				tiros.remove(tiro);
				//Efetua o dano no inimigo
				((Zumbi) inimigo).atacado(tiro.getDano());
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
