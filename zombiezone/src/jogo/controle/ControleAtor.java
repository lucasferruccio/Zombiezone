package jogo.controle;

import jplay.Sprite;

public abstract class ControleAtor extends Sprite{
	
	//Atributos
	protected double velocidade = 3;
	protected int direcao = 5;
	protected boolean movendo = false;
	
	//Construtor
	public ControleAtor(String fileName, int numFrames) {
		super(fileName, numFrames);
	}
	
	//Gets e Sets
	public double getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(double velocidade) {
		this.velocidade = velocidade;
	}

	public int getDirecao() {
		return direcao;
	}

	public void setDirecao(int direcao) {
		this.direcao = direcao;
	}

	public boolean isMovendo() {
		return movendo;
	}

	public void setMovendo(boolean movendo) {
		this.movendo = movendo;
	}
}