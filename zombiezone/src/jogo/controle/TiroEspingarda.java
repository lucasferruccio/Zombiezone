package jogo.controle;

public class TiroEspingarda extends ControleTiros{
	static int municoes = 6;
	static int municoesTotais = 30;
	
	public TiroEspingarda(double x, double y, int caminho) {
		super(x, y, caminho);
		this.velocidadeTiro = 4;
		this.dano = 100;
	}
	
	//Gets e Sets:
	
	public static void setMunicoes(int quantidade) {
		municoes = quantidade;
	}
	
	public static void setMaxMunicoes() {
		municoesTotais = 30;
	}
	
	public static int getMaxMunicoes() {
		return municoesTotais;
	}
	
	public static int getMunicoes() {
		return municoes;
	}
	
	//Recarga da arma
	public static void recargaArma() {
		if (municoesTotais > 0) {
			//Toda recarga diminui 6 balas totais
			municoesTotais -= 6;
			municoes = 6;
		}
	}
}
