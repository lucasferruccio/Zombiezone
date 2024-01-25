package jogo.controle;

import java.util.ArrayList;
import java.util.Random;

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
	protected static ArrayList<Zumbi> zumbis = new ArrayList<>();
	protected static int qtdZumbiMax;
	protected static int qtdZumbiVivos;
	
	//Adiciona os zumbis na tela
	protected void adicionarZumbis(int[][] pontosSpawn) {
		//System.out.println(pontosSpawn[0].length);
		//Gerador randomico do spawn dos zumbis
		Random gerador = new Random();
		//Loop para gerar os zumbis
		for (int i = 0; i < qtdZumbiMax; i++) {
			//Gera o indice da posição onde o zumbi vai nascer
			int indicePosicaoZumbi = gerador.nextInt(pontosSpawn[0].length);
			//Cria o zumbi na posição 
			Zumbi zumbiAux = new Zumbi(pontosSpawn[0][indicePosicaoZumbi], pontosSpawn[1][indicePosicaoZumbi]);

			//Adiciona o zumbi na lista
			zumbis.add(zumbiAux);
		}
	}
	
	protected void realocarZumbis(int[][] pontosSpawn, int mapaAtual) {
		System.out.println(mapaAtual);
		//Gerador de numeros aleatorios
		Random gerador = new Random();
		if (mapaAtual == 1) {
			for (int j = 0; j < qtdZumbiVivos; j++) {
				int indicePosicaoZumbi = gerador.nextInt(pontosSpawn[0].length);
				zumbis.get(j).x = pontosSpawn[0][indicePosicaoZumbi];
				zumbis.get(j).y = pontosSpawn[1][indicePosicaoZumbi];
			}
		} else if(mapaAtual == 2) {
			for (int j = 0; j < qtdZumbiVivos; j++) {
				int lugar = gerador.nextInt(2);
				if(lugar == 0) {
					int posicaoX = gerador.nextInt(128, 216);
					zumbis.get(j).x = posicaoX;
				} else {
					int posicaoX = gerador.nextInt(520, 600);
					zumbis.get(j).x = posicaoX;
				}
				zumbis.get(j).y = 0;
			}
		} else if(mapaAtual == 3) {
			for (int j = 0; j < qtdZumbiVivos; j++) {
				int lugar = gerador.nextInt(2);
				if(lugar == 0) {
					int posicaoX = gerador.nextInt(128, 216);
					zumbis.get(j).x = posicaoX;
				} else {
					int posicaoX = gerador.nextInt(520, 600);
					zumbis.get(j).x = posicaoX;
				}
				zumbis.get(j).y = 451;
			}
		}
	}
}
