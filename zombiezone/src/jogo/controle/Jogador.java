package jogo.controle;
import java.awt.event.KeyEvent;
import jogo.gui.Tiros;
import jogo.repositorio.Score;
import jogo.gui.Mapa1;
import jogo.gui.Mapa2;
import jogo.gui.Mapa3;
import jplay.Keyboard;
import jplay.Scene;
import jplay.URL;
import jplay.Window;
import java.util.Scanner;
import jogo.gui.Final;
import jogo.gui.InterfaceJogo;

public class Jogador extends Ator{
	
	private Score tabela = new Score();
	private String nome;
	private double energia = 200;
	private double pontuacao = 1000;
	//PORTAS: 0 -> porta de cima / 1 -> porta de baixo
	private boolean[] portas = {false, false};
	//ARMAS: 1 -> PISTOLA / 2 -> FUZIL / 3 -> ESPINGARDA\
	private int armaAtual = 1;
	private boolean[] armas = {true, false, false}; //A posição é a arma e a chave é se o personagem possui ou não a arma, por padrão ele só possui a pistola

	Scanner scanner = new Scanner(System.in);
	InterfaceJogo mensagemTela = new InterfaceJogo();
	Tiros tiros = new Tiros(); //Instancia do objeto para atirar e recarregar
	
	public double getEnergia() {
		return energia;
	}

	//Construtor
	public Jogador(int x, int y) {
		super(URL.sprite("jogador.png"), 48);
		//Cordenadas
		this.height = 32;
		this.width = 32;
		this.x = x;
		this.y = y;
		this.setTotalDuration(2000); //Tempo em milisegundos que para mudar os frames
	}
	
	//Gets e sets
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

	
	public void atirar(Window janela, Scene cena, Keyboard teclado, Monstro inimigo) {
		//Seta a tecla para atirar
		if (teclado.keyDown(KeyEvent.VK_SPACE)) {
			ControleTiros.criarTiro(this.x, this.y, this.direcao, cena, this.armaAtual);
		}
		tiros.run(janela, inimigo); //Faz o tiro andar
	}
	
	
	//Aumenta a quantidade de pontos
	public void receberPontos() {
		this.pontuacao += 10;
	}
	
	//Recebe o dano do zumbi
	public void atacado(double dano, Window janela) {
		this.energia -= dano;
		//Abre a tela final do jogo caso o jogador fique sem vida
		if(this.energia <= 0) {
			System.out.println("Pontuação: " + this.pontuacao);
			System.out.println("Como você gostaria de ser lembrado:");
			nome = scanner.next();
			tabela.sobrescrever(pontuacao,nome);
			new Final(janela);
		}
	}
	
	//Recarregar arma
	public void recarregar(Keyboard teclado) {
		//Seta a tecla para recarregar
		if (teclado.keyDown(KeyEvent.VK_R)){
			ControleTiros.recarga(this.getArma());
		}
	}
	
	
	//Trocar a arma do personagem
	public void trocarArma(Keyboard teclado) {
		if(teclado.keyDown(KeyEvent.VK_1) && armas[0]) { //Pistola
			this.armaAtual = 1;
		} else if(teclado.keyDown(KeyEvent.VK_2) && armas[1]) { //Fuzil
			this.armaAtual = 2;
		} else if(teclado.keyDown(KeyEvent.VK_3) && armas[2]) { //Espingarda
			this.armaAtual = 3;
		} 
	}
	
	//Interagir com objetos do mapa
	public void interacao(Scene cena, Keyboard teclado, Window janela, int codigoMapa) {
		//Checa com a colisao com a porta1(A porta de cima no mapa1 e a unica porta nos outros mapas)
		if(Colisao.colisaoPorta1(this)) {
			//Instrução na tela
			mensagemTela.desbloquearPorta(janela, portas[0]);
			//Sair do Mapa1 ou Mapa2
			if(codigoMapa == 2 || codigoMapa == 3) {
				//Instrução na tela
				mensagemTela.desbloquearPorta(janela, true);
				if (teclado.keyDown(Keyboard.ENTER_KEY)) {
					new Mapa1(janela, this);
				}
			}
			//Se o personagem ja tiver desbloqueado a porta ele entra caso contrario aparece uma mensagem para ele comprar
			else if (portas[0]) {
				if (teclado.keyDown(Keyboard.ENTER_KEY)) {
					//Ir para o Mapa1
					if(codigoMapa == 1) {
						new Mapa2(janela, this);
					}
				} 
			} else {
				//Compra a liberação da porta
				if (teclado.keyDown(Keyboard.ENTER_KEY)) {
					if(this.pontuacao >= 100) {
						portas[0] = true;
						this.pontuacao -= 100;
					}
				}
			}
		}
		//Checa a colisao com a porta para o Mapa3
		if(Colisao.colisaoPorta2(this)) {
			//Instrução na tela
			mensagemTela.desbloquearPorta(janela, portas[1]);
			//Checa se o personagem ja liberou a porta
			if (portas[1]) {
				//Aperte enter para ir pro Mapa3
				if (teclado.keyDown(Keyboard.ENTER_KEY)) {
					new Mapa3(janela, this);
				}
			} else {
				//Compra a liberação da porta
				if (teclado.keyDown(Keyboard.ENTER_KEY)) {
					if(this.pontuacao >= 100) {
						portas[1] = true;
						this.pontuacao -= 100;
					}
				}
			}
		}
		//Checa a Colisao com o item do mapa
		if (Colisao.colisaoItem(this)) {
			//Checa qual mapa o jogador esta
			if (codigoMapa == 1) {
				mensagemTela.mensKitMedico(janela, this.energia);
				//Checa se a vida ja esta cheia
				if (this.energia != 200) {
					//Compra o kit medico e aumenta a vida do jogador
					if (teclado.keyDown(Keyboard.ENTER_KEY)) {
						if(this.pontuacao >= 100) {
							//Checa para nao passar da vida maxima
							if ((this.energia + 50) > 200.0) {
								this.energia = 200;
							} else {
								this.energia += 50;
							}
							this.pontuacao -= 100;
						}
					}
				} 
			} else if(codigoMapa == 2) {
				//Instrução na tela
				mensagemTela.mensRifle(janela, this.armas[1]);
				if (!this.armas[1]) {
					//Compra o Rifle
					if (teclado.keyDown(Keyboard.ENTER_KEY)) {
						if(this.pontuacao >= 100) {
							this.armas[1] = true;
							this.pontuacao -= 100;
						}
					}
				} else {
					if (teclado.keyDown(Keyboard.ENTER_KEY)) {
						if(this.pontuacao >= 50) {
							TiroFuzil.setMaxMunicoes();
							this.pontuacao -= 50;
						}
					}
				}
			} else if(codigoMapa == 3) {
				//Instrução na tela
				mensagemTela.mensEspingarda(janela, this.armas[2]);
				if(!this.armas[2]) {
					//Compra a espingarda
					if (teclado.keyDown(Keyboard.ENTER_KEY)) {
						if(this.pontuacao >= 100) {
							this.armas[2] = true;
							this.pontuacao -= 100;
						} 
					}
				} else {
					if (teclado.keyDown(Keyboard.ENTER_KEY)) {
						if(this.pontuacao >= 50) {
							TiroEspingarda.setMaxMunicoes();
							this.pontuacao -= 50;
						}
					}
				}
				
			}
		}
			
	}
	
	//Status:
	
	public void status(Window janela) {
		//Desenha a vida na tela
		mensagemTela.energia(janela, energia);
		//Desenha a potunação do jogador na tela
		mensagemTela.pontuacao(janela, this.pontuacao);
		//Desenha a quantidade de munição da arma em mãos
		if (this.getArma() == 1) {
			mensagemTela.infoPistola(janela, TiroPistola.getMunicoes());
		} else if (this.getArma() == 2) {
			mensagemTela.infoFuzil(janela, TiroFuzil.getMunicoes(), TiroFuzil.getMaxMunicoes());
		} else if(this.getArma() == 3) {
			mensagemTela.infoEspingarda(janela, TiroEspingarda.getMunicoes(), TiroEspingarda.getMaxMunicoes());
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
