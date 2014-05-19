package ControllerPkg;

import ModelPkg.PkgEvents.*;

import java.util.ArrayList;
import java.util.Random;

public class EventController {

    private MasterController masterController;
    private Random rnd=new Random();
    private ArrayList<GameEvent> goodGameEvents = new ArrayList<>();
    private ArrayList<GameEvent> badGameEvents = new ArrayList<>();
    private ArrayList<GameEvent> neutralGameEvents = new ArrayList<>();
    private int badThreshold=50;
    private int goodThreshold=150;

    /**
     * Constructeur du controller des événements
     * @param masterController controller principal
     */
    public EventController(final MasterController masterController){
        this.masterController=masterController;
        goodGameEvents.add(new LingeringAlmightyBlessing());
        goodGameEvents.add(new InstantaneousHumanitarianHelp(masterController));
        neutralGameEvents.add(new GameEventSunnyWeather());
        badGameEvents.add(new MassInstantaneousCombustion(masterController));
        badGameEvents.add(new LingeringHackTroll(masterController));

    }

    /**
     * Méthode pour déterminer si l'événement est positif, négatif ou neutre. Un événement aléatoire de cette cathégorie
     * est ensuite sélectionner.
     * @return le prochain événement
     */
    public GameEvent whatIsTheWeather(){
        int rollResult=rnd.nextInt(200)+1;

        int currentPlayerKarma=masterController.getPlayerDataController().getKarma();
        int eventFinalGravity=masterController.getPlayerDataController().getNextEventGravtity();

        GameEvent nextGameEvent;
        //Le joueur n'est pas chanceux
        if (rollResult+currentPlayerKarma<=badThreshold){
            nextGameEvent =chooseRandomEvent(badGameEvents);
            masterController.getPlayerDataController().modifyKarma(eventFinalGravity);
            masterController.getPlayerDataController().setNextEventGravity(1);
        }
        //Le joueur est chanceux
        else if (rollResult+currentPlayerKarma>=goodThreshold){
            nextGameEvent =chooseRandomEvent(goodGameEvents);
            masterController.getPlayerDataController().modifyKarma(-eventFinalGravity);
            masterController.getPlayerDataController().setNextEventGravity(1);
        }
        else{
            nextGameEvent =chooseRandomEvent(neutralGameEvents);
            masterController.getPlayerDataController().increaseNextEventGravity();
        }
        //TODO Enlever cette ligne pour avoir des bons et des mauvais événements
        nextGameEvent = chooseRandomEvent(goodGameEvents);
        masterController.deadAnimal();
        nextGameEvent.setGravity(eventFinalGravity);
        return nextGameEvent;
    }

    /**
     * Méthode qui choisi un chiffre aléatoire pour déterminer le prochain événement. Les limites du random sont
     * déterminées par la longeure de la liste d'événements.
     * @param appropriateGameEventList la liste d'événement dans laquelle on veut un événement aléatoire.
     * @return un événement aléatoire de la liste
     */
    public GameEvent chooseRandomEvent(ArrayList<GameEvent> appropriateGameEventList){
        int eventIndex=rnd.nextInt(appropriateGameEventList.size());
        return appropriateGameEventList.get(eventIndex);
    }
}
