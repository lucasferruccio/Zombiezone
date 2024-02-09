package jogo.controle;

import java.awt.Window;
import java.util.LinkedList;

import jogo.gui.InterfaceTiros;

public class ControleTiros {
	private static LinkedList<ControleBala> tiros = new LinkedList<>();
	
	public static void adicionarTiro(ControleBala tiro) {
		tiros.add(tiro);
	}
	
	//Movimenta o disparo
	public void run(Window janela, ControleMonstro inimigo) {
		//Desenha os tiros na tela\\
		
		InterfaceTiros.desenharTiros(tiros);
		
		for (int i = 0; i < tiros.size(); i++) {
			ControleBala tiro = tiros.removeFirst();
			tiro.mover();
			tiros.addLast(tiro);
			
			//Checa se o tiro pegou em algum inimigo
			if (inimigo != null && tiro.collided(inimigo)) {
				//Termina o loop para evitar erros
				i = tiros.size();
				//Remove o tiro do array
				tiros.remove(tiro);
				//Efetua o dano no inimigo
				inimigo.atacado(tiro.getDano());
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
