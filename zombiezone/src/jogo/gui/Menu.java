package jogo.gui;

import jplay.GameImage;
import jplay.Keyboard;
import jplay.URL;
import jplay.Window;

public class Menu {
	//atributos
	Window janela;
	GameImage plano;
	Keyboard teclado = new Keyboard();
	
	public Menu(Window janela) {
		this.janela = janela;
		this.teclado = janela.getKeyboard();
		this.plano = new GameImage(URL.sprite("menu.png"));
		System.out.println("1"); 
		run();
	}
	
	
	public void run() {
		while (true) {
			plano.draw();
			janela.update();

	        if (teclado.keyDown(Keyboard.ENTER_KEY)) {
	            new Mapa1(janela);
	        }
	
	        if (teclado.keyDown(Keyboard.SPACE_KEY)) {
	            new ScoreJogo(janela);
	              
	                                                  }
	                   }
}
}
