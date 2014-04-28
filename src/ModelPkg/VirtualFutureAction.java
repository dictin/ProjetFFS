package ModelPkg;

import java.awt.*;

public class VirtualFutureAction {

    private Point targetLocation = null;
    private ActionTypes actionType;

    public VirtualFutureAction(Point targetLocation, ActionTypes actionType){
        this.targetLocation = targetLocation;
        this.actionType = actionType;
    }

    public Point getTargetLocation() {
        return targetLocation;
    }

    public ActionTypes getActionType() {
        return actionType;
    }
}
