package ViewPkg;

import ControllerPkg.MasterController;
import ControllerPkg.QuestionsHandler;
import ModelPkg.QuestionChaman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Chaman extends JLabel{
    private int noQuestion;
    private JLabel question = new JLabel();
    private JLabel answer0 = new JLabel();
    private JLabel answer1 = new JLabel();
    private JLabel answer2 = new JLabel();
    private JLabel answer3 = new JLabel();
    private QuestionsHandler questionsHandler;
    private MasterController controller;
    private QuestionChaman actualQuestion;

public Chaman(final MasterController controller){
    this.actualQuestion = controller.getChamanController().getQuestion();
    this.controller=controller;
    this.questionsHandler = new QuestionsHandler();
    this.setOpaque(true);
    this.setBackground(Color.PINK);
    this.setSize(500,500);
    JLabel numberQuestion = new JLabel("Question noX");
    numberQuestion.setFont(new Font("Impact", Font.PLAIN, 20));
    numberQuestion.setSize(300,20);
    numberQuestion.setLocation(5,5);
    this.add(numberQuestion);
    question.setText(actualQuestion.getQuestion());
    question.setFont(new Font("Courier New", Font.PLAIN, 15));
    question.setSize(495,20);
    question.setLocation(5, 50);
    this.add(question);
this.addMouseListener(this.questionsHandler);

    answer0.setText(actualQuestion.getAnswer0());
    answer0.setFont(new Font("Arial Black", Font.PLAIN, 15));
    answer0.setSize(200, 75);
    answer0.setLocation(5, 100);
    answer0.setOpaque(true);
    answer0.setBackground(Color.YELLOW);
    this.add(answer0);
    answer1.setText(actualQuestion.getAnswer1());
    answer1.setFont(new Font("Arial Black", Font.PLAIN, 15));
    answer1.setSize(200,75);
    answer1.setLocation(295,100);
    answer1.setOpaque(true);
    answer1.setForeground(Color.white);
    answer1.setBackground(Color.BLUE);
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
            System.out.println("Réponse 2!");
        }
    });
    answer2.setText(actualQuestion.getAnswer2());
    answer2.setFont(new Font("Arial Black", Font.PLAIN, 15));
    answer2.setSize(200,75);
    answer2.setLocation(5,200);
    answer2.setOpaque(true);
    answer2.setForeground(Color.white);
    answer2.setBackground(Color.RED);
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
            System.out.println("Réponse 3!");
        }
    });

    answer3.setText(actualQuestion.getAnswer3());
    answer3.setFont(new Font("Arial Black", Font.PLAIN, 15));
    answer3.setSize(200,75);
    answer3.setLocation(295,200);
    answer3.setOpaque(true);
    answer3.setBackground(Color.GREEN);
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
            System.out.println("Réponse 4!");
        }
    });
}



public void update(){
    repaint();
    System.out.println("repaiiiint");
    }
}

