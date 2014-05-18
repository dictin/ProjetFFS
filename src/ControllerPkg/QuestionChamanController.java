package ControllerPkg;
import ModelPkg.QuestionChaman;
import ModelPkg.QuestionData;

import java.util.ArrayList;
import java.util.Random;


public class QuestionChamanController {

    /**
     * Méthode qui choisi aléatoirement une question du Chaman et qui vérifie que cette question n'a pas déjà été affichée
     * @return la question choisi aléatoirement
     */
    public QuestionChaman getQuestion(){
        int noQuestion;
        do{
        Random random = new Random();
        noQuestion = random.nextInt(QuestionData.getQuestionList().size());
        }while(QuestionData.getQuestionList().get(noQuestion).getQuestionTaTuBienRepondu()!=0);
        return QuestionData.getQuestionList().get(noQuestion);
    }

}
