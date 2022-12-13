package pingpong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Blocos {
    
    static int pontuacao;
    private final int x, y, tamanho;
    private final Rectangle faceBaixo, faceCima, faceDireita, faceEsquerda;
    private final Random rand;
    private final int[][] matrizX, matrizY;
    private final int quantLinhas, quantBlocos, distX, distY, integridade;
    
    public Blocos() {
        rand = new Random();
        faceBaixo = new Rectangle();
        faceCima = new Rectangle();
        faceDireita = new Rectangle();
        faceEsquerda = new Rectangle();
        
        x = 4;
        y = 2;
        tamanho = 16;
        
        quantLinhas = rand.nextInt(11)+5;//quantidade de linhas de blocos
        quantBlocos = 34; //só é aceito valores <= 34
        distX = 1; //distância entre os blocos no eixo x
        distY = 20;//distância entre os blocos no eixo y
        integridade = rand.nextInt(4)+2; //nível de destruição dos blocos
        
        pontuacao = 0;
        
        matrizX = new int[quantLinhas][quantBlocos];
        matrizY = new int[quantLinhas][quantBlocos];
        
        carregarLista();
    }
    
    public void pintar(Graphics g){
        for (int i=0; i<quantLinhas; i++){
            for (int j=0; j<quantBlocos; j++){
                if (matrizX[i][j] > 0){
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(matrizX[i][j], matrizY[i][j], tamanho, tamanho);
                    
                    //Face Baixo
                    faceBaixo.setBounds(matrizX[i][j], matrizY[i][j]+15, tamanho, tamanho-15);
                    //Face Cima
                    faceCima.setBounds(matrizX[i][j], matrizY[i][j], tamanho, tamanho-15);
                    //Face Esquerda
                    faceEsquerda.setBounds(matrizX[i][j], matrizY[i][j], tamanho-15, tamanho);
                    //Face Direita
                    faceDireita.setBounds(matrizX[i][j]+15, matrizY[i][j], tamanho-15, tamanho);
                    
                    if (faceBaixo.intersects(Bola.r) || faceCima.intersects(Bola.r) ||
                        faceEsquerda.intersects(Bola.r) || faceDireita.intersects(Bola.r)){
                        matrizX[i][j] = 0;
                        matrizY[i][j] = 0;
                        pontuacao++;
                    }
                    colidirBloco();
                }
            }
        }
    }
    
    private void carregarLista(){
        for (int i=0; i<quantLinhas; i++){
            int distancia = x;
            for (int j=0; j<quantBlocos; j++){
                if (rand.nextInt(integridade) == 0){
                    matrizX[i][j] = 0;
                }else{
                    matrizX[i][j] = distancia;
                }
                distancia += tamanho+distX;
            }
        }
        
        int distancia = y;
        for (int i=0; i<quantLinhas; i++){
            for (int j=0; j<quantBlocos; j++){
                matrizY[i][j] = distancia;
            }
            distancia += distY;
        }
    }
    
    private void colidirBloco(){
        if (faceBaixo.intersects(Bola.r)){
            int[] vetor = {-1,3};
            
            Bola.direcao = vetor[rand.nextInt(2)];
            if (rand.nextBoolean()){
                Bola.eixoX = true;
                Bola.eixoY = true;
            }else{
                Bola.eixoX = false;
                Bola.eixoY = true;
            }
        }
        
        if (faceCima.intersects(Bola.r)){
            int[] vetor = {1,3};
            
            Bola.direcao = vetor[rand.nextInt(2)];
            if (rand.nextBoolean()){
                Bola.eixoX = true;
                Bola.eixoY = false;
            }else{
                Bola.eixoX = false;
                Bola.eixoY = false;
            }
        }
        
        if (faceEsquerda.intersects(Bola.r)){
            int[] vetor = {-2,3};
            
            Bola.direcao = vetor[rand.nextInt(2)];
            if (rand.nextBoolean()){
                Bola.eixoX = false;
                Bola.eixoY = true;
            }else{
                Bola.eixoX = false;
                Bola.eixoY = false;
            }
        }
        
        if (faceDireita.intersects(Bola.r)){
            int[] vetor = {2,3};
            
            Bola.direcao = vetor[rand.nextInt(2)];
            if (rand.nextBoolean()){
                Bola.eixoX = true;
                Bola.eixoY = false;
            }else{
                Bola.eixoX = true;
                Bola.eixoY = true;
            }
        }
    }
}
