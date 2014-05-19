package ControllerPkg;

import ModelPkg.Animal;
import ModelPkg.Case;
import ModelPkg.MapData;
import ModelPkg.WildObjects.FoodSource;
import ModelPkg.WildObjects.WildObject;
import ViewPkg.VisualCase;

import java.awt.*;
import java.util.ArrayList;

public class MapController {


    /**
     * Constructeur de la création de la carte
     */
    public MapController(){
        MapData.initialize();
    }

    public void addObserver(VisualCase visualCase){
        Case selectedCase = MapData.getCase(visualCase.getCaseCoord());
        selectedCase.addObserver(visualCase);
    }

    /**
     * Méthode pour vérifier si une case du terrain est occupée
     * @param point position de la case à vérifier
     * @return l'image de la case si elle est occupée, sinon retourne null
     */
    public Image getOccupancy(Point point){
        Case selectedCase = MapData.getCase(point);
        if (selectedCase.getOccupant()!=null){
        return selectedCase.getOccupant().getSprite();
        }
        else{
            return null;
        }
    }

    /**
     * Méthode pour avoir l'image d'un objet sauvage sur le terrain
     * @param point position de la case de l'objet sauvage
     * @return l'image de l'objet sauvage
     */
    public Image getVisualWildObject(Point point){
        Case selectedCase = MapData.getCase(point);
        return selectedCase.getWildObject().getSprite();
    }

    /**
     * Méthode pour avoir la liste des animaux en vie
     * @return la liste des animaux en vie
     */
    public static ArrayList<Animal> getAnimalList(){
        return MapData.getAnimalList();
    }

    /**
     * Méthode pour retirer l'odeur d'un objet détruit
     * @param destroyedObject l'objet détruit (soit un animal soit de la nourriture)
     */
    public void removeSmellSourceOf(Object destroyedObject) {
        Animal deadAnimal=null;
        FoodSource depletedFoodSource=null;
        long smellID=0;
        if (destroyedObject instanceof Animal){
            deadAnimal=(Animal)destroyedObject;
            smellID=deadAnimal.getSmell().getID();
        }
        else if (destroyedObject instanceof FoodSource){
            depletedFoodSource=(FoodSource)destroyedObject;
            smellID=deadAnimal.getID();
        }
        for (int i=0; i<MapData.MAP_SIZE; i++){
            for (int j=0; j<MapData.MAP_SIZE;j++){
                Case selectedCase=MapData.getCase(new Point (i,j));
                for (int k=0;k<selectedCase.getSortedSmellArrayList().size(); k++){
                    if (smellID==selectedCase.getSortedSmellArrayList().get(k).getID()){
                        selectedCase.getSortedSmellArrayList().remove(k);
                    }
                }
                for (int k=0;k<selectedCase.getSortedSmellSourceArrayList().size(); k++){
                    if (smellID==selectedCase.getSortedSmellSourceArrayList().get(k).getID()){
                        selectedCase.getSortedSmellSourceArrayList().remove(k);
                    }
                }
            }
        }
    }
}
