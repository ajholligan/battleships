
/**
 * @author Andrew Holligan
 * This class contains the all the information about a ship
 *
 */
public class Ships {
	
	private boolean sunk;
	private int size;
	private int numHits;
	
	
	/**
	 * This sets the values of the fields of Ship to the values passed to it
	 * @param shipSize This is the size of the ship
	 * @param sunkShip This is a boolean of if the ship is sunk or not
	 * @param numHit This is the number of hits on the ship
	 */ 
	public Ships(int shipSize, boolean sunkShip, int numHit)
	{ 
		sunk = sunkShip; 
		size = shipSize;
		numHits = numHit;
		
	}


	/**
	 * @return the sunk
	 */
	public boolean isSunk() {
		return sunk;
	}


	/**
	 * @param sunk the sunk to set
	 */
	public void setSunk(boolean sunk) {
		this.sunk = sunk;
	}


	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}


	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}


	/**
	 * @return the numHits
	 */
	public int getNumHits() {
		return numHits;
	}


	/**
	 * @param numHits the numHits to set
	 */
	public void setNumHits(int numHits) {
		this.numHits = numHits;
	}

	
	
}
