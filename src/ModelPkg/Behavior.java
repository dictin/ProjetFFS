package ModelPkg;

import com.sun.xml.internal.bind.v2.TODO;

import java.awt.*;
import java.util.Random;

/**
 * Created by Chloé on 14-03-17.
 */
public class Behavior {

    //objectif est composé de -1,0 et 1. il indique le déplacement à faire. Donc coordonné + objectif = noulles coordonneé du fourmilier après le déplacement
    private static Point objectif;

    public Behavior(){
    }

    public static Point drunk(){
        Random random = new Random();
        int rndobjectifX = random.nextInt(2);
        int rndobjectifY = random.nextInt(2);
        objectif.setLocation(rndobjectifX-1,rndobjectifY-1);
        return objectif;
    }

    //TODO Vérifier s'il n'y a pas de null pointer exception (vérification d'une case inexistante)
    public static Point search(Case [][] table, int odorat, int typeOdeur){
        int goodLigne = 0;
        int goodColonne = 0;

        int goodSmell = 0;
        for(int ligne = 0; ligne < 3; ligne++){
            for(int colonne = 0; colonne < 3 ; colonne++){
                //La boucle for se fait plusieurs fois tout dépendament de la puissance de l'odorat du fourmilier
                for(int i =0; i<= odorat; i++ ){
                    //On ne veut pas vérifier la case du milieu (l'endroit où est le fourmilier) et que la cas n'est pas null
                    if(ligne !=0 && colonne != 0 && table[ligne][colonne] != null){
                    //Le type de smell ( ex: 1 est de la nourriture, 2 est un ennemi... (voir Smell pour tous les détails))
                       /* if(table[ligne][colonne].getSortedSmellArrayList().get(i).getType() == typeOdeur){
                        //Si l'intensité de cette odeur est plus forte que l'odeur déjà enregistrée
                            if(table[ligne][colonne].getSortedSmellArrayList().get(i).getIntensity() > goodSmell){
                                goodSmell = table[ligne][colonne].getSortedSmellArrayList().get(i).getIntensity();
                                goodLigne = ligne;
                                goodColonne = colonne;
                            }
                        }*/
                    }
                }
            }
        }

        //Maintenant ligne et colonne ont les coordonnées de la case où la senteur de nourriture est la plus forte.
        objectif.setLocation(goodLigne-1,goodColonne-1);
        return objectif;
    }



}


