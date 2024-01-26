package jogo.controle;

import java.util.ArrayList;

import jplay.URL;

public class Monstro extends Ator{
	
	protected double energia;
	protected double ataque;

	public Monstro(String imagem) {
		super(URL.sprite(imagem), 16);
		this.setTotalDuration(2000); //Tempo em milisegundos que para mudar os frames
	}
	
	//Recebe o dano
	public void atacado(double dano) {
		this.energia -= dano;
	}
	
	//Ataca o jogador caso eles entrem em colisão
	public void atacar(Jogador jogador) {
		if(this.collided(jogador)) {
			jogador.atacado(ataque);
		}
	}
	
	//Retorna a quantidade de energia do zumbi
	public double getEnergia() {
		return this.energia;
	}
	
	//Faz com que o monstro percorra o jogador
	public void perseguir(double x, double y, ArrayList<Monstro> monstros) {
		for (int j = 0; j < monstros.size(); j++) {
			Monstro monstro = monstros.get(j);
			if (!this.equals(monstro) && this.collided(monstro)){
				//System.out.println("Colidindo");
				this.velocidade = 1;
				if (this.x == monstro.x && this.y == monstro.y) {
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