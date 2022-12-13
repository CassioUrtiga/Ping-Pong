package pingpong;

import javax.swing.JFrame;

public class Tela {

    public static void main(String[] args) {
        JFrame tela = new JFrame("Ping pong");
        Grafico grafico = new Grafico();
        
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(600, 600);
        tela.setLocationRelativeTo(null);
        tela.setResizable(false);

        tela.add(grafico);
        tela.addKeyListener(grafico);
        tela.setVisible(true);
    }
    
}
