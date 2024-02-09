package jogo.controle;

public class ControleTiroFuzil extends ControleBala{
	static int municoes = 30;
	static int municoesTotais = 240;
	
	public ControleTiroFuzil(double x, double y, int caminho) {
		super(x, y, caminho);
		this.velocidadeTiro = 6;
		this.dano = 50;
	}
	
	//Gets e Sets:
	
	public static void setMunicoes(int quantidade) {
		municoes = quantidade;
	}
	
	public static void setMaxMunicoes() {
		municoesTotais = 240;
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
			municoesTotais -= 40;
			municoes = 40;
		}
	}
}	