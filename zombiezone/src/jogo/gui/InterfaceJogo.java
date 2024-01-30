package jogo.gui;

import java.awt.Color;
import java.awt.Font;
import jplay.Window;

public class InterfaceJogo {
	//atributos
	Font fonte = new Font("arial", Font.BOLD, 15);
	
	//Mensagem de Desbloqueio de porta
	public void desbloquearPorta(Window janela, boolean comprado) {
		if (comprado) {
			janela.drawText("Clique ENTER para entrar", 300, 300, Color.green, fonte);
		} else {
			janela.drawText("Clique ENTER para liberar (100 pontos)", 270, 300, Color.green, fonte);
		}
		
	}
	
	//MENSAGEM DOS ITENS:
	public void mensRifle(Window janela, boolean comprado) {
		if (comprado) {
			janela.drawText("Clique ENTER para comprar munição (50 pontos)", 230, 300, Color.green, fonte);
		} else {
			janela.drawText("Clique ENTER para comprar o Fuzil (100 pontos)", 230, 300, Color.green, fonte);
		}
	}
	
	public void mensEspingarda(Window janela, boolean comprado) {
		if (comprado) {
			janela.drawText("Clique ENTER para comprar munição (50 pontos)", 230, 300, Color.green, fonte);
		} else {
			janela.drawText("Clique ENTER para comprar A Espingarda (100 pontos)", 230, 300, Color.green, fonte);
		}
	}

	public void mensKitMedico(Window janela, double energia) {
		if (energia != 200) {
			janela.drawText("Clique ENTER para comprar 50 de vida (100 pontos)", 230, 300, Color.green, fonte);
		} else {
			janela.drawText("Saude cheia", 350, 300, Color.green, fonte);
		}
	}
	
	
	//MENSAGEM DOS STATUS:
	public void energia(Window janela, double energia) {
		janela.drawText("Vida:" + energia, 10, 20, Color.green, fonte);
	}
	
	public void pontuacao(Window janela, double pontuacao) {
		janela.drawText("Pontos:" + pontuacao, 10, 460, Color.green, fonte);
	}
	
	//Informações armas
	
	public void infoPistola(Window janela, int municoes) {
		janela.drawText("Munições:" + municoes + "/12", 10, 40, Color.green, fonte);
	}
	
	public void infoFuzil(Window janela, int municoes, int municoesTotais) {
		janela.drawText("Munições:" + municoes + "/" + municoesTotais, 10, 40, Color.green, fonte);
	}
	
	public void infoEspingarda(Window janela, int municoes, int municoesTotais) {
		janela.drawText("Munições:" + municoes + "/" + municoesTotais, 10, 40, Color.green, fonte);
	}
}
