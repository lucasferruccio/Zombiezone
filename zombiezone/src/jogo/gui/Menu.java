package jogo.gui;

import jogo.controle.MapaControle;
import jplay.GameImage;
import jplay.Keyboard;
import jplay.Window;

public class Menu {
	//atributos
	Window janela;
	GameImage plano;
	Keyboard teclado = new Keyboard();
	
	//Construtor
	public Menu(Window janela) {
		this.janela = janela;
		this.teclado = new Keyboard();
		this.teclado = janela.getKeyboard(); //Reconhece o teclado
		this.plano = new GameImage("src/recursos/cenarios/menu.png"); //Adiciona uma imgaem de fundo
		run();
	}
	
	//Loop do menu
	public void run() {
		teclado.addKey(Keyboard.ENTER_KEY);
		
		while (true) {
			plano.draw();
			janela.update();

			//Enter para iniciar o jogo
	        if (teclado.keyDown(Keyboard.ENTER_KEY)) {
	        	System.out.println(9);
	        	MapaControle.iniciarJogo();
	            new Mapa1(janela);
	        } 
	        
	        //Espaço para ir para o score board
	        if (teclado.keyDown(Keyboard.SPACE_KEY)) {
	        	System.out.println(10 );
	            new InterfaceScore(janela); 
            }
	    }    
	}
}
