package jogo.controle;

public class Zumbi extends Monstro{
	
	//Construtor
	public Zumbi(int x, int y) {
		super("zumbi.png");
		//Posição Inicial
		this.x = x;
		this.y = y;
		//Setando as velocidades
		this.velocidadeMax = 2;
		this.velocidade = 2;
		//Setando os status
		this.ataque = 0.5;
		this.energia = 100;
	}
}