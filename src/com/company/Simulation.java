package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import static java.lang.System.*;


public class Simulation {

    Simulation () throws Exception {
        int U1Cost, U2Cost;

        /*
        //let's check to see that the rockets initialise correctly:
        U1 testU1 = new U1();
        out.println(testU1.getMaxWeight());
        */

        //First, load items and then load them into the fleets.
        ArrayList <Item> itemList = loadItems(1);

        ArrayList<U1> U1fleet = loadU1(itemList);
        out.println("U1 fleet size: " + U1fleet.size());

        itemList = loadItems(1);
        ArrayList<U2> U2fleet = loadU2(itemList);
        out.println("U2 fleet size: " + U2fleet.size());

        //printFleetContents (U1fleet, U2fleet);

        out.println("\n\nLaunching rockets:");

        U1Cost = runSimulation(U1fleet);
        U2Cost = runSimulation(U2fleet);

        out.println("\nPhase 1:\nU1 cost: "+U1Cost+"\nU2 cost: "+U2Cost);

        //Now let's do phase 2 for both types of rocket:

        itemList = loadItems(2);
        U1fleet = loadU1(itemList);
        out.println("U1 fleet size: " + U1fleet.size());

        itemList = loadItems(2);
        for (Item thisItem : itemList) {
            out.println(thisItem.getName());
        }
        U2fleet = loadU2(itemList);
        out.println("U2 fleet size: " + U2fleet.size());

        out.println("\n\nLaunching rockets:");

        U1Cost = runSimulation(U1fleet);
        U2Cost = runSimulation(U2fleet);

        out.println("\nPhase 2:\nU1 cost: "+U1Cost+"\nU2 cost: "+U2Cost);
    }

    int runSimulation (ArrayList rocketFleet) {

        //Now the launches!
        int runningTotalCost = 0;
        int launchCount = 0;

        for (Object myRocket : rocketFleet) {

            boolean launched = false;
            boolean landed = false;
            Rocket thisRocket;
            thisRocket = (Rocket) myRocket;

            //We're going to try each rocket until we can complete the mission.
            while (!landed) {
                Rocket launchRocket = new Rocket();
                launchRocket = thisRocket;
                //Keep attempting launches for each rocket.

                while (!launched) {
                    launched = launchRocket.launch();
                    runningTotalCost += thisRocket.getCost();
                    //out.println("This rocket costs: " + thisRocket.getCost());
                    if (launched) {
                        out.println("Rocket launch OK.");
                    } else {
                        out.println("Rocket launch failure.");
                    }
                    //out.println("Rocket launch OK: "+launched);
                    launchCount++;
                    out.println("Launches: " + launchCount + " Cost so far: " + runningTotalCost + "M");
                }

                //Now we've got the rocket in the sky, we have to try to land.
                //If it works, the loop exits, and goes to the next rocket in the fleet.  Otherwise, back to launch.
                landed = launchRocket.land();
                if (landed){
                    System.out.println("Landing of rocket OK.");
                } else {
                    out.println("Landing failed, re-launching.");
                }
            }
        }
        return runningTotalCost;
    }


    //Method to read items file.
    ArrayList <Item> loadItems (int phase) throws Exception {

        //Can be phase 1 or 2, choose the file accordingly:
        String fileName = "";
        //assert (phase==1 || phase==2);
        switch (phase) {
            case 1:
                fileName = "phase-1.txt";
                break;
            case 2:
                fileName = "phase-2.txt";
                break;
            default: fileName = "invalid";
        }

        //Open the file.
        ArrayList <Item> itemList = new ArrayList();
        File cargoFile = new File(fileName);
        Scanner fileScanner = new Scanner (cargoFile);

        //Read items, and add to array.

        while (fileScanner.hasNextLine()) {
            String thisLine = fileScanner.nextLine();
            String [] thisLineParts = thisLine.split("\\=");
            String thisItemName = thisLineParts [0];
            int thisItemWeight;

            try {
                thisItemWeight = Integer.parseInt(thisLineParts [1]);
                Item thisItem = new Item();
                thisItem.setName(thisItemName);
                thisItem.setWeight(thisItemWeight);
                itemList.add(thisItem);
            } catch (NumberFormatException e){
                out.println("Item weight error for line "+thisLine);
            }
        }

        //Sort the list to make loading more efficient.
        itemList.sort(Comparator.comparing(Item::getWeight).reversed());
        return itemList;
    }

    ArrayList <U1> loadU1 (ArrayList<Item> itemList) {
        out.println("Loading U1s");

        //Here's a new fleet of this model!
        ArrayList <U1> thisFleet = new ArrayList<U1>();

        //We're making a few rockets here, so will keep working while the item list has anything in it.
        while (!itemList.isEmpty()) {

            ArrayList<Item> itemsInThisRocket = new ArrayList<Item>();

            //Make a new Rocket!
            U1 thisRocket = new U1 ();
            //We'll need to keep track of remaining payload capacity...
            int remainingWeight;
            //Cycle through the item list, if it fits, put it in the rocket, and remove from the list.

            for (Item thisItem : itemList) {
                remainingWeight = thisRocket.getMaxWeight() - thisRocket.getCurrentWeight();
                if (thisRocket.canCarry(thisItem, remainingWeight)) {
                    thisRocket.carry(thisItem);
                }
            }
            //Take items off the list that have been loaded in this rocket
            for (Item thisItem : thisRocket.payload) {
                itemList.remove(thisItem);
            }
            //Put the payload in the rocket, and create it.
            thisFleet.add(thisRocket);

            //Print the contents of the rocket.
            int totalWeight = 0;
            for (Item inTheRocket : thisRocket.payload) {
                totalWeight+=inTheRocket.getWeight();
            }
            //If the cargo isn't all carried yet, go back and make a new rocket.
        }
        out.println("Loaded: "+thisFleet.size()+" U1s");
        return (thisFleet);
    }

    ArrayList <U2> loadU2 (ArrayList<Item> itemList) {
        out.println("Loading U2s");

        //Here's a new fleet of this model!
        ArrayList <U2> thisFleet = new ArrayList<U2>();

        //We're making a few rockets here, so will keep working while the item list has anything in it.
        while (!itemList.isEmpty()) {

            ArrayList<Item> itemsInThisRocket = new ArrayList<Item>();

            //Make a new Rocket!
            U2 thisRocket = new U2 ();
            //We'll need to keep track of remaining payload capacity...
            int remainingWeight;
            //Cycle through the item list, if it fits, put it in the rocket, and remove from the list.

            for (Item thisItem : itemList) {
                remainingWeight = thisRocket.getMaxWeight() - thisRocket.getCurrentWeight();
                if (thisRocket.canCarry(thisItem, remainingWeight)) {
                    thisRocket.carry(thisItem);
                }
            }
            //Take items off the list that have been loaded in this rocket
            for (Item thisItem : thisRocket.payload) {
                itemList.remove(thisItem);
            }
            //Put the payload in the rocket, and create it.
            thisFleet.add(thisRocket);

            //Print the contents of the rocket.
            int totalWeight = 0;
            for (Item inTheRocket : thisRocket.payload) {
                totalWeight+=inTheRocket.getWeight();
            }
            //If the cargo isn't all carried yet, go back and make a new rocket.
        }
        out.println("Loaded: "+thisFleet.size()+" U2s");
        return (thisFleet);
    }

    void printFleetContents (ArrayList<U1> U1Fleet, ArrayList<U2> U2Fleet) {
        for (U1 thisU1 : U1Fleet) {
            System.out.println("Rocket U1 no "+U1Fleet.indexOf(thisU1));
            for (Item thisItem : thisU1.payload) {
                out.println(thisItem.getName()+" "+thisItem.getWeight());
            }
            out.println("Total rocket weight: "+thisU1.getCurrentWeight());
            out.println("");
        }

        for (U2 thisU2 : U2Fleet) {
            System.out.println("Rocket U2 no "+U2Fleet.indexOf(thisU2));
            for (Item thisItem : thisU2.payload) {
                out.println(thisItem.getName()+" "+thisItem.getWeight());
            }
            out.println("Total rocket weight: "+thisU2.getCurrentWeight());
            out.println("");
        }
    }
}
