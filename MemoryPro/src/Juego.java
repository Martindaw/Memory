import java.awt.Dimension;
import javax.swing.JFrame;


public class Juego{
    public static void main(String[] args){
        Tablero b = new Tablero();
        b.setPreferredSize(new Dimension(500,500)); //configura el tamaño
        b.setLocation(500, 250);
        b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b.pack();
        b.setVisible(true);
    }   
}