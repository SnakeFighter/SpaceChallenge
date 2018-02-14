package com.company;

public class U2 extends Rocket {

    private int maxWeight = 29000;
    private int emptyWeight = 18000 ;
    private int cost = 120;
    private double chanceOfLaunchExplosionFactor = 0.04;
    private double chanceOfLandingCrashFactor = 0.08;
    private int currentWeight = 18000;

    U2 () {
    }

    //@Override
    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public double getChanceOfLaunchExplosionFactor() {
        return chanceOfLaunchExplosionFactor;
    }

    public void setChanceOfLaunchExplosionFactor(double chanceOfLaunchExplosionFactor) {
        this.chanceOfLaunchExplosionFactor = chanceOfLaunchExplosionFactor;
    }

    public double getGetChanceOfLandingCrashFactor() {
        return chanceOfLandingCrashFactor;
    }

    public boolean launch () {
        // Generate a random no, if it's smaller or equal to launch explosion chance * load factor, then return true.
        double random = Math.random();
        double loadFactor = (double) (this.currentWeight - this.emptyWeight)/ (this.maxWeight - this.emptyWeight);
        double crashChance = this.chanceOfLaunchExplosionFactor * loadFactor;
        //System.out.println("Crash chance: "+crashChance+" LF: "+loadFactor);
        return (random > crashChance);
    }

    public boolean land () {
        // Generate a random no, if it's smaller or equal to launch explosion chance * load factor, then return true.
        double random = Math.random();
        double loadFactor = (double) (this.currentWeight - this.emptyWeight)/ (this.maxWeight - this.emptyWeight);
        double crashChance = this.chanceOfLandingCrashFactor * loadFactor;
        //System.out.println("Crash chance: "+crashChance+" LF: "+loadFactor);
        return (random > crashChance);
    }

}
