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
		while(true == true){
			train(0.05);
		}
	}
	
	public static void train(double balls){
		int iterations = 0;
		Tournament t = new Tournament(); 								   //create the tournament
		t.addPlayer(new blank_space("father", balls)); 					   //add the father with best balls
		t.addPlayer(new blank_space("v1", balls + 1+(-2)*(Math.random()))); //add the variants
		t.addPlayer(new blank_space("v2", balls + 1+(-2)*(Math.random()))); //add the variants
		t.addPlayer(new blank_space("v3", balls + 1+(-2)*(Math.random()))); //add the variants
		t.addPlayer(new blank_space("v4", balls + 1+(-2)*(Math.random()))); //add the variants
		t.addPlayer(new blank_space("v5", balls + 1+(-2)*(Math.random()))); //add the variants
		t.addPlayer(new blank_space("v6", balls + 1+(-2)*(Math.random()))); //add the variants
		t.addPlayer(new blank_space("v7", balls + 1+(-2)*(Math.random()))); //add the variants
		t.addPlayer(new blank_space("v8", balls + 1+(-2)*(Math.random()))); //add the variants
		ArrayList<Player> players = t.playTournament(10000, false);        //play the tournament
		Player winner = players.get(players.size()-1);					   //get the winner
		double winnerBalls = ((blank_space)winner).getBalls();			   //get the balls of the winner
		iterations++;
		System.out.println("Winner Balls: " + winnerBalls + " || Number of iterations: " + iterations);
		balls = winnerBalls;											   //set the new balls to best balls
		train(balls);
		
	}

}
