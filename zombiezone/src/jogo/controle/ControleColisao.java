package jogo.controle;

import java.util.Vector;
import jplay.GameObject;
import jplay.TileInfo;

public class ControleColisao {
	private static TileInfo porta1;
	private static TileInfo porta2;
	private static TileInfo item;
	
	//Preenche os objetos de cada mapa para liberar interações
	public static void preencherObjetosMapa(Vector<?> objetosDoMapa) {
		porta1 = null;
		porta2 = null;
		item = null;
		//Percorre os objs do mapa
		for (int i = 0; i < objetosDoMapa.size(); i++) {
			TileInfo objeto = (TileInfo) objetosDoMapa.elementAt(i);
			//Pega a porta e o item de cada parte do mapa
			if(objeto.id == 1) {
				porta1 = objeto;
				
			} else if(objeto.id == 2) { 
				porta2 = objeto;
			}
			else if (objeto.id == 3) {
				item = objeto;
			}
		}
	}
	
	//Chega a colisão do jogador com a porta 1
	public static boolean colisaoPorta1(GameObject jogador) {
		if (jogador.collided(porta1)) {
			return true;
		} else {
			return false;
		}
	}
	
	//Chega a colisão do jogador com a porta 2
	public static boolean colisaoPorta2(GameObject jogador) {
		if (porta2 != null && jogador.collided(porta2)) {
			return true;
		} else {
			return false;
		}
	}
	
	//Chega a colisão do jogador com a item
	public static boolean colisaoItem(GameObject jogador) {
		if (item != null && jogador.collided(item)) {
			return true;
		} else {
			return false;
		}
	}
}


