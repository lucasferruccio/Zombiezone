package jogo.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import jogo.repositorio.Score;
import jogo.controle.MapaControle;

public class JanelaMorte {
    // Atributo
	Score score;
	String nome;
	double pontos;
    JFrame janelajframe;
    JTextField txtNome;
    JButton btnSalvar;
    
    // Construtor
 
    public JanelaMorte(double pontuacao) {
    	this.score = new Score();
    	this.pontos = pontuacao;
    	this.txtNome = new JTextField();
    	this.btnSalvar = new JButton("Salvar");
        this.janelajframe = new JFrame();
        abrirJanelaMorte(janelajframe);
	}



	public void abrirJanelaMorte(JFrame janela) {
		MapaControle.finalJogo(); // resetar o jogo
        janela.setSize(800, 480);// definindo o tamanho da nova janela
        txtNome.setBounds(335, 250, 100, 20);// inserindo o textfield
        btnSalvar.setBounds(330,380,100,30);// inserindo o botão
        
        
        
        // Colocando a imagem do alerta
        ImageIcon imagemDeFundo = new ImageIcon("src/recursos/cenarios/alerta.png");
        // Criando um JLabel com a imagem
        JLabel labelImagem = new JLabel(imagemDeFundo);



        // Adicionando o JLabel com a imagem ao JFrame
        janela.setContentPane(labelImagem);
        
        //adicionando a barra de texto e o botão
        janela.add(txtNome);
        janela.add(btnSalvar);
        
        // função do botão
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nome = txtNome.getText(); // Obtém o valor do JTextField e salva na variável
                nome = nome.replaceAll(" ","");
                score.sobrescrever(pontos,nome); // sobrescreve o score atual
                janelajframe.dispose(); // fecha a janela
            }
        });
        
        // Definindo a operação padrão de fechamento e tornando a janela visível
        
        janela.setVisible(true);
    }
}