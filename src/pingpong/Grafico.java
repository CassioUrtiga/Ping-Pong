package pingpong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class Grafico extends JPanel implements Runnable, KeyListener{
    
    private final Bola bola;
    private final Barra barra;
    private final Blocos blocos;
    
    public Grafico(){
        bola = new Bola();
        barra = new Barra();
        blocos = new Blocos();
        
        Thread gameLoop = new Thread(this);
        gameLoop.start();
    }
    
    private void atualizar(){
        bola.atualizar();
        barra.atualizar();
    }
    
    private void fps(){
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            System.out.println("FPS ERRO");
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 600, 600);
        
        blocos.pintar(g);
        bola.pintar(g);
        barra.pintar(g);
    }
    
    @Override
    public void run() {
        while (true){
            atualizar();
            repaint();
            fps();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                Barra.direcao = 1;
                break;
            case KeyEvent.VK_LEFT:
                Barra.direcao = -1;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Barra.direcao = 0;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
