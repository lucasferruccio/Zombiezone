package jogo.controle;

import java.util.ArrayList;

public class Zumbi extends Monstro{
	
	private double energia = 100;
	private double ataque = 1;

	public Zumbi(int x, int y) {
		super("zumbi.png");
		this.x = x;
		this.y = y;
		this.velocidade = 1;
	}
	
	//Retorna a quantidade de energia do zumbi
	public double getEnergia() {
		return this.energia;
	}
	
	public void perseguir(double x, double y, ArrayList<Zumbi> zumbis) {
		for (int j = 0; j < zumbis.size(); j++) {
			Zumbi zumbi = zumbis.get(j);
			if (!this.equals(zumbi) && this.collided(zumbi)){
				//System.out.println("Colidindo");
				this.velocidade = 1;
				if (this.x == zumbi.x && this.y == zumbi.y) {
					this.x += 40;
				}
			} else {
				this.velocidade = 2;
			}
		}
		
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
