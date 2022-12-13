package pingpong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.JOptionPane;

public class Bola {
    
    static Rectangle r;
    static int direcao;
    static boolean eixoY, eixoX;
    private final Random rand;
    private final int tamanho;
    private int x, y;
    
    public Bola(){
        rand = new Random();
        r = new Rectangle();
        
        x = 290;
        y = 350;
        tamanho = 14;
        
        direcao = -1;
    }
    
    public void atualizar(){
        movimento();
        colidir();
    }
    
    public void pintar(Graphics g){
        g.setColor(Color.RED);
        g.fillOval(x,y,tamanho,tamanho);
        r.setBounds(x+1, y, tamanho-2, tamanho-2);
    }
    
    private void movimento(){
        switch (direcao){
            case -1: // baixo
                y++;
                break;
            case 1: // cima
                y--;
                break;
            case 2: //Direita
                x++;
                break;
            case -2: //Esquerda
                x--;
                break;
            case 3: //Diagonal
                if (eixoX){
                    x++;
                }else{
                    x--;
                }
                
                if (eixoY){
                    y++;
                }else{
                    y--;
                }
                break;
            default:
                //parado
                break;
        }
    }
    
    private void colidir(){
        fimJogo();
        colidirBarra();
        colidirTela();
    }
    
    private void colidirBarra(){
        //BOLA COLIDE COM A BARRA
        if (r.intersects(Barra.r)){
            switch (Barra.direcao) {
                case 1:
                    direcao = 3;
                    eixoX = true;
                    eixoY = false;
                    break;
                case -1:
                    direcao = 3;
                    eixoX = false;
                    eixoY = false;
                    break;
                default:
                    int[] vetor = {1,3};

                    direcao = vetor[rand.nextInt(2)];
                    if (rand.nextBoolean()){
                        eixoX = true;
                        eixoY = false;
                    }else{
                        eixoX = false;
                        eixoY = false;
                    }
                    break;
            }
        }
    }
    
    private void colidirTela(){
        //BOLA COLIDE (CIMA) DA TELA
        if (y <= -2){
            int[] vetor = {-1,3};
            
            direcao = vetor[rand.nextInt(2)];
            if (rand.nextBoolean()){
                eixoX = true;
                eixoY = true;
            }else{
                eixoX = false;
                eixoY = true;
            }
        }
        
        //BOLA COLIDE (DIREITA) DA TELA
        if (x >= 590-tamanho){
            //int[] vetor = {-2,3};
            //direcao = vetor[rand.nextInt(2)];
            direcao = 3;
            if (rand.nextBoolean()){
                eixoX = false;
                eixoY = false;
            }else{
                eixoX = false;
                eixoY = true;
            }
        }
        
        //BOLA COLIDE (ESQUERDA) DA TELA
        if (x <= 0){
            //int[] vetor = {2,3};
            //direcao = vetor[rand.nextInt(2)];
            direcao = 3;
            if (rand.nextBoolean()){
                eixoX = true;
                eixoY = true;
            }else{
                eixoX = true;
                eixoY = false;
            }
        }
    }
    
    private void fimJogo(){
        if (y > 600){
            JOptionPane.showMessageDialog(null, "Pontuação:"+Blocos.pontuacao, "Fim de Jogo", 1);
            System.exit(0);
        }
    }
    
}
