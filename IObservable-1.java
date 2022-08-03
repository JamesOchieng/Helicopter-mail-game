package org.csc133.a3;

import java.util.ArrayList;

public interface IObservable {
    void notifyAllObserver();
    void addObservers(IObserver newObserver);
}
