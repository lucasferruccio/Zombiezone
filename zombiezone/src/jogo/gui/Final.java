package jogo.gui;

import jplay.GameImage;
import jplay.Keyboard;
import jplay.URL;
import jplay.Window;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class Final {
	//atributos
	GameImage plano;
	Window janela;
	Keyboard teclado = new Keyboard();

	
	public  Final(Window janela) {
		this.janela = janela;
		this.plano = new GameImage(URL.sprite("alerta.png"));
		this.teclado = janela.getKeyboard();
		run();
	}


	private void run() {
		while (true) {
			plano.draw();
			janela.update();
	}
	}

}
