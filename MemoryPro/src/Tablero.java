import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class Tablero extends JFrame{


    private List<Cartas> cards;
    private Cartas selectedCard;
    private Cartas c1;
    private Cartas c2;
    private Timer t;

    public Tablero(){

        int pairs = 10;
        List<Cartas> cardsList = new ArrayList<Cartas>();
        List<Integer> cardVals = new ArrayList<Integer>();

        for (int i = 0; i < pairs; i++){
            cardVals.add(i);
            cardVals.add(i);
        }
        Collections.shuffle(cardVals);

        for (int val : cardVals){
            Cartas c = new Cartas();
            c.setId(val);
            c.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                    selectedCard = c;
                    doTurn();
                }
            });
            cardsList.add(c);
        }
        this.cards = cardsList;
        
        //configurar el tiempo
        
        t = new Timer(750, new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                checkCards();
            }
        });

        t.setRepeats(false);

        //configurar el tablero
        
        Container pane = getContentPane();
        pane.setLayout(new GridLayout(4, 5));
        for (Cartas c : cards){
            pane.add(c);
        }
        setTitle("Memory");
    }

    public void doTurn(){
        if (c1 == null && c2 == null){
            c1 = selectedCard;
            c1.setText(String.valueOf(c1.getId()));
        }

        if (c1 != null && c1 != selectedCard && c2 == null){
            c2 = selectedCard;
            c2.setText(String.valueOf(c2.getId()));
            t.start();

        }
    }

    public void checkCards(){
        if (c1.getId() == c2.getId()){ //condiciones de la partida
            c1.setEnabled(false); //desactiva el boton
            c2.setEnabled(false);
            c1.setMatched(true); //el boton desaparece al encontrar las parejas
            c2.setMatched(true);
            if (this.isGameWon()){
                JOptionPane.showMessageDialog(this, "Sabes encontrar parejas, muy bien");
                System.exit(0);
            }
        }

        else{
            c1.setText(""); 
            c2.setText("");
        }
        c1 = null; //resetea el c1 y c2
        c2 = null;
    }

    public boolean isGameWon(){
        for(Cartas c: this.cards){
            if (c.getMatched() == false){
                return false;
            }
        }
        return true;
    }

}