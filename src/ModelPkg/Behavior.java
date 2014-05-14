package ModelPkg;

import ModelPkg.WildObjects.WildObject;

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

        int rndobjectifX = 0;
        int rndobjectifY = 0;

        while(rndobjectifX == 0 && rndobjectifY == 0){
            rndobjectifX = random.nextInt(Behavior.SUBSECTION_SIZE);
            rndobjectifY = random.nextInt(Behavior.SUBSECTION_SIZE);
        }

        objectif = new Point(rndobjectifX,rndobjectifY);
        return objectif;
    }

    private static boolean isThereASmell(Point location){
        Case[][] subsection = MapData.getSubsection2(location);
        boolean isEmpty = true;

        for(int i = 0; i < subsection.length && isEmpty; i++){
            for (int j = 0; j < subsection[i].length && isEmpty; j++){
                if (!subsection[i][j].getSortedSmellArrayList().isEmpty()){
                    isEmpty = false;
                }
            }
        }

        return isEmpty;
    }

    public static VirtualFutureAction evaluateBestObjective(Point position, MentalStates mentalState, int moralValue){
        if (Behavior.isThereASmell(position)){
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
                            if (i != 1 || j != 1){
                                if (strongestSmellPoint == null && subsection[i][j].getSortedSmellArrayList().get(0).getType() == SmellType.FOOD){
                                    strongestSmellPoint = new Point(i,j);
                                }else if(subsection[strongestSmellPoint.x][strongestSmellPoint.y].getSortedSmellArrayList().get(0).getIntensity() < subsection[i][j].getSortedSmellArrayList().get(0).getIntensity() && subsection[i][j].getSortedSmellArrayList().get(0).getType() == SmellType.FOOD){
                                    strongestSmellPoint = new Point(i,j);
                                }
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
                        int weakestIndex = subsection[i][j].getSortedSmellArrayList().size()-1;
                        if (weakAllySmell == null && subsection[i][j].getSortedSmellArrayList().get(weakestIndex).getType() == SmellType.ALLY){
                            weakAllySmell = new Point(i,j);
                        }else if(subsection[weakAllySmell.x][weakAllySmell.y].getSortedSmellArrayList().get(weakestIndex).getIntensity() < subsection[i][j].getSortedSmellArrayList().get(weakestIndex).getIntensity() && subsection[i][j].getSortedSmellArrayList().get(weakestIndex).getType() == SmellType.ALLY){
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
                        if (strongestSmellPoint == null && subsection[i][j].getSortedSmellArrayList().get(0).getType() == SmellType.FOE){
                            strongestSmellPoint = new Point(i,j);
                        }else if(subsection[strongestSmellPoint.x][strongestSmellPoint.y].getSortedSmellArrayList().get(0).getIntensity() < subsection[i][j].getSortedSmellArrayList().get(0).getIntensity() &&subsection[i][j].getSortedSmellArrayList().get(0).getType() == SmellType.FOE){
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
                        if (strongestSmellPoint == null && subsection[i][j].getSortedSmellArrayList().get(0).getType() == SmellType.FOE){
                            strongestSmellPoint = new Point(i,j);
                        }else if(subsection[strongestSmellPoint.x][strongestSmellPoint.y].getSortedSmellArrayList().get(0).getIntensity() < subsection[i][j].getSortedSmellArrayList().get(0).getIntensity() &&subsection[i][j].getSortedSmellArrayList().get(0).getType() == SmellType.FOE){
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
                        if (subsection[i][j].getOccupant().getSmell().getType() == SmellType.FOE){
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
                            if (strongestSmellPoint == null && subsection[i][j].getSortedSmellArrayList().get(0).getType() == SmellType.FOE){
                                strongestSmellPoint = new Point(i,j);
                            }else if(subsection[strongestSmellPoint.x][strongestSmellPoint.y].getSortedSmellArrayList().get(0).getIntensity() < subsection[i][j].getSortedSmellArrayList().get(0).getIntensity() &&subsection[i][j].getSortedSmellArrayList().get(0).getType() == SmellType.FOE){
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
                        if (strongestSmellPoint != null && subsection[strongestSmellPoint.x][strongestSmellPoint.y].getSortedSmellArrayList().get(0).getType() == SmellType.FOE){
                            enemyNear = true;
                        }
                    }

                }

                if (enemyNear){
                    if (Behavior.moralCheck(moralValue)){
                        for (int i = 0; i < Behavior.SUBSECTION_SIZE; i++){
                            for (int j = 0; j < Behavior.SUBSECTION_SIZE; j++){
                                if (strongestSmellPoint == null && subsection[i][j].getSortedSmellArrayList().get(0).getType() == SmellType.FOOD){
                                    strongestSmellPoint = new Point(i,j);
                                }else if(subsection[strongestSmellPoint.x][strongestSmellPoint.y].getSortedSmellArrayList().get(0).getIntensity() < subsection[i][j].getSortedSmellArrayList().get(0).getIntensity() &&subsection[i][j].getSortedSmellArrayList().get(0).getType() == SmellType.FOOD){
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
                                if (strongestSmellPoint == null && subsection[i][j].getSortedSmellArrayList().get(0).getType() == SmellType.FOE){
                                    strongestSmellPoint = new Point(i,j);
                                }else if(subsection[strongestSmellPoint.x][strongestSmellPoint.y].getSortedSmellArrayList().get(0).getIntensity() < subsection[i][j].getSortedSmellArrayList().get(0).getIntensity() &&subsection[i][j].getSortedSmellArrayList().get(0).getType() == SmellType.FOE){
                                    strongestSmellPoint = new Point(i,j);
                                }
                            }

                        }

                        if (subsection[strongestSmellPoint.x][strongestSmellPoint.y].getOccupant() != null && subsection[strongestSmellPoint.x][strongestSmellPoint.y].getOccupant().getSmell().getType() == SmellType.FOE){
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
                            if (strongestSmellPoint == null && subsection[i][j].getSortedSmellArrayList().get(0).getType() == SmellType.FOOD){
                                strongestSmellPoint = new Point(i,j);
                            }else if(subsection[strongestSmellPoint.x][strongestSmellPoint.y].getSortedSmellArrayList().get(0).getIntensity() < subsection[i][j].getSortedSmellArrayList().get(0).getIntensity() &&subsection[i][j].getSortedSmellArrayList().get(0).getType() == SmellType.FOOD){
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

        }else {
            VirtualFutureAction virtualFutureAction = new VirtualFutureAction(Behavior.drunk(), ActionTypes.GO_TO_LOCATION);
            return virtualFutureAction;
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


