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
	
	private boolean portaMapa2 = false;
	
	private boolean portaMapa3 = false;
	
	//Construtores:
	
	public int[][] getPontosSpawn() {
		return pontosSpawn;
	}

	public boolean getPortaMapa2() {
		return portaMapa2;
	}

	public void setPortaMapa2(boolean portaMapa2) {
		this.portaMapa2 = portaMapa2;
	}

	public boolean getPortaMapa3() {
		return portaMapa3;
	}

	public void setPortaMapa3(boolean portaMapa3) {
		this.portaMapa3 = portaMapa3;
	}

	//Construtor para primeira inicialização do mapa
	public Mapa1(Window window) {
		janela = window; 
		cena = new Scene(); //Instancia um cena 
		cena.loadFromFile(URL.scenario("Mapa1.scn")); //Carrega o arquivo do cenario
		jogador = new Jogador(516, 247); //Instancia o jogador na tela
		cena.addOverlay(jogador); //Adiciona o jogador no mapa
		teclado = janela.getKeyboard(); //Possibilita a leitura do teclado
		mapaAtual = 1;
		qtdZumbiMax = 4;
		qtdZumbiVivos = 4;
		adicionarZumbis(pontosSpawn); //Cria os primeiros zumbis
		run(); //Chama o loop infinito para iniciar o jogo
	}
	
	public Mapa1(Window window, Jogador jogador) {
		janela = window; 
		cena = new Scene(); //Instancia um cena 
		cena.loadFromFile(URL.scenario("Mapa1.scn")); //Carrega o arquivo do cenario
		this.jogador = jogador; //Instancia o jogador na tela
		//Setando a posição do jogador
		jogador.x = 372;
		jogador.y = 0;
		cena.addOverlay(jogador);
		teclado = janela.getKeyboard();
		realocarZumbis(pontosSpawn, mapaAtual);
		mapaAtual = 1;
		run(); //Chama o loop infinito para iniciar o jogo
	}
	
	
	//Loop do jogo
	private void run() {
		while(true) {
			jogador.mover(janela, teclado); //Possibilita o jogador de mover pelo mapa
			cena.moveScene(jogador); //Move o jogador pelo mapa
			jogador.draw();
			jogador.status(janela);
			
			//System.out.println("x:" + jogador.x);
			//System.out.println("y:" + jogador.y);
			
			
			
			//Checa se ainda existem zumbis vivos
			if (qtdZumbiVivos != 0) {
				//Percorre todos os zumbis 
				for (int j = 0; j < qtdZumbiVivos; j++) {
					//Zumbi morreu -> Deletado do ArrayList
					if (zumbis.get(j).getEnergia() <= 0) {
						zumbis.remove(zumbis.get(j));
						j = qtdZumbiVivos;
						qtdZumbiVivos -= 1;
						jogador.receberPontos();
					} 
					//Continua desenhando o zumbi na tela
					else {
						jogador.atirar(janela, cena, teclado, zumbis.get(j)); //Possibilita o personagem de acertar o zumbi
						jogador.recarregar(teclado);
						zumbis.get(j).perseguir(jogador.x, jogador.y, zumbis); //Possibilita o zumbi ir atras do jogador
						zumbis.get(j).atacar(jogador); //Possibilita o zumbi de atacar o personagem
						zumbis.get(j).draw(); //Desenha o zumbi na tela
					}
				}
			} else {
				jogador.atirar(janela, cena, teclado, null); //Possibilita o personagem de atirarg
				jogador.recarregar(teclado);
				qtdZumbiVivos = qtdZumbiMax;
				adicionarZumbis(pontosSpawn);
			}
			
			jogador.trocarArma(teclado);
			
			//Checa as interações do persongaem com o mapa
			jogador.interacao(cena, teclado, janela, mapaAtual);
			
			janela.delay(10);
			janela.update(); //E atualiza a janela
		}
	}
}
