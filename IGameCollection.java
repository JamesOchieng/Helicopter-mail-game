package org.csc133.a2;


public interface IGameCollection {
    void addObjects(GameObject gameObjects);
    IGameIterator getGameIterator();
    int size();
    GameObject get(int i);
    void clear();
}
