package jogo.controle;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import jogo.gui.InterfaceJogo;
import jplay.Keyboard;
import jplay.Scene;
import jplay.Window;

public class ControleMapa {

	//Atributos
	protected Window janela;
	protected Scene cena;  //Define o arquivo que molda o cenario
	protected ControleJogador jogador;
	protected Keyboard teclado;
	protected ControleColisao colisao;
	protected static int mapaAtual; //1 -> Veio do Mapa 1, 2 -> Veio do Mapa 2, 3 -> Veio do Mapa 3
	protected static ArrayList<ControleMonstro> monstros;
	protected static int qtdMonstrosMaximo;
	protected static int qtdMonstrosVivos;
	protected static int qtdZumbis;
	protected static int rodada;
	
	//Configuração inicio do jogo
	public static void iniciarJogo() {
		mapaAtual = 1;
		rodada = 1;
		qtdMonstrosMaximo = 3;
		qtdMonstrosVivos =3;
		qtdZumbis = 3;
		ControleSom.stop();
		ControleSom.playMusica("AudioJogo.wav");
	}
	
	//Adiciona os zumbis na tela
	protected void adicionarMonstros(int[][] pontosSpawn) {
		monstros = new ArrayList<>();
		//Gerador randomico do spawn dos zumbis
		Random gerador = new Random();
		ControleMonstro monstroAux;
		//Loop para gerar os zumbis
		for (int i = 0; i < qtdMonstrosMaximo; i++) {
			//Gera o indice da posição onde o monstro vai surgir
			int indicePosicaoZumbi = gerador.nextInt(pontosSpawn[0].length);
			//Cria o zumbi na posição 
			if (i < qtdZumbis) {
				monstroAux = new ControleZumbi(pontosSpawn[0][indicePosicaoZumbi], pontosSpawn[1][indicePosicaoZumbi]);
			} else {
				monstroAux = new ControleCachorro(pontosSpawn[0][indicePosicaoZumbi], pontosSpawn[1][indicePosicaoZumbi]);
			}
			//Adiciona o monstro na lista
			monstros.add(monstroAux);
		}
	}
	
	protected void realocarZumbis(int[][] pontosSpawn, int mapaAtual) {
		//Gerador de numeros aleatorios
		Random gerador = new Random();
		//Mapa1
		if (mapaAtual == 1) {
			for (int j = 0; j < qtdMonstrosVivos; j++) {
				//Gera um indice aleatorio para a posição dos monstros
				int indicePosicaoZumbi = gerador.nextInt(pontosSpawn[0].length);
				monstros.get(j).x = pontosSpawn[0][indicePosicaoZumbi];
				monstros.get(j).y = pontosSpawn[1][indicePosicaoZumbi];
			}
		} 
		//Mapa2
		else if(mapaAtual == 2) {
			for (int j = 0; j < qtdMonstrosVivos; j++) {
				int lugar = gerador.nextInt(2);
				//Gera um valor aleatorio para a posição dos monstros entre a janela 1 ou a 2
				if(lugar == 0) {
					int posicaoX = gerador.nextInt(128, 216);
					monstros.get(j).x = posicaoX;
				} else {
					int posicaoX = gerador.nextInt(520, 600);
					monstros.get(j).x = posicaoX;
				}
				monstros.get(j).y = -40;
			}
		}
		//Mapa3
		else if(mapaAtual == 3) {
			for (int j = 0; j < qtdMonstrosVivos; j++) {
				int lugar = gerador.nextInt(2);
				//Gera um valor aleatorio para a posição dos monstros entre a janela 1 ou a 2
				if(lugar == 0) {
					int posicaoX = gerador.nextInt(128, 216);
					monstros.get(j).x = posicaoX;
				} else {
					int posicaoX = gerador.nextInt(520, 600);
					monstros.get(j).x = posicaoX;
				}
				monstros.get(j).y = 510;
			}
		}
	}
	
	//Sistema de rodadas
	public static void rodadaFim() {
		rodada++;
		//A cada 4 rodadas mais um zumbi e a cada 5 rodadas mais um cachorro
		if(rodada % 4 == 0) {
			qtdMonstrosMaximo++;
			qtdZumbis++;
		} 
		if(rodada % 5 == 0) {
			qtdMonstrosMaximo++;
		}
	}
	
	protected static Vector<?> coletarObjetosMapa(Scene cena) {
		Point posicaoMinimo = new Point(0, 0);
		Point posicaoMaximo = new Point(800, 480);
		Vector<?> objetosDoCenário = cena.getTilesFromRect(posicaoMinimo, posicaoMaximo);
		return objetosDoCenário;
	}

	public static double getRodada() {
		return rodada;
	}
	
	//Loop do jogo
	public void run(int[][] pontosSpawn) {
		//Carrega os objetos do mapa no sistema
		Vector<?> objetosDoMapa = ControleMapa.coletarObjetosMapa(cena);
		ControleColisao.preencherObjetosMapa(objetosDoMapa);
		//Adiciona o teclado
		teclado = janela.getKeyboard();
		
		
		while(true) {
			jogador.mover(janela, teclado); //Possibilita o jogador de mover pelo mapa
			cena.moveScene(jogador); //Move o jogador pelo mapa
			InterfaceJogo.desenharAtor(jogador);
			jogador.status(janela); //Chama a função que desenha os status do jogador
			
			//Checa se ainda existem zumbis vivos
			if (qtdMonstrosVivos != 0) {
				//Percorre todos os zumbis 
				for (int j = 0; j < qtdMonstrosVivos; j++) {
					//Zumbi morreu -> Deletado do ArrayList
					if (monstros.get(j).getEnergia() <= 0) {
						ControleSom.playMorte("AudioZumbiMorte.wav");
						monstros.remove(monstros.get(j));
						j = qtdMonstrosVivos;
						qtdMonstrosVivos -= 1;
						jogador.receberPontos();
					} 
					//Continua desenhando o zumbi na tela
					else {
						jogador.atirar(janela, cena, teclado, monstros.get(j)); //Possibilita o personagem de acertar o zumbi
						jogador.recarregar(teclado);
						monstros.get(j).perseguir(jogador.x, jogador.y, monstros); //Possibilita o zumbi ir atras do jogador
						monstros.get(j).atacar(jogador,janela); //Possibilita o zumbi de atacar o personagem
						InterfaceJogo.desenharAtor(monstros.get(j));
					}
				}
			} else {
				qtdMonstrosVivos = qtdMonstrosMaximo;
				rodadaFim();  //Chama o sistema de final de rodada
				adicionarMonstros(pontosSpawn); //Adiciona zumbis
			}
			
			//Possibilita do personagem trocar de Armas\
			jogador.trocarArma(teclado);
			
			//Checa as interações do persongaem com o mapa
			jogador.interacao(cena, teclado, janela, mapaAtual); 
			
			janela.delay(10); //Delay para diminuir a velocidade do jogo
			janela.update();  //Atualiza a janela
		}
	}
}
