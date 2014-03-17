package ModelPkg;

import java.util.Random;

/**
 * Created by Chloé on 14-03-17.
 */
public class Behavior {

    private int objectif;
    private int [][] table1 = new int [3][3];
    private int [][] table2 = new int [5][5];
    private int [][] table3 = new int [7][7];

    public Behavior(){
        //remplir la table1 avec des ints
        int i = 1;
        for (int ligne = 0; ligne < 3; ligne++) {
            for (int colonne = 0; colonne < 3; colonne++) {
                if(ligne == 1 && colonne ==1){
                    table1[ligne][colonne] = 0;
                }
                else{
                    table1[ligne][colonne] = i;
                    i++;
                }
            }
        }
    }

    public int drunk(){
        Random random = new Random();
        int rndobjectif = random.nextInt(8);
        return objectif;
    }
    //TODO Modifier le code pour que le table soit de type 'Case' et pour aller chercher le SmellID du Smell dominant de cette case
    public int nourriture(Case [][] table, int odorat){
        int ligne = 0, colonne = 0;
        for(int ligne = 0; ligne < 3; ligne++){
            for(int colonne = 0; colonne < 3 ; colonne++){
                for(int )
            }
        }


            }
            //Goodsmell équivaut à la case où l'odeur de nourriture est la plus forte
           // objectif =table1[ligne][colonne];
        }


      //  return objectif;
    }

}


