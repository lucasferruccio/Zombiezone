package jogo;

import jplay.URL;

public class Zumbi extends Ator{

	public Zumbi(int x, int y) {
		super(URL.sprite("zumbi.png"), 16);
		this.x = x;
		this.y = y;
		this.setTotalDuration(2000); //Tempo em milisegundos que para mudar os frames
		this.velocidade = 0.2;
	}
	
	public void perseguir(double x, double y) {
		
		//Movimentacao do personagem e atualizacao do sprite baseado na posicao do jogador
		if (this.x > x && this.y <= y + 50 && this.y >= y - 50) {
			moveTo(x, y, velocidade);
			if (direcao != 1) {
				setSequence(5, 8);
				direcao = 1;
			}
			movendo = true;
		} else if (this.x < x && this.y <= y + 50 && this.y >= - 50) {
			moveTo(x , y, velocidade);
			if (direcao != 2) {
				setSequence(9, 12);
				direcao = 2;
			}
			movendo = true;
		} else if (this.y > y) {
			moveTo(x, y, velocidade);
			if (direcao != 3) {
				setSequence(13, 16);
				direcao = 3;
			}
			movendo = true;
		} else if (this.y < y) {
			moveTo(x, y, velocidade);
			if (direcao != 4) {
				setSequence(1, 4);
				direcao = 4;
			}
			movendo = true;
		}
		
		if(movendo) {
			update();
			movendo = false;
		}
		
	}

}
