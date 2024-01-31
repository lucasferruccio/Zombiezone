package jogo.controle;
import java.time.LocalTime;

import jogo.gui.Tiros;
import jplay.Scene;
import jplay.Sprite;
import jplay.URL;


public class ControleTiros extends Sprite  {
		//Direção do tiro
		public static final int ESQUERDA = 1, DIREITA = 2, CIMA = 3, BAIXO = 4, PARADO = 5;
		
		//Atributos
		protected int velocidadeTiro;
		protected int dano;
		protected int caminho = PARADO;
		protected boolean movendo = false;
		protected int direcao = 3;
		private static LocalTime tempoAtual;
		private static LocalTime tempoParaRecarregar = LocalTime.now();
		
		//Construtor
		public ControleTiros(double x, double y, int caminho) {
			super(URL.sprite("tiro.png"), 16);
			this.caminho = caminho;
			this.x = x;
			this.y = y;
		}
		
		//Gets e Sets
		public int getDano(){
			return dano;
		}
		
		//Cria os tiros
		public static void criarTiro(double x, double y, int caminho, Scene cena,int arma) {
			int municoes;
			ControleTiros tiro;
			
			//Pega
			if (arma == 2) {
				Som.playTiro("Áudio_Metralhadora_Edit.mid");
				tiro = new TiroFuzil(x,y, caminho);
				municoes = TiroFuzil.getMunicoes();
			} else if (arma == 3) {
				Som.playTiro("Áudio_Shotgun_Edit.mid");
				tiro = new TiroEspingarda(x,y, caminho);
				municoes = TiroEspingarda.getMunicoes();
			}
			else {
				Som.playTiro("Áudio_Pistola_Edit.mid");
				tiro = new TiroPistola(x,y, caminho);
				municoes = TiroPistola.getMunicoes();
			}
			if (municoes > 0) {
				tempoAtual = LocalTime.now();
				if(tempoAtual.isAfter(tempoParaRecarregar)) {
					if (arma == 2) {
						tiro = new TiroFuzil(x,y, caminho);
						TiroFuzil.setMunicoes(municoes - 1);
						
					} else if (arma == 3){
						tiro = new TiroEspingarda(x,y, caminho);
						TiroEspingarda.setMunicoes(municoes - 1);
					} else {
						tiro = new TiroPistola(x,y, caminho);
						TiroPistola.setMunicoes(municoes - 1);
					}
					Tiros.adicionarTiro(tiro);
				} 
			}
		}
		
		//Recarga de armas
		public static void recarga(int arma) {
			tempoParaRecarregar = LocalTime.now().plusSeconds(2);
			if (arma == 1) {
				TiroPistola.recargaArma();
			} else if (arma == 2){
				TiroFuzil.recargaArma();
			} else if (arma == 3) {
				TiroEspingarda.recargaArma();
			}
		}		
		
		//Movimentação do Tiro do tiro
		public void mover() {
			if (caminho == ESQUERDA) {
				this.x -= velocidadeTiro;
				if (direcao != 1) {
					setSequence(4,7);
					
				}
				movendo = true;
			}
			if (caminho == DIREITA) {
				this.x += velocidadeTiro;
				if (direcao != 2) {
					setSequence(8,11);
					
				}
				movendo = true;
			}
			if (caminho == CIMA) {
				this.y -= velocidadeTiro;
				if (direcao != 4) {
					setSequence(12,15);
				}
				movendo = true;
			}
			if (caminho == BAIXO || caminho == PARADO) {
				this.y += velocidadeTiro;
				if (direcao != 5) {
					setSequence(0,3);
				}
				movendo = true;	
			}
			
			
			if (movendo) {
				update();
				movendo = false;
			}
		}
	}

