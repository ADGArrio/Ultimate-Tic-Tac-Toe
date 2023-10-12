
public class UltimateTTTBoard 
{
	TTTBoard[][] ultimateBoard;
	
	public UltimateTTTBoard()
	{
		ultimateBoard = new TTTBoard[3][3];
		
		ultimateBoard[0][0] = new TTTBoard();
		ultimateBoard[0][1] = new TTTBoard();
		ultimateBoard[0][2] = new TTTBoard();
		
		ultimateBoard[1][0] = new TTTBoard();
		ultimateBoard[1][1] = new TTTBoard();
		ultimateBoard[1][2] = new TTTBoard();
		
		ultimateBoard[2][0] = new TTTBoard();
		ultimateBoard[2][1] = new TTTBoard();
		ultimateBoard[2][2] = new TTTBoard();
		
		ultimateBoard[0][0].makeBoard();
		ultimateBoard[0][1].makeBoard();
		ultimateBoard[0][2].makeBoard();
		
		ultimateBoard[1][0].makeBoard();
		ultimateBoard[1][1].makeBoard();
		ultimateBoard[1][2].makeBoard();
		
		ultimateBoard[2][0].makeBoard();
		ultimateBoard[2][1].makeBoard();
		ultimateBoard[2][2].makeBoard();
	}
}
