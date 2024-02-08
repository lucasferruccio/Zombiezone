package jogo.gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import jogo.controle.ControleJanelaMorte;
import jogo.repositorio.RepositorioScore;
import jplay.Window;

public class InterfaceJanelaMorte extends ControleJanelaMorte {
    
    // Construtor
 
    public InterfaceJanelaMorte(double pontuacao, Window janela) {
    	this.janela = janela;
    	this.teclado = janela.getKeyboard();
    	this.score = new RepositorioScore();
    	this.txtNome = new JTextField();
    	this.btnSalvar = new JButton("Salvar");
    	this.pontos = pontuacao;
    	this.loopJanela = true;
        this.abrirJanelaMorte();
        this.run();
	}
    
    //Configuracao da janela 
	public void abrirJanelaMorte() {
        // Colocando a imagem do alerta
        ImageIcon imagemDeFundo = new ImageIcon("src/recursos/cenarios/alerta.png");
        // Criando um JLabel com a imagem
        JLabel labelImagem = new JLabel(imagemDeFundo);
        // Adicionando o JLabel com a imagem ao JFrame
        janela.setContentPane(labelImagem);
        
        // inserindo o textfield
        txtNome.setBounds(350, 250, 100, 20);
        // inserindo o botão
        btnSalvar.setBounds(350,380,100,30);
        //adicionando a barra de texto e o botão
        janela.add(txtNome);
        janela.add(btnSalvar);
        
        
        //função do botão
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	acaoBotao();
            }
        });
        
        // tornando a janela visível
        
        janela.setVisible(true);
    }
}