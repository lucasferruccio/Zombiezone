package jogo.gui; 

import java.awt.event.KeyEvent;
import jplay.Keyboard;
import jplay.Window;


public class Main {
    public static void main(String[] args) {
    	
        Window janela = new Window(800, 480);

        Keyboard teclado = janela.getKeyboard();
        teclado.addKey(KeyEvent.VK_R);
        teclado.addKey(KeyEvent.VK_1);
        teclado.addKey(KeyEvent.VK_2);
        teclado.addKey(KeyEvent.VK_3);
        
        new Menu(janela);

      
}
}			 
					 
						
						
						

		
		

