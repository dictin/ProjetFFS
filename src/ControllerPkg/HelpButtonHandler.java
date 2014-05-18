package ControllerPkg;


import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HelpButtonHandler implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        JOptionPane.showMessageDialog(null, "Bienvenue dans la section aide. \n\n" +
                "Le but de Fourmilier Fourmilière Simulator est de créer sa colonie de fourmilier et de survivre à travers les niveaux \n \n" +
                "Pour ce faire, vous devez jouer stratégiquement et utiliser judicieusement les objets du magasins.\n" +
                "Les fourmiliers vous aideront à récolter de la nourriture que vous pourrez utiliser pour créer d'autres fourmiliers\n" +
                "ou pour acheter des améliorations et objets au magasin.\n\n" +
                "Lorsque la quantité de nourriture nécessaire pour passer au prochain niveau est atteinte, le Chaman des fourmiliers \n" +
                "va vous posez 3questions pour déterminer si vous méritez sa bénédiction pour le prochain niveau. Une bonne réponse \n" +
                "augmentera votre chance d'avoir des bons événements et vis-versa pour une mauvaise réponse.\n\n" +
                "Après les 3 questions, Vous aurez la possibilité de créer votre nouvelle race de fourmiliers.\n\n" +
                "Bonne chance!");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
