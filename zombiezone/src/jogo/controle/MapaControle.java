package jogo.controle;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import jplay.Keyboard;
import jplay.Scene;
import jplay.Window;

public class MapaControle {

	//Atributos
	protected Window janela;
	protected Scene cena;  //Define o arquivo que molda o cenario
	protected Jogador jogador;
	protected Keyboard teclado;
	protected Colisao colisao;
	protected static int mapaAtual; //1 -> Veio do Mapa 1, 2 -> Veio do Mapa 2, 3 -> Veio do Mapa 3
	protected static ArrayList<Monstro> monstros = new ArrayList<>();
	protected static int qtdMonstrosMaximo;
	protected static int qtdMonstrosVivos;
	protected static int qtdZumbis;
	protected static int rodada;
	
	//Adiciona os zumbis na tela
	protected void adicionarMonstros(int[][] pontosSpawn) {
		//Gerador randomico do spawn dos zumbis
		Random gerador = new Random();
		Monstro monstroAux;
		//Loop para gerar os zumbis
		for (int i = 0; i < qtdMonstrosMaximo; i++) {
			//Gera o indice da posição onde o monstro vai surgir
			int indicePosicaoZumbi = gerador.nextInt(pontosSpawn[0].length);
			//Cria o zumbi na posição 
			if (i < qtdZumbis) {
				monstroAux = new Zumbi(pontosSpawn[0][indicePosicaoZumbi], pontosSpawn[1][indicePosicaoZumbi]);
			} else {
				monstroAux = new Cachorro(pontosSpawn[0][indicePosicaoZumbi], pontosSpawn[1][indicePosicaoZumbi]);
			}
			//Adiciona o monstro na lista
			monstros.add(monstroAux);
		}
	}
	
	protected void realocarZumbis(int[][] pontosSpawn, int mapaAtual) {
		//Gerador de numeros aleatorios
		Random gerador = new Random();
		if (mapaAtual == 1) {
			for (int j = 0; j < qtdMonstrosVivos; j++) {
				int indicePosicaoZumbi = gerador.nextInt(pontosSpawn[0].length);
				monstros.get(j).x = pontosSpawn[0][indicePosicaoZumbi];
				monstros.get(j).y = pontosSpawn[1][indicePosicaoZumbi];
			}
		} else if(mapaAtual == 2) {
			for (int j = 0; j < qtdMonstrosVivos; j++) {
				int lugar = gerador.nextInt(2);
				if(lugar == 0) {
					int posicaoX = gerador.nextInt(128, 216);
					monstros.get(j).x = posicaoX;
				} else {
					int posicaoX = gerador.nextInt(520, 600);
					monstros.get(j).x = posicaoX;
				}
				monstros.get(j).y = 0;
			}
		} else if(mapaAtual == 3) {
			for (int j = 0; j < qtdMonstrosVivos; j++) {
				int lugar = gerador.nextInt(2);
				if(lugar == 0) {
					int posicaoX = gerador.nextInt(128, 216);
					monstros.get(j).x = posicaoX;
				} else {
					int posicaoX = gerador.nextInt(520, 600);
					monstros.get(j).x = posicaoX;
				}
				monstros.get(j).y = 451;
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
	
	public static void finalJogo() {
		monstros = new ArrayList<>();
	}
}
