package jogo.controle;

public class TiroFuzil extends ControleTiros{
	static int municoes = 30;
	
	public TiroFuzil(double x, double y, int caminho) {
		super(x, y, caminho);
		this.VELOCIDADE_TIRO = 6;
		this.dano = 50;
	}
	
	public static void setMunicoes(int quantidade) {
		municoes = quantidade;
	}
	
	public static int getMunicoes() {
		return municoes;
	}
}
