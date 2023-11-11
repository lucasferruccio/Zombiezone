package jogo;

import jplay.Keyboard;
import jplay.Scene;
import jplay.URL;
import jplay.Window;

public class Cenario1 {

	//Atributos
	private Window janela;
	private Scene cena;  //Define o arquivo que molda o cenario
	private Jogador jogador;
	private Keyboard teclado;
	private Zumbi zumbi;
	
	//Construtor
	public Cenario1(Window window) {
		janela = window; 
		cena = new Scene(); //Instancia um cena 
		cena.loadFromFile(URL.scenario("Cenario1.scn")); //e carrega o arquivo do cenario
		jogador = new Jogador(640, 350);
		teclado = janela.getKeyboard();
		zumbi = new Zumbi(300, 300);
		
		run(); //Chama o loop infinito
	}
	
	private void run() {
		while(true) {
			
			cena.draw(); //Desenha a cena
			
			jogador.atirar(janela, cena, teclado); //Possibilita o personagem de atirar
			jogador.mover(janela, teclado); //Possibilita o jogador de mover pelo mapa
			jogador.draw(); //Desenha o jogador no mapa
			
			
			zumbi.perseguir(jogador.x, jogador.y); //Faz o zumbi ir atras do jogador
			zumbi.draw(); //Desenha o zumbi na tela
			janela.update(); //E atualiza a janela
			
			
		}
	}
	

}
