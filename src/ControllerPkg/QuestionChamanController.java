package ControllerPkg;
import ModelPkg.QuestionChaman;
import ModelPkg.QuestionData;

import java.util.ArrayList;
import java.util.Random;


public class QuestionChamanController {

    public QuestionChamanController(){

    }
    public QuestionChaman getQuestion(){
        int noQuestion;
        do{
        Random random = new Random();
        noQuestion = random.nextInt(QuestionData.getQuestionList().size());
        }while(QuestionData.getQuestionList().get(noQuestion).getFinish());
        return QuestionData.getQuestionList().get(noQuestion);

    }

}
