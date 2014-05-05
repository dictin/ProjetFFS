package ModelPkg;

import java.awt.*;
import java.util.Random;


public class Behavior {

    //objectif est composé de -1,0 et 1. il indique le déplacement à faire. Donc coordonné + objectif = noulles coordonneé du fourmilier après le déplacement
    private static Point objectif;
    private static final int SUBSECTION_SIZE = 3;

    public Behavior(){
    }

    public static Point drunk(){
        Random random = new Random();
        int rndobjectifX = random.nextInt(Behavior.SUBSECTION_SIZE-1);
        int rndobjectifY = random.nextInt(Behavior.SUBSECTION_SIZE-1);
        objectif.setLocation(rndobjectifX-1,rndobjectifY-1);
        return objectif;
    }

    //TODO Vérifier s'il n'y a pas de null pointer exception (vérification d'une case inexistante)
    public static Point search(Case [][] table, int odorat, int typeOdeur){
        int goodLigne = 0;
        int goodColonne = 0;

        int goodSmell = 0;
        for(int ligne = 0; ligne < Behavior.SUBSECTION_SIZE; ligne++){
            for(int colonne = 0; colonne < Behavior.SUBSECTION_SIZE ; colonne++){
                //La boucle for se fait plusieurs fois tout dépendament de la puissance de l'odorat du fourmilier
                for(int i =0; i<= odorat; i++ ){
                    //On ne veut pas vérifier la case du milieu (l'endroit où est le fourmilier) et que la cas n'est pas null
                    if(ligne !=0 && colonne != 0 && table[ligne][colonne] != null){
                    //Le type de smell ( ex: 1 est de la nourriture, 2 est un ennemi... (voir Smell pour tous les détails))
                        if(table[ligne][colonne].getSortedSmellArrayList().get(i).getType() == typeOdeur){
                        //Si l'intensité de cette odeur est plus forte que l'odeur déjà enregistrée
                            if(table[ligne][colonne].getSortedSmellArrayList().get(i).getIntensity() > goodSmell){
                                goodSmell = table[ligne][colonne].getSortedSmellArrayList().get(i).getIntensity();
                                goodLigne = ligne;
                                goodColonne = colonne;
                            }
                        }
                    }
                }
            }
        }

        //Maintenant ligne et colonne ont les coordonnées de la case où la senteur de nourriture est la plus forte.
        objectif.setLocation(goodLigne-1,goodColonne-1);
        return objectif;
    }

    public static VirtualFutureAction evaluateBestObjective(Point position, MentalStates mentalState, int moralValue){
        Case[][] subsection = MapData.getSubsection2(position);
        boolean nearFoodSource = false;
        if (mentalState == MentalStates.WEAK){
            Point strongestSmellPoint = null;
            for (int i = 0; i < Behavior.SUBSECTION_SIZE; i++) {
                for (int j = 0; j < Behavior.SUBSECTION_SIZE; j++) {
                    if (subsection[i][j].getWildObject().getType() == WildObject.HIVE_ID || subsection[i][j].getWildObject().getType() == WildObject.FOOD_ID){
                        nearFoodSource = true;
                    }

                }
            }

            if (nearFoodSource){
                Point foodLocation = null;
                for (int i = 0; i < Behavior.SUBSECTION_SIZE; i++){
                    for (int j = 0; j < Behavior.SUBSECTION_SIZE; j++){
                        if (foodLocation == null && subsection[i][j].getWildObject().getType() == WildObject.FOOD_ID){
                            foodLocation = new Point(i,j);
                        }
                    }
                }

                if (foodLocation != null){
                    return new VirtualFutureAction(foodLocation, ActionTypes.EAT_FROM_LOCATION);
                }else {
                    return new VirtualFutureAction(Behavior.drunk(), ActionTypes.GO_TO_LOCATION);
                }
            }else{
               for (int i = 0; i < Behavior.SUBSECTION_SIZE; i++){
                   for (int j = 0; j < Behavior.SUBSECTION_SIZE; j++){
                       if (strongestSmellPoint == null && subsection[i][j].getSortedSmellArrayList().get(0).getType() == Smell.FOOD_ODOR){
                           strongestSmellPoint = new Point(i,j);
                       }else if(subsection[strongestSmellPoint.x][strongestSmellPoint.y].getSortedSmellArrayList().get(0).getIntensity() < subsection[i][j].getSortedSmellArrayList().get(0).getIntensity() &&subsection[i][j].getSortedSmellArrayList().get(0).getType() == Smell.FOOD_ODOR){
                           strongestSmellPoint = new Point(i,j);
                       }
                   }
               }

               if (strongestSmellPoint == null){
                   return new VirtualFutureAction(Behavior.drunk(), ActionTypes.GO_TO_LOCATION);
               }else {
                   return new VirtualFutureAction(strongestSmellPoint, ActionTypes.GO_TO_LOCATION);
               }
           }



        }else if (mentalState == MentalStates.RETURN_TO_BASE){
            Point weakAllySmell = null;
            for (int i = 0; i < Behavior.SUBSECTION_SIZE; i++){
                for(int j = 0; j < Behavior.SUBSECTION_SIZE; j++){
                    if (weakAllySmell == null && subsection[i][j].getSortedSmellArrayList().get(0).getType() == Smell.ALLY_ODOR){
                        weakAllySmell = new Point(i,j);
                    }else if(subsection[weakAllySmell.x][weakAllySmell.y].getSortedSmellArrayList().get(0).getIntensity() < subsection[i][j].getSortedSmellArrayList().get(0).getIntensity() &&subsection[i][j].getSortedSmellArrayList().get(0).getType() == Smell.ALLY_ODOR){
                        weakAllySmell = new Point(i,j);
                    }

                }
            }

            if (weakAllySmell == null){
                return new VirtualFutureAction(Behavior.drunk(), ActionTypes.GO_TO_LOCATION);
            }else {
                return new VirtualFutureAction(weakAllySmell, ActionTypes.GO_TO_LOCATION);
            }
        }else if (mentalState == MentalStates.PARALYSED){
            return new VirtualFutureAction(new Point(0,0), ActionTypes.DO_NOTHING);
        }else if (mentalState == MentalStates.SCARED){
            Point strongestSmellPoint = null;

            for (int i = 0; i < Behavior.SUBSECTION_SIZE; i++){
                for (int j = 0; j < Behavior.SUBSECTION_SIZE; j++){
                    if (strongestSmellPoint == null && subsection[i][j].getSortedSmellArrayList().get(0).getType() == Smell.ENEMY_ODOR){
                        strongestSmellPoint = new Point(i,j);
                    }else if(subsection[strongestSmellPoint.x][strongestSmellPoint.y].getSortedSmellArrayList().get(0).getIntensity() < subsection[i][j].getSortedSmellArrayList().get(0).getIntensity() &&subsection[i][j].getSortedSmellArrayList().get(0).getType() == Smell.ENEMY_ODOR){
                        strongestSmellPoint = new Point(i,j);
                    }
                }

            }

            if (strongestSmellPoint == null){
                return new VirtualFutureAction(Behavior.drunk(), ActionTypes.FLEE_TO_LOCATION);
            }else {
                int x = strongestSmellPoint.x;
                int y = strongestSmellPoint.y;

                x = Math.abs(x-Behavior.SUBSECTION_SIZE-1);
                y = Math.abs(y-Behavior.SUBSECTION_SIZE-1);

                return new VirtualFutureAction(new Point(x,y), ActionTypes.FLEE_TO_LOCATION);
            }

        }else if (mentalState == MentalStates.FLEEING){

            Point strongestSmellPoint = null;

            for (int i = 0; i < Behavior.SUBSECTION_SIZE; i++){
                for (int j = 0; j < Behavior.SUBSECTION_SIZE; j++){
                    if (strongestSmellPoint == null && subsection[i][j].getSortedSmellArrayList().get(0).getType() == Smell.ENEMY_ODOR){
                        strongestSmellPoint = new Point(i,j);
                    }else if(subsection[strongestSmellPoint.x][strongestSmellPoint.y].getSortedSmellArrayList().get(0).getIntensity() < subsection[i][j].getSortedSmellArrayList().get(0).getIntensity() &&subsection[i][j].getSortedSmellArrayList().get(0).getType() == Smell.ENEMY_ODOR){
                        strongestSmellPoint = new Point(i,j);
                    }
                }

            }

            if (strongestSmellPoint == null){
                return new VirtualFutureAction(Behavior.drunk(), ActionTypes.GO_TO_LOCATION);
            }else {
                int x = strongestSmellPoint.x;
                int y = strongestSmellPoint.y;

                x = Math.abs(x-Behavior.SUBSECTION_SIZE-1);
                y = Math.abs(y-Behavior.SUBSECTION_SIZE-1);

                return new VirtualFutureAction(new Point(x,y), ActionTypes.FLEE_TO_LOCATION);
            }

        }else if (mentalState == MentalStates.AGRESSIVE){
            Point occupiedCase = null;

            for (int i = 0; i < Behavior.SUBSECTION_SIZE; i++){
                for (int j = 0; j < Behavior.SUBSECTION_SIZE; j++){
                    if (subsection[i][j].getOccupant().getSmellID() == Smell.ENEMY_ODOR){
                        occupiedCase = new Point(i,j);
                    }
                }
            }

            if (occupiedCase != null){
                return new VirtualFutureAction(occupiedCase, ActionTypes.ATTACK_AT_LOCATION);
            }else {
                Point strongestSmellPoint = null;
                for (int i = 0; i < Behavior.SUBSECTION_SIZE; i++){
                    for (int j = 0; j < Behavior.SUBSECTION_SIZE; j++){
                        if (strongestSmellPoint == null && subsection[i][j].getSortedSmellArrayList().get(0).getType() == Smell.ENEMY_ODOR){
                            strongestSmellPoint = new Point(i,j);
                        }else if(subsection[strongestSmellPoint.x][strongestSmellPoint.y].getSortedSmellArrayList().get(0).getIntensity() < subsection[i][j].getSortedSmellArrayList().get(0).getIntensity() &&subsection[i][j].getSortedSmellArrayList().get(0).getType() == Smell.ENEMY_ODOR){
                            strongestSmellPoint = new Point(i,j);
                        }
                    }

                }

                if (strongestSmellPoint == null){
                    return new VirtualFutureAction(Behavior.drunk(), ActionTypes.GO_TO_LOCATION);
                }else {
                    return new VirtualFutureAction(strongestSmellPoint, ActionTypes.RUN_AT_ENEMY);
                }
            }



        }else{ // mentalState == MentalStates.NEUTRAL
            Point strongestSmellPoint = null;
            boolean enemyNear = false;
            for (int i = 0; i < Behavior.SUBSECTION_SIZE; i++){
                for (int j = 0; j < Behavior.SUBSECTION_SIZE; j++){
                    if (subsection[strongestSmellPoint.x][strongestSmellPoint.y].getSortedSmellArrayList().get(0).getType() == Smell.ENEMY_ODOR){
                        enemyNear = true;
                    }
                }

            }

            if (enemyNear){
                if (Behavior.moralCheck(moralValue)){
                    for (int i = 0; i < Behavior.SUBSECTION_SIZE; i++){
                        for (int j = 0; j < Behavior.SUBSECTION_SIZE; j++){
                            if (strongestSmellPoint == null && subsection[i][j].getSortedSmellArrayList().get(0).getType() == Smell.FOOD_ODOR){
                                strongestSmellPoint = new Point(i,j);
                            }else if(subsection[strongestSmellPoint.x][strongestSmellPoint.y].getSortedSmellArrayList().get(0).getIntensity() < subsection[i][j].getSortedSmellArrayList().get(0).getIntensity() &&subsection[i][j].getSortedSmellArrayList().get(0).getType() == Smell.FOOD_ODOR){
                                strongestSmellPoint = new Point(i,j);
                            }
                        }

                    }

                    if (subsection[strongestSmellPoint.x][strongestSmellPoint.y].getWildObject().getType() == WildObject.FOOD_ID ||subsection[strongestSmellPoint.x][strongestSmellPoint.y].getWildObject().getType() == WildObject.HIVE_ID){
                        return new VirtualFutureAction(strongestSmellPoint, ActionTypes.PICKUP_FROM_LOCATION);
                    }else if (strongestSmellPoint != null){
                        return new VirtualFutureAction(strongestSmellPoint, ActionTypes.GO_TO_LOCATION);
                    }else {
                        return new VirtualFutureAction(Behavior.drunk(), ActionTypes.GO_TO_LOCATION);
                    }


                }else{
                    for (int i = 0; i < Behavior.SUBSECTION_SIZE; i++){
                        for (int j = 0; j < Behavior.SUBSECTION_SIZE; j++){
                            if (strongestSmellPoint == null && subsection[i][j].getSortedSmellArrayList().get(0).getType() == Smell.ENEMY_ODOR){
                                strongestSmellPoint = new Point(i,j);
                            }else if(subsection[strongestSmellPoint.x][strongestSmellPoint.y].getSortedSmellArrayList().get(0).getIntensity() < subsection[i][j].getSortedSmellArrayList().get(0).getIntensity() &&subsection[i][j].getSortedSmellArrayList().get(0).getType() == Smell.ENEMY_ODOR){
                                strongestSmellPoint = new Point(i,j);
                            }
                        }

                    }

                    if (subsection[strongestSmellPoint.x][strongestSmellPoint.y].getOccupant() != null && subsection[strongestSmellPoint.x][strongestSmellPoint.y].getOccupant().getSmellID() == Smell.ENEMY_ODOR){
                        return new VirtualFutureAction(strongestSmellPoint, ActionTypes.ATTACK_AT_LOCATION);
                    }else if (strongestSmellPoint != null){
                        return new VirtualFutureAction(strongestSmellPoint, ActionTypes.RUN_AT_ENEMY);
                    }else {
                        return new VirtualFutureAction(Behavior.drunk(), ActionTypes.GO_TO_LOCATION);
                    }

                }
            }else{
                for (int i = 0; i < Behavior.SUBSECTION_SIZE; i++){
                    for (int j = 0; j < Behavior.SUBSECTION_SIZE; j++){
                        if (strongestSmellPoint == null && subsection[i][j].getSortedSmellArrayList().get(0).getType() == Smell.FOOD_ODOR){
                            strongestSmellPoint = new Point(i,j);
                        }else if(subsection[strongestSmellPoint.x][strongestSmellPoint.y].getSortedSmellArrayList().get(0).getIntensity() < subsection[i][j].getSortedSmellArrayList().get(0).getIntensity() &&subsection[i][j].getSortedSmellArrayList().get(0).getType() == Smell.FOOD_ODOR){
                            strongestSmellPoint = new Point(i,j);
                        }
                    }

                }

                if (subsection[strongestSmellPoint.x][strongestSmellPoint.y].getWildObject().getType() == WildObject.FOOD_ID ||subsection[strongestSmellPoint.x][strongestSmellPoint.y].getWildObject().getType() == WildObject.HIVE_ID){
                    return new VirtualFutureAction(strongestSmellPoint, ActionTypes.PICKUP_FROM_LOCATION);
                }else if (strongestSmellPoint != null){
                    return new VirtualFutureAction(strongestSmellPoint, ActionTypes.GO_TO_LOCATION);
                }else {
                    return new VirtualFutureAction(Behavior.drunk(), ActionTypes.GO_TO_LOCATION);
                }
            }
        }
    }

    public static boolean moralCheck(int moralValue){
        boolean returnValue;
        Random random = new Random();
        int rollResult = random.nextInt(100)+1;

        returnValue = rollResult < moralValue;
        return returnValue;


    }


}


