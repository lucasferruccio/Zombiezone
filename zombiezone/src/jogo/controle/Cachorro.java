package jogo.controle;

public class Cachorro extends Monstro{

	public Cachorro(int x, int y) {
		super("cachorroMonstro.png");
		this.x = x;
		this.y = y;
		this.velocidadeMax = 4;
		this.velocidade = 4;
		this.ataque = 1;
		this.energia = 50;
	}
}