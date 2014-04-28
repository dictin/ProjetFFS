package ControllerPkg;

import ModelPkg.QuestionChaman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class QuestionsHandler implements MouseListener{
    private QuestionChaman actualQuestion;
    private JLabel answerClick = new JLabel();
    private Color answerColor;
    public QuestionsHandler(QuestionChaman actualQuestion){
        this.actualQuestion = actualQuestion;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        answerClick = (JLabel)e.getSource();
        if(answerClick.getText().equals(actualQuestion.getAnswer2())){
            System.out.println("Bonne réponse!");
            actualQuestion.setGoodAnswer(true);
        }else{
            System.out.println("Mauvaise réponse");
        }
        actualQuestion.setFinish(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        answerClick = (JLabel)e.getSource();
        answerColor = answerClick.getBackground();
        answerClick.setBackground(Color.PINK);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        answerClick.setBackground(answerColor);
    }
}
