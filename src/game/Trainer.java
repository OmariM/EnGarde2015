package game;

import players.*;

import java.util.ArrayList;

/*
 * What it does:
 * runs a tounament with our bot and other bots randomly deviated from them our bot
 * figures out who the winner of the tournament is
 * extracts the coefficients from the winner
 * runs a tournament with new bot
 */

public class Trainer {

	static double balls;

	public Trainer(int balls) {

	}

	public static void main(String[] args) {
		int iterations = 0;
		train(iterations);

	}

	public static void train(int iterations) {
		while (true) {
			double oldBalls = balls;
			Tournament t = new Tournament(); 							// create the tournament
			t.addPlayer(new BlankSpace("father", balls));			    // add the father with best balls
			for (int i = 0; i < 10; i++) {								// creates the players with random balls
				balls = oldBalls + randomNum();
				t.addPlayer(new BlankSpace("variant " + i + " Balls: " + balls, balls));
			}
			ArrayList<Player> players = t.playTournament(1000, false);     // play the tournament
			Player winner = players.get(players.size() - 1);            // get the winner
			balls = ((BlankSpace) winner).getBalls(); 					// set the balls of the winner to father's balls
			iterations++;
			System.out.println("=======================================================================");
			System.out.println("Winner's Balls: " + balls	+ " || Number of iterations: " + iterations);
			System.out.println("=======================================================================");
		}
	}
	
	public static double randomNum(){
		double randNum = Math.random();
		if(Math.random() > .5) randNum = randNum*(-1);
		return randNum;
	}

}
