package ModelPkg;

import ModelPkg.WildObjects.FoodSource;
import ModelPkg.WildObjects.WildObject;

import java.awt.*;
import java.util.Random;


public class Behavior {

    //objective est composé de -1,0 et 1. il indique le déplacement à faire. Donc coordonné + objective = noulles coordonneé du fourmilier après le déplacement
    private static Point objective;
    private static final int SUBSECTION_SIZE = 3;

    /**
     * Méthode qui choissi aléatoirement l'objectif du fourmilier.
     * @param position position actuelle du fourmilier
     * @return l'objectif du fourmilier (l'endroit où il veut se déplacer)
     */
    public static Point drunk(Point position){
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

    /**
     * Méthode qui vérifie si l'objectif du fourmilier est une case vide (où il n'y a pas d'obstacle)
     * @param objective destination du fourmilier
     * @return true si la case est valide, false si la case n'est pas valide
     */
    private static boolean confirmObjective(Point objective) {
        boolean isValidObjective;
        Point caseToCheck = objective;
        isValidObjective = (MapData.getCase(caseToCheck).getWildObject().getType() == WildObject.EMPTY_ID) ? true : false;

        return isValidObjective;
    }

    /**
     * Méthode qui détermine si le fourmilier n'a plus d'option de déplacement
     * @param origin position initial du fourmilier
     * @return true si le fourmilier ne peut plus bougé, false si le fourmilier peut encore bougé
     */
    private static boolean isThereNowhereToGo(Point origin) {
        boolean nowhereToGo = true;
        Case[][] subsection = MapData.getSubsection2(origin);
        for(int i = 0; i < subsection.length&&nowhereToGo; i++){
            for(int j = 0; j < subsection[i].length&&nowhereToGo; j++){
                if((!((i==j)&&i==0))&&subsection[i][j].getOccupant() == null && subsection[i][j].getWildObject().getType() == WildObject.EMPTY_ID){
                    nowhereToGo = false;
                }

            }
        }

        return nowhereToGo;
    }

    /**
     *
     * @param location
     * @return
     */
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

    /**
     *
     * @param moralValue
     * @return
     */
    public static boolean moralCheck(int moralValue){
        boolean returnValue;
        Random random = new Random();
        int rollResult = random.nextInt(100)+1;

        returnValue = rollResult < moralValue;
        return returnValue;
    }

    /**
     * Méthode qui fait empêche au fourmilier de se déplacer ce tour-ci
     * @return l'action du fourmilier, c'est-à-dire DO_NOTHING
     */
    public static VirtualFutureAction skipTurn() {
        return new VirtualFutureAction(new Point(0,0), ActionTypes.DO_NOTHING);
    }

    /**
     * Méthode qui véridie si le fourmilier est proche de l'objet qu'il cherche
     * @param id identification de l'objet qu'il cherche
     * @param position position actuelle du fourmilier
     * @return true si le fourmilier est proche, false si le fourmilier n'est pas proche
     */
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

    /**
     * Renvoit true ou fasle dépendamment de si le fourmilier détecte le type d'odeur donné en paramètre
     * @param subsection cases à analyser
     * @param smellType type d'odeur recherché
     * @return boolean
     */
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

    /**
     * Méthode qui trouve un objetctif en fonction de l'odeur recherché par un fourmilier
     * @param position position actuelle du fourmilier
     * @param cases cases à analyser
     * @param type type d'odeur recherché
     * @param desiredQuality quelle puissance d'odeur cherche-t-on(odeur maximale ou odeur minimale)
     * @return
     */
    public static VirtualFutureAction scanForWildObject(Point position, Case[][] cases, SmellType type, String desiredQuality) {
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
            }
        }
        Point targetPoint = null;
        int preferredIntensity=0;
        Point correctedReferential=null;
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                for (int k = 0; k < cases[i][j].getSortedSmellArrayList().size(); k++){
                    if (targetPoint==null&&cases[i][j].getSortedSmellArrayList().get(k).getType()==type){
                        targetPoint=new Point(i,j);
                        preferredIntensity=cases[targetPoint.x][targetPoint.y].getSortedSmellArrayList().get(k).getIntensity();

                    }
                    else if(desiredQuality.equals(Animal.LEAST_INTENSE)&&(targetPoint!=null&&cases[i][j].getSortedSmellArrayList().get(k).getIntensity()<preferredIntensity&&cases[i][j].getSortedSmellArrayList().get(k).getType()==type)){
                        targetPoint=new Point(i,j);
                        preferredIntensity=cases[targetPoint.x][targetPoint.y].getSortedSmellArrayList().get(k).getIntensity();
                    }
                    else if(desiredQuality.equals(Animal.MOST_INTENSE)&&(targetPoint!=null&&cases[i][j].getSortedSmellArrayList().get(k).getIntensity()>preferredIntensity&&cases[i][j].getSortedSmellArrayList().get(k).getType()==type)){
                        targetPoint=new Point(i,j);
                        preferredIntensity=cases[targetPoint.x][targetPoint.y].getSortedSmellArrayList().get(k).getIntensity();
                    }
                }
            }
        }

        if (cases[targetPoint.x][targetPoint.y].getPassable()){
            correctedReferential=new Point(targetPoint.x-1,targetPoint.y-1);
        }
        else{
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
                }
            }
        }

        if (correctedReferential==null){
            correctedReferential=drunk(position);
        }

        return new VirtualFutureAction(correctedReferential, ActionTypes.GO_TO_LOCATION);
    }

    /**
     * Méthode qui déplace le fourmilier vers la base
     * @param position position actuelle du fourmilier
     * @return retourne la futur action du fourmilier
     */
    public static VirtualFutureAction dropToHive(Point position) {
        return new VirtualFutureAction(new Point(-1,-1), ActionTypes.DROP_TO_HIVE);
    }

    /**
     * Méthode qui fait ramasser de la nourriture au fourmilier
     * @param position position actuelle du fourmilier
     * @return  retourne la futur action du fourmilier
     */
    public static VirtualFutureAction pickUpFood(Point position) {
        Point correctedFoodLocation = findFoodiestLocation(position);

        return new VirtualFutureAction(correctedFoodLocation, ActionTypes.PICKUP_FROM_LOCATION);
    }

    /**
     * Méthode qui fait manger de la nourriture au fourmilier s'il se trouve à proximité d'une source de nourriture
     * @param position position actuelle du fourmilier
     * @return retourne la futur action du fourmilier
     */
    public static VirtualFutureAction eatAdjacentFood(Point position) {
        Point correctedFoodLocation = findFoodiestLocation(position);

        return new VirtualFutureAction(correctedFoodLocation, ActionTypes.EAT_FROM_LOCATION);
    }

    /**
     * Méthode qui détermine la position de la nourriture avec la plus grande quantité
     * @param position position actuelle du fourmilier
     * @return la position où il y a le plus de nourriture
     */
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


