package com.company;

public interface SpaceShip {
    boolean launch ();
    boolean land ();
    boolean canCarry (Item thisItem, int remainingPayload);
    void carry (Item thisItem);
}
