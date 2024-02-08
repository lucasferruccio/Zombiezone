package jogo.controle;

public class ControleCachorro extends ControleMonstro{
	
	//Construtor
	public ControleCachorro(int x, int y) {
		super("cachorroMonstro.png");
		//Posição inicial
		this.x = x;
		this.y = y;
		//Setando as velocidades
		this.velocidadeMax = 3;
		this.velocidade = 3;
		//Setando os status
		this.ataque = 1;
		this.energia = 50;
	}
}