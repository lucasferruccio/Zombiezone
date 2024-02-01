package jogo.gui;

import java.awt.Color;
import java.awt.Font;

import jogo.repositorio.Score;
import jplay.GameImage;
import jplay.Keyboard;
import jplay.Window;


public class InterfaceScore {
	//atributos
	private GameImage plano;
	private String[]nomes = new String[10];
	private double[] pontuacoes = new double[10];
	private Keyboard teclado;
	private Window janela;
	Font fonte = new Font("arial", Font.BOLD, 20);
	
	//Construtor
	public InterfaceScore(Window janela) {
		Score.leituradedados(); //Faz a leitura do arquivo
		this.janela = janela;
		this.teclado = janela.getKeyboard(); //Pega o teclado
		this.plano = new GameImage("src/recursos/cenarios/ranking.png");
		run();
	}
	
	//Desenha os nomes e pontuações na tela
	public void desenharNomes(Window janela) {
		//Pega os nomes e pontuações dos jogadores
		nomes = Score.getNomes();
		pontuacoes = Score.getPontuacoes();
		int altura = 145; //Altura Inicial
		//Loop pra percorrer as pontuações
		for(int i=9;i>=0;i--) {
			if(!nomes[i].equalsIgnoreCase("null")) {
				janela.drawText(nomes[i],150,altura,Color.red,fonte);
				janela.drawText(String.valueOf(pontuacoes[i]),580,altura,Color.red,fonte);
				altura = altura + 36;
			}
		}
	 }
	
	//Loop da tela de pontuação
	public void run() {
		while (true) {
			plano.draw();
			desenharNomes(janela);
			janela.update();
			
	        if (teclado.keyDown(Keyboard.ESCAPE_KEY)) {
	            new Menu(janela);
	        }
	    }
	}	 
}

