package org.csc133.a1;


import java.util.*;
import java.util.Observable;



public class GameWorld extends Observable {
    // new private variables
    private ArrayList<GameObject> gamesObjects = new ArrayList<>();
    private Helicopter helicopter;
    private int gamelLife = 3;
    private int tick;
/*
    The method init will create all the objects for the
    game.
*/
    public void init(){

        SkyScraper skyScraper1 = new SkyScraper();
        SkyScraper skyScraper2 = new SkyScraper();
        SkyScraper skyScraper3 = new SkyScraper();
        SkyScraper skyScraper4 = new SkyScraper();
        skyScraper1.setLocation(25,25);
        skyScraper2.setLocation(125,125);
        skyScraper3.setLocation(225,225);
        skyScraper4.setLocation(325,325);


        helicopter = new Helicopter();
        helicopter.setLocation(500,500);

        Bird bird1 = new Bird();
        Bird bird2 = new Bird();


        RefuelingBlimp blimp1 = new RefuelingBlimp();
        RefuelingBlimp blimp2 = new RefuelingBlimp();

        gamesObjects.add(skyScraper1);
        gamesObjects.add(skyScraper2);
        gamesObjects.add(skyScraper3);
        gamesObjects.add(skyScraper4);
        gamesObjects.add(helicopter);
        gamesObjects.add(bird1);
        gamesObjects.add(bird2);
        gamesObjects.add(blimp1);
        gamesObjects.add(blimp2);

    }
/**
 *  The confirm method exits the game
  **/
    public void confirm(){
        System.exit(0);
    }
/**
 * The not confirm method lets the user know they can keep playing
  **/
    public void notConfirm(){
        System.out.println("Resume Playing");
        init();
    }

    /**
     *The exit method asks the user if they want to keep playing
     **/
    public void exit(){

        System.out.println("Quit or keep playing?");
    }
    /**
     * This method turns the stick angle left
     **/
    public void left() {
        for (int i = 0; i < gamesObjects.size(); i++) {
            if (gamesObjects.get(i) instanceof Helicopter) {
                ((Helicopter) gamesObjects.get(i)).left();
            }

        }
        System.out.println("Moving Left");
    }
    /**
     * This method turns the stick angle right
     **/

    public void right(){
            for(int i = 0; i < gamesObjects.size(); i++) {
                if (gamesObjects.get(i) instanceof Helicopter) {
                    ((Helicopter) gamesObjects.get(i)).right();
                }
            }
        System.out.println("Moving right");
    }
    /**
     * This method accelerates the helicopter
     **/
    public void accelerate(){
        for(int i = 0; i < gamesObjects.size(); i++) {
        if (gamesObjects.get(i) instanceof Helicopter) {
            ((Helicopter) gamesObjects.get(i)).accelerate();
        }
    }

    }
    /**
     * This method slows down the helicopter
     **/
    public void brake(){
        for(int i = 0; i < gamesObjects.size(); i++){
            if(gamesObjects.get(i) instanceof Helicopter){
                ((Helicopter) gamesObjects.get(i)).decelerate();
            }
        }
    }
    /**
     * This method simulates a collision with a helicopter
     **/
    public void helicopterCollision(){

      int currVal = 1;
        for(int i = 0; i < gamesObjects.size(); i++){
            if(gamesObjects.get(i) instanceof Helicopter){
                currVal = ((Helicopter) gamesObjects.get(i)).collision();
            }
        }
        if(currVal == -1){
            gamelLife--;
            System.out.println("One life down " + gamelLife);
            gamesObjects.clear();
            init();
        }
        else if(currVal == -8){
            System.out.println("Game over, better luck next time!");
            confirm();
        }

    }
    /**
     * This method simulates a collision with a bird
     **/
    public void helicopterCollisionBird(){

        int currVal = 1;
        for(int i = 0; i < gamesObjects.size(); i++){
            if(gamesObjects.get(i) instanceof Helicopter){
                currVal = ((Helicopter) gamesObjects.get(i)).birdcollision();
            }
        }
        if(currVal == -1){
            gamelLife--;
            System.out.println("One life down " + gamelLife);
            gamesObjects.clear();
            init();
        }
        else if(currVal == -8){
            System.out.println("Game over, better luck next time!");
            confirm();
        }

    }
    /**
     * This method simulates a collision with a refuelling blimp
     **/
    public void helicopterCollisionRefuelling(){
        int capacity = 0;
        for(int i = 0; i < gamesObjects.size(); i++){
            if(gamesObjects.get(i) instanceof RefuelingBlimp){
                capacity = ((RefuelingBlimp) gamesObjects.get(i)).refuelHeli();
                break;
            }
        }

        for(int i = 0; i < gamesObjects.size(); i++){
            if(gamesObjects.get(i) instanceof Helicopter){
                ((Helicopter) gamesObjects.get(i)).refuelFromBlimp(capacity);
            }
        }

        gamesObjects.add(new RefuelingBlimp());


    }
    /**
     * This method simulates a collision with a sky scraper
     **/

    public void helicopterCollisionSkyScraper(int seqNum) {
        for(int i = 0; i < gamesObjects.size(); i++){
            if(gamesObjects.get(i) instanceof Helicopter){
                ((Helicopter)gamesObjects.get(i)).collideWithSkyScrapr(seqNum);
            }
        }
    }
    /**
     * This method updates the game clock and calls the move method
     * on all movable objects
     **/

    public void updateGameClock(){
        for(int i = 0; i < gamesObjects.size(); i++){
           if(gamesObjects.get(i) instanceof Helicopter){
                ((Helicopter) gamesObjects.get(i)).move();
                System.out.println("helicopter move");

            }
            if(gamesObjects.get(i) instanceof Bird){
                ((Bird) gamesObjects.get(i)).move();
                System.out.println("bird move");
            }
        }
        tick++;
    }
    /**
     * This method displays the games current status
     **/
    public void generateDisplay()
    {
        System.out.println("Number of lives left: "+ gamelLife);
        System.out.println("Curent clock ticks: "+ tick);
        System.out.println("Sky scrapper:"+helicopter.getLastSkyScraperReached());
        System.out.println("Helicopter fuel level: "+helicopter.getFuelLevel());
        System.out.println("Helicopter damage level: "+helicopter.getDamageLevel());

    }
    /**
     * This method displays all the objects and all their attributes
     **/
    public void outputMap()
    {
        for(GameObject i: gamesObjects )
        {
            if(i instanceof Bird)
            {
                System.out.println(((Bird) i).toString());
            }
            if(i instanceof RefuelingBlimp)
            {
                System.out.println(((RefuelingBlimp) i).toString());
            }
            if(i instanceof SkyScraper)
            {
                System.out.println(((SkyScraper) i).toString());
            }
        }
        System.out.println(helicopter.toString());
    }
}