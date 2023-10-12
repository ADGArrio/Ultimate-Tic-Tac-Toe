public class TTTBoard
{
	char[][] board;
	char winner;
	
	TTTBoard()
	{
		board = new char[3][3];
		winner = ' ';
	}
	
	public void makeBoard()
	{
		for(int row = 0; row < board.length; row++)
		{
			for(int col = 0; col < board[row].length; col++)
			{
				board[row][col] = '-';
			}
		}
	}
	
	//Checks left diagonal for winner
	private boolean checkLeftDiag(char player)
	{
		if(board[0][0] == player)
		{
			if(board[1][1] == player)
			{
				if(board[2][2] == player)
					{
						return true;
					}
			}
		}
		return false;
	}
	
	//Checks right diagonal for winner
	private boolean checkRightDiag(char player)
	{
		if(board[0][2] == player)
		{
			if(board[1][1] == player)
			{
				if(board[2][0] == player)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean checkCenterLine(char player)
	{
		if(board[0][1] == player)
		{
			if(board[1][1] == player)
			{
				if(board[2][1] == player)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean checkRightLine(char player)
	{
		if(board[0][2] == player)
		{
			if(board[1][2] == player)
			{
				if(board[2][2] == player)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean checkLeftLine(char player)
	{
		if(board[0][0] == player)
		{
			if(board[1][0] == player)
			{
				if(board[2][0] == player)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean checkTopHorizontalLine(char player)
	{
		if(board[0][0] == player)
		{
			if(board[0][1] == player)
			{
				if(board[0][2] == player)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean checkMiddleHorizontalLine(char player)
	{
		if(board[1][0] == player)
		{
			if(board[1][1] == player)
			{
				if(board[1][2] == player)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean checkLowerHorizontalLine(char player)
	{
		if(board[2][0] == player)
		{
			if(board[2][1] == player)
			{
				if(board[2][2] == player)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkIfWinner()
	{
		char playerX ='X';
		char playerO = 'O';
					
		//Check Left Diagonal
		if(checkLeftDiag(playerX))
		{
			winner = 'X';
			System.out.println(winner + " HAS WON THE GAME!");
			return true;
		}
		
		else if(checkLeftDiag(playerO))
		{
			winner = 'O';
			System.out.println(winner + " HAS WON THE GAME!");
			return true;
		}
			
		//Check Right Diagonal
		else if(checkRightDiag(playerX))
		{
			winner = 'X';
			System.out.println(winner + " HAS WON THE GAME!");
			return true;
		}
			
		else if(checkRightDiag(playerO))
		{
			winner = 'O';
			System.out.println(winner + " HAS WON THE GAME!");
			return true;
		}
			
		//Check Center Vertical Line
		else if(checkCenterLine(playerX))
		{
			winner = 'X';
			System.out.println(winner + " HAS WON THE GAME!");
			return true;
		}
		
		else if(checkCenterLine(playerO))
		{
			winner = 'O';
			System.out.println(winner + " HAS WON THE GAME!");
			return true;
		}

		//Check Right Vertical Line
		else if(checkRightLine(playerX))
		{
			winner = 'X';
			System.out.println(winner + " HAS WON THE GAME!");
			return true;
		}
		
		else if(checkRightLine(playerO))
		{
			winner = 'O';
			System.out.println(winner + " HAS WON THE GAME!");
			return true;
		}
			
		//Check Left Vertical Line
		else if(checkLeftLine(playerX))
		{
			winner = 'X';
			System.out.println(winner + " HAS WON THE GAME!");
			return true;
		}
		
		else if(checkLeftLine(playerO))
		{
			winner = 'O';
			System.out.println(winner + " HAS WON THE GAME!");
			return true;
		}
		
		//Check Top Horizontal Line
		else if(checkTopHorizontalLine(playerX))
		{
			winner = 'X';
			System.out.println(winner + " HAS WON THE GAME!");
			return true;
		}
		
		else if(checkTopHorizontalLine(playerO))
		{
			winner = 'O';
			System.out.println(winner + " HAS WON THE GAME!");
			return true;
		}
		
		//Check Middle Horizontal Line
		else if(checkMiddleHorizontalLine(playerX))
		{
			winner = 'X';
			System.out.println(winner + " HAS WON THE GAME!");
			return true;
		}
		
		else if(checkMiddleHorizontalLine(playerO))
		{
			winner = 'O';
			System.out.println(winner + " HAS WON THE GAME!");
			return true;
		}
				
		//Check Lower Horizontal Line
		
		else if(checkLowerHorizontalLine(playerX))
		{
			winner = 'X';
			System.out.println(winner + " HAS WON THE GAME!");
			return true;
		}
		
		else if(checkLowerHorizontalLine(playerO))
		{
			winner = 'O';
			System.out.println(winner + " HAS WON THE GAME!");
			return true;
		}
		
		return false;
	}
	
	public boolean checkIfWinnerTotal()
	{
		char playerX ='X';
		char playerO = 'O';
					
		//Check Left Diagonal
		if(checkLeftDiag(playerX))
		{
			winner = 'X';
			return true;
		}
		
		else if(checkLeftDiag(playerO))
		{
			winner = 'O';
			return true;
		}
			
		//Check Right Diagonal
		else if(checkRightDiag(playerX))
		{
			winner = 'X';
			return true;
		}
			
		else if(checkRightDiag(playerO))
		{
			winner = 'O';
			return true;
		}
			
		//Check Center Vertical Line
		else if(checkCenterLine(playerX))
		{
			winner = 'X';
			return true;
		}
		
		else if(checkCenterLine(playerO))
		{
			winner = 'O';
			return true;
		}

		//Check Right Vertical Line
		else if(checkRightLine(playerX))
		{
			winner = 'X';
			return true;
		}
		
		else if(checkRightLine(playerO))
		{
			winner = 'O';
			return true;
		}
			
		//Check Left Vertical Line
		else if(checkLeftLine(playerX))
		{
			winner = 'X';
			return true;
		}
		
		else if(checkLeftLine(playerO))
		{
			winner = 'O';
			return true;
		}
		
		//Check Top Horizontal Line
		else if(checkTopHorizontalLine(playerX))
		{
			winner = 'X';
			return true;
		}
		
		else if(checkTopHorizontalLine(playerO))
		{
			winner = 'O';
			return true;
		}
		
		//Check Middle Horizontal Line
		else if(checkMiddleHorizontalLine(playerX))
		{
			winner = 'X';
			return true;
		}
		
		else if(checkMiddleHorizontalLine(playerO))
		{
			winner = 'O';
			return true;
		}
				
		//Check Lower Horizontal Line
		
		else if(checkLowerHorizontalLine(playerX))
		{
			winner = 'X';
			return true;
		}
		
		else if(checkLowerHorizontalLine(playerO))
		{
			winner = 'O';
			return true;
		}
		
		return false;
	}
		
		public void print()
		{
			for(int i = 0; i < board.length; i++)
			{
				for(int j = 0; j < board[i].length; j++)
				{
					System.out.print(board[i][j] + " ");
				}
				System.out.println();
			}
		}
		
		public boolean isTie()
		{
			int count = 0;
			
			for(int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					if(board[i][j] != '-' && board[i][j] != '\0')
					{
						count++;
					}
				}
			}
			
			if(count == 9)
			{
				System.out.println("The Game Has Ended in a Tie");
				return true;
			}
			return false;
		}
		
		public boolean isFull()
		{
			int count = 0;
			
			for(int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					if(board[i][j] != '-' && board[i][j] != '\0')
					{
						count++;
					}
				}
			}
			
			if(count == 9)
			{
				return true;
			}
			return false;
		}
	}
