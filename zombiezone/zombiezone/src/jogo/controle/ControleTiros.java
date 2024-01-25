package jogo.controle;
import jplay.Sprite;
import jplay.URL;


public class ControleTiros extends Sprite  {
		//Direção do tiro
		public static final int ESQUERDA = 1, DIREITA = 2, CIMA = 3, BAIXO = 4, PARADO = 5;
		
		//Atributos
		protected int VELOCIDADE_TIRO;
		protected int dano;
		protected int caminho = PARADO;
		protected boolean movendo = false;
		protected int direcao = 3;
		
		//Construtor
		public ControleTiros(double x, double y, int caminho) {
			super(URL.sprite("tiro3.png"), 16);
			this.caminho = caminho;
			this.x = x;
			this.y = y;
		}
		
		public int getVELOCIDADE_TIRO() {
			return VELOCIDADE_TIRO;
		}

		public void setVELOCIDADE_TIRO(int vELOCIDADE_TIRO) {
			VELOCIDADE_TIRO = vELOCIDADE_TIRO;
		}

		public int getDano() {
			return dano;
		}

		public void setDano(int dano) {
			this.dano = dano;
		}

		public int getCaminho() {
			return caminho;
		}

		public void setCaminho(int caminho) {
			this.caminho = caminho;
		}

		public boolean isMovendo() {
			return movendo;
		}

		public void setMovendo(boolean movendo) {
			this.movendo = movendo;
		}

		public int getDirecao() {
			return direcao;
		}

		public void setDirecao(int direcao) {
			this.direcao = direcao;
		}

		public double getX(){
			return this.x;
		}
		
		public double getY(){
			return this.y;
		}		
		
		// movimenta do tiro
		public void mover() {
			if (caminho == ESQUERDA) {
				this.x -= VELOCIDADE_TIRO;
				if (direcao != 1) {
					setSequence(4,7);
					
				}
				movendo = true;
			}
			if (caminho == DIREITA) {
				this.x += VELOCIDADE_TIRO;
				if (direcao != 2) {
					setSequence(8,11);
					
				}
				movendo = true;
			}
			if (caminho == CIMA) {
				this.y -= VELOCIDADE_TIRO;
				if (direcao != 4) {
					setSequence(12,15);
				}
				movendo = true;
			}
			if (caminho == BAIXO || caminho == PARADO) {
				this.y += VELOCIDADE_TIRO;
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

