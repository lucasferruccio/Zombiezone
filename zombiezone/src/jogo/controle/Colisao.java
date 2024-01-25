package jogo.controle;

import jplay.GameObject;
import jplay.TileInfo;

public class Colisao {
	
	//Colisao Do Mapa 1:
	
	//Portas:
	
	//Porta para o Mapa 2
	public boolean portaEntradaMapa2(GameObject jogador, TileInfo tile) {
		if((tile.id == 1) && jogador.collided(tile)) {
			return true;
		}
		return false; 
	}
	
	
	
	
	//Colisao do Mapa 2:
	
	
	//Portas:
	
	//Porta para sair do mapa 2
	public boolean portaSaidaMapa2(GameObject jogador, TileInfo tile) {
		if((tile.id == 1) && jogador.collided(tile)) {
			return true;
		}
		return false;
	}
}


