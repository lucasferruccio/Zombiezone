package jogo.controle;

public class TiroFuzil extends ControleTiros{
	static int municoes = 30;
	static int municoesTotais = 240;
	
	public TiroFuzil(double x, double y, int caminho) {
		super(x, y, caminho);
		this.VELOCIDADE_TIRO = 6;
		this.dano = 50;
	}
	
	public static void setMunicoes(int quantidade) {
		municoes = quantidade;
	}
	
	public static void setMaxMunicoes() {
		municoesTotais = 240;
	}
	
	public static void recargaArma() {
		municoesTotais -= 30;
	}
	
	public static int getMaxMunicoes() {
		return municoesTotais;
	}
	
	public static int getMunicoes() {
		return municoes;
	}
}	