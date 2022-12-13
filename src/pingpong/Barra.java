package pingpong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Barra {
    
    static int direcao;// 0 = PARADO, 1 = DIREITA, -1 = ESQUERDA
    static Rectangle r;
    private final int altura, comprimento, y;
    private int x;
    
    public Barra() {
        r = new Rectangle();
        
        x = 270;
        y = 550;
        altura = 10;
        comprimento = 63;
    }
    
    public void atualizar(){
        colidirBordas();
        switch (direcao){
            case 1:
                x++;
                break;
            case -1:
                x--;
                break;
            default:
                break;
        }
    }
    
    private void colidirBordas(){
        if (x > 520){
            x = 520;
        }
        
        if (x < 2){
            x = 2;
        }
    }
    
    public void pintar(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, comprimento, altura);
        r.setBounds(x, y, comprimento, altura-5);
    }
}
