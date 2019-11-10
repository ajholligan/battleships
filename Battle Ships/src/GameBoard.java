import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;
/**
 * 
 */
import java.util.Scanner;

/**
 * @author Andrew Holligan
 *This class controls the 2 boards and can save and load the game
 */
public class GameBoard {

	
	
	
	
	private Ships[] shipArray;
	private int[][] grid;
	private char[][] userGrid;
	private int shotCount;
	
	
	/**
	 * @return the grid
	 */
	public int[][] getGrid() {
		return grid;
	}

	/**
	 * @param grid the grid to set
	 */
	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

	/**
	 * This constructor makes a 10x10 2d int array and inisialises  every value to 0. 
	 * It also inisialises a 10x10 char array and sets each char to O
	 * It also sets the value of shot count to 0
	 */
	public GameBoard()
	{ 
	
		grid = new int[10][10];
		
		
		//sets all values of grid to 0
		for (int col = 0; col<grid.length; col++)
		{ 
			for (int row = 0; row<grid.length; row++)
			{ 
				grid[row][col] = 0;
			}
		
		}
		
		userGrid = new char[10][10];
		// sets the values in user grid to O
		for (int col = 0; col < userGrid.length; col++)
		{ 
			
			for(int row = 0; row < userGrid.length; row++)
			{ 
				userGrid[row][col] = 'O';
			}
		}
		
		shotCount = 0;
	}
	
	
	
	/**
	 * This method generates a random number between 0 and a range.
	 * @param range this is the range of between 0 and range that the random number will be generated
	 * @return the random number generated between 0 and the range.
	 */
	public static int randomNumber(int range)
	{ 
		
		 Random randomGenerator = new Random();
		 int randomInt = randomGenerator.nextInt(range);
		
		 return randomInt;
		
	}

	
	/**
	 * This method checks if the random coordinates are possible to place the ship and stay in the board and not touch another ship
	 * @param shipsSize This is the size of the ship to be placed
	 * @param i This is the number of the ship being placed
	 * @return This is an array of ints which contains the coordinates of the placement direction of placement ship number and ship size
	 * 
	 */
	public int[] getOrientation(int shipsSize, int i)
	{ 
		
		boolean possibleRowLeft = false;
		boolean possibleRowRight= false;
		boolean possibleColUp = false;
		boolean possibleColDown = false;
		boolean works = false;
		int randomRow = 0;
		int randomCol = 0;
		int orientation = 5;
		
	
		do
		{
		
			possibleColUp = false;
			possibleColDown = false;
			possibleRowLeft = false;
			possibleRowRight = false;
		
			//gets random row and colomn
			
		 randomRow = randomNumber(10);
		 randomCol = randomNumber(10);

	//checks if it works in every orientation
		
		if(randomCol + getShipArray()[i].getSize()+1 < 10)
		{ 
			possibleRowRight = true;
		}
		if (randomCol - getShipArray()[i].getSize()+1 > 0)
		{ 
			possibleRowLeft = true;
		}
		
		if (randomRow + getShipArray()[i].getSize()+1 < 10)
		{ 
			possibleColDown = true;
			
		}
		
		if (randomRow - getShipArray()[i].getSize()+1 > 0 )
		{ 
			possibleColUp = true;
		}
		
		orientation = randomNumber(4);
		
	
		
		switch (orientation)
		{
			case 0: 
				if (possibleRowRight == false)
				{ 
				
					works = false;
					break;
				}
				else {
					
					works = true;
					// checks for ship overlap
					for (int j = 0; j<getShipArray()[i].getSize(); j++)
					{ 
						if (grid[randomRow][randomCol+j]  !=0)
						{ 
							works = false;
						}
					}
				break;
				}
				
			case 1: 
				
				if (possibleRowLeft == false)
				{ 
					
					break;
				}
				else 
				{ 
					
					works = true;
					for (int j = 0; j<getShipArray()[i].getSize(); j++)
					{ 
						if (grid[randomRow][randomCol-j] !=0)
						{ 
							works = false;
						}
					}
					
					break;
				}
				
			case 2: 
				
				if (possibleColUp == false)
				{ 
				
					break;
				}
				
				else 
				{ 
					
					works = true;
					for (int j = 0; j<getShipArray()[i].getSize(); j++)
					{ 
						if (grid[randomRow-j][randomCol] !=0)
						{ 
							works = false;
						}
					}
					break;
				}
				
				
			case 3:
				if (possibleColDown == false)
				{ 
					
					
					break;
				}
				
				else 
				{ 
					
					works = true;
					
					for (int j = 0; j<getShipArray()[i].getSize(); j++)
					{ 
						if (grid[randomRow+j][randomCol] !=0)
						{ 
							works = false;
						}
					}
					break;
				}
		}
		
		}
		while (works != true);
		
		
		int arrayReturn [] = new int[5];
		
		arrayReturn[0] = orientation;
		arrayReturn[1] = randomRow;
		arrayReturn[2] = randomCol;
		arrayReturn[3] = shipsSize;
		arrayReturn[4] = i;
		
		return arrayReturn;
	}
	
	
	
	
	
	
	/**
	 * This method places the ships into there position in the 2d array
	 * @param arrayReturn This contains the coordinates to place the ship the direction to place ship number and ship Size
	 */
	public void placeShips(int[] arrayReturn)
	{

		int orientation = arrayReturn[0];
		int randomRow= arrayReturn [1];
		int randomCol= arrayReturn [2];
		int shipsSize = arrayReturn[3];
		int j = arrayReturn[4];
		j++;
		
		switch (orientation)
		{ 
		case 0:
			//places the ships in the correct place 
			for (int i = 0 ; i < shipsSize; i++)
			{
				getGrid()[randomRow][randomCol+i] = j;
				
				
		}
			break;
			
		case 1:
			
			for (int i = 0 ; i < shipsSize; i++)
			{
				getGrid()[randomRow][randomCol-i] = j;
				
				
		}
			break;
			
		case 2:
			
			for (int i = 0 ; i < shipsSize; i++)
			{
				getGrid()[randomRow-i][randomCol] = j;
				
				
		}
			break;
			
		case 3:
			
			for (int i = 0 ; i < shipsSize; i++)
			{
				
				getGrid()[randomRow+i][randomCol] = j;
				
				
		}
			break;
		}
		

	 
}
	
/**
 * This method makes 9 ship objects and saves them in an array of objects
 */
public void arrayOfShips()
{ 
	int shipSize = 0;
	Ships[] ship = new Ships[9];
	boolean sunk = false;
	int numHits = 0;
	for (int i = 0; i<ship.length; i++)
	{ 
		// makes ships of different sizes as per the requirments
		if (i < 3)
		{	
				shipSize = 1; 
				ship[i] = new Ships(shipSize, sunk, numHits);
			
				
		}	
		
		if (i < 6 && i>2)
		{	
				shipSize = 2; 
				ship[i] = new Ships(shipSize, sunk, numHits);
			
		}	
		
		if (i < 8 && i>5 )
		{	
				shipSize = 3; 
				ship[i] = new Ships(shipSize, sunk, numHits);
				
		}	
		if (i < 9 && i > 7)
		{	
				shipSize = 4; 
				ship[i] = new Ships(shipSize, sunk, numHits);
			
		}	
	}
	
	setShipArray(ship);
}

/**
 * This method passes each ship object to the getOrientation and placeShips methods to place
 * all 9 ships.
 */
public void placeTheShips()
{
	int shipsSize = 0;
	for(int i = 0 ; i<shipArray.length; i++)
	{
	
		if (i < 3)
		{	//gives the objects to the place ships method
			shipsSize = 1;
			int[] arrayReturn = getOrientation(shipsSize, i);
			placeShips(arrayReturn);
			
			
		}	
	
		if (i < 6 && i>2)
		{	
			shipsSize = 2;
			int[] arrayReturn = getOrientation(shipsSize, i);
			placeShips(arrayReturn);
		}	
	
		if (i < 8 && i>5 )
		{	
			shipsSize = 3;
			int[] arrayReturn = getOrientation(shipsSize, i);
			placeShips(arrayReturn);
			
		}	
		if (i < 9 && i > 7)
		{	
			shipsSize = 4;
			int[] arrayReturn = getOrientation(shipsSize, i);
			placeShips(arrayReturn);
		}
	}
}

/**
 * This method updates the user grid displayed to the user to show the current state of the game
 */
public void userGrid()
{ 
	
	int[][] board = grid;
	char[][] userBoard = userGrid;
	
	for (int col = 0; col<grid.length; col++)
	{ 
		for (int row = 0; row<grid.length; row++)
		{  //displays the correct char depending on the int in the grid array
			int shipNum = Math.abs(board[row][col]);
		
			if(shipNum == 0)
			{ 
				continue;
			}
			
			if(shipNum == 10 )
			{ 
				userBoard[row][col] = '-';
				continue;
			}
			
			else if (board[row][col] < 0)
			{ 
				userBoard[row][col] = 'X';
			}
		
			
				if (shipArray[shipNum-1].isSunk() == true)
				{ 
					userBoard[row][col] = 'S';
				}
			
		}
		setUserGrid(userBoard);
	}
	
	                                                                                                                                
}


/**
 * @return the shipArray
 */
public Ships[] getShipArray() {
	return shipArray;
}

/**
 * @param shipArray the shipArray to set
 */
public void setShipArray(Ships[] shipArray) {
	
		this.shipArray = shipArray;
}

/**
 * @return the userGrid
 */
public char[][] getUserGrid() {
	return userGrid;
}

/**
 * @param userGrid the userGrid to set
 */
public void setUserGrid(char[][] userGrid) {
	this.userGrid = userGrid;
}


/**
 * This method saves all the relevent information to save the game
 */
public void saveGame()
{ 
	String fileName;
	OutputStream outputStream;
	PrintWriter printWriter;
	
	System.out.println("Please enter the name of the new file");
	Scanner s = new Scanner(System.in);
	fileName = s.nextLine() + ".txt";
	
	try
	{
		outputStream = new FileOutputStream(fileName);
		printWriter = new PrintWriter(outputStream);
		
		
		for(int i = 0; i < shipArray.length ; i++ )
		{
			//saves all the fields of each ship
			String outputShip = (getShipArray()[i].isSunk() + "," + getShipArray()[i].getSize() +","+getShipArray()[i].getNumHits());
					
			printWriter.println(outputShip); 
		
		}
		
		//saves grid 
		for(int i = 0; i < grid.length ; i++ )
		{
			String outputGrid = "";
			for(int j =0 ; j< grid.length; j++)
			{
				if(j<9)
				{ 
					outputGrid += (getGrid()[i][j] +",");
				}
				else 
				{ 
					outputGrid += (getGrid()[i][j]);
				}
			}
			printWriter.println(outputGrid);
		}
		//saves user grid
		for(int i = 0; i < userGrid.length ; i++ )
		{
			String outputUserGrid = "";
			for(int j =0 ; j< userGrid.length; j++)
			{
				if(j<9)
				{ 
					outputUserGrid += (getUserGrid()[i][j] +",");
				}
				else 
				{ 
					outputUserGrid += (getUserGrid()[i][j]);
				}
			}
			printWriter.println(outputUserGrid);
		}
		
		printWriter.println(shotCount);
		
		printWriter.close();


	}
	
	
	catch (IOException e)
	{ 
		System.out.println("An Error Occured" + e);
	}
	
}


/**
 * This method reads in the save file and continues from where the previous game left off
 */
public void loadGame()
{ 
	FileReader fileReader;
	BufferedReader bufferedReader;
	String fileName;
	
	System.out.println("Please enter the name of the file you wish to load");
	Scanner s = new Scanner(System.in);
	fileName = s.nextLine() + ".txt";
	
	try
	{
		fileReader = new FileReader(fileName);
		bufferedReader = new BufferedReader(fileReader); 
		String nextLine = bufferedReader.readLine();
		String [] ships = new String[3];
		Ships[] shipArray = new Ships[9];
	
		for (int i = 0 ; i<9 ; i++)
		{
			// loads in the ships fields and makes objects and refills the array
			ships = nextLine.split(",");
			boolean sunk = Boolean.parseBoolean(ships[0]);
			int size = Integer.parseInt(ships[1]);
			int numHits = Integer.parseInt(ships[2]);
			
			Ships ship = new Ships(size, sunk, numHits);
			shipArray[i] = ship;
			
			nextLine = bufferedReader.readLine();
		}
		
		setShipArray(shipArray);
		
		int[][] grid = new int[10][10];
		char[][] userGrid = new char[10][10];
		int[] tempInt = new int[10];
		String[] tempString = new String[10];
		char[] tempChar = new char[10];
//reads in the grid
		for (int i = 0; i<10; i++)
		{  
			//System.out.println(nextLine);
			for (int j = 0 ; j< 10; j++)
			{ 
				tempString[j] = nextLine.split(",")[j];
				tempInt[j] = Integer.parseInt(tempString[j]);
				grid[i][j] = tempInt[j];
			//	System.out.print(grid[i][j]);
			}
		//	System.out.println("");
			nextLine = bufferedReader.readLine();
		}
		
		setGrid(grid);
		
		for (int i = 0; i<10; i++)
		{  
			//System.out.println(nextLine);
			for (int j = 0 ; j< 10; j++)
			{ 
				tempString[j] = nextLine.split(",")[j];
				tempChar[j] = tempString[j].charAt(0);
				userGrid[i][j] = tempChar[j];
			//	System.out.print(userGrid[i][j]);
			}
			//System.out.println("");
			nextLine = bufferedReader.readLine();
		}
		
		setUserGrid(userGrid);
		
		bufferedReader.close();
		
		
	}
	catch (IOException e)
	{ 
		System.out.println("An Error has occured " + e);
	}
}




/**
 * @return the shotCount
 */
public int getShotCount() {
	return shotCount;
}

/**
 * @param shotCount the shotCount to set
 */
public void setShotCount(int shotCount) {
	this.shotCount = shotCount;
}
	

	


	}
