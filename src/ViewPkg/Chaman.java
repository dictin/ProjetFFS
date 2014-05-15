package ViewPkg;

import ControllerPkg.MasterController;
import ControllerPkg.QuestionsHandler;
import ModelPkg.QuestionChaman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Chaman extends JLabel{
    private int noQuestion;

    private JLabel question = new JLabel();
    private JLabel questionNext = new JLabel();
    private JLabel questionNext2 = new JLabel();
    private JLabel questionNext3 = new JLabel();
    private JLabel answer0 = new JLabel();
    private JLabel answer1 = new JLabel();
    private JLabel answer2 = new JLabel();
    private JLabel answer3 = new JLabel();
    private QuestionsHandler questionsHandler;
    private MasterController controller;
    private ModelPkg.QuestionChaman actualQuestion;

public Chaman(final MasterController controller, ModelPkg.QuestionChaman actualQuestion){
    this.actualQuestion = actualQuestion;
    this.controller=controller;
    this.questionsHandler = new QuestionsHandler(this.actualQuestion);
    this.setOpaque(true);
    this.setBackground(new Color(Integer.parseInt("324159", 19)));
    this.setSize(500,375);
    JLabel numberQuestion = new JLabel("Question noX");
    numberQuestion.setFont(new Font("Impact", Font.PLAIN, 20));
    numberQuestion.setSize(300, 20);
    numberQuestion.setLocation(5, 5);
    this.add(numberQuestion);
    question.setText(this.actualQuestion.getQuestion());
    if(this.actualQuestion.getQuestionSize() > 55){
        //TODO Si on a le temps, s'assurer que le changement de ligne ne se fait pas au milieu d'un mot
        String question1 = this.actualQuestion.getQuestion().substring(0,55);
        String question2 = this.actualQuestion.getQuestion().substring(55);
        String question3,question4;
        question.setText(question1);
        questionNext.setText(question2);
        questionNext.setFont(new Font("Courier New", Font.PLAIN, 15));
        questionNext.setSize(495,20);
        questionNext.setLocation(5, 70);
        if(this.actualQuestion.getQuestionSize() > 110){
            question2 = this.actualQuestion.getQuestion().substring(55,110);
            question3 = this.actualQuestion.getQuestion().substring(110);
            questionNext.setText(question2);
            questionNext2.setText(question3);
            questionNext2.setFont(new Font("Courier New", Font.PLAIN, 15));
            questionNext2.setSize(495,20);
            questionNext2.setLocation(5, 90);
        }
        if(this.actualQuestion.getQuestionSize() > 165){
            question3 = this.actualQuestion.getQuestion().substring(110,165);
            question4 = this.actualQuestion.getQuestion().substring(165);
            questionNext2.setText(question3);
            questionNext3.setText(question4);
            questionNext3.setFont(new Font("Courier New", Font.PLAIN, 15));
            questionNext3.setSize(495,20);
            questionNext3.setLocation(5, 110);
        }
    }
    question.setFont(new Font("Courier New", Font.PLAIN, 15));
    question.setSize(495,20);
    question.setLocation(5, 50);
    this.add(question);
    this.add(questionNext);
    this.add(questionNext2);
    this.add(questionNext3);

    Random random = new Random();
    int answerMix = random.nextInt(3);
    switch(answerMix){
        case 0:
            answer0.setText(this.actualQuestion.getAnswer0());
            answer1.setText(this.actualQuestion.getAnswer1());
            answer2.setText(this.actualQuestion.getAnswer2());
            answer3.setText(this.actualQuestion.getAnswer3());
            break;
        case 1:
            answer0.setText(this.actualQuestion.getAnswer1());
            answer1.setText(this.actualQuestion.getAnswer2());
            answer2.setText(this.actualQuestion.getAnswer3());
            answer3.setText(this.actualQuestion.getAnswer0());
            break;
        case 2:
            answer0.setText(this.actualQuestion.getAnswer2());
            answer1.setText(this.actualQuestion.getAnswer3());
            answer2.setText(this.actualQuestion.getAnswer0());
            answer3.setText(this.actualQuestion.getAnswer1());
            break;
        case 3:
            answer0.setText(this.actualQuestion.getAnswer3());
            answer1.setText(this.actualQuestion.getAnswer0());
            answer2.setText(this.actualQuestion.getAnswer1());
            answer3.setText(this.actualQuestion.getAnswer2());
            break;
    }

    answer0.setFont(new Font("Arial Black", Font.PLAIN, 15));
    answer0.setSize(240, 75);
    answer0.setLocation(5, 150);
    answer0.setOpaque(true);
    answer0.setBackground( new Color(Integer.parseInt("054659", 21)));
    this.add(answer0);
    answer0.addMouseListener(questionsHandler);

    answer1.setFont(new Font("Arial Black", Font.PLAIN, 15));
    answer1.setSize(240,75);
    answer1.setLocation(255,150);
    answer1.setOpaque(true);
    answer1.setForeground(Color.white);
    answer1.setBackground( new Color(Integer.parseInt("254659", 30)));
    this.add(answer1);
    answer1.addMouseListener(questionsHandler);

    answer2.setFont(new Font("Arial Black", Font.PLAIN, 15));
    answer2.setSize(240, 75);
    answer2.setLocation(5, 250);
    answer2.setOpaque(true);
    answer2.setForeground(Color.white);
    answer2.setBackground(new Color(Integer.parseInt("254659", 21)));
    this.add(answer2);
    answer2.addMouseListener(questionsHandler);

    answer3.setFont(new Font("Arial Black", Font.PLAIN, 15));
    answer3.setSize(240, 75);
    answer3.setLocation(255, 250);
    answer3.setOpaque(true);
    answer3.setBackground(new Color(Integer.parseInt("115351", 20)));
    this.add(answer3);
    answer3.addMouseListener(questionsHandler) ;

}

public void updateQuestion(){
    repaint();
    }
}

