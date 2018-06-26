package logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Gridmanager represents production with list of grid which contains nine boxes.
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 * */
public class GridManager {
	
	/**list of grid which contains nine boxes.*/
	private List<BoxManager> list;
	
	/**
	 * Create grid which contains nine boxes. 
	 */
	public GridManager() {
		list = new ArrayList<BoxManager>();
	}
	
	/**
	 * Get list of grid which contains nine boxes.
	 *
	 * @return list of grid which contains nine boxes.
	 * */
	public List<BoxManager> getList() {
		return list;
	}
}
