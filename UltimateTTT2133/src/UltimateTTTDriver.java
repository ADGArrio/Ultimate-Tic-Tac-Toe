import java.util.Scanner;

/**
 * @Author Arrio Gonsalves
 * @Class CS 2336
 * @Section 003
 * @ProjectName Ultimate Tic-Tac-Toe
 * 
 * @Analysis:
 * We need to design and implement an Ultimate Tic-Tac-Toe Game in which each box is a unique Tic-Tac-Toe game.
 * Each move is based on the previous move by the opponent.
 * If a marker is placed in a specific coordinate in the small Tic-Tac-Toe Board, the next move is in the exact same coordinate, but in the larger board.
 * 
 * @Design:
 * I have 4 different classes (following OOP concepts and functionality):
 * 1. UltimateTTTDriver.java
 * 2. UltimateTTTBoard.java
 * 3. TTTBoard.java
 * 4. ComputerPlayer.java
 * 
 * TTTBoard consists of a 3*3 2D char array that represents 1 single TTT Board out of the total 9 boards
 * UltimateTTTBoard consists of a 3*3 array of TTTBoard objects that represents a single Ultimate TTT Board with 9 smaller boards (of type TTTBoard)
 * ComputerPlayer is the class used for the AI player functionality
 * The UltimateTTTDriver class serves as a driver class. It allows the user to choose among 3 game versions: Human v. Human, AI v. AI, and Human v. AI
 * 1. Human v. Human is for 2 human players, each for X and O markers.
 * 2. AI v. AI is a completely automated gameplay that needs no human intervention.
 * 3. Human v. AI allows a human player to compete with the Computer.
 * 
 * The UltimateTTTDriver class has multiple input validation features that makes sure that the game is played fairly and that no illegal moves are made.
 * 
 * @Testing
 * I have tried my best to test each and every feature of the game for errors and bugs.
 * A few of my most common errors were positioning mistakes for the markers. I fixed them by debugging and working the problem out by hand to see which code snippet was the issue.
 * Another problem I faced was implementing the AI functionality, specifically in the version of the game in which a human plays against the AI.
 * I found it hard to switch players as the human uses a char and the ComputerPlayer is a different class.
 * I fixed this problem by implementing an extra method called switchHumanAndAI() in the UltimateTTTDriver, that uses a boolean variable called isTurn from the ComputerPlayer.java object to see if
 * its the AI's turn, in addition to the switchPlayers() method I was using for all other use cases.
 */

public class UltimateTTTDriver 
{
	private static char player = ' ';
	private static TTTBoard winnerBoard = new TTTBoard();
	private static ComputerPlayer AI = new ComputerPlayer();
	
	public static void main(String args[])
	{
		winnerBoard.makeBoard();
		System.out.println("					===== WELCOME TO ULTIMATE TIC-TAC-TOE!! =====\n");
		
		int choice;
		
		do {
			System.out.println("Please choose one of the following options.");
			System.out.println("1. Human v. Human");
			System.out.println("2. AI v. AI");
			System.out.println("3. Human v. AI");
			choice = getInput();
			
			if(choice != 1 && choice != 2 && choice != 3)
			{
				System.out.println("You can only choose options 1, 2 or 3. Please try again.");
			}
		}while(choice != 1 && choice != 2 && choice != 3);
		
		switch(choice)
		{
		case 1: 
			UltimateTTTBoard newBoard = new UltimateTTTBoard();

			printUltimateTTTBoard(newBoard.ultimateBoard[0][0], newBoard.ultimateBoard[0][1], newBoard.ultimateBoard[0][2]);
			printUltimateTTTBoard(newBoard.ultimateBoard[1][0], newBoard.ultimateBoard[1][1], newBoard.ultimateBoard[1][2]);
			printUltimateTTTBoard(newBoard.ultimateBoard[2][0], newBoard.ultimateBoard[2][1], newBoard.ultimateBoard[2][2]);
			
			int bigRow, bigCol;
					
			do {
				System.out.println("Please choose a marker (X OR O).");
				player = getCharInput();
				
				if(player != 'X' && player != 'O')
				{
					System.out.println("Marker can only be X OR O. Please try again.\n");
				}
			}while (player != 'X' && player != 'O');
			
			do{
				System.out.println("Player " + player + " please choose a Row Number from the board.");
				bigRow = getInput();
				
				if(bigRow < 0 || bigRow > 2)
				{
					System.out.println("Row Number Cannot be Less Than 0 or Greater than 2.\n\n");
				}
				
			}while(bigRow < 0 || bigRow > 2);
			
			do {
				System.out.println("Player " + player + " please choose a Column Number from the board.");
				bigCol = getInput();
				
				if(bigCol < 0 || bigCol > 2)
				{
					System.out.println("Col Number Cannot be Less Than 0 or Greater than 2.\n\n");
				}
			}while(bigCol < 0 || bigCol > 3);
			
			char otherPlayer = ' ';
			if(player == 'X')
			{
				otherPlayer = 'O';
			}
			else 
			{
				otherPlayer = 'X';
			}
			
			int smallRow, smallCol;
			
			do{
				System.out.println("Player " + player + " please choose a Row Number from the small board.");
				smallRow = getInput();
				
				if(smallRow < 0 || smallRow > 2)
				{
					System.out.println("Row Number Cannot be Less Than 0 or Greater than 2.\n\n");
				}
				
			}while(smallRow < 0 || smallRow > 2);
			
			do {
				System.out.println("Player " + player + " please choose a Column Number from the small board.");
				smallCol = getInput();
				
				if(smallCol < 0 || smallCol > 2)
				{
					System.out.println("Col Number Cannot be Less Than 0 or Greater than 2.\n\n");
				}
			}while(smallCol < 0 || smallCol > 3);
			
		
			if(newBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] != otherPlayer)
			{
				if(newBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] != player)
				{
					newBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] = player;
				}
			}
			
			bigCol = smallCol;
			bigRow = smallRow;
			
			switchPlayers();
			
			printUltimateTTTBoard(newBoard.ultimateBoard[0][0], newBoard.ultimateBoard[0][1], newBoard.ultimateBoard[0][2]);
			printUltimateTTTBoard(newBoard.ultimateBoard[1][0], newBoard.ultimateBoard[1][1], newBoard.ultimateBoard[1][2]);
			printUltimateTTTBoard(newBoard.ultimateBoard[2][0], newBoard.ultimateBoard[2][1], newBoard.ultimateBoard[2][2]);
			
			do
			{
				do{
					//new code start
					if(winnerBoard.checkIfWinnerTotal())
					{
						printUltimateTTTBoard(newBoard.ultimateBoard[0][0], newBoard.ultimateBoard[0][1], newBoard.ultimateBoard[0][2]);
						printUltimateTTTBoard(newBoard.ultimateBoard[1][0], newBoard.ultimateBoard[1][1], newBoard.ultimateBoard[1][2]);
						printUltimateTTTBoard(newBoard.ultimateBoard[2][0], newBoard.ultimateBoard[2][1], newBoard.ultimateBoard[2][2]);
						System.out.println("Player " + winnerBoard.winner + " HAS WON THE ULTIMATE TIC-TAC-TOE!!! CONGRATULATIONS!!!");
						winnerBoard.print();
						break;
					}
					
					else if(winnerBoard.isTie())
					{
						printUltimateTTTBoard(newBoard.ultimateBoard[0][0], newBoard.ultimateBoard[0][1], newBoard.ultimateBoard[0][2]);
						printUltimateTTTBoard(newBoard.ultimateBoard[1][0], newBoard.ultimateBoard[1][1], newBoard.ultimateBoard[1][2]);
						printUltimateTTTBoard(newBoard.ultimateBoard[2][0], newBoard.ultimateBoard[2][1], newBoard.ultimateBoard[2][2]);
						System.out.println("The Ultimate Tic-Tac-Toe Game has Resulted in a Tie!");
						winnerBoard.print();
						break;
					}
					//end code

										
						System.out.println("Player " + player + " please choose a Row Number from the small board.");
						smallRow = getInput();
						
						System.out.println("Player " + player + " please choose a Column Number from the small board.");
						smallCol = getInput();
						
						if(smallCol < 0 || smallCol > 2)
						{
							System.out.println("Col Number Cannot be Less Than 0 or Greater than 2.\n\n");
						}
						
						if(smallRow < 0 || smallRow > 2)
						{
							System.out.println("Row Number Cannot be Less Than 0 or Greater than 2.\n\n");
						}
						
						if(newBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] == otherPlayer || newBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] == player)
						{
							System.out.println("There already is a marker at this coordinate. Please try again.\n");
						}
						
					}while(smallCol < 0 || smallCol > 3 || smallRow < 0 || smallRow > 2 || newBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] == otherPlayer || newBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] == player);
				
				if(player == 'X')
				{
					otherPlayer = 'O';
				}
				else 
				{
					otherPlayer = 'X';
				}
						
				if(newBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] != otherPlayer)
				{
					if(newBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] != player)
					{
						newBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] = player;
					}
				}
					
				if(newBoard.ultimateBoard[bigRow][bigCol].checkIfWinner())
				{
					winnerBoard.board[bigRow][bigCol] = newBoard.ultimateBoard[bigRow][bigCol].winner;
				}
				else if(newBoard.ultimateBoard[bigRow][bigCol].isTie())
				{
					winnerBoard.board[bigRow][bigCol] = 'T';
				}
			
				if(winnerBoard.checkIfWinnerTotal())
				{
					System.out.println("Player " + winnerBoard.winner + " HAS WON THE ULTIMATE TIC-TAC-TOE!!! CONGRATULATIONS!!!");
					winnerBoard.print();
					break;
				}
				else if(winnerBoard.isTie())
				{
					System.out.println("The Ultimate Tic-Tac-Toe Game has Resulted in a Tie!");
					winnerBoard.print();
					break;
				}
							
				bigRow = smallRow;
				bigCol = smallCol;
				
				switchPlayers();

				System.out.println("\n");			
				
				printUltimateTTTBoard(newBoard.ultimateBoard[0][0], newBoard.ultimateBoard[0][1], newBoard.ultimateBoard[0][2]);
				printUltimateTTTBoard(newBoard.ultimateBoard[1][0], newBoard.ultimateBoard[1][1], newBoard.ultimateBoard[1][2]);
				printUltimateTTTBoard(newBoard.ultimateBoard[2][0], newBoard.ultimateBoard[2][1], newBoard.ultimateBoard[2][2]);
				
			}while(true);
			break;
		case 2:
			
			UltimateTTTBoard AIBoard = new UltimateTTTBoard();

			printUltimateTTTBoard(AIBoard.ultimateBoard[0][0], AIBoard.ultimateBoard[0][1], AIBoard.ultimateBoard[0][2]);
			printUltimateTTTBoard(AIBoard.ultimateBoard[1][0], AIBoard.ultimateBoard[1][1], AIBoard.ultimateBoard[1][2]);
			printUltimateTTTBoard(AIBoard.ultimateBoard[2][0], AIBoard.ultimateBoard[2][1], AIBoard.ultimateBoard[2][2]);
			
			do {
				System.out.println("Please choose a marker (X OR O).");
				player = AI.getPlayer();
				System.out.println(player);
				}while (player != 'X' && player != 'O');

			do{
				System.out.println("Player " + player + " please choose a Row Number from the ENTRIE board.");
				bigRow = AI.getCoordinates();
				System.out.println(bigRow);
				}while(bigRow < 0 || bigRow > 2);
			
			do {
				System.out.println("Player " + player + " please choose a Column Number from the ENTIRE board.");
				bigCol = AI.getCoordinates();
				System.out.println(bigCol);
				
			}while(bigCol < 0 || bigCol > 3);
			
			
			if(player == 'X')
			{
				otherPlayer = 'O';
			}
			else 
			{
				otherPlayer = 'X';
			}
			
			
			do{
				System.out.println("Player " + player + " please choose a Row Number from the small board.");
				smallRow = AI.getCoordinates();
			}while(smallRow < 0 || smallRow > 2);
			
			do {
				System.out.println("Player " + player + " please choose a Column Number from the small board.");
				smallCol = AI.getCoordinates();
				System.out.println(smallCol);
			}while(smallCol < 0 || smallCol > 3);
			
		
			if(AIBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] != otherPlayer)
			{
				if(AIBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] != player)
				{
					AIBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] = player;
				}
			}
			
			bigCol = smallCol;
			bigRow = smallRow;
			
			switchPlayers();
			
			printUltimateTTTBoard(AIBoard.ultimateBoard[0][0], AIBoard.ultimateBoard[0][1], AIBoard.ultimateBoard[0][2]);
			printUltimateTTTBoard(AIBoard.ultimateBoard[1][0], AIBoard.ultimateBoard[1][1], AIBoard.ultimateBoard[1][2]);
			printUltimateTTTBoard(AIBoard.ultimateBoard[2][0], AIBoard.ultimateBoard[2][1], AIBoard.ultimateBoard[2][2]);
			
			do
			{
				if(player == 'X')
				{
					otherPlayer = 'O';
				}
				else 
				{
					otherPlayer = 'X';
				}
				
				
				do{
					if(winnerBoard.checkIfWinnerTotal())
					{
						printUltimateTTTBoard(AIBoard.ultimateBoard[0][0], AIBoard.ultimateBoard[0][1], AIBoard.ultimateBoard[0][2]);
						printUltimateTTTBoard(AIBoard.ultimateBoard[1][0], AIBoard.ultimateBoard[1][1], AIBoard.ultimateBoard[1][2]);
						printUltimateTTTBoard(AIBoard.ultimateBoard[2][0], AIBoard.ultimateBoard[2][1], AIBoard.ultimateBoard[2][2]);
						System.out.println("Player " + winnerBoard.winner + " HAS WON THE ULTIMATE TIC-TAC-TOE!!! CONGRATULATIONS!!!");
						winnerBoard.print();
						break;
					}
					
					else if(winnerBoard.isTie())
					{
						printUltimateTTTBoard(AIBoard.ultimateBoard[0][0], AIBoard.ultimateBoard[0][1], AIBoard.ultimateBoard[0][2]);
						printUltimateTTTBoard(AIBoard.ultimateBoard[1][0], AIBoard.ultimateBoard[1][1], AIBoard.ultimateBoard[1][2]);
						printUltimateTTTBoard(AIBoard.ultimateBoard[2][0], AIBoard.ultimateBoard[2][1], AIBoard.ultimateBoard[2][2]);
						System.out.println("The Ultimate Tic-Tac-Toe Game has Resulted in a Tie!");
						winnerBoard.print();
						break;
					}
					
					if (AIBoard.ultimateBoard[bigRow][bigCol].isFull())
					{
						bigRow = AI.getCoordinates();
						bigCol = AI.getCoordinates();
						
					}
					
					
					
						System.out.println("Player " + player + " please choose a Row Number from the small board.");
						smallRow = AI.getCoordinates();
						System.out.println(smallRow);
						
						System.out.println("Player " + player + " please choose a Column Number from the small board.");
						smallCol = AI.getCoordinates();
						System.out.println(smallCol);
												
						if(AIBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] == otherPlayer || AIBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] == player)
						{
							System.out.println("A marker already exists in that position. Please try again.");
						}
						
					
					}while(AIBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] == otherPlayer || AIBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] == player);
			
						
				if(AIBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] != otherPlayer)
				{
					if(AIBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] != player)
					{
						AIBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] = player;
					}
				}
					
				if(AIBoard.ultimateBoard[bigRow][bigCol].checkIfWinner())
				{
					winnerBoard.board[bigRow][bigCol] = AIBoard.ultimateBoard[bigRow][bigCol].winner;
				}
				else if(AIBoard.ultimateBoard[bigRow][bigCol].isTie())
				{
					winnerBoard.board[bigRow][bigCol] = 'T';
				}
			
				if(winnerBoard.checkIfWinnerTotal())
				{
					printUltimateTTTBoard(AIBoard.ultimateBoard[0][0], AIBoard.ultimateBoard[0][1], AIBoard.ultimateBoard[0][2]);
					printUltimateTTTBoard(AIBoard.ultimateBoard[1][0], AIBoard.ultimateBoard[1][1], AIBoard.ultimateBoard[1][2]);
					printUltimateTTTBoard(AIBoard.ultimateBoard[2][0], AIBoard.ultimateBoard[2][1], AIBoard.ultimateBoard[2][2]);
					System.out.println("Player " + winnerBoard.winner + " HAS WON THE ULTIMATE TIC-TAC-TOE!!! CONGRATULATIONS!!!");
					winnerBoard.print();
					break;
				}
				else if(winnerBoard.isTie())
				{
					printUltimateTTTBoard(AIBoard.ultimateBoard[0][0], AIBoard.ultimateBoard[0][1], AIBoard.ultimateBoard[0][2]);
					printUltimateTTTBoard(AIBoard.ultimateBoard[1][0], AIBoard.ultimateBoard[1][1], AIBoard.ultimateBoard[1][2]);
					printUltimateTTTBoard(AIBoard.ultimateBoard[2][0], AIBoard.ultimateBoard[2][1], AIBoard.ultimateBoard[2][2]);
					System.out.println("The Ultimate Tic-Tac-Toe Game has Resulted in a Tie!");
					winnerBoard.print();
					break;
				}
							
				bigRow = smallRow;
				bigCol = smallCol;
				
				switchPlayers();

				System.out.println("\n");			
				
				printUltimateTTTBoard(AIBoard.ultimateBoard[0][0], AIBoard.ultimateBoard[0][1], AIBoard.ultimateBoard[0][2]);
				printUltimateTTTBoard(AIBoard.ultimateBoard[1][0], AIBoard.ultimateBoard[1][1], AIBoard.ultimateBoard[1][2]);
				printUltimateTTTBoard(AIBoard.ultimateBoard[2][0], AIBoard.ultimateBoard[2][1], AIBoard.ultimateBoard[2][2]);
				
			}while(true);
			
			break;
			
		case 3:
			UltimateTTTBoard AIHumanBoard = new UltimateTTTBoard();
			
//			printUltimateTTTBoard(AIHumanBoard.ultimateBoard[0][0], AIHumanBoard.ultimateBoard[0][1], AIHumanBoard.ultimateBoard[0][2]);
//			printUltimateTTTBoard(AIHumanBoard.ultimateBoard[1][0], AIHumanBoard.ultimateBoard[1][1], AIHumanBoard.ultimateBoard[1][2]);
//			printUltimateTTTBoard(AIHumanBoard.ultimateBoard[2][0], AIHumanBoard.ultimateBoard[2][1], AIHumanBoard.ultimateBoard[2][2]);
//			
			do {
				System.out.println("Please choose a marker (X OR O).");
				player = getCharInput();
				
				if(player != 'X' && player != 'O')
				{
					System.out.println("Marker can only be X OR O. Please try again.\n");
				}
			}while (player != 'X' && player != 'O');
			
			do{
				System.out.println("Player " + player + " please choose a Row Number from the board.");
				bigRow = getInput();
				
				if(bigRow < 0 || bigRow > 2)
				{
					System.out.println("Row Number Cannot be Less Than 0 or Greater than 2.\n\n");
				}
				
			}while(bigRow < 0 || bigRow > 2);
			
			do {
				System.out.println("Player " + player + " please choose a Column Number from the board.");
				bigCol = getInput();
				
				if(bigCol < 0 || bigCol > 2)
				{
					System.out.println("Col Number Cannot be Less Than 0 or Greater than 2.\n\n");
				}
			}while(bigCol < 0 || bigCol > 3);
			
			if(player == 'X')
			{
				otherPlayer = 'O';
			}
			else 
			{
				otherPlayer = 'X';
			}
			
			do{
				System.out.println("Player " + player + " please choose a Row Number from the small board.");
				smallRow = getInput();
				
				if(smallRow < 0 || smallRow > 2)
				{
					System.out.println("Row Number Cannot be Less Than 0 or Greater than 2.\n\n");
				}
				
			}while(smallRow < 0 || smallRow > 2);
			
			do {
				System.out.println("Player " + player + " please choose a Column Number from the small board.");
				smallCol = getInput();
				
				if(smallCol < 0 || smallCol > 2)
				{
					System.out.println("Col Number Cannot be Less Than 0 or Greater than 2.\n\n");
				}
			}while(smallCol < 0 || smallCol > 3);
		
			if(AIHumanBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] != otherPlayer)
			{
				if(AIHumanBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] != player)
				{
					AIHumanBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] = player;
				}
			}
			
			bigCol = smallCol;
			bigRow = smallRow;
			
			switchPlayers();
			switchHumanAndAI();
			
			printUltimateTTTBoard(AIHumanBoard.ultimateBoard[0][0], AIHumanBoard.ultimateBoard[0][1], AIHumanBoard.ultimateBoard[0][2]);
			printUltimateTTTBoard(AIHumanBoard.ultimateBoard[1][0], AIHumanBoard.ultimateBoard[1][1], AIHumanBoard.ultimateBoard[1][2]);
			printUltimateTTTBoard(AIHumanBoard.ultimateBoard[2][0], AIHumanBoard.ultimateBoard[2][1], AIHumanBoard.ultimateBoard[2][2]);
			
			do
			{
				if(player == 'X')
				{
					otherPlayer = 'O';
				}
				else 
				{
					otherPlayer = 'X';
				}
				
				do{
					do {
						if(winnerBoard.checkIfWinnerTotal())
						{
							printUltimateTTTBoard(AIHumanBoard.ultimateBoard[0][0], AIHumanBoard.ultimateBoard[0][1], AIHumanBoard.ultimateBoard[0][2]);
							printUltimateTTTBoard(AIHumanBoard.ultimateBoard[1][0], AIHumanBoard.ultimateBoard[1][1], AIHumanBoard.ultimateBoard[1][2]);
							printUltimateTTTBoard(AIHumanBoard.ultimateBoard[2][0], AIHumanBoard.ultimateBoard[2][1], AIHumanBoard.ultimateBoard[2][2]);
							System.out.println("Player " + winnerBoard.winner + " HAS WON THE ULTIMATE TIC-TAC-TOE!!! CONGRATULATIONS!!!");
							winnerBoard.print();
							break;
						}
						
						else if(winnerBoard.isTie())
						{
							printUltimateTTTBoard(AIHumanBoard.ultimateBoard[0][0], AIHumanBoard.ultimateBoard[0][1], AIHumanBoard.ultimateBoard[0][2]);
							printUltimateTTTBoard(AIHumanBoard.ultimateBoard[1][0], AIHumanBoard.ultimateBoard[1][1], AIHumanBoard.ultimateBoard[1][2]);
							printUltimateTTTBoard(AIHumanBoard.ultimateBoard[2][0], AIHumanBoard.ultimateBoard[2][1], AIHumanBoard.ultimateBoard[2][2]);
							System.out.println("The Ultimate Tic-Tac-Toe Game has Resulted in a Tie!");
							winnerBoard.print();
							break;
						}
						
						if (AIHumanBoard.ultimateBoard[bigRow][bigCol].isFull())
						{
							bigRow = AI.getCoordinates();
							bigCol = AI.getCoordinates();
							
						}
							System.out.println("Player " + player + " please choose a Row Number from the small board.");//
							if(AI.isTurn)
							{
								smallRow = AI.getCoordinates();
								System.out.println(smallRow);
							}
							else
							{
								smallRow = getInput();
							}
							
							System.out.println("Player " + player + " please choose a Column Number from the small board.");
							if(AI.isTurn)
							{
								smallCol = AI.getCoordinates();
								System.out.println(smallCol);
							}
							else
							{
								smallCol = getInput();
							}

							if(smallRow < 0 || smallRow > 2)
							{
								System.out.println("Row Number Cannot be Less Than 0 or Greater than 2.\n\n");
							}
							if(smallCol < 0 || smallCol > 2)
							{
								System.out.println("Column Number Cannot be Less Than 0 or Greater than 2.\n\n");
							}
							
						}while(smallRow < 0 || smallRow > 2 || smallCol < 0 || smallCol > 2);
												
						if(AIHumanBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] == otherPlayer || AIHumanBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] == player)
						{
							System.out.println("A marker already exists in that position. Please try again.");
						}
						
					}while(AIHumanBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] == otherPlayer || AIHumanBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] == player);
			
						
				if(AIHumanBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] != otherPlayer)
				{
					if(AIHumanBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] != player)
					{
						AIHumanBoard.ultimateBoard[bigRow][bigCol].board[smallRow][smallCol] = player;
					}
				}
					
				if(AIHumanBoard.ultimateBoard[bigRow][bigCol].checkIfWinner())
				{
					winnerBoard.board[bigRow][bigCol] = AIHumanBoard.ultimateBoard[bigRow][bigCol].winner;
				}
				else if(AIHumanBoard.ultimateBoard[bigRow][bigCol].isTie())
				{
					winnerBoard.board[bigRow][bigCol] = 'T';
				}
			
				if(winnerBoard.checkIfWinnerTotal())
				{
					printUltimateTTTBoard(AIHumanBoard.ultimateBoard[0][0], AIHumanBoard.ultimateBoard[0][1], AIHumanBoard.ultimateBoard[0][2]);
					printUltimateTTTBoard(AIHumanBoard.ultimateBoard[1][0], AIHumanBoard.ultimateBoard[1][1], AIHumanBoard.ultimateBoard[1][2]);
					printUltimateTTTBoard(AIHumanBoard.ultimateBoard[2][0], AIHumanBoard.ultimateBoard[2][1], AIHumanBoard.ultimateBoard[2][2]);
					System.out.println("Player " + winnerBoard.winner + " HAS WON THE ULTIMATE TIC-TAC-TOE!!! CONGRATULATIONS!!!");
					winnerBoard.print();
					break;
				}
				else if(winnerBoard.isTie())
				{
					printUltimateTTTBoard(AIHumanBoard.ultimateBoard[0][0], AIHumanBoard.ultimateBoard[0][1], AIHumanBoard.ultimateBoard[0][2]);
					printUltimateTTTBoard(AIHumanBoard.ultimateBoard[1][0], AIHumanBoard.ultimateBoard[1][1], AIHumanBoard.ultimateBoard[1][2]);
					printUltimateTTTBoard(AIHumanBoard.ultimateBoard[2][0], AIHumanBoard.ultimateBoard[2][1], AIHumanBoard.ultimateBoard[2][2]);
					System.out.println("The Ultimate Tic-Tac-Toe Game has Resulted in a Tie!");
					winnerBoard.print();
					break;
				}
							
				bigRow = smallRow;
				bigCol = smallCol;
				
				switchPlayers();
				switchHumanAndAI();

				System.out.println("\n");			
				
				printUltimateTTTBoard(AIHumanBoard.ultimateBoard[0][0], AIHumanBoard.ultimateBoard[0][1], AIHumanBoard.ultimateBoard[0][2]);
				printUltimateTTTBoard(AIHumanBoard.ultimateBoard[1][0], AIHumanBoard.ultimateBoard[1][1], AIHumanBoard.ultimateBoard[1][2]);
				printUltimateTTTBoard(AIHumanBoard.ultimateBoard[2][0], AIHumanBoard.ultimateBoard[2][1], AIHumanBoard.ultimateBoard[2][2]);
				
			}while(true);
			
			break;
		}
				
		
	}
	
	//Gets User Input
	private static int getInput()
	{
		Scanner sc = new Scanner (System.in);
		return sc.nextInt();
	}
	
	private static char getCharInput()
	{
		Scanner sc = new Scanner (System.in);
		return sc.next().charAt(0);
	}
	
	//Prints the Ultimate TTT Board
	private static void printUltimateTTTBoard(TTTBoard one, TTTBoard two, TTTBoard three)
	{
		for(int i = 0; i < 3; i++)
		{
			System.out.print(one.board[0][i] + " ");
		}
		System.out.print("\t");
		
		for(int i = 0; i < 3; i++)
		{
			System.out.print(two.board[0][i] + " ");
		}
		System.out.print("\t");
		
		for(int i = 0; i < 3; i++)
		{
			System.out.print(three.board[0][i] + " ");
		}
		System.out.println();
		
		
		for(int i = 0; i < 3; i++)
		{
			System.out.print(one.board[1][i] + " ");
		}
		System.out.print("\t");
		
		for(int i = 0; i < 3; i++)
		{
			System.out.print(two.board[1][i] + " ");
		}
		System.out.print("\t");
		
		for(int i = 0; i < 3; i++)
		{
			System.out.print(three.board[1][i] + " ");
		}
		System.out.println();
		
		
		for(int i = 0; i < 3; i++)
		{
			System.out.print(one.board[2][i] + " ");
		}
		System.out.print("\t");
		
		for(int i = 0; i < 3; i++)
		{
			System.out.print(two.board[2][i] + " ");
		}
		System.out.print("\t");
		
		for(int i = 0; i < 3; i++)
		{
			System.out.print(three.board[2][i] + " ");
		}
		System.out.println();
		System.out.println();
	}
	
	//Switches the players from X to O and vice versa
	private static void switchPlayers()
	{
		if(player == 'X')
		{
			player = 'O';
		}
		else
		{
			player = 'X';
		}
	}
	
	private static void switchHumanAndAI()
	{
		if(AI.isTurn)
		{
			AI.isTurn = false;
		}
		else
		{
			AI.isTurn = true;
		}
	}
}
