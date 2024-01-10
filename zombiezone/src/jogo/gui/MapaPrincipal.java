package jogo.gui;

import java.util.ArrayList;
import java.util.Random;

import jogo.controle.Jogador;
import jogo.controle.Zumbi;
import jplay.Keyboard;
import jplay.Scene;
import jplay.URL;
import jplay.Window;

public class MapaPrincipal {

	//Atributos
	private Window janela;
	private Scene cena;  //Define o arquivo que molda o cenario
	private Jogador jogador;
	private Keyboard teclado;
	private ArrayList<Zumbi> zumbis = new ArrayList<>();
	private int qtdZumbi = 10;
	
	//Posicoes de Spawn dos zumbis possiveis sendo a linha zero o eixo x e a segunda linha o eixo y 
	private int pontosSpawn[][] = {
			{-60,1200,235,486,769},
			{250,220,-50,-50,-50}
		};
	
	//Construtor
	public MapaPrincipal(Window window) {
		janela = window; 
		cena = new Scene(); //Instancia um cena 
		cena.loadFromFile(URL.scenario("Cenario1.scn")); //e carrega o arquivo do cenario
		jogador = new Jogador(640, 350);
		teclado = janela.getKeyboard();
		
		run(); //Chama o loop infinito
	}
	
	//Adiciona os zumbis na tela
	private void adicionarZumbis() {
		//Gerador randomico do spawn dos zumbis
		Random gerador = new Random();
		//Loop para gerar os zumbis
		for (int i = 0; i < qtdZumbi; i++) {
			//Gera o indice da posição onde o zumbi vai nascer
			int indicePosicaoZumbi = gerador.nextInt(5);
			//Cria o zumbi na posição determinada
			Zumbi zumbiAux = new Zumbi(pontosSpawn[0][indicePosicaoZumbi], pontosSpawn[1][indicePosicaoZumbi]);
			//Adiciona o zumbi na lista
			zumbis.add(zumbiAux);
		}
	}
	
	private void run() {
		//Cria o primeiro zumbis
		adicionarZumbis();
		while(true) {
			cena.draw(); //Desenha a cena
			jogador.mover(janela, teclado); //Possibilita o jogador de mover pelo mapa
			jogador.draw(); //Desenha o jogador no mapa
			
			//Checa se ainda existem zumbis vivos
			if (qtdZumbi != 0) {
				//Percorre todos os zumbis 
				for (int j = 0; j < qtdZumbi; j++) {
					//Zumbi morreu -> Deletado do ArrayList
					if (zumbis.get(j).getEnergia() <= 0) {
						zumbis.remove(zumbis.get(j));
						j = qtdZumbi;
						qtdZumbi -= 1;
						jogador.receberPontos();
					} 
					//Continua desenhando o zumbi na tela
					else {
						jogador.atirar(janela, cena, teclado, zumbis.get(j)); //Possibilita o personagem de acertar o zumbi
						zumbis.get(j).perseguir(jogador.x, jogador.y, zumbis); //Possibilita o zumbi ir atras do jogador
						zumbis.get(j).atacar(jogador); //Possibilita o zumbi de atacar o personagem
						zumbis.get(j).draw(); //Desenha o zumbi na tela
					}
				}
			} else {
				jogador.atirar(janela, cena, teclado, null); //Possibilita o personagem de atirarg
				qtdZumbi = 10;
				adicionarZumbis();
			}
			
			
			janela.update(); //E atualiza a janela
			
			
		}
	}
	

}
