package jogo.gui; 

import java.awt.event.KeyEvent;

import jplay.GameImage;
import jplay.Keyboard;
import jplay.URL;
import jplay.Window;

public class Main {

	public static void main(String[] args) {
		
		//Construindo a janela do jogo
		Window janela = new Window(800, 480); //Instanciando uma janela e setando o tamanho
		GameImage plano = new GameImage(URL.sprite("menu.png")); //Carrega a imagem de fundo da tela de menu
		
		//Faz o reconhecimento de que o teclado foi apertado
		Keyboard teclado = janela.getKeyboard();
		//Adiciona a tecla R
		teclado.addKey(KeyEvent.VK_R);
		//Adiciona a tecla 1
		teclado.addKey(KeyEvent.VK_1);
		//Adiciona a tecla 2
		teclado.addKey(KeyEvent.VK_2);
		//Adiciona a tecla 3
		teclado.addKey(KeyEvent.VK_3);
		
		
		//Loop infinito onde o jogo roda
		while(true) {
			plano.draw(); //Exibe a imagem na tela
			janela.update(); //Atualiza a janela enquanto o loop roda
			
			//Reconhece que a tecla ENTER foi apertada 
			if(teclado.keyDown(Keyboard.ENTER_KEY)) {
				new Mapa1(janela); //Cria a janela passando a janela como parametro 
			}
		}
		//Comentario
	}
}
