package ModelPkg;

import ObserverPkg.Observable;
import ObserverPkg.Observer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class QuestionData implements Observable {

    /**
     * Observateurs de la classe
     */
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    /**
     * Question du chaman
     */
    private QuestionChaman actualQuestion;
    /**
     * Liste des questions possibles
     */
    private static ArrayList<QuestionChaman> questionList = new ArrayList<QuestionChaman>();

    /**
     * Méthode qui lit les questions et les réponses dans un fichier text et qui les ajoutent à une liste
     */
    public static void initialize(){

        String line="";

        BufferedReader fileEnter = null;
        try {
            fileEnter = new BufferedReader(new FileReader("Question.txt"));
            line = fileEnter.readLine();
            String question = "";
            String answer0 = "";
            String answer1 = "";
            String answer2 = "";
            String answer3 = "";
            int count = 0;
            while (line != null){
                if(count == 0){
                question = line;
                    count++;
                }
               else if(count ==1){
                    answer0 = line;
                    count++;
                }
               else if(count ==2){
                    answer1 = line;
                    count++;
                }
              else  if(count ==3){
                    answer2 = line;
                    count++;
                }
               else if(count ==4){
                    answer3 = line;
                    count++;
                }
                else if(line.equals("*")){
                    count =0;
                    QuestionChaman questionC = new QuestionChaman(question,answer0,answer1,answer2,answer3);
                    questionList.add(questionC);
                }
                line = fileEnter.readLine();
            }
            fileEnter.close();
        } catch (IOException e) {
            System.out.println("fichier introuvable");
            e.printStackTrace();
        }
    }

    /**
     * Méthode qui retourne la liste avec toutes les questions du chaman
     * @return la liste avec toutes les questions du chaman
     */
    public static ArrayList<QuestionChaman> getQuestionList(){
        return questionList;
    }

    /**
     *
     * @param i
     */
    public void chooseQuestion(int i){
        actualQuestion=questionList.get(i);
        updateObservers();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    public void updateObservers(){
        for(int i=0; i<observers.size();i++){
            observers.get(i).update();
        }
    }
}
