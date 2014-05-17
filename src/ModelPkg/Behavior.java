package ModelPkg;

import ModelPkg.WildObjects.FoodSource;
import ModelPkg.WildObjects.WildObject;

import java.awt.*;
import java.util.Random;


public class Behavior {

    //objective est composé de -1,0 et 1. il indique le déplacement à faire. Donc coordonné + objective = noulles coordonneé du fourmilier après le déplacement
    private static Point objective;
    private static final int SUBSECTION_SIZE = 3;

    public Behavior(){
    }

    public static Point drunk(Point position){
        System.out.println("Hic! I'm drunk");
        Point correctedObjective = null;
        Random random = new Random();

        if ((Behavior.isThereNowhereToGo(position))){
            objective=position;
        }else{
            do {
                int rndobjectifX = 0;
                int rndobjectifY = 0;

                rndobjectifX = random.nextInt(3)-1;
                rndobjectifY = random.nextInt(3)-1;



                objective = new Point(rndobjectifX,rndobjectifY);
                correctedObjective = new Point(objective.x+position.x, objective.y+position.y);
            }while (!Behavior.confirmObjective(correctedObjective));
        }

        return objective;
    }

    private static boolean confirmObjective(Point objective) {
        boolean isValidObjective;
        Point caseToCheck = objective;
        isValidObjective = (MapData.getCase(caseToCheck).getWildObject().getType() == WildObject.EMPTY_ID) ? true : false;

        return isValidObjective;
    }

    private static boolean isThereNowhereToGo(Point origin) {
        boolean nowhereToGo = true;
        Case[][] subsection = MapData.getSubsection2(origin);
        for(int i = 0; i < subsection.length&&nowhereToGo; i++){
            for(int j = 0; j < subsection[i].length&&nowhereToGo; j++){
                //System.out.println("x;y"+origin.x+";"+origin.y);
                if((!((i==j)&&i==0))&&subsection[i][j].getOccupant() == null && subsection[i][j].getWildObject().getType() == WildObject.EMPTY_ID){
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

    public static boolean moralCheck(int moralValue){
        boolean returnValue;
        Random random = new Random();
        int rollResult = random.nextInt(100)+1;

        returnValue = rollResult < moralValue;
        return returnValue;


    }


    public static VirtualFutureAction skipTurn() {
        return new VirtualFutureAction(new Point(0,0), ActionTypes.DO_NOTHING);
    }

    public static boolean isCloseTo(int id, Point position) {
        Case[][] subsection = MapData.getSubsection2(position);
        boolean isClose = false;

        for (int i = 0; i < subsection.length && !isClose; i++){
            for (int j = 0; j < subsection[i].length && !isClose; j++){
                if (subsection[i][j]!=null&&subsection[i][j].getWildObject().getType() == id){
                    isClose = true;
                }
            }
        }

        return isClose;

    }


    public static boolean doesItSmell(Case[][] subsection, SmellType smellType) {
        Case selectedCase = null;
        boolean isItSmelling = false;

        for (int i = 0; i < subsection.length; i++) {
            for (int j = 0; j < subsection[i].length; j++) {
                selectedCase = subsection[i][j];
                for (int k = 0; k < selectedCase.getSortedSmellArrayList().size(); k++){
                    if (selectedCase.getSortedSmellArrayList().get(k).getType() == smellType){
                        isItSmelling = true;
                    }

                }

            }
        }

        return isItSmelling;
    }

    public static VirtualFutureAction scanForWildObject(Point position, Case[][] cases, SmellType type, String desiredQuality) {
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                System.out.println(new Point(i,j));
                System.out.println(cases[i][j].getSortedSmellArrayList().get(0).getIntensity());
            }
        }
        System.out.println("I smell with my little nose "+type);
        Point targetPoint = null;
        int preferredIntensity=0;
        Point correctedReferential=null;
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                for (int k = 0; k < cases[i][j].getSortedSmellArrayList().size(); k++){
                    if (targetPoint==null&&cases[i][j].getSortedSmellArrayList().get(k).getType()==type){
                        targetPoint=new Point(i,j);
                        preferredIntensity=cases[targetPoint.x][targetPoint.y].getSortedSmellArrayList().get(k).getIntensity();

                        System.out.println(targetPoint);
                        System.out.println("Intensity "+cases[i][j].getSortedSmellArrayList().get(k).getIntensity());

                    }
                    else if(desiredQuality.equals("lesser")&&(targetPoint!=null&&cases[i][j].getSortedSmellArrayList().get(k).getIntensity()<preferredIntensity&&cases[i][j].getSortedSmellArrayList().get(k).getType()==type)){
                        targetPoint=new Point(i,j);
                        preferredIntensity=cases[targetPoint.x][targetPoint.y].getSortedSmellArrayList().get(k).getIntensity();

                        System.out.println(targetPoint);
                        System.out.println("lesser Intensity "+cases[i][j].getSortedSmellArrayList().get(k).getIntensity());
                    }
                    else if(desiredQuality.equals("greater")&&(targetPoint!=null&&cases[i][j].getSortedSmellArrayList().get(k).getIntensity()>preferredIntensity&&cases[i][j].getSortedSmellArrayList().get(k).getType()==type)){
                        targetPoint=new Point(i,j);
                        preferredIntensity=cases[targetPoint.x][targetPoint.y].getSortedSmellArrayList().get(k).getIntensity();

                        System.out.println(targetPoint);
                        System.out.println("greater Intensity "+cases[i][j].getSortedSmellArrayList().get(k).getIntensity());
                    }
                }
            }
        }

        if (cases[targetPoint.x][targetPoint.y].getPassable()){
            System.out.println("NP");
            correctedReferential=new Point(targetPoint.x-1,targetPoint.y-1);
        }
        else{
            System.out.println("oh oooooh oh");
            if(Math.abs(targetPoint.x-1)==Math.abs(targetPoint.y-1)){
                //C'est un coin
                if (cases[1][targetPoint.y].getPassable()){
                    correctedReferential=new Point(0, targetPoint.y-1);
                }
                else if(cases[targetPoint.x][1].getPassable()){
                    correctedReferential=new Point(targetPoint.x-1, 0);
                }
            }
            else{
                if (targetPoint.x==1){
                    if (cases[0][targetPoint.y].getPassable()){
                        correctedReferential=new Point(-1, targetPoint.y-1);
                    }
                    else if(cases[2][targetPoint.y].getPassable()){
                        correctedReferential=new Point(1, targetPoint.y-1);
                    }
                }
                else{
                    if (cases[targetPoint.x][0].getPassable()){
                        correctedReferential=new Point(targetPoint.x-1, -1);
                    }
                    else if(cases[targetPoint.x][2].getPassable()){
                        correctedReferential=new Point(targetPoint.x-1, 1);
                    }
                    else{
                        System.out.println("manque qqc");
                    }
                }
            }
        }

        if (correctedReferential==null){
            correctedReferential=drunk(position);
        }

        return new VirtualFutureAction(correctedReferential, ActionTypes.GO_TO_LOCATION);
    }

    public static VirtualFutureAction dropToHive(Point position) {
        return new VirtualFutureAction(new Point(-1,-1), ActionTypes.DROP_TO_HIVE);
    }

    public static VirtualFutureAction pickUpFood(Point position) {
        Point correctedFoodLocation = findFoodiestLocation(position);

        return new VirtualFutureAction(correctedFoodLocation, ActionTypes.PICKUP_FROM_LOCATION);
    }

    public static VirtualFutureAction eatAdjacentFood(Point position) {
        Point correctedFoodLocation = findFoodiestLocation(position);

        return new VirtualFutureAction(correctedFoodLocation, ActionTypes.EAT_FROM_LOCATION);
    }

    public static Point findFoodiestLocation(Point position){
        Case[][] subsection = MapData.getSubsection2(position);
        Point foodLocation = null;
        Point correctedFoodLocation; //referential of the fourmillier
        int highestFoodValue = 0;


        for (int i = 0; i < subsection.length; i++){
            for (int j = 0; j < subsection[i].length; j++){
                if (subsection[i][j].getWildObject() instanceof FoodSource){
                    if (foodLocation == null){
                        foodLocation = new Point(i,j);
                        highestFoodValue = ((FoodSource) subsection[i][j].getWildObject()).getFoodQuantity();
                    }else if (foodLocation != null && (((FoodSource) subsection[i][j].getWildObject()).getFoodQuantity()) > highestFoodValue){
                        foodLocation = new Point(i,j);
                        highestFoodValue = ((FoodSource) subsection[i][j].getWildObject()).getFoodQuantity();
                    }
                }
            }

        }

        correctedFoodLocation = new Point(foodLocation.x-1, foodLocation.y-1);
        return correctedFoodLocation;
    }

}


