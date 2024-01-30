package jogo.controle;

public class TiroPistola extends ControleTiros{
	static int municoes = 12;
		
	public TiroPistola(double x, double y, int caminho) {
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
		municoes = 6;
	}
}
