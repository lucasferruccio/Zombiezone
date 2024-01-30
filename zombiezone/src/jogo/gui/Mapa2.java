package jogo.gui;

import jplay.Window;

import java.util.Vector;

import jogo.controle.Colisao;
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
		this.jogador = jogador;
		jogador.x = 386;
		jogador.y = 451;
		cena.addOverlay(jogador);
		teclado = janela.getKeyboard();
		realocarZumbis(pontosSpawn, mapaAtual);
		mapaAtual = 2;
		run(); //Chama o loop infinito para iniciar o jogo
	}
	
	
	
	private void run() {
		Vector<?> objetosDoMapa = MapaControle.coletarObjetosMapa(cena);
		Colisao.preencherObjetosMapa(objetosDoMapa);
		
		while(true) {
			jogador.mover(janela, teclado); //Possibilita o jogador de mover pelo mapa
			cena.moveScene(jogador); //Move o jogador pelo mapa
			jogador.draw();
			jogador.status(janela);
			
			//System.out.println("x:" + jogador.x);
			//System.out.println("y:" + jogador.y);
			
			
			
			//Checa se ainda existem zumbis vivos
			if (qtdMonstrosVivos != 0) {
				//Percorre todos os zumbis 
				for (int j = 0; j < qtdMonstrosVivos; j++) {
					//Zumbi morreu -> Deletado do ArrayList
					if (monstros.get(j).getEnergia() <= 0) {
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
						monstros.get(j).atacar(jogador); //Possibilita o zumbi de atacar o personagem
						monstros.get(j).draw(); //Desenha o zumbi na tela
					}
				}
			} else {
				jogador.atirar(janela, cena, teclado, null); //Possibilita o personagem de atirarg
				jogador.recarregar(teclado);
				qtdMonstrosVivos = qtdMonstrosMaximo;
				adicionarMonstros(pontosSpawn);
			}
			
			jogador.trocarArma(teclado);
			
			//Checa as interações do persongaem com o mapa
			jogador.interacao(cena, teclado, janela, mapaAtual);
			
			janela.delay(10);
			janela.update(); //E atualiza a janela
		}
	}
}
