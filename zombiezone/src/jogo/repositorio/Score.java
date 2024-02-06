package jogo.repositorio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Score {
	//Atributos
	private static String[]nomes = new String[10];
	private static double[] pontuacoes = new double[10];
	private static String caminhoDoArquivo = "src/recursos/pontuacao/tabela.txt";
	
	
	//Gets e Sets
	public static String[] getNomes(){
		return nomes;
	}
	public static double[] getPontuacoes() {
		return pontuacoes;
	}

	//Le o arquivo txt
	public static void leituradedados() {
        // Tentativa de ler o arquivo
        try {
            // Criação de objetos para leitura
            File arquivo = new File(caminhoDoArquivo);
            FileReader fileReader = new FileReader(arquivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //Leitura do arquivo linha por linha
            String linha;
            int contadorLinhas = 0;
            //Percorre as 10 linhas
            while ((linha = bufferedReader.readLine()) != null && contadorLinhas < 10) {
                //Dividindo a linha em nome e pontuação usando o espaço como separador
                String[] partes = linha.split(" ");

                //Armazenando os dados
                nomes[contadorLinhas] = partes[0];
                pontuacoes[contadorLinhas] = Double.parseDouble(partes[1]);

                contadorLinhas++;
            }
            
            //Fechando os recursos após a leitura
            bufferedReader.close();
            fileReader.close();

        } catch (IOException e) {
            //Tratamento de exceção, caso ocorra algum problema na leitura do arquivo
            e.printStackTrace();
        }

	}
	
	public void sobrescrever(double pontos, String nome) {
		Integer indice = null;
		//Percorre as pontuações pra ver qual é a maior
		for(int i =0;i<10;i++) {
			if(pontos >= pontuacoes[i]) {
				indice = i;
			}
		}
		
		//Se for o ultimo jogador ele so substitui
		if(indice != null && indice == 0) {
			nomes[0] = nome;
			pontuacoes[0] = pontos;
		}
		//Caso contrário ele desce as outras pontuações
		else if(indice != null && indice > 0) {
			String auxNome;
			double auxPontuacao;
			while(indice >= 0 ) {
				auxNome = nomes[indice];
				auxPontuacao = pontuacoes[indice];
				nomes[indice] = nome;
				pontuacoes[indice]=pontos;
				nome =auxNome;
				pontos = auxPontuacao;
				indice--;
			}
		}
		//Reescreve o arquivo
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
}

