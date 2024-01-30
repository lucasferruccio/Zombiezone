package jogo.gui;

import jplay.GameImage;
import jplay.Keyboard;
import jplay.URL;
import jplay.Window;

public class Final {
	//atributos
	GameImage plano;
	Window janela;
	Keyboard teclado = new Keyboard();

	//Construtor
	public  Final(Window janela) {
		this.janela = janela;
		this.plano = new GameImage(URL.sprite("alerta.png"));
		this.teclado = janela.getKeyboard();
		run();
	}

	//loop da tela
	private void run() {
		while (true) {
			plano.draw();
			janela.update();
		}
	}

}
