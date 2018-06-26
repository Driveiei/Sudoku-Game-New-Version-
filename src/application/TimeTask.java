package application;

import javafx.concurrent.Task;

/**
 * A worker that counts time from 0 to a total times.
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 */
public class TimeTask extends Task<Integer> {

	/**time run on milli seconds.*/
	private int timer = 0;
	/**status of game which already run or not.*/
	private boolean onRun = true;
	/**time run on second per second.*/
	private int sec = 0;
	/**time run on one minute per sixty seconds.*/
	private int minute = 0;
	/**base second add to time for special event*/
	private final int SPECIALTIME = 25;

	/**
	 * Invoked when the Task is executed, the call method must be overridden and
	 * implemented by subclasses.
	 * 
	 * @throws Checked exceptions need to be declared in a method or constructor's
	 *             throws clause if they can be thrown by the execution of the
	 *             method or constructor and propagate outside the method or
	 *             constructor boundary.
	 */
	@Override
	protected Integer call() throws Exception {
		while (onRun) {
			updateMessage(String.valueOf(String.format("%02d : %02d : %03d", minute, sec, timer)));
			Thread.sleep(1);
			if (timer == 1000) {
				timer = 0;
				sec++;
				if (sec == 60) {
					sec = 0;
					minute++;
				}
			}
			timer++;
		}
		return timer;
	}
	
	/**
	 * add special second time.
	 * 
	 * @param sec
	 */
	public void setTime() {
		this.sec += SPECIALTIME;
		if(this.sec >= 60) {
			this.sec -= 60;
			minute++;
		}
	}

	
	/**
	 * Get the total time that user used for solve sudoku problem.
	 * */
	public String getTime() {
		return String.format("%2d/%2d/%3d", minute, sec, timer);
	}

}