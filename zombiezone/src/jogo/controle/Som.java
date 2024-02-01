package jogo.controle;

import jplay.Sound;
import jplay.URL;

public class Som {
	
	private static Sound musica;
	private static Sound morte;
	private static Sound tiro;
	private static Sound item;
	
	public static void playMusica(String audio) {
		stop(); //Para a musica antiga
		musica = new Sound(URL.audio(audio)); //Abre a musica
		Som.musica.play(); //Toca a musica
		Som.musica.setRepeat(true); //Sem parar
	}
	
	public static void playTiro(String audio_arma) {
		tiro = new Sound(URL.audio(audio_arma));
		Som.tiro.play();
	}
	
	public static void playItem(String audio) {
		item = new Sound(URL.audio(audio));
		Som.item.play();
		
	}
	
	public static void playMorte(String audio) {
		morte = new Sound(URL.audio(audio));
		Som.morte.play();
		
	}
	
	//Para a musica
	public static void stop() {
		if(Som.musica !=  null) {
			musica.stop();
		}
	}
	
	
	
	
}
