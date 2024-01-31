package jogo.gui;

import jogo.controle.MapaControle;
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
		MapaControle.finalJogo();
		
		while (true) {
			plano.draw();
			janela.update();
			
			//ESC para ir para o menu
	        if (teclado.keyDown(Keyboard.ESCAPE_KEY)) {
	            new Menu(janela); 
            }
		}
	}

}
