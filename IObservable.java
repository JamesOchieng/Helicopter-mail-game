package org.csc133.a2;

import java.util.ArrayList;

public interface IObservable {
    void notifyAllObserver();
    void addObservers(IObserver newObserver);
}
