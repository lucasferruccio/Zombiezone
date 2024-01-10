package jogo.controle;

import java.awt.event.KeyEvent;

import jogo.gui.ControleTiros;
import jplay.Keyboard;
import jplay.Scene;
import jplay.URL;
import jplay.Window;

public class Jogador extends Ator{
	
	private double energia = 10000;
	private double pontuacao = 0;
	
	public double getEnergia() {
		return energia;
	}

	//Construtor
	public Jogador(int x, int y) {
		super(URL.sprite("jogador.png"), 16);
		//Cordenadas
		this.x = x;
		this.y = y;
		this.setTotalDuration(2000); //Tempo em milisegundos que para mudar os frames
	}
	
	ControleTiros tiros = new ControleTiros(); //Instancia do objeto atirar
	
	public void atirar(Window janela, Scene cena, Keyboard teclado, Ator inimigo) {
		//Seta a tecla para atirar
		if (teclado.keyDown(KeyEvent.VK_SPACE)) {
			tiros.criarTiro(x, y, direcao, cena);
		}
		tiros.run(janela, inimigo); //Faz o tiro andar
	}
	
	//Aumenta a quantidade de pontos
	public void receberPontos() {
		this.pontuacao += 10;
	}
	
	//Recebe o dano do zumbi
	public void atacado(double dano) {
		this.energia -= dano;
		//Fecha o jogo caso o jogador fique sem vida
		if(this.energia <= 0) {
			System.out.println("Pontuação: " + this.pontuacao);
			System.exit(0);
		}
	}
	
	//Movimentacao do jogador
	public void mover(Window janela, Keyboard teclado) {
		
		//Checa qual tecla o teclado esta precionando 
		if(teclado.keyDown(Keyboard.LEFT_KEY)) {
			//Nao deixa vazar da tela
			if(this.x > 0) { 
				this.x -= velocidade;
			}
			if (direcao != 1) {
				setSequence(4, 8);
				direcao = 1;
			}
			movendo = true;
		}
		if(teclado.keyDown(Keyboard.RIGHT_KEY)) {
			//Nao deixa vazar da tela
			if(this.x < janela.getWidth() - 60) { 
				this.x += velocidade;
			}
			if (direcao != 2) {
				setSequence(8, 12);
				direcao = 2;
			}
			movendo = true;
		}
		if(teclado.keyDown(Keyboard.UP_KEY)) {
			//Nao deixa vazar da tela
			if(this.y > 0) { 
				this.y -= velocidade;
			}
			if (direcao != 3) {
				setSequence(12, 16);
				direcao = 3;
			}
			movendo = true;
		}
		if(teclado.keyDown(Keyboard.DOWN_KEY)) {
			//Nao deixa vazar da tela
			if(this.y < janela.getHeight() - 60) { 
				this.y += velocidade;
			}
			if (direcao != 4) {
				setSequence(0, 4);
				direcao = 4;
			}
			movendo = true;
		}
		
		//Atualiza as sprites
		if (movendo) {
			update(); 
			movendo = false;
		}
	}
}
