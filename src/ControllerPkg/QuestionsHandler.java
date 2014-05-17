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
    private MasterController controller;


    public QuestionsHandler(QuestionChaman actualQuestion, MasterController controller){
        this.actualQuestion = actualQuestion;
        this.controller = controller;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        answerClick = (JLabel)e.getSource();
        if(answerClick.getText().equals(actualQuestion.getAnswer2())){
            //TODO avertir joueur d'une bonne réponse avec son
             controller.getPlayerDataController().modifyKarma(33);
             actualQuestion.setQuestionTaTuBienRepondu(1);

        }else{
            //TODO avertir joueur d'une mauvaise réponse avec son
            controller.getPlayerDataController().modifyKarma(-33);
            actualQuestion.setQuestionTaTuBienRepondu(-1);
        }

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
