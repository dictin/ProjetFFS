package ControllerPkg;

import ModelPkg.QuestionChaman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class QuestionsHandler implements MouseListener{
    /**
     * Question actuelle du Chaman
     */
    private QuestionChaman actualQuestion;
    /**
     * Réponse sélectionnée
     */
    private JLabel answerClick = new JLabel();
    /**
     * Couleur de la réponse sélectionné
     */
    private Color answerColor;
    /**
     * Contrôleur principal
     */
    private MasterController controller;

    /**
     * Constructeur du contrôleur des réponses aux questions du Chaman
     * @param actualQuestion question actuelle
     * @param controller contrôleur principal
     */
    public QuestionsHandler(QuestionChaman actualQuestion, MasterController controller){
        this.actualQuestion = actualQuestion;
        this.controller = controller;
    }

    /**
     * Méthode pour gérer lorsque le joueur click sur une réponse. ELle vérifie si c'est une bonne réponse ou non et ajuste
     * le karma en conséquence
     * @param e source du label clické
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        answerClick = (JLabel)e.getSource();
        if(answerClick.getText().equals(actualQuestion.getAnswer2())){
             controller.getPlayerDataController().modifyKarma(33);
             actualQuestion.setQuestionTaTuBienRepondu(1);

        }else{
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

    /**
     * Méthode pour modifier la couleur du label d'une réponse lorsque la souri entre dans le label
     * @param e source du label
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        answerClick = (JLabel)e.getSource();
        answerColor = answerClick.getBackground();
        answerClick.setBackground(Color.PINK);
    }

    /**
     * Méthode pour remmettre le label à sa couleur de base lorsque la souri quitte le label
     * @param e source du label
     */
    @Override
    public void mouseExited(MouseEvent e) {
        answerClick.setBackground(answerColor);
    }
}
