import java.util.Scanner;
/**
 * 
 */

/**
 * @author Andrew Holligan
 * This class controls all of the game logic
 */
public class Game {

	private GameBoard gameBoard;
	
	/**
	 * This constructor makes an object of gameBoard
	 */
	public Game()
	{ 
		gameBoard= new GameBoard();	
	}
	

	/**
	 * This method takes in the shot coordinates in the form A1 and returns them in array reference for i.e. 0,0
	 * @return returns an array with the 2 grid coordinates to fire the shot
	 */
	public int[] getShot()
	{ 
		String coordinate = "";
		String[] coordinateArray;
		int[] returnCoordinates =new int[2];
		boolean save = false;
		boolean validInput = false;
		
		
		do
		{
			
			System.out.println("Please enter the grid location of your shot in the form A1");
			Scanner s = new Scanner(System.in);
			coordinate = s.nextLine();
			
			if (coordinate.equals("s"))
			{ 
			
				save = true;
				
				returnCoordinates[0] = -1;
				return returnCoordinates;
				
				
			}
			
			coordinateArray = coordinate.split("");
			
			if(coordinateArray.length  == 2)
			{ 
				validInput=true;
				System.out.println(coordinateArray[0] +" "+ coordinateArray[1]);
			}
			else if (coordinateArray.length  == 3)
			{ 
				validInput=true;
				coordinateArray[1] = coordinateArray[1] + coordinateArray[2];
				System.out.println(coordinateArray[1]);
			}
			else
			{ 
				System.out.println("Invalid input please make sure there are no spaces in your input");
			}
			
		}
		while(validInput == false);
		
		coordinateArray[0] = coordinateArray[0].toUpperCase();
		switch (coordinateArray[0])
		{ 
		//converts the letters into numbers and takes 1 from other number so it matches array reference
		case "A": 
			returnCoordinates[0] = 0;
			returnCoordinates[1] = Integer.parseInt(coordinateArray[1]) - 1;
			break;
			
		case "B": 
			returnCoordinates[0] = 1;
			returnCoordinates[1] = Integer.parseInt(coordinateArray[1]) - 1;
			break;
			
		case "C": 
			returnCoordinates[0] = 2;
			returnCoordinates[1] = Integer.parseInt(coordinateArray[1]) - 1;
			break;
			
		case "D": 
			returnCoordinates[0] = 3;
			returnCoordinates[1] = Integer.parseInt(coordinateArray[1]) - 1;
			break;
			
		case "E": 
			returnCoordinates[0] =4;
			returnCoordinates[1] = Integer.parseInt(coordinateArray[1]) - 1;
			break;
			
		case "F": 
			returnCoordinates[0] = 5;
			returnCoordinates[1] = Integer.parseInt(coordinateArray[1]) - 1;
			break;
			
		case "G": 
			returnCoordinates[0] = 6;
			returnCoordinates[1] = Integer.parseInt(coordinateArray[1]) - 1;
			break;
			
		case "H": 
			returnCoordinates[0] = 7;
			returnCoordinates[1] = Integer.parseInt(coordinateArray[1]) - 1;
			break;
			
		case "I": 
			returnCoordinates[0] = 8;
			returnCoordinates[1] = Integer.parseInt(coordinateArray[1]) - 1;
			break;
			
		case "J": 
			returnCoordinates[0] = 9;
			returnCoordinates[1] = Integer.parseInt(coordinateArray[1]) - 1;
			break;
			
		
			
		}
		
		return returnCoordinates;
		
	}
	
	
	/**
	 * This method checks if the shot chosen is a valid choice
	 * @param row row of grid location from the user to fire the shots
	 * @param col col of grid location from user to fire
	 * @return  boolean true or false depending on if the shot is valid
	 */
	public boolean validShot(int row, int col)
	{ 
		int[][] grid = gameBoard.getGrid();
		boolean possible = false;
		
		
		
		if (grid[row][col] < 0)
		{ 
			possible = false;
		}
		else 
		{ 
			possible = true;
			System.out.println("works");
			
		}
		return possible;
		
	}
	
	/**
	 * This method fires the shot and changes the relevent information on the grid.
	 * @param returnCoordinates  This contains the shot coordinates
	 * @return hit whether it was a hit or not
	 */
	public boolean shotsFired(int[] returnCoordinates)
	{ 
		
	
		
		int xCoord = returnCoordinates[0];
		int yCoord = returnCoordinates[1];
		int[][] newGrid = gameBoard.getGrid();
		int num = gameBoard.getGrid()[xCoord][yCoord];
		boolean hit;
		
		
		if (gameBoard.getGrid()[xCoord][yCoord] == 0)
		{ 
			// sets value in the grid to new post shot value
			newGrid[xCoord][yCoord] = -10;
			gameBoard.setGrid(newGrid);
			System.out.println("Its a miss");
			hit = false;
		}
		
		else 
		{ 
			newGrid[xCoord][yCoord] = gameBoard.getGrid()[xCoord][yCoord]*-1;
			gameBoard.setGrid(newGrid);
			System.out.println("Its a hit");
			hit = true;
		}
		
	
		shipSunk(num);
		return hit;
	}

	/**
	 * @param row row of hit
	 * @param col col of hit
	 * @return hit whether it was a hit or not
	 * This method controls the game
	 */
	public boolean testControl(int row, int col)
	{ 
		int[] returnCoordinates = new int[2];
		returnCoordinates[0] = row;
		returnCoordinates[1] = col;
		boolean hit;
		
			
		hit = shotsFired(returnCoordinates);
		gameBoard.setShotCount(gameBoard.getShotCount()+1);
		gameBoard.userGrid();
		System.out.println("The current shot count is " + getGameBoard().getShotCount());
			
	
		if(gameOver() == true)
		{ 
			System.out.println("Congratulations all the ships are sunk");
			
		}
				
		return hit;	
	}


	/**
	 * @return the gameBoard
	 */
	public GameBoard getGameBoard() {
		return gameBoard;
	}


	/**
	 * @param gameBoard the gameBoard to set
	 */
	public void setGameBoard(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}
	
	/**
	 * This method detects if a ship has been sunk
	 * @param num This is the value in the 2d array on the spot where the shot was fired
	 */
	public void shipSunk(int num)
	{ 
		Ships[] ship = gameBoard.getShipArray();
		if(num == 0)
		{ 
			return;
		}
		num--;
		
		if(ship[num].getSize() == 1)
		{ 
			ship[num].setSunk(true);
			System.out.println("A ship is sunk");
		}
		if(ship[num].getSize() == 2)
		{ 
			if(ship[num].getNumHits() == 1)
			{ 
				ship[num].setSunk(true);
				System.out.println("A ship is sunk");
			}
			
			else 
			{
				 ship[num].setNumHits(ship[num].getNumHits()+1);
				 System.out.println(ship[num].getNumHits());
			}
		}
		
		if(ship[num].getSize() == 3)
		{ 
			if(ship[num].getNumHits() == 2)
			{ 
				ship[num].setSunk(true);
				System.out.println("A ship is sunk");
			}
			
			else 
			{
				 ship[num].setNumHits(ship[num].getNumHits()+1);
			}
		}
		
		if(ship[num].getSize() == 4)
		{ 
			if(ship[num].getNumHits() == 3)
			{ 
				ship[num].setSunk(true);
				System.out.println("A ship is sunk");
			}
			
			else 
			{
				 ship[num].setNumHits(ship[num].getNumHits()+1);
			}
		}
	}
	
	/**
	 * Checks if all the ships are sunk and if the game is over
	 * @return returns boolean true or false if the all the ships are sunk or not
	 */
	
	public boolean gameOver()
	{ 
		Ships[] ship = gameBoard.getShipArray();
		
		
		for(int i = 0; i < ship.length; i++)
		{ 
			if(ship[i].isSunk() == false)
			{ 
				System.out.println("Is ship destroyed: " + ship[i].isSunk());
				return false;
			}
			
		}
		
		return true;
	}
}
