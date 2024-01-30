package jogo.controle;

public class Zumbi extends Monstro{

	public Zumbi(int x, int y) {
		super("zumbi.png");
		this.x = x;
		this.y = y;
		this.velocidadeMax = 2;
		this.velocidade = 2;
		this.ataque = 0.5;
		this.energia = 100;
	}
}