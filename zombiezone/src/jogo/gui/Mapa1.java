package jogo.gui;

import java.util.Vector;
import jogo.controle.Colisao;
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
		jogador = new Jogador(516, 247); //Instancia o jogador na tela
		cena.addOverlay(jogador); //Adiciona o jogador no mapa
		teclado = janela.getKeyboard(); //Possibilita a leitura do teclado
		mapaAtual = 1;
		rodada = 1;
		qtdMonstrosMaximo = 3;
		qtdMonstrosVivos =3;
		qtdZumbis = 3;
		adicionarMonstros(pontosSpawn); //Cria os primeiros zumbis
		run(); //Chama o loop infinito para iniciar o jogo
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
		teclado = janela.getKeyboard(); //Adiciona o teclado
		realocarZumbis(pontosSpawn, mapaAtual); //Realoca os zumbis
		mapaAtual = 1; //Define o mapa
		run(); //Chama o loop infinito para iniciar o jogo
	}
	
	//Loop do jogo
	private void run() {
		//Carrega os objetos do mapa no sistema
		Vector<?> objetosDoMapa = MapaControle.coletarObjetosMapa(cena);
		Colisao.preencherObjetosMapa(objetosDoMapa);
		
		while(true) {
			jogador.mover(janela, teclado); //Possibilita o jogador de mover pelo mapa
			cena.moveScene(jogador); //Move o jogador pelo mapa
			jogador.draw(); //Desenha o jogador
			jogador.status(janela); //Chama a função que desenha os status do jogador
			
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
						monstros.get(j).atacar(jogador,janela); //Possibilita o zumbi de atacar o personagem
						monstros.get(j).draw(); //Desenha o zumbi na tela
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
