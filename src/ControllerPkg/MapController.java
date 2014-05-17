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



    public MapController(){
        MapData.initialize();
    }

    public void addObserver(VisualCase visualCase){
        Case selectedCase = MapData.getCase(visualCase.getCaseCoord());
        selectedCase.addObserver(visualCase);
    }

    public Image getOccupancy(Point point){
        Case selectedCase = MapData.getCase(point);
        if (selectedCase.getOccupant()!=null){
        return selectedCase.getOccupant().getSprite();
        }
        else{
            return null;
        }
    }

    public Image getVisualWildObject(Point point){
        Case selectedCase = MapData.getCase(point);
        return selectedCase.getWildObject().getSprite();
    }

    public static ArrayList<Animal> getAnimalList(){
        return MapData.getAnimalList();
    }

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
