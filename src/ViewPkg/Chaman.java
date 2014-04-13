package ViewPkg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Chaman extends JLabel{
    private int noQuestion;
    private String theQuestion;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    
public Chaman(){
    this.setOpaque(true);
    this.setBackground(Color.PINK);
    this.setSize(500,500);
    JLabel numberQuestion = new JLabel("Question noX");
    numberQuestion.setFont(new Font("Impact", Font.PLAIN, 20));
    numberQuestion.setSize(300,20);
    numberQuestion.setLocation(5,5);
    this.add(numberQuestion);
    JLabel question = new JLabel ("La question est....");
    question.setFont(new Font("Courier New", Font.PLAIN, 20));
    question.setSize(495,20);
    question.setLocation(5, 50);
    this.add(question);
    this.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            System.out.println("Panneau Questions!!");
        }
    });

    JLabel answer1 = new JLabel("Oui!");
    answer1.setFont(new Font("Arial Black", Font.PLAIN, 20));
    answer1.setSize(200, 75);
    answer1.setLocation(5, 100);
    answer1.setOpaque(true);
    answer1.setBackground(Color.YELLOW);
    JLabel answer2 = new JLabel("Non!");
    this.add(answer1);
    answer1.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            System.out.println("Réponse 1!");
        }
    });

    answer2.setFont(new Font("Arial Black", Font.PLAIN, 20));
    answer2.setSize(200,75);
    answer2.setLocation(295,100);
    answer2.setOpaque(true);
    answer2.setForeground(Color.white);
    answer2.setBackground(Color.BLUE);
    this.add(answer2);
    answer2.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            System.out.println("Réponse 2!");
        }
    });

    JLabel answer3 = new JLabel("Peut-être?");
    answer3.setFont(new Font("Arial Black", Font.PLAIN, 20));
    answer3.setSize(200,75);
    answer3.setLocation(5,200);
    answer3.setOpaque(true);
    answer3.setForeground(Color.white);
    answer3.setBackground(Color.RED);
    this.add(answer3);
    answer3.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            System.out.println("Réponse 3!");
        }
    });


    JLabel answer4 = new JLabel("Over 9000!!!!");
    answer4.setFont(new Font("Arial Black", Font.PLAIN, 20));
    answer4.setSize(200,75);
    answer4.setLocation(295,200);
    answer4.setOpaque(true);
    answer4.setBackground(Color.GREEN);
    this.add(answer4);
    answer4.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            System.out.println("Réponse 4!");
        }
    });




}
}
