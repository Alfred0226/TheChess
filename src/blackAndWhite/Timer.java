package blackAndWhite;

import javax.swing.JLabel;

public class Timer {
	private int limitSec;
	private int limitMin;
	
	public Timer(int minutes , int seconds)	//初始化時間
	{
		this.limitMin = minutes;
		this.limitSec = seconds;
	}
	
	public int getTime(String time)
	{
		if(time.equals("minute")) {
			return this.limitMin;
		}
		if(time.equals("second")) {
			return this.limitSec;
		}
		return -1;
	}
	
	public void start(JLabel label) {	//計時開始
		while (limitMin >= 0) {

			if (limitSec > 0) {

				limitSec--;

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (limitSec == 0) {
				limitSec = 59;
				limitMin--;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			label.setText(Integer.toString(limitMin) + ":" + Integer.toString(limitSec));
		}
	}
}
