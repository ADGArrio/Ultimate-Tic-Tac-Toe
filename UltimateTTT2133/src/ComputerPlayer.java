import java.util.concurrent.ThreadLocalRandom;

public class ComputerPlayer 
{
	boolean isTurn = false;
	public int getCoordinates()
	{
		return ThreadLocalRandom.current().nextInt(0, 2 + 1);
	}
	
	public char getPlayer()
	{
		if(ThreadLocalRandom.current().nextInt(0, 1 + 1) == 1)
		{
			return 'X';
		}
		else 
		{
			return 'O';
		}
	}
}
