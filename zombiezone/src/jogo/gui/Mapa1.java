package jogo.gui;

import jogo.controle.Jogador;
import jogo.controle.MapaControle;
import jplay.Scene;
import jplay.URL;
import jplay.Window;

public class Mapa1 extends MapaControle{

	//SPAWN DE INIMIGOS
	//Posicoes de Spawn dos zumbis possiveis sendo a linha zero o eixo x e a linha um o eixo y 
	private int pontosSpawn[][] = {
			{850,850,850,850},
			{200,300,250,110}
	};
	
	//CONSTRUTORES:
	
	//Construtor para primeira inicialização do mapa
	public Mapa1(Window window) {
		janela = window; 
		cena = new Scene(); //Instancia um cena 
		cena.loadFromFile(URL.scenario("Mapa1.scn")); //Carrega o arquivo do cenario
		jogador = new Jogador(200, 225); //Instancia o jogador na tela
		cena.addOverlay(jogador); //Adiciona o jogador no mapa
		teclado = janela.getKeyboard(); //Possibilita a leitura do teclado
		adicionarMonstros(pontosSpawn);
		this.run(pontosSpawn); //Chama o loop infinito para iniciar o jogo
	}
	
	//Construtor para quando o jogador voltar para o mapa
	public Mapa1(Window window, Jogador jogador) {
		janela = window; 
		cena = new Scene(); //Instancia um cena 
		cena.loadFromFile(URL.scenario("Mapa1.scn")); //Carrega o arquivo do cenario
		this.jogador = jogador; //Instancia o jogador na tela
		//Setando a posição do jogador
		if (mapaAtual == 2) {
			jogador.x = 386;
			jogador.y = 0;
		} else if (mapaAtual == 3) {
			jogador.x = 386;
			jogador.y = 440;
		}
		cena.addOverlay(jogador);
		realocarZumbis(pontosSpawn, mapaAtual); //Realoca os zumbis
		mapaAtual = 1; //Define o mapa
		this.run(pontosSpawn); //Chama o loop infinito para iniciar o jogo
	}
	
	
}
