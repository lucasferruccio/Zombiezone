package jogo.controle;

import java.time.LocalTime;
import jplay.Scene;
import jplay.Sprite;
import jplay.URL;


public class ControleBala extends Sprite  {
	//Direção do tiroa
	public static final int ESQUERDA = 1, DIREITA = 2, CIMA = 3, BAIXO = 4, PARADO = 5;
	
	//Atributos
	protected int velocidadeTiro;
	protected int dano;
	protected int caminho = PARADO;
	protected boolean movendo = false;
	protected int direcao = 3;
	private static LocalTime tempoAtual;
	private static LocalTime tempoParaRecarregar = LocalTime.now();
	
	//Construtor
	public ControleBala(double x, double y, int caminho) {
		super(URL.sprite("tiro.png"), 16);
		this.caminho = caminho;
		this.x = x;
		this.y = y;
	}
	
	//Gets e Sets
	public int getDano(){
		return dano;
	}
	
	//Cria os tiros
	public static void criarTiro(double x, double y, int caminho, Scene cena, int arma) {
		int municoes;
		ControleBala tiro;
		
		//Pega
		if (arma == 2) {
			
			municoes = ControleTiroFuzil.getMunicoes();
		} else if (arma == 3) {
			
			municoes = ControleTiroEspingarda.getMunicoes();
		}
		else {

			municoes = ControleTiroPistola.getMunicoes();
		}
		if (municoes > 0) {
			tempoAtual = LocalTime.now();
			if(tempoAtual.isAfter(tempoParaRecarregar)) {
				if (arma == 2) {
					ControleSom.playTiro("AudioTiroFuzil.wav");
					tiro = new ControleTiroFuzil(x,y, caminho);
					ControleTiroFuzil.setMunicoes(municoes - 1);
					
				} else if (arma == 3){
					ControleSom.playTiro("AudioTiroEspingarda.wav");
					tiro = new ControleTiroEspingarda(x,y, caminho);
					ControleTiroEspingarda.setMunicoes(municoes - 1);
				} else {
					ControleSom.playTiro("AudioTiroPistola.wav");
					tiro = new ControleTiroPistola(x,y, caminho);
					ControleTiroPistola.setMunicoes(municoes - 1);
				}
				ControleTiros.adicionarTiro(tiro);
			} 
		} else {
			ControleSom.playTiro("AudioArmaSemMunicao.wav");
		}
	}
	
	//Recarga de armas
	public static void recarga(int arma) {
		ControleSom.playTiro("AudioArmaRecarga.wav");
		tempoParaRecarregar = LocalTime.now().plusSeconds(1);
		if (arma == 1) {
			ControleTiroPistola.recargaArma();
		} else if (arma == 2){
			ControleTiroFuzil.recargaArma();
		} else if (arma == 3) {
			ControleTiroEspingarda.recargaArma();
		}
	}		
	
	//Movimentação do Tiro do tiro
	public void mover() {
		if (caminho == ESQUERDA) {
			this.x -= velocidadeTiro;
			if (direcao != 1) {
				setSequence(4,7);
				
			}
			movendo = true;
		}
		if (caminho == DIREITA) {
			this.x += velocidadeTiro;
			if (direcao != 2) {
				setSequence(8,11);
				
			}
			movendo = true;
		}
		if (caminho == CIMA) {
			this.y -= velocidadeTiro;
			if (direcao != 4) {
				setSequence(12,15);
			}
			movendo = true;
		}
		if (caminho == BAIXO || caminho == PARADO) {
			this.y += velocidadeTiro;
			if (direcao != 5) {
				setSequence(0,3);
			}
			movendo = true;	
		}
		
		
		if (movendo) {
			update();
			movendo = false;
		}
	}
}

