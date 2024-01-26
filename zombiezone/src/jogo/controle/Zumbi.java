package jogo.controle;

public class Zumbi extends Monstro{

	public Zumbi(int x, int y) {
		super("zumbi2.png");
		this.x = x;
		this.y = y;
		this.velocidade = 1;
		this.ataque = 1;
		this.energia = 100;
	}
}