package game;

import players.Player;

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
			Tournament t = new Tournament();
			//make the tournament
			//add the people
			ArrayList<Player> players = t.playTournament(10000, false);
			Player winner = players.get(players.size()-1);
			//winnerBalls = winner.getBalls;
			//System.out.println(winnerBalls)
			System.out.println(balls);
			//Player father = new Player(father, winnerBalls);
			

		}
	}

}
