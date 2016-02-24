import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Home on 2016-01-10.
 */
public class Engine {

    static Random rand = new Random();
    public static ArrayList<Door> doors = new ArrayList<>();
    public static ArrayList<Player> players = new ArrayList<>();
    static int rounds, winsByChange, winsByKeep, firstScore, secondScore, finalChoice;
    static double decimalChange, decimalKeep;
    static boolean montyTurn=false;

    /*The players are added (Monty as well for the sake of it), the round starts and the doors are
    * added and shuffled about, the player chooses a random door, Monty opens a remaining one,
    * a random choice whether or not to choose another door occurs and lastly a check is
    * done to see what the best choice would've been, doors are cleared and back to the start.*/
    public static void runGame(){
        setupPlayers();
        while(rounds<10000) {
            setupGame();
            System.out.println(doors);
            int choice = rand.nextInt(3);

            openDoor(choice);
            openDoor(0);

            System.out.println("Do you want to change to the unopened door?");

            finalChoice = rand.nextInt(2);

            if(finalChoice==1){

                System.out.println("Y");
                System.out.println(players.get(0).getName() + "'s score is " + secondScore);
            }
            else if(finalChoice==0){
                System.out.println("N");
                System.out.println(players.get(0).getName() + "'s score is " + firstScore);
            }
            betterChance();
            doors.clear();



        }
        System.out.println("Potential wins by changing is " + (winsByChange-winsByKeep) + "/" + rounds +" higher than by keeping.");
        System.out.println("Total score is " + players.get(0).getPlayerScore() + " or " + players.get(0).getPlayerScore() / 10 + " wins.");
    }
    public static void setupPlayers(){

        players.add(new Player("Sean Connery"));
        players.add(new Player("Monty Hall"));

    }
    /*Checks the best potential move given the situation, if the player chose correctly
    * the score is saved to his/her personal record.*/
    public static void betterChance(){

        if(firstScore<secondScore){
            winsByChange++;
            if(finalChoice==1){
                players.get(0).setScore(secondScore);
            }

        }
        else if(firstScore>secondScore){
            winsByKeep++;
            if(finalChoice==0){
                players.get(0).setScore(firstScore);
            }

        }
        rounds++;
        decimalKeep = (double) winsByKeep/rounds;
        decimalChange = (double) winsByChange/rounds;
        System.out.println("wins by changing: "+ decimalChange + " ("+winsByChange+"/"+rounds+")");
        System.out.println("wins by keeping: "+ decimalKeep + " ("+winsByKeep+"/"+rounds+")");


    }

    public static void setupGame() {

        doors.add(new Door(0));
        doors.add(new Door(10));
        doors.add(new Door(0));

        Collections.shuffle(doors);
    }

    /*Split up method using an if for the sake of clarity. A door is chosen, its content stored,
     it is then removed from the list. If it's Monty's turn the remaining doors are looped
     through and when a "non-10" is found it's removed. What remains is 10 (if the player chose 0)
     or 0 (if the player chose 10).*/
    public static void openDoor(int doorNr){
        if(montyTurn){
            for(int i=0; i<doors.size(); i++) {
                if (doors.get(i).getPrize() != 10) {
                    System.out.println("Behind " + players.get(1).getName() +"'s door was... " + doors.get(i).getPrize());
                    doors.remove(i);
                    System.out.println(doors);
                    montyTurn = false;
                    break;
                }
            }
            secondScore=doors.get(0).getPrize();
        }

        else {

            System.out.println(players.get(0).getName() + " chose door "+doorNr);
            firstScore = doors.get(doorNr).getPrize();
            doors.remove(doorNr);
            montyTurn=true;
        }
    }
}
