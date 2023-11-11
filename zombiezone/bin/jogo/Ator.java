package jogo;

import jplay.Sprite;

public class Ator extends Sprite{
	
	//Atributos
	protected double velocidade = 0.05;
	int direcao = 5;
	boolean movendo = false;
	
	//Construtor
	public Ator(String fileName, int numFrames) {
		super(fileName, numFrames);
	}
	
}
