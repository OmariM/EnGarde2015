package players;

//import javax.swing.JOptionPane;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class blank_space extends Player {
	// ArrayList<ArrayList<Integer>> cardDb=new ArrayList<ArrayList<Integer>>();
	private int[][] cardDb = new int[5][5];
	ArrayList<Integer> themMoves = new ArrayList<Integer>();
	private boolean direction = true;
	private int check = 0;
	private int move = 0;
	private double balls=.05;
	private int[] hand = { 0, 0, 0, 0, 0 };

	public blank_space(String name) {
		super(name);
	}

	/**
	 * Adds opponent move to the themMoves list
	 */
	public void count() {
		if (getOpponentLocation() != 0 && getOpponentLocation() != 22) {
			int y = getLastMove();
			themMoves.add(y);
		}
	}

	/**
	 * Finds whether the opponent makes more positive or negative moves this
	 * game
	 * 
	 * @return 1 if they made more positive moves, 0 if otherwise
	 */
	public int proper() {
		double pos = 1;
		double neg = 1;
		for (int i = 0; i < themMoves.size(); i++) {
			if (themMoves.get(i) <= 0) {
				neg += 1;
			} else {
				pos += 1;
			}
		}
		// System.out.println(pos/neg);
		if (pos > neg) {
			return 1;
		} else {
			return 0;
		}
		// return 1-(pos/neg);
	}

	/**
	 * Finds the probability that the opponent has a card
	 * 
	 * @param x
	 *            The card in question
	 * @return the probability
	 */
	public double win(int x) {
		if (x > 5) {
			return 3;
		}
		double n = getTurnsRemaining();
		double r = next(x);
		double check = (((5.0 + n - r) / (5.0 + n)) * ((4.0 + n - r) / (4.0 + n)) * ((3.0 + n - r) / (3.0 + n)) * ((2.0 + n - r) / (2.0 + n)) * ((1.0 + n - r) / (1.0 + n)));
		/*if (proper() == 1) {
				if (1.0 - (((5.0 + n - r) / (5.0 + n))
						* ((4.0 + n - r) / (4.0 + n))
						* ((3.0 + n - r) / (3.0 + n))
						* ((2.0 + n - r) / (2.0 + n)) * ((1.0 + n - r) / (1.0 + n))) > check) {*/
		/*
		 * else{ for (int i = 0; i < hand.length; i++) { if (1.0 - (((5.0 + n -
		 * r) / (5.0 + n)) * ((4.0 + n - r) / (4.0 + n))* ((3.0 + n - r) / (3.0
		 * + n)) * ((2.0 + n - r) / (2.0 + n)) * ((1.0 + n - r) / (1.0 + n))) >
		 * check) { check = (1.0-((5.0 + n - r) / (5.0 + n)) * ((4.0 + n - r) /
		 * (4.0 + n))* ((3.0 + n - r) / (3.0 + n)) * ((2.0 + n - r) / (2.0 + n))
		 * * ((1.0 + n - r) / (1.0 + n))); } } }
		 */
		return check;
	}

	/**
	 * Finds the highest move in an array
	 * 
	 * @param hand
	 *            The array to search
	 * @return the highest move
	 */
	public int highestMove(int[] hand) {
		int check = -5;
		for (int i = 0; i < hand.length; i++) {
			if (hand[i] > check) {
				check = hand[i];
			}
		}
		return check;
	}

	/**
	 * Finds the smallest abs val negative move in an array
	 * 
	 * @param hand
	 *            The array to search
	 * @return the negative move with the smallest absolute value
	 */
	public int lowestNeg(int[] hand) {
		int check = 5;
		for (int i = 0; i < hand.length; i++) {
			if (Math.abs(hand[i]) < check && hand[i] < 0) {
				check = hand[i];
			}
		}
		return check;
	}

	/**
	 * Finds the highest negative move in an array
	 * 
	 * @param hand
	 *            The array in question
	 * @return the negative move with the highest abs val
	 */
	public int highestNeg(int[] hand) {
		return getPossibleMoves()[0];
	}

	/**
	 * Finds the lowest positive move in an array
	 * 
	 * @param hand
	 *            The array to search
	 * @return the lowest positive move
	 */
	public int lowestMove(int[] hand) {
		int check = 5;
		for (int i = 0; i < hand.length; i++) {
			if (Math.abs(hand[i]) < check) {
				check = Math.abs(hand[i]);
			}
		}
		return check;
	}

	/**
	 * Determines whether a given value is in an array.
	 * 
	 * @param arr
	 *            The array to search for the value
	 * @param n
	 *            The value to search for
	 * @return true if n is in arr, false otherwise.
	 */
	public boolean contains(int[] arr, int n) {
		for (int i = 0; i < arr.length; i++) {
			if (n == arr[i]) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Finds how many of a certain card have been seen
	 * 
	 * @param card
	 *            The card number to check
	 * @return the number of that card that have been seen (in hand and their
	 *         move)
	 */
	public int next(int card) {
		int out = 0;
		card -= 1;
		if (card >= 0) {
			for (int i = 0; i < cardDb[card].length; i++) {
				if (cardDb[card][i] != 0) {
					out += 1;
				} else {
					out += 0;
				}
			}
		}
		return out;
	}

	/**
	 * Adds an array of ints (most likely the hand) into the card database,
	 * because we have seen them. Only occurs at the start of each game
	 * 
	 * @param hand
	 *            The array to add
	 */
	public void addHand(int[] hand) {// int[][] cardDb){
		if (check == 0) {
			for (int j = 0; j < hand.length; j++) {
				if (hand[j] == 1)
					cardDb[0][next(1)] = hand[j];
				else if (hand[j] == 2)
					cardDb[1][next(2)] = hand[j];
				else if (hand[j] == 3)
					cardDb[2][next(3)] = hand[j];
				else if (hand[j] == 4)
					cardDb[3][next(4)] = hand[j];
				else if (hand[j] == 5)
					cardDb[4][next(5)] = hand[j];
			}
			check = 1;
		}
	}

	/**
	 * Finds the sum of an array of ints
	 * 
	 * @param arr
	 *            The array to sum
	 * @return the sum of all the elements
	 */
	public int sum(int[] arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		return sum;
	}

	/**
	 * Adds the card drawn to the hand
	 * 
	 * @param hand
	 *            The old instance of hand[]
	 */
	public void addNewCardFromHand(int[] hand) {
		int[] check = getHand();
		int y = Math.abs(sum(check) - (sum(hand) - move));
		// JOptionPane.showMessageDialog(null, y);
		if (y == 0 && getTurnsRemaining() < 15) {
			/*
			 * JOptionPane.showMessageDialog(null,sum(check));
			 * JOptionPane.showMessageDialog(null,sum(hand));
			 * JOptionPane.showMessageDialog(null,move);
			 */
		} else if (y != 0) {
			cardDb[y - 1][next(y)] = y;
		}
		/*
		 * for (int i = 0; i < check.length; i++) { if (hand[i] != check[i] &&
		 * hand[i] != 0) { System.out.println("85.1"); cardDb[check[i] -
		 * 1][next(check[i])] = check[i]; System.out.println("85.2"); }
		 * System.out.println("85.3"); } System.out.println("85.4");
		 */
	}

	/**
	 * Passively counts their moves
	 */
	public void countThemCards() {
		if (getOpponentLocation() != 0 && getOpponentLocation() != 22) {
			int y = getLastMove();
			if (next(Math.abs(y)) < 5) {
				cardDb[Math.abs(y) - 1][next(Math.abs(y))] = Math.abs(y);
			}
		}
	}

	/**
	 * Aggressive move aka play the highest card
	 * 
	 * @param direction
	 *            True if you start on left side, false otherwise
	 * @return the highest move towards the opponent
	 */
	public int agressive(boolean direction) {
		if (direction) {
			System.out.println(highestMove(getPossibleMoves()) + "-1");
			return highestMove(getPossibleMoves());
		} else {
			System.out.println(highestNeg(getPossibleMoves()) + "0");
			return highestNeg(getPossibleMoves());
		}
	}

	/**
	 * This is the actual smart part of the program. If you are on the left
	 * side, and your distance to the opponent is less than 5, check if there is
	 * a move that can hit them. If not, move the lowest negative move backwards
	 * only if the probability of them having the card of the distance between
	 * you and them after your move is less than 5%. If not that, then move back
	 * the second highest negative move. Otherwise, if the distance between you
	 * and them is 12, loop through the possible moves array and find the first
	 * element that the probability
	 * 
	 * @param space
	 *            The distance between you and the opponent
	 * @param direction
	 *            True if you start on left side, false otherwise
	 * @return the smartest move
	 */
	public int defensive(int space, boolean direction) {
		if (direction) {
			if (space <= 5) {
				for (int i = 0; i < getPossibleMoves().length; i++) {// if u can
																		// hit
																		// them
					if (getPossibleMoves()[i] == space) {
						System.out.println(getPossibleMoves()[i] + " 1");
						return getPossibleMoves()[i];
					}else if(getPossibleMoves()[i]==space-1 && win(1) < balls){
						System.out.println(getPossibleMoves()[i] + " 2");
						return getPossibleMoves()[i];
					}
				}
				for (int i = 0; i < getPossibleMoves().length; i++) {
					if (space + Math.abs(getPossibleMoves()[i]) >=5 ){
						System.out.println(getPossibleMoves()[i] + " 3");
						return getPossibleMoves()[i];
					}
				}
					System.out.println(getPossibleMoves()[0] + " 4");
					//System.out.println(win(space - getPossibleMoves()[0]));
					return getPossibleMoves()[0];
			} else if (space <= 12) {
				// CARD CEWNTING
				for (int i = 0; i < getPossibleMoves().length; i++) {
					if (win(space - getPossibleMoves()[i]) < .05) {
						System.out.println(Math.abs(getPossibleMoves()[i]) + " 5");
						return getPossibleMoves()[i];
					}
				}
				if (space - highestMove(getPossibleMoves()) > 5) {
					System.out.println(highestMove(getPossibleMoves()) + " 6");
					return highestMove(getPossibleMoves());
				} else if (space > Math.abs(lowestMove(getPossibleMoves())) + 5) {
					System.out.println(lowestMove(getPossibleMoves()) + " 7");
					return lowestMove(getPossibleMoves());
				} else if (win(space - lowestNeg(getPossibleMoves())) < balls) {
					System.out.println(lowestNeg(getPossibleMoves()) + " 8");
					return lowestNeg(getPossibleMoves());
				} else {
					// //////////////////////////////////////////////////
					System.out.println(getPossibleMoves()[1] + " 9");
					return getPossibleMoves()[1];
				}
			} else {
				System.out.println(highestMove(getPossibleMoves()) + " 10");
				return highestMove(getPossibleMoves());
			}
			// /////////////////////////////////////////////////////////////////////
		} else {
			if (space <= 5) {
				for (int i = 0; i < getPossibleMoves().length; i++) {// if u can
					if (getPossibleMoves()[i] == -space) {
						System.out.println(getPossibleMoves()[i] + " 11");
						return getPossibleMoves()[i];
					}
				}
				for (int i = 0; i < getPossibleMoves().length; i++) {// if u can
					// hit
					// them
					if (win(space - getPossibleMoves()[i]) < balls &&space - getPossibleMoves()[i]<=5){
						System.out.println(getPossibleMoves()[i] + " 12");
						return getPossibleMoves()[i];
					}
				}
					System.out.println(getPossibleMoves()[getPossibleMoves().length - 2] + " 13");
					return getPossibleMoves()[getPossibleMoves().length - 2];
			} else if (space <= 12) {
				// CARD CEWNTING
				for (int i = 0; i < getPossibleMoves().length; i++) {
					if (space - Math.abs(getPossibleMoves()[i]) <= 5) {
						if (win(space - Math.abs(getPossibleMoves()[i])) < balls) {
							System.out.println(getPossibleMoves()[i] + " 14");
							return getPossibleMoves()[i];
						}
					}
				}
				if (space + highestNeg(getPossibleMoves()) > 5) {
					System.out.println(highestNeg(getPossibleMoves()) + " 15");
					return highestNeg(getPossibleMoves());
				} else if (space > Math.abs(lowestNeg(getPossibleMoves())) + 5) {
					System.out.println(lowestNeg(getPossibleMoves()) + " 16");
					return lowestNeg(getPossibleMoves());
				} else {
					System.out.println(getPossibleMoves()[getPossibleMoves().length - 2] + " 17");
					return getPossibleMoves()[getPossibleMoves().length - 2];
				}
			} else {
				System.out.println(highestNeg(getPossibleMoves()) + " 18");
				return highestNeg(getPossibleMoves());
			}
		}
	}

	@Override
	public int move() {
		// TODO Auto-generated method stub
		this.addNewCardFromHand(hand);//
		hand = getHand();
		int space = Math.abs(getLocation() - getOpponentLocation());
		/*
		 * System.out.println(getHand()[0]); System.out.println(getHand()[1]);
		 * System.out.println(getHand()[2]); System.out.println(getHand()[3]);
		 * System.out.println(getHand()[4]);
		 */
		this.count();
		this.countThemCards();//
		// return defensive(space,direction);
		/*
		 * for (int i = 0; i < cardDb.length; i++) { for (int k = 0; k <
		 * cardDb[i].length; k++) { System.out.print(cardDb[i][k]); } }
		 * System.out.println();
		 */
		if (space > 12) {
			move = Math.abs(agressive(direction));
			return agressive(direction);
		} else {
			move = Math.abs(defensive(space, direction));
			return defensive(space, direction);
		}
	}

	public void start() {
		this.check = 0;
		this.themMoves = new ArrayList<Integer>();
		this.cardDb = new int[5][5];
		this.direction = true;
		if (this.getLocation() >= 4) {
			this.direction = false;
		}
		hand = getHand();
		addHand(hand);
	}
}