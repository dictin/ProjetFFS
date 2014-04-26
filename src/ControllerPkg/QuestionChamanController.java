package ControllerPkg;
import ModelPkg.QuestionChaman;
import ModelPkg.QuestionData;

import java.util.ArrayList;
import java.util.Random;


public class QuestionChamanController {

    public QuestionChamanController(){

    }
    public QuestionChaman getQuestion(){
        Random random = new Random();
        int noQuestion = random.nextInt(QuestionData.getQuestionList().size());
        return QuestionData.getQuestionList().get(noQuestion);

    }

}
