//import java.util.Scanner;
//
///**
// * 
// */
//
///**
// * @author aholl
// *
// */
///**public class Menu {
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		
//		Menu test = new Menu();
//		
//	
//		
//		test.startUpMenu();
//		
////		Game game = new Game();		
////		game.getGameBoard().arrayOfShips();
////		game.getGameBoard().placeTheShips();
////		game.testControl();
//	}
//
//	
//	private GameBoard gameBoard;
//	private Game game;
//
//	
//	public Menu()
//	{ 
//		setGame(new Game());
//		setGameBoard(new GameBoard());
//		
//	}
//	
//	
//	public void displayMenu()
//	{
////		int choice;
////		System.out.println("Please enter the number of the option in the menu below");
////		System.out.println("1. Start a new game");
////		System.out.println("2. Save a current game");
////		System.out.println("3. Load a previous game");
////		
////		Scanner s = new Scanner(System.in);
////		choice = s.nextInt();
////		
////		startUpMenu(choice);
//	}
//	
//	
//	public void startUpMenu()
//	{ 
//
//		boolean exit = false;
//		do
//		{
//			int choice;
//			System.out.println("Please enter the number of the option in the menu below");
//			System.out.println("1. Start a new game");
//			System.out.println("2. Save a current game");
//			System.out.println("3. Load a previous game");
//			
//			Scanner s = new Scanner(System.in);
//			choice = s.nextInt();
//		
//		switch(choice)
//		{ 
//		
//		case 1: 
//			System.out.println("Starting new game");
//				
//			game.getGameBoard().arrayOfShips();
//			game.getGameBoard().placeTheShips();
//			game.testControl();
//			break;
//			
//		case 2:
//			game.getGameBoard().saveGame();
//			break;
//			
//		case 3: 
//			game.getGameBoard().loadGame();
//			game.testControl();
//			break;
//			
//		case 4:
//			exit = true;
//			break;
//		} 
//		}
//		while(exit != true); 
//	}
//
//
//	/**
//	 * @return the game
//	 */
//	public Game getGame() {
//		return game;
//	}
//
//
//	/**
//	 * @param game the game to set
//	 */
//	public void setGame(Game game) {
//		this.game = game;
//	}
//
//
//	/**
//	 * @return the gameBoard
//	 */
//	public GameBoard getGameBoard() {
//		return gameBoard;
//	}
//
//
//	/**
//	 * @param gameBoard the gameBoard to set
//	 */
//	public void setGameBoard(GameBoard gameBoard) {
//		this.gameBoard = gameBoard;
//	}
//
//
//
//	
//	
//}
