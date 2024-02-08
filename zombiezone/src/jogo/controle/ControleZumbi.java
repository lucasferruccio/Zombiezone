package jogo.controle;

public class ControleZumbi extends ControleMonstro{
	
	//Construtor
	public ControleZumbi(int x, int y) {
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