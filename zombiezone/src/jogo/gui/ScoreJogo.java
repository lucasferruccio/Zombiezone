package jogo.gui;

	import java.awt.Color;
	import java.awt.Font;

	

import jogo.controle.Score;
import jplay.GameImage;
import jplay.Keyboard;
import jplay.URL;
import jplay.Window;


	public class ScoreJogo {
		//atributo
		private GameImage plano;
		Window janela;
		Font fonte = new Font("arial", Font.BOLD, 20);
		private String[]nomes = new String[10];
		private double[] pontuacoes = new double[10];
		private Keyboard teclado;
		
		public ScoreJogo(Window janela) {
			Score.leituradedados();
			this.janela = janela;
			this.teclado = janela.getKeyboard();
			this.plano = new GameImage(URL.sprite("ranking.png"));
			System.out.println("2");
			run();
		}
		


		public void printar(Window janela) {
			nomes = Score.getNomes();
			pontuacoes = Score.getPontuacoes();
			int altura = 145;
			for(int i=9;i>=0;i--) {
				 janela.drawText(nomes[i],150,altura,Color.red,fonte);
				 janela.drawText(String.valueOf(pontuacoes[i]),580,altura,Color.red,fonte);
				 altura = altura + 36;
			}
			 }
		
			public void run() {
				while (true) {
					plano.draw();
					printar(janela);
					janela.update();
					
			        if (teclado.keyDown(Keyboard.ESCAPE_KEY)) {
			            new Menu(janela);
			        }
			
			                   }
		}
			 
			 
			 
		}

