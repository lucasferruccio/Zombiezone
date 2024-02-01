package jogo.gui; 

import java.awt.event.KeyEvent;

import jogo.controle.Som;
import jogo.repositorio.Score;
import jplay.Keyboard;
import jplay.Window;

public class Main {
      

	public static void main(String[] args) {
		
		//Atributos
		//Construindo a janela do jogo
		Window janela = new Window(800, 480); //Instanciando uma janela e setando o tamanho
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
		
		//Abre a tela de menu
		Som.playMusica("AudioMenu.wav"); //Toca a musica tema do jogo
		Score.leituradedados();
		new Menu(janela);
		
	}
}
						
						
						

		
		

