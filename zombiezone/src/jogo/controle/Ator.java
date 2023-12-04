package jogo.controle;

import jplay.Sprite;

public abstract class Ator extends Sprite{
	
	//Atributos
	protected double velocidade = 0.2;
	protected int direcao = 5;
	protected boolean movendo = false;
	
	//Construtor
	public Ator(String fileName, int numFrames) {
		super(fileName, numFrames);
	}

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
