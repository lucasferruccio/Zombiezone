package jogo.controle;

public class TiroEspingarda extends ControleTiros{
	static int municoes = 6;
	
	public TiroEspingarda(double x, double y, int caminho) {
		super(x, y, caminho);
		this.VELOCIDADE_TIRO = 4;
		this.dano = 100;
	}
	
	public static void setMunicoes(int quantidade) {
		municoes = quantidade;
	}
	
	public static int getMunicoes() {
		return municoes;
	}
}
