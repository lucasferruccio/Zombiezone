package jogo.gui;

import jogo.controle.ControleMapa;
import jplay.GameImage;
import jplay.Keyboard;
import jplay.Window;

public class InterfaceMenu {
	//atributos
	Window janela;
	GameImage plano;
	Keyboard teclado = new Keyboard();
	
	//Construtor
	public InterfaceMenu(Window janela) {
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
	        	ControleMapa.iniciarJogo();
	            new InterfaceMapa1(janela);
	        } 
	        
	        //Espaço para ir para o score board
	        if (teclado.keyDown(Keyboard.SPACE_KEY)) {
	            new InterfaceScore(janela); 
            }
	    }    
	}
}
