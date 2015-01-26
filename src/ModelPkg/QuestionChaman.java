package ModelPkg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class QuestionChaman {

    /**
     * Question du chaman
     */
    private String question;
    /**
     * 1ere réponse possible
     */
    private String answer0;
    /**
     * 2eme réponse possible
     */
    private String answer1;
    /**
     * 3eme réponse possible
     */
    private String answer2;
    /**
     * 4eme réponse possible
     */
    private String answer3;
    /**
     * Taille de la question
     */
    private int questionSize;
    /**
     * S'agit-il de la bonne réponse
     * 0:= aucune réponse, -1 := mauvaise réponse, 1 := bonne réponse
     */
    private int questionTaTuBienRepondu = 0; //

    /**
     * Constructeur d'une question du chaman
     * @param q question du chaman
     * @param a0 réponse1
     * @param a1 réponse2
     * @param a2 réponse3
     * @param a3 réponse4
     */
    public  QuestionChaman(String q, String a0, String a1, String a2, String a3){
        this.question = q;
        this.answer0 = a0;
        this.answer1 = a1;
        this.answer2 = a2;
        this.answer3 = a3 ;
        questionSize =question.length();
    }

    /**
     * Méthode qui retourne la longeur de la question
     * @return longeur de la question
     */
    public int getQuestionSize() {
        return questionSize;
    }

    /**
     * Méthode qui retourne la réponse1
     * @return la réponse1
     */
    public String getAnswer1() {
        return answer1;
    }
    /**
     * Méthode qui retourne la réponse2
     * @return la réponse2
     */
    public String getAnswer2() {
        return answer2;
    }
    /**
     * Méthode qui retourne la réponse3
     * @return la réponse3
     */
    public String getAnswer3() {
        return answer3;
    }
    /**
     * Méthode qui retourne la réponse0
     * @return la réponse0
     */
    public String getAnswer0() {
        return answer0;
    }

    /**
     * Méthode qui retourne la question
     * @return la question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Méthode qui retourne si le joueur a bien répondu ou non à la question
     * @return -1 si le joueur a mal répondu, 0 si le joueur n'a pas répondu et 1 si le joueur a bien répondu
     */
    public int getQuestionTaTuBienRepondu() {

        return questionTaTuBienRepondu;
    }

    /**
     * Méthode qui modifie si le joueur a bien répond ou non à la question
     * @param questionTaTuBienRepondu réponse du joueur à la question
     */
    public  void setQuestionTaTuBienRepondu(int questionTaTuBienRepondu) {



















        this.questionTaTuBienRepondu = questionTaTuBienRepondu;
    }
}

