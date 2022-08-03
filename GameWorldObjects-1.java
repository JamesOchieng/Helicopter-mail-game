package org.csc133.a3;

import java.util.ArrayList;


public class GameWorldObjects implements IGameCollection {

    private ArrayList<GameObject> gamesObjects ;

    public GameWorldObjects(){
        gamesObjects = new ArrayList<GameObject>();
    }

    @Override
    public void addObjects(GameObject gameObjects) {
        gamesObjects.add(gameObjects);
    }

    @Override
    public IGameIterator getGameIterator() {
        return null;
    }

    @Override
    public int size() {
        return gamesObjects.size();
    }

    @Override
    public GameObject get(int i) {
        return gamesObjects.get(i);
    }

    @Override
    public void clear() {
        gamesObjects.clear();
    }


    private class GameObjectsIterator implements IGameIterator{

        private int currIndex;
        public GameObjectsIterator(){
            currIndex = -1;
        }

        @Override
        public GameObject getNext() {
            currIndex++;
            return gamesObjects.get(currIndex);
        }

        @Override
        public boolean hasNext() {
            if(gamesObjects.size() <= 0){
                return false;
            }
            if(currIndex == gamesObjects.size()-1){
                return false;

            }
            else
                return true;
        }

    }
}
