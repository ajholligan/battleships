import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class GUIMenu implements ActionListener 
{
    // Default filename to use for saving and loading files
    // Possible improvement: replace with a FileChooser
    private final static String DEFAULT_FILENAME = "battlegui.txt";
    private int GRID_SIZE = 10;
    private JButton [] buttonArray; 
    
    private static Game game;

    
    /**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	public JMenuBar createMenu() 
    {
        JMenuBar menuBar  = new JMenuBar();;
        JMenu menu = new JMenu("Battle Menu");
        JMenuItem menuItem;
       
        menuBar.add(menu);

        // A group of JMenuItems. You can create other menu items here if desired
        menuItem = new JMenuItem("New Game");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem("Load Game");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem("Save Game");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //a submenu
        menu.addSeparator();
        return menuBar;
    }

    public Container createContentPane() 
    {
        int numButtons = GRID_SIZE * GRID_SIZE;
        JPanel grid = new JPanel(new GridLayout(GRID_SIZE,GRID_SIZE));
        buttonArray = new JButton[numButtons];
        
        for (int i=0; i<numButtons; i++)
        {
            buttonArray[i] = new JButton(" ");

			// This label is used to identify which button was clicked in the action listener
            buttonArray[i].setActionCommand("" + i); // String "0", "1" etc.
            buttonArray[i].addActionListener(this);
            grid.add(buttonArray[i]);
        }
        return grid;
    }

    /**
     * This method handles events from the Menu and the board.
     *
     */
    public void actionPerformed(ActionEvent e) 
    {
        String classname = getClassName(e.getSource());
        JComponent component = (JComponent)(e.getSource());
    
        if (classname.equals("JMenuItem"))
        {
            JMenuItem menusource = (JMenuItem)(e.getSource());
            String menutext  = menusource.getText();
            
            // Determine which menu option was chosen
            if (menutext.equals("Load Game"))
            {
                /* BATTLEGUI    Add your code here to handle Load Game **********/
                LoadGame();
                int[][] grid = game.getGameBoard().getGrid();
                int bnum = 0;
                for(int i = 0; i<10; i++)
                { 
                	for(int j =0 ; j<10; j++)
                	{
                		 if (grid[i][j] == -10)
                         {
                		
                         	String temp = "X";
                         	buttonArray[bnum].setText(temp);
                         	buttonArray[bnum].setBackground(Color.green);
                         	buttonArray[bnum].setForeground(Color.black);
                			 
                         }
                			 
                		 else if (grid[i][j] < 0 )
                         {
                         	buttonArray[bnum].setText("-");
                         	buttonArray[bnum].setBackground(Color.red);
                         	buttonArray[bnum].setForeground(Color.black);
                         
                         }
                		 bnum++;
                	}
                }
               
            }
            else if (menutext.equals("Save Game"))
            {
                /* BATTLEGUI    Add your code here to handle Save Game **********/
                SaveGame();
            }
            else if (menutext.equals("New Game"))
            {
                /* BATTLEGUI    Add your code here to handle Save Game **********/
                NewGame();
                
                for(int bnum = 0; bnum<100; bnum++)
                { 
                	buttonArray[bnum].setText(null);
                	buttonArray[bnum].setBackground(null);
                }
            }
        }
        // Handle the event from the user clicking on a command button
        else if (classname.equals("JButton"))
        {
            JButton button = (JButton)(e.getSource());
            int bnum = Integer.parseInt(button.getActionCommand());
            int row = bnum / GRID_SIZE;
            int col = bnum % GRID_SIZE;
                   
            /* BATTLEGUI    Add your code here to handle user clicking on the grid ***********/
            boolean hit = fireShot(row, col);
            
            if (hit == true)
            {
            	String temp = "X";
            	buttonArray[bnum].setText(temp);
            	buttonArray[bnum].setBackground(Color.red);
            	buttonArray[bnum].setForeground(Color.black);
            }
            if (hit == false)
            {
            	buttonArray[bnum].setText("-");
            	
            	buttonArray[bnum].setBackground(Color.green);
            	buttonArray[bnum].setForeground(Color.black);
            }
            
        }  
    }
    
    /**
     *  Returns the class name
     */
    protected String getClassName(Object o) 
    {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }

    /**
     * Create the GUI and show it.
     * For thread safety, this method should be invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI() 
    {
        // Create and set up the window.
        JFrame frame = new JFrame("Battleships");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane.
        GUIMenu battlegui = new GUIMenu();
        frame.setJMenuBar(battlegui.createMenu());
        frame.setContentPane(battlegui.createContentPane());

        // Display the window, setting the size
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
    
    /**
     * Sets a Gui grid square at row, col to display a character
     */
    public boolean setGuiSquare(int row, int col, char c)
    {
        int bnum = row * GRID_SIZE + col;
        if (bnum >= (GRID_SIZE*GRID_SIZE))
        {
            return false;
        }
        else
        {
            buttonArray[bnum].setText(Character.toString(c));
        }
        return true;
    }
    
    /**
     * This is a standard main function for a Java GUI
     */
    public static void main(String[] args) 
    {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
    	game = new Game();
    	game.getGameBoard().arrayOfShips();
    	game.getGameBoard().placeTheShips();
    	
        javax.swing.SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
                createAndShowGUI();
            }
        });
    }
      
    //************************************************************************
    //*** BATTLEGUI: Modify the methods below to respond to Menu and Mouse click events
     
    /**
     * This method is called from the Menu event: New Game.
     * BATTLEGUI
     */
    public void NewGame()
    {
         System.out.println("New game selected");
         game = new Game();		
         game.getGameBoard().arrayOfShips();
         game.getGameBoard().placeTheShips();
    }
    
    
    /**
     * This method is called from the Menu event: Load Game.
     * BATTLEGUI
     */
    public void LoadGame()
    {
          System.out.println("Load game selected");
        
          game.getGameBoard().loadGame();
    }
    
    
    /**
     * This method is called from the Menu event: Save Game.
     * BATTLEGUI
     */
    public void SaveGame()
    {
          System.out.println("Save game selected");
          game.getGameBoard().saveGame();
          
    }
    
    /**
     * This method is called from the Mouse Click event.
     * BATTLEGUI
     * @return 
     */
    public boolean fireShot(int row, int col)
    {
    	int[] returnCoordinates = new int[2];
    		returnCoordinates[0] = row;
    		returnCoordinates[1] = col;
          System.out.println("Fire shot selected: at (" + row + ", " + col + ")");
  		boolean valid = game.validShot(row, col);
  		boolean hit = false;
  		if (valid == true)
  		{
  			hit = game.testControl(row, col);
            game.getGameBoard().userGrid();
            
  		}
  		if (valid == false)
  		{
  			System.out.println("You have already hit these coordinates, please try again");
  		}
		return hit;
  		
          
          
    }

}
