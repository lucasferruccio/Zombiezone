package jogo;

import jplay.Sprite;

public class Tiro extends Sprite {
	
	//Direção do tiro
	public static final int LEFT = 1, RIGHT = 2, STOP = 3, UP = 4, DOWN = 5;
	
	//Atributos
	protected static final int VELOCIDADE_TIRO = 20;
	protected int caminho = STOP;
	protected boolean movendo = false;
	protected int direcao = 3;
	
	//Construtor
	public Tiro(double x, double y, int caminho) {
		super(URL.sprit("tiro.png"), 12);
		this.caminho = caminho;
		this.x = x;
		this.y = y;
	}
	
	// movimenta do tiro
	public void mover () {
		if (caminho == LEFT) {
			this.x -= VELOCIDADE_TIRO;
			if (direção != 1) {
				setSequence(3,6);
				
			}
			movendo = true;
		}
		if (caminho == Rith) {
			this.x += VELOCIDADE_TIRO;
			if (direção != 2) {
				setSequence(6,9);
				
			}
			movendo = true;
		}
		if (caminho == UP) {
			this.y -= VELOCIDADE_TIRO;
			if (direção != 4) {
				setSequence(9,12);
			}
			movendo = true;
		}
		if (caminho == DOWN || caminho == STOP) {
			this.y += VELOCIDADE_TIRO;
			if (direção != 5) {
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
