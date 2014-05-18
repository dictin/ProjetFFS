package ControllerPkg;

import ModelPkg.PkgEvents.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Xav on 08/05/14.
 */
public class EventController {

    private MasterController masterController;
    private Random rnd=new Random();
    private ArrayList<GameEvent> goodGameEvents = new ArrayList<>();
    private ArrayList<GameEvent> badGameEvents = new ArrayList<>();
    private ArrayList<GameEvent> neutralGameEvents = new ArrayList<>();
    private int badThreshold=50;
    private int goodThreshold=150;

    public EventController(final MasterController masterController){
        this.masterController=masterController;
        goodGameEvents.add(new LingeringAlmightyBlessing());
        goodGameEvents.add(new InstantaneousHumanitarianHelp(masterController));
        neutralGameEvents.add(new GameEventSunnyWeather());
        badGameEvents.add(new MassInstantaneousCombustion(masterController));

    }

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
        nextGameEvent.setGravity(eventFinalGravity);
        return nextGameEvent;
    }

    public GameEvent chooseRandomEvent(ArrayList<GameEvent> appropriateGameEventList){
        int eventIndex=rnd.nextInt(appropriateGameEventList.size());
        return appropriateGameEventList.get(eventIndex);
    }
}
