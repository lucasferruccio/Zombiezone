package jogo.controle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Score {
	//atributo
	private static String[]nomes = new String[10];
	private static double[] pontuacoes = new double[10];
	private static String caminhoDoArquivo = "src/recurso/Pontuacao/tabela.txt";
	
	public static String[] getNomes(){
		return nomes;
	}
	public static double[] getPontuacoes() {
		return pontuacoes;
	}

        // Caminho do arquivo a ser lido
	public static void leituradedados() {

        // Tentativa de ler o arquivo
        try {
            // Criação de objetos para leitura
            File arquivo = new File(caminhoDoArquivo);
            FileReader fileReader = new FileReader(arquivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Leitura do arquivo linha por linha
            String linha;
            int contadorLinhas = 0;

            while ((linha = bufferedReader.readLine()) != null && contadorLinhas < 10) {
                // Dividindo a linha em nome e pontuação usando o espaço como separador
                String[] partes = linha.split(" ");

                // Armazenando os dados na matriz
                nomes[contadorLinhas] = partes[0]; // Nome
                pontuacoes[contadorLinhas] = Double.parseDouble(partes[1]); // Pontuação

                contadorLinhas++;
            }

            // Fechando recursos após a leitura
            bufferedReader.close();
            fileReader.close();

        } catch (IOException e) {
            // Tratamento de exceção, caso ocorra algum problema na leitura do arquivo
            e.printStackTrace();
        }

}
	public void sobrescrever(double pontos, String nome) {
		Integer oraculo = null;
		for(int i =0;i<10;i++) {
			if(pontos > pontuacoes[i]) {
				oraculo = i;
			}
		}
		if(oraculo ==0) {
			nomes[0] =nome;
			pontuacoes[0] = pontos;
		}
		else if(oraculo>0) {
			String tempnome = nomes[oraculo];
			double temppontuacao = pontuacoes[oraculo];
			while(oraculo >= 0 ) {
				 tempnome = nomes[oraculo];
				 temppontuacao = pontuacoes[oraculo];
				nomes[oraculo] = nome;
				pontuacoes[oraculo]=pontos;
				nome =tempnome;
				pontos = temppontuacao;
				oraculo--;
			}
		}
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoDoArquivo))) {
            // Escrevendo os dados no arquivo
            for (int i = 0; i < nomes.length; i++) {
                // Escrevendo o nome e a pontuação separados por espaço
                writer.write(nomes[i] + " " + pontuacoes[i]);
                writer.newLine(); // Adicionando uma quebra de linha
            }


        } catch (IOException e) {
            // Tratamento de exceção, caso ocorra algum problema na escrita do arquivo
            e.printStackTrace();
        }
		
	}
	public void printar() {
		for(int i=9;i>=0;i--) {
			System.out.println(nomes[i]+" "+pontuacoes[i]);
		}
	}
	
	
	
}

