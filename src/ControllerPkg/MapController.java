package ControllerPkg;

import ModelPkg.Case;
import ModelPkg.MapData;
import ViewPkg.VisualCase;

import java.awt.*;

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
        return selectedCase.getOccupant().getSprite();
    }

    public Image getVisualWildObject(Point point){
        Case selectedCase = MapData.getCase(point);
        return selectedCase.getWildObject().getSprite();
    }
}
