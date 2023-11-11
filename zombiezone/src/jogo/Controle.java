package jogo;

import jplay.GameObject;
import jplay.TileInfo;

public class Controle {
	
	//Checa a colisao do jogador com o cenario
	public boolean colisao(GameObject obj, TileInfo tile) {
		//Aqui ele indica quais os tiles que NAO vao ter colisoes
		if((tile.id >= 2) && obj.collided(tile)) {
			return true;
		}
		return false;
	}
}
