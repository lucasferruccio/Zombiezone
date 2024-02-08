package jogo.controle;

public class ControleTiroPistola extends ControleTiros{
	static int municoes = 20;
		
	public ControleTiroPistola(double x, double y, int caminho) {
		super(x, y, caminho);
		this.velocidadeTiro = 5;
		this.dano = 25;
	}
	
	//Gets e Sets
	
	public static void setMunicoes(int quantidade) {
		municoes = quantidade;
	}
	
	public static int getMunicoes() {
		return municoes;
	}
	
	public static void recargaArma() {
		municoes = 20;
	}
}
