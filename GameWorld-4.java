package org.csc133.a3;


import java.util.ArrayList;
import java.util.Arrays;


public class GameWorld implements IObservable {
    private static int gameAttackChange = 0;
    private  int gameWidth ;
    private int gameHeight;

    // new private variables
    private ArrayList<GameObject> gamesObjects;
    private ArrayList<IObserver> observers = new ArrayList<>();
    private PlayerHelicopter playerHelicopter;
    private NonPlayerHelicopter nonPlayerHelicopter1;
    private NonPlayerHelicopter nonPlayerHelicopter2;
    private NonPlayerHelicopter nonPlayerHelicopter3;
    private NonPlayerHelicopter nonPlayerHelicopter4;
    private ITimer digitTimer;
    private BGSound backgroundSound= new BGSound("/backgroundSound.wav");
    private BGSound heliSound = new BGSound("/chopper.wav");
    RefuelingBlimp blimp1;
    RefuelingBlimp blimp2;

    private static int speed = 300;
    private int startSpeed = 0;
    private int height;
    private int width;

    private boolean birdAttackMode = false;
    private boolean npHeliAttackMode = false;

    private int gamelLife = 3;
    private int tick;


    /*
        The method init will create all the objects for the
        game.
    */
    public GameWorld(){

    }
    public void init(ITimer digitTimer){
        this.digitTimer = digitTimer;
        gamesObjects = new ArrayList<>();
        addGameObjects();
        backgroundSound.setVolume(55);
        backgroundSound.play();
        heliSound.setVolume(40);
        heliSound.play();
        this.notifyAllObserver();




    }

    public void addGameObjects()  {
        SkyScraper skyScraper0 = new SkyScraper("skyscrapper"+0,0);
        SkyScraper skyScraper1 = new SkyScraper("skyscrapper"+1,1);
        SkyScraper skyScraper2 = new SkyScraper("skyscrapper"+2,2);
        SkyScraper skyScraper3 = new SkyScraper("skyscrapper"+3,3);
        SkyScraper skyScraper4 = new SkyScraper("skyscrapper"+4, 4);
        SkyScraper skyScraper5 = new SkyScraper("skyscrapper"+5, 5);
        SkyScraper skyScraper6 = new SkyScraper("skyscrapper"+6 ,6 );
        SkyScraper skyScraper7 = new SkyScraper("skyscrapper"+7,7);
        SkyScraper skyScraper8 = new SkyScraper("skyscrapper"+8, 8);
        SkyScraper skyScraper9 = new SkyScraper("skyscrapper"+9, 9);

        skyScraper0.setLocation(1300,1100);
        skyScraper1.setLocation(1400,600);
        skyScraper2.setLocation(1260,900);
        skyScraper3.setLocation(455,840);
        skyScraper4.setLocation(366,1200);
        skyScraper5.setLocation(543,287);
        skyScraper6.setLocation(1600,661);
        skyScraper7.setLocation(200,551);
        skyScraper8.setLocation(1300,257);
        skyScraper9.setLocation(1000,512);

        //System.out.println("started re init" + gamelLife);



        playerHelicopter = new PlayerHelicopter(1340,1150);
        nonPlayerHelicopter1 = new NonPlayerHelicopter();
        nonPlayerHelicopter2 = new NonPlayerHelicopter();
        nonPlayerHelicopter3 = new NonPlayerHelicopter();
        nonPlayerHelicopter4 = new NonPlayerHelicopter();

        Bird bird1 = new Bird();
        Bird bird2 = new Bird();


         blimp1 = new RefuelingBlimp();
         blimp2 = new RefuelingBlimp();

        gamesObjects.add(skyScraper0);
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
        gamesObjects.add(nonPlayerHelicopter1);
        gamesObjects.add(nonPlayerHelicopter2);
        gamesObjects.add(nonPlayerHelicopter3);
        //gamesObjects.add(nonPlayerHelicopter4);



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
        //init(digitTimer);
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
            init(digitTimer);
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

    public int checkLastSkyscrapper(){
        for(int i = 0; i < gamesObjects.size(); i++){
            if(gamesObjects.get(i) instanceof PlayerHelicopter){
                return ((PlayerHelicopter)gamesObjects.get(i)).getLastSkyScraperReached();
            }
        }
        return 0;
    }

    public int getDamageLvl(){
        for(int i = 0; i < gamesObjects.size(); i++){
            if(gamesObjects.get(i) instanceof PlayerHelicopter){
                int damage =
                        ((PlayerHelicopter)gamesObjects.get(i)).getDamageLevel();
                return damage;
            }
        }
        return 0;

    }

    public void setDamageLvl(){
        for(int i = 0; i < gamesObjects.size(); i++) {
            if (gamesObjects.get(i) instanceof PlayerHelicopter) {
                 ((PlayerHelicopter) gamesObjects.get(i)).setDamageLevel(0);
            }
        }
    }

    public void setPlayerHelicopterLife(int x){
        for(int i = 0; i < gamesObjects.size(); i++){
        if(gamesObjects.get(i) instanceof PlayerHelicopter){
            ((PlayerHelicopter)gamesObjects.get(i)).setGameLife(x);
        }
    }}

    /**
     * This method updates the game clock and calls the move method
     * on all movable objects
     *
     * @param GAME_TICK*/

    public void updateGameClock(int GAME_TICK){
        if(checkLastSkyscrapper() == 9){
            printTime();
            System.exit(0);
        }

       else if (getDamageLvl() == 100 && gamelLife != 0){
            setDamageLvl();
            gamesObjects.clear();
            addGameObjects();
            setPlayerHelicopterLife((gamelLife-1));
            System.out.println("life " + gamelLife + "damage "+ getDamageLvl());
            this.notifyAllObserver();


        }
       if(gamelLife == 0){
           printGameOver();
           System.exit(0);

       }


        for(int i = 0; i < gamesObjects.size(); i++){
            if(gamesObjects.get(i) instanceof PlayerHelicopter){

                if(startSpeed == speed) {
                    ((PlayerHelicopter) gamesObjects.get(i)).move(GAME_TICK, height,
                            width);
                }else{
                    startSpeed = startSpeed+50;
                   // System.out.println("starting speed "+ startSpeed);
                }
                //String x = ((PlayerHelicopter) gamesObjects.get(i)).toString();
                //System.out.println("helicopter move");
                //System.out.println(x);

            }
            if(gamesObjects.get(i) instanceof Bird){
                ((Bird) gamesObjects.get(i)).move(GAME_TICK, height, width);
                if (birdAttackMode) {
                    ((Bird) gamesObjects.get(i)).invokeStrategy(gamesObjects);
                }
                //System.out.println("bird heading" +((Bird) gamesObjects.get
                // (i)).getHeading());
            }
            if((gamesObjects.get(i) instanceof NonPlayerHelicopter)) {
                ((NonPlayerHelicopter) gamesObjects.get(i)).move(GAME_TICK, height,
                        width);
                if (npHeliAttackMode) {
                    ((NonPlayerHelicopter) gamesObjects.get(i)).invokeStrategy(gamesObjects);
                }

                //String x = ((NonHelicopter) gamesObjects.get(i)).toString();
                //System.out.println("NonHelicopter move");
                //System.out.println(x);
            }
        }
        tick++;
        checkBlimpCapacity();
        this.notifyAllObserver();
        checkObjectCollision();
        //System.out.println("gameworld notified");
    }

    public void checkObjectCollision() {
        for(int i = 0; i < gamesObjects.size()-1; i++){
            for(int j = i+1; j < gamesObjects.size(); j++){
                if(gamesObjects.get(i).collidesWith(gamesObjects.get(j))){
                    gamesObjects.get(i).handleCollision(gamesObjects.get(j));

                }
            }
        }

    }

    public void checkBlimpCapacity(){
        int capacity = 0;
        for(int i = 0; i < gamesObjects.size(); i++){
            if(gamesObjects.get(i) instanceof RefuelingBlimp){
                capacity = ((RefuelingBlimp) gamesObjects.get(i)).getCapacity();
                if(capacity == 0){
                    gamesObjects.remove(gamesObjects.get(i));
                    //RefuelingBlimp newBlimp = new RefuelingBlimp();
                    gamesObjects.add(new RefuelingBlimp());
                }
            }
        }
    }

    /**
     * This method displays the games current status
     **/
    public void generateDisplay()
    {
        System.out.println("Number of lives left: "+ gamelLife);
        System.out.println("Curent clock ticks: "+ tick);
        System.out.println("Sky scrapper:"+playerHelicopter.getLastSkyScraperReached());
        System.out.println("Helicopter fuel level: "+playerHelicopter.getFuelLvl());
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

    public void birdEngage(){
        if(gameAttackChange == 0) {
            birdAttackMode = true;
            gameAttackChange = 1;
        }
        else if (gameAttackChange == 1){
            birdAttackMode = false;
            gameAttackChange = 0;
        }

    }

    public void nonPlayerHeliEngage(){
        if(gameAttackChange == 0) {
            npHeliAttackMode = true;
            gameAttackChange = 1;
        }
        else if (gameAttackChange == 1){
            npHeliAttackMode = false;
            gameAttackChange = 0;
        }
    }

    public boolean isAttackMode(){
        return birdAttackMode;
    }


    public void pause() {
        digitTimer.stopElapsedTime();
    }

    public void unPause() {
        digitTimer.startElapsedTime();
    }

    public void printTime(){
        int[] endTime = digitTimer.getElapsedTime();
        System.out.println("Game over, you win! Total time: " +(endTime[0]/10)
        +(endTime[0]%10)+":" + (endTime[1]/10)+(endTime[1]%10)+":"+(endTime[2]%10)) ;

    }

    public  void printGameOver(){
        System.out.println("Game over, better luck next time!");
    }


    public void pauseSound() {
        backgroundSound.pause();
        heliSound.pause();
    }

    public void playSound() {
        backgroundSound.play();
        heliSound.play();
    }
}