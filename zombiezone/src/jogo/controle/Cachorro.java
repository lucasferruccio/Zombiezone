package jogo.controle;

public class Cachorro extends Monstro{
	
	//Construtor
	public Cachorro(int x, int y) {
		super("cachorroMonstro.png");
		//Posição inicial
		this.x = x;
		this.y = y;
		//Setando as velocidades
		this.velocidadeMax = 4;
		this.velocidade = 4;
		//Setando os status
		this.ataque = 1;
		this.energia = 50;
	}
}