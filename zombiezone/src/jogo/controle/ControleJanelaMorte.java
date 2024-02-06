package jogo.controle;

import javax.swing.JButton;
import javax.swing.JTextField;
import jogo.gui.Menu;
import jogo.repositorio.Score;
import jplay.Keyboard;
import jplay.Window;

public class ControleJanelaMorte {
	//Atributos
	// Atributo
	protected Score score;
	protected String nome;
	protected double pontos;
	protected Window janela;
	protected JTextField txtNome;
	protected JButton btnSalvar;
	protected Keyboard teclado;
	protected boolean loopJanela;
	
	public void acaoBotao() {
		janela.requestFocusInWindow();
        nome = txtNome.getText(); // Obtém o valor do JTextField e salva na variável
        nome = nome.replaceAll(" ","");
        score.sobrescrever(pontos,nome); // sobrescreve o score atual
        teclado = janela.getKeyboard();
        loopJanela = false;
	}
	
	public void run() {
    	while(loopJanela) {
    		if (teclado.keyDown(Keyboard.ESCAPE_KEY)) {
    			loopJanela = false;
    		}
    	}
    	new Menu(janela);
    }
}
