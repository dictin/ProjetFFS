package ModelPkg;

import java.awt.*;

public class VirtualFutureAction {

    /**
     * Endroit ciblé par l'action
     */
    private Point targetLocation = null;
    /**
     * Action à faire en direction de l'endroit ciblé
     */
    private ActionTypes actionType;

    /**
     * Constructeur de VirtualFuturAction
     * @param targetLocation  position cible
     * @param actionType type de l'action
     */
    public VirtualFutureAction(Point targetLocation, ActionTypes actionType){
        this.targetLocation = targetLocation;
        this.actionType = actionType;
    }

    /**
     * Méthode qui retourne la position cible
     * @return postition cible
     */
    public Point getTargetLocation() {
        return targetLocation;
    }

    /**
     * Méthode qui retourne le type d'action
     * @return type d'action
     */
    public ActionTypes getActionType() {
        return actionType;
    }
}
