package com.company;

public class U1 extends Rocket {

    private int maxWeight = 18000;
    private int emptyWeight = 10000 ;
    private int cost = 100;
    private double chanceOfLaunchExplosionFactor = 0.05;
    private double chanceOfLandingCrashFactor = 0.01;
    private int currentWeight = 10000;

    U1 () {
    }


    @Override
    public int getCost() {
        return cost;
    }

    //@Override
    public int getCurrentWeight() {
        return currentWeight;
    }

    public int getEmptyWeight() {
        return emptyWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
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

    public double getChanceOfLandingCrashFactor() {
        return chanceOfLandingCrashFactor;
    }

    public void setGetChanceOfLandingCrashFactor(double getChanceOfLandingCrashFactor) {
        this.chanceOfLandingCrashFactor = getChanceOfLandingCrashFactor;
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
