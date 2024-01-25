package jogo.controle;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.Vector;

import jogo.gui.Tiros;
import jogo.gui.Mapa1;
import jogo.gui.Mapa2;
import jplay.Keyboard;
import jplay.Scene;
import jplay.TileInfo;
import jplay.URL;
import jplay.Window;

public class Jogador extends Ator{
	Font fonte = new Font("arial", Font.BOLD, 15);
	
	private double energia = 200;
	private double pontuacao = 0;
	
	//ARMAS: 1 -> PISTOLA / 2 -> FUZIL / 3 -> ESPINGARDA
	private boolean[] armas = {true, true, true}; //A posição é a arma e a chave é se o personagem possui ou não a arma, por padrão ele só possui a pistola
	private int armaAtual = 1;
	
	//Instância da classe de colisões de itens
	Colisao colisao = new Colisao();
	Tiros tiros = new Tiros(); //Instancia do objeto atirar e recarregar 
	
	public double getEnergia() {
		return energia;
	}

	//Construtor
	public Jogador(int x, int y) {
		super(URL.sprite("jogador4.png"), 48);
		//Cordenadas
		this.height = 32;
		this.width = 32;
		this.x = x;
		this.y = y;
		this.setTotalDuration(2000); //Tempo em milisegundos que para mudar os frames
	}
	
	//Gets e setters
	public double getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(double pontuacao) {
		this.pontuacao = pontuacao;
	}

	public int getArma() {
		return armaAtual;
	}

	public void setArma(int arma) {
		this.armaAtual = arma;
	}

	public void setEnergia(double energia) {
		this.energia = energia;
	}

	
	public void atirar(Window janela, Scene cena, Keyboard teclado, Ator inimigo) {
		//Seta a tecla para atirar
		if (teclado.keyDown(KeyEvent.VK_SPACE)) {
			tiros.criarTiro(x, y, direcao, cena, this.getArma());
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
	
	public void recarregar(Keyboard teclado) {
		//Seta a tecla para recarregar
		if (teclado.keyDown(KeyEvent.VK_R)){
			Tiros.recarga(this.getArma());
		}
	}
	
	public void trocarArma(Keyboard teclado) {
		if(teclado.keyDown(KeyEvent.VK_1) && armas[0]) {
			this.armaAtual = 1;
		} else if(teclado.keyDown(KeyEvent.VK_2) && armas[1]) {
			this.armaAtual = 2;
		} else if(teclado.keyDown(KeyEvent.VK_3) && armas[2]) {
			this.armaAtual = 3;
		} 
	}
	
	//Interagir com objetos do mapa
	public void interacao(Scene cena, Keyboard teclado, Window janela, int codigoMapa) {
		if (teclado.keyDown(Keyboard.ENTER_KEY)) {
			//Area de interação do personagem
			Point posicaoMinimo = new Point((int) this.x, (int) this.y);
			Point posicaoMaximo = new Point((int) (this.x + this.width), (int) (this.y + this.height));
	
			//System.out.println(posicaoMinimo);
			//System.out.println(posicaoMaximo);
			
			//Pega os tiles que o personagem esta colidindo 
			Vector<?> objetosDoCenário = cena.getTilesFromRect(posicaoMinimo, posicaoMaximo);
	
			//System.out.println(objetosDoCenário.size());
			//Loop para percorrer todos os objetos do cenário que colidem com a área do peersonagem
			for (int i = 0; i < objetosDoCenário.size(); i++) {
				//Pega o objeto
				TileInfo objeto = (TileInfo) objetosDoCenário.elementAt(i);
				
				
				//Colisao Mapa 1:
				
				if(codigoMapa == 1) {
					//Checa colisao com a porta para Mapa 2
					if (colisao.portaEntradaMapa2(this, objeto)) {
						new Mapa2(janela, this);
					}
				} else if(codigoMapa == 2) {
					//Checa a colisao com a porta para voltar ao Mapa 1
					if (colisao.portaSaidaMapa2(this, objeto)) {
						new Mapa1(janela, this);
					}
				}
			}
		}
	}
	
	//Status:
	
	public void status(Window janela) {
		//Desenha a vida na tela
		janela.drawText("Vida:" + this.energia, 0, 15, Color.green, fonte);
		//Desenha a potunação do jogador na tela
		janela.drawText("Pontos:" + this.getPontuacao(), 0, 460, Color.green, fonte);
		//Desenha a quantidade de munição da arma em mãos
		if (this.getArma() == 1) {
			janela.drawText("Munições:" + TiroPistola.getMunicoes() + "/12", 0, 40, Color.green, fonte);
		} else if (this.getArma() == 2) {
			janela.drawText("Munições:" + TiroFuzil.getMunicoes() + "/30", 0, 40, Color.green, fonte);
		} else if(this.getArma() == 3) {
			janela.drawText("Munições:" + TiroEspingarda.getMunicoes() + "/6", 0, 40, Color.green, fonte);
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
				if(armaAtual == 1) {
					setSequence(4, 8);
				} else if (armaAtual == 2) {
					setSequence(36,40);
				} else if(armaAtual == 3) {
					setSequence(20,24);
				}
				direcao = 1;
			}
			movendo = true;
		}
		if(teclado.keyDown(Keyboard.RIGHT_KEY)) {
			//Nao deixa vazar da tela
			if(this.x < janela.getWidth() - this.width) { 
				this.x += velocidade;
			}
			if (direcao != 2) {
				if(armaAtual == 1) {
					setSequence(8, 12);
				} else if (armaAtual == 2) {
					setSequence(40, 44);
				} else if(armaAtual == 3) {
					setSequence(24, 28);
				}
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
				if(armaAtual == 1) {
					setSequence(12, 16);
				} else if (armaAtual == 2) {
					setSequence(44, 48);
				} else if(armaAtual == 3) {
					setSequence(28, 32);
				}
				direcao = 3;
			}
			movendo = true;
		}
		if(teclado.keyDown(Keyboard.DOWN_KEY)) {
			//Nao deixa vazar da tela
			if(this.y < janela.getHeight() - this.height) { 
				this.y += velocidade;
			}
			if (direcao != 4) {
				if(armaAtual == 1) {
					setSequence(0, 4);
				} else if (armaAtual == 2) {
					setSequence(32, 36);
				} else if(armaAtual == 3) {
					setSequence(16, 20);
				}
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
