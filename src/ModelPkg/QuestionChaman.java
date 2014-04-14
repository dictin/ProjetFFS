package ModelPkg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Chloé on 14-04-14.
 */
public class QuestionChaman {
    private String question;
    private String answer0;
    private String answer1;
    private String answer2;
    private String answer3;

    public  QuestionChaman(String q, String a0, String a1, String a2, String a3){
        this.question = q;
        this.answer0 = a0;
        this.answer1 = a1;
        this.answer2 = a2;
        this.answer3 = a3 ;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer0() {
        return answer0;
    }

    public String getQuestion() {
        return question;
    }
}
