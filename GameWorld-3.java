package org.csc133.a2;


import java.util.ArrayList;



public class GameWorld implements IObservable {
    private  int gameHeight;
    private  int gameWidth ;
    // new private variables
    private ArrayList<GameObject> gamesObjects;
    private ArrayList<IObserver> observers = new ArrayList<>();
    private PlayerHelicopter playerHelicopter;
    private NonHelicopter nonHelicopter1;
    private NonHelicopter nonHelicopter2;
    private NonHelicopter nonHelicopter3;
    private NonHelicopter nonHelicopter4;
    private MapView mp;
    private int height;
    private int width;


    private int gamelLife = 3;
    private int tick;


    /*
        The method init will create all the objects for the
        game.
    */

    public void init(){
        gamesObjects = new ArrayList<>();
        addGameObjects();
        this.notifyAllObserver();



    }

    public void addGameObjects()  {
        SkyScraper skyScraper1 = new SkyScraper("skyscrapper"+1);
        SkyScraper skyScraper2 = new SkyScraper("skyscrapper"+2);
        SkyScraper skyScraper3 = new SkyScraper("skyscrapper"+3);
        SkyScraper skyScraper4 = new SkyScraper("skyscrapper"+4);
        SkyScraper skyScraper5 = new SkyScraper("skyscrapper"+5);
        SkyScraper skyScraper6 = new SkyScraper("skyscrapper"+6);
        SkyScraper skyScraper7 = new SkyScraper("skyscrapper"+7);
        SkyScraper skyScraper8 = new SkyScraper("skyscrapper"+8);
        SkyScraper skyScraper9 = new SkyScraper("skyscrapper"+9);

        skyScraper1.setLocation(1300,1100);
        skyScraper2.setLocation(1260,900);
        skyScraper3.setLocation(455,840);
        skyScraper4.setLocation(366,1200);
        skyScraper5.setLocation(543,287);
        skyScraper6.setLocation(1600,661);
        skyScraper7.setLocation(200,551);
        skyScraper8.setLocation(1300,257);
        skyScraper9.setLocation(1000,512);





        playerHelicopter = new PlayerHelicopter(1300,1300);
        nonHelicopter1= new NonHelicopter();
        nonHelicopter2= new NonHelicopter();
        nonHelicopter3= new NonHelicopter();
        nonHelicopter4= new NonHelicopter();

        Bird bird1 = new Bird();
        Bird bird2 = new Bird();


        RefuelingBlimp blimp1 = new RefuelingBlimp();
        RefuelingBlimp blimp2 = new RefuelingBlimp();

        gamesObjects.add(skyScraper1);
        gamesObjects.add(skyScraper2);
        gamesObjects.add(skyScraper3);
        gamesObjects.add(skyScraper4);
        gamesObjects.add(skyScraper5);
        gamesObjects.add(skyScraper6);
        gamesObjects.add(skyScraper7);
        gamesObjects.add(skyScraper8);
        gamesObjects.add(skyScraper9);

        gamesObjects.add(playerHelicopter);
        gamesObjects.add(nonHelicopter1);
        //gamesObjects.add(nonHelicopter2);
        // gamesObjects.add(nonHelicopter3);
        //gamesObjects.add(nonHelicopter4);


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

    public void setMaxLoc(int height, int width){
        this.height = height;
        this.width = width;

        System.out.println("height* " + height);
        System.out.println("width* " + width);
    }
    /**
     * This method turns the stick angle left
     **/
    public void left() {
        for (int i = 0; i < gamesObjects.size(); i++) {
            if (gamesObjects.get(i) instanceof PlayerHelicopter) {
                ((PlayerHelicopter) gamesObjects.get(i)).left();
            }

        }
        System.out.println("Moving Left");
        this.notifyAllObserver();
    }

    public ArrayList<GameObject> getGamesObjects(){
        return gamesObjects;
    }

    public int getFuel() {
        for (int i = 0; i < gamesObjects.size(); i++) {
            if (gamesObjects.get(i) instanceof PlayerHelicopter) {
                return  ((PlayerHelicopter) gamesObjects.get(i)).getFuelLvl();
            }

        }
        return 0;
    }
    public int getDamage() {
        for (int i = 0; i < gamesObjects.size(); i++) {
            if (gamesObjects.get(i) instanceof PlayerHelicopter) {
                return  ((PlayerHelicopter) gamesObjects.get(i)).getDamageLevel();
            }

        }
        return 0;
    }

    public int getLastSkyScraper() {
        for (int i = 0; i < gamesObjects.size(); i++) {
            if (gamesObjects.get(i) instanceof PlayerHelicopter) {
                return  ((PlayerHelicopter) gamesObjects.get(i)).getLastSkyScraperReached();
            }

        }
        return 0;
    }

    public int getHeading() {
        for (int i = 0; i < gamesObjects.size(); i++) {
            if (gamesObjects.get(i) instanceof PlayerHelicopter) {
                return  ((PlayerHelicopter) gamesObjects.get(i)).getHeading();
            }

        }
        return 0;
    }

    public int getLives() {

        return gamelLife;
    }




    /**
     * This method turns the stick angle right
     **/

    public void right(){
        for(int i = 0; i < gamesObjects.size(); i++) {
            if (gamesObjects.get(i) instanceof PlayerHelicopter) {
                ((PlayerHelicopter) gamesObjects.get(i)).right();
            }
        }
        this.notifyAllObserver();
        System.out.println("Moving right");
    }
    /**
     * This method accelerates the helicopter
     **/
    public void accelerate(){
        for(int i = 0; i < gamesObjects.size(); i++) {
            if (gamesObjects.get(i) instanceof PlayerHelicopter) {
                ((PlayerHelicopter) gamesObjects.get(i)).accelerate();
            }
        }
        this.notifyAllObserver();
    }
    /**
     * This method slows down the helicopter
     **/
    public void brake(){
        for(int i = 0; i < gamesObjects.size(); i++){
            if(gamesObjects.get(i) instanceof PlayerHelicopter){
                ((PlayerHelicopter) gamesObjects.get(i)).decelerate();
            }
        }
        this.notifyAllObserver();
    }
    /**
     * This method simulates a collision with a helicopter
     **/
    public void helicopterCollision(){

        int currVal = 1;
        for(int i = 0; i < gamesObjects.size(); i++){
            if(gamesObjects.get(i) instanceof PlayerHelicopter){
                currVal = ((PlayerHelicopter) gamesObjects.get(i)).collision();
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
        this.notifyAllObserver();
    }
    /**
     * This method simulates a collision with a bird
     **/
    public void helicopterCollisionBird(){
        int currVal = 1;
        for(int i = 0; i < gamesObjects.size(); i++){
            if(gamesObjects.get(i) instanceof PlayerHelicopter){
                currVal = ((PlayerHelicopter) gamesObjects.get(i)).birdcollision();
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
        this.notifyAllObserver();
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
            if(gamesObjects.get(i) instanceof PlayerHelicopter){
                ((PlayerHelicopter) gamesObjects.get(i)).refuelFromBlimp(capacity);
            }
        }

        gamesObjects.add(new RefuelingBlimp());

        this.notifyAllObserver();
    }
    /**
     * This method simulates a collision with a sky scraper
     **/

    public void helicopterCollisionSkyScraper(int seqNum) {
        for(int i = 0; i < gamesObjects.size(); i++){
            if(gamesObjects.get(i) instanceof PlayerHelicopter){
                ((PlayerHelicopter)gamesObjects.get(i)).collideWithSkyScrapr(seqNum);
            }
        }
        this.notifyAllObserver();
    }
    /**
     * This method updates the game clock and calls the move method
     * on all movable objects
     *
     * @param GAME_TICK*/

    public void updateGameClock(int GAME_TICK){
        for(int i = 0; i < gamesObjects.size(); i++){
            if(gamesObjects.get(i) instanceof PlayerHelicopter){
                ((PlayerHelicopter) gamesObjects.get(i)).move(GAME_TICK, height,
                        width);

                //String x = ((PlayerHelicopter) gamesObjects.get(i)).toString();
                //System.out.println("helicopter move");
                //System.out.println(x);

            }
            if(gamesObjects.get(i) instanceof Bird){
                ((Bird) gamesObjects.get(i)).move(GAME_TICK, height, width);
                //System.out.println("bird heading" +((Bird) gamesObjects.get
                // (i)).getHeading());
            }
            if((gamesObjects.get(i) instanceof NonHelicopter)) {
                ((NonHelicopter) gamesObjects.get(i)).move(GAME_TICK, height,
                        width);

                //String x = ((NonHelicopter) gamesObjects.get(i)).toString();
                //System.out.println("NonHelicopter move");
                //System.out.println(x);
            }
        }
        tick++;
        this.notifyAllObserver();
        //System.out.println("gameworld notified");
    }
    /**
     * This method displays the games current status
     **/
    public void generateDisplay()
    {
        System.out.println("Number of lives left: "+ gamelLife);
        System.out.println("Curent clock ticks: "+ tick);
        System.out.println("Sky scrapper:"+playerHelicopter.getLastSkyScraperReached());
        System.out.println("Helicopter fuel level: "+playerHelicopter.getFuelLevel());
        System.out.println("Helicopter damage level: "+playerHelicopter.getDamageLevel());

    }
    /**
     * This method displays all the objects and all their attributes
     **/
    public void outputMap()
    {

        for( GameObject i : gamesObjects )
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
        System.out.println(playerHelicopter.toString());
    }

    public  int getGameHeight() {
        return gameHeight;
    }
    public  int getGameWidth() {
        return gameWidth;
    }



    @Override
    public void notifyAllObserver() {
        for (IObserver currObserver : observers){
            currObserver.update(gamesObjects);
        }

    }

    @Override
    public void addObservers(IObserver newObserver) {
        observers.add(newObserver);
    }


    public ArrayList<IObserver> getObservers() {
        return observers;
    }


}