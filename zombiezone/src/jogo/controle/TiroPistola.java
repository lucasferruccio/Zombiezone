package jogo.controle;

public class TiroPistola extends ControleTiros{
	static int municoes = 12;
		
	public TiroPistola(double x, double y, int caminho) {
		super(x, y, caminho);
		this.VELOCIDADE_TIRO = 5;
		this.dano = 25;
	}
	
	public static void setMunicoes(int quantidade) {
		municoes = quantidade;
	}
	
	public static int getMunicoes() {
		return municoes;
	}
}
