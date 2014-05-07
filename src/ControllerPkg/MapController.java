package ControllerPkg;

import ModelPkg.Animal;
import ModelPkg.Case;
import ModelPkg.MapData;
import ObserverPkg.Observable;
import ObserverPkg.Observer;
import ViewPkg.VisualCase;

import java.awt.*;
import java.util.ArrayList;

public class MapController implements Observable {



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

    @Override
    public void addObserver(Observer observer) {

    }

    @Override
    public void updateObservers() {

    }
}
