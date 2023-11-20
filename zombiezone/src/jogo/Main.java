package jogo;

//import javax.swing.JOptionPane; JOptionPane.showMessageDialog(null, "Funcionando"); Exibe na tela uma msg só para confirmar

import jplay.GameImage;
import jplay.Keyboard;
import jplay.URL;
import jplay.Window;

public class Main {

	public static void main(String[] args) {
		
		//Construindo a janela do jogo
		Window janela = new Window(1100, 618); //Instanciando uma janela e setando o tamanho
		GameImage plano = new GameImage(URL.sprite("menu.png")); //Carrega a imagem de fundo da tela de menu
		
		//Faz o reconhecimento de que o teclado foi apertado
		Keyboard teclado = janela.getKeyboard();
	
		//Loop infinito onde o jogo roda
		while(true) {
			plano.draw(); //Exibe a imagem na tela
			janela.update(); //Atualiza a janela enquanto o loop roda
			
			//Reconhece que a tecla ENTER foi apertada 
			if(teclado.keyDown(Keyboard.ENTER_KEY)) {
				new MapaPrincipal(janela); //Cria a janela passando a janela como parametro 
			}
		}
		
	}

}
