package ModelPkg;

import ModelPkg.WildObjects.FoodSource;
import ModelPkg.WildObjects.WildObject;

import java.awt.*;
import java.util.Random;


public class Behavior {

    //objectif est composé de -1,0 et 1. il indique le déplacement à faire. Donc coordonné + objectif = noulles coordonneé du fourmilier après le déplacement
    private static Point objectif;
    private static final int SUBSECTION_SIZE = 3;

    public Behavior(){
    }

    public static Point drunk(Point position){
        System.out.println("Hic! I'm drunk");
        Point theoreticalPosition;
        Random random = new Random();

        int rndobjectifX = 0;
        int rndobjectifY = 0;

        rndobjectifX = random.nextInt(3) - 1;
        rndobjectifY = random.nextInt(3) - 1;



        objectif = new Point(rndobjectifX,rndobjectifY);
        theoreticalPosition = new Point(objectif.x+position.x, objectif.y+position.y);

        if (!Behavior.isThereNowhereToGo(position)){
            if (MapData.getCase(theoreticalPosition).getOccupant() == null && MapData.getCase(theoreticalPosition).getWildObject().getType() == WildObject.EMPTY_ID){
                return objectif;
            }else{
                return Behavior.drunk(position);
            }
        }else{
            return new Point(0,0);
        }




    }

    private static boolean isThereNowhereToGo(Point origin) {
        boolean nowhereToGo = true;
        Case[][] subsection = MapData.getSubsection2(origin);
        for(int i = 0; i < subsection.length; i++){
            for(int j = 0; j < subsection[i].length; j++){
                if(subsection[i][j].getOccupant() == null && subsection[i][j].getWildObject().getType() == WildObject.EMPTY_ID){
                    nowhereToGo = false;
                }

            }
        }

        return nowhereToGo;
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

        return !isEmpty;
    }

    public static VirtualFutureAction evaluateBestObjective(Point position, MentalStates mentalState, int moralValue, int sensitivityThreshold){

    }

    public static boolean moralCheck(int moralValue){
        boolean returnValue;
        Random random = new Random();
        int rollResult = random.nextInt(100)+1;

        returnValue = rollResult < moralValue;
        return returnValue;


    }


}


