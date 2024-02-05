package jogo.gui;

import jplay.Window;
import jogo.controle.Jogador;
import jogo.controle.MapaControle;
import jplay.Scene;
import jplay.URL;

public class Mapa2 extends MapaControle {
	//SPAWN DE INIMIGOS
	//Posicoes de Spawn dos zumbis possiveis sendo a linha zero o eixo x e a segunda linha o eixo y 
	private int pontosSpawn[][] = {
			{200,590},
			{460,460}
		};
	
	//Construtor
	public Mapa2(Window window, Jogador jogador) {
		janela = window; 
		cena = new Scene(); //Instancia um cena 
		cena.loadFromFile(URL.scenario("Mapa2.scn")); //Carrega o arquivo do cenario
		//Setando a posição do jogador na tela:
		this.jogador = jogador;
		jogador.x = 386;
		jogador.y = 451;
		cena.addOverlay(jogador);
		realocarZumbis(pontosSpawn, mapaAtual); //Realoca os zumbis
		mapaAtual = 2; //Define o mapa
		this.run(pontosSpawn);//Chama o loop infinito para iniciar o jogo
	}
}
