package com.company;

import java.util.ArrayList;

public class Rocket implements SpaceShip {

    int maxWeight;
    //static int emptyWeight;
    int cost;

    //static double chanceOfLaunchExplosionFactor;
    //static double getChanceOfLandingCrashFactor;
    int currentWeight;

    ArrayList <Item> payload = new ArrayList<Item>();

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public ArrayList<Item> getPayload() {
        return payload;
    }

    public void setPayload(ArrayList<Item> payload) {
        this.payload = payload;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public boolean launch() {
        return true;
    }

    @Override
    public boolean land() {
        return true;
    }

    @Override
    //Needs to be implemented here
    public boolean canCarry(Item thisItem, int remainingPayload) {
        remainingPayload = this.getMaxWeight() - this.getCurrentWeight();
        if (remainingPayload >= thisItem.getWeight()) {
            return true;
        } else {
            return false;
        }
    }

    public void carry(Item thisItem) {
        this.setCurrentWeight(thisItem.getWeight() + this.getCurrentWeight());
        this.payload.add(thisItem);
    }

}
