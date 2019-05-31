package tests;

import java.util.Timer;
import java.util.TimerTask;

public class TimerSetIntervalTest {

	public static void main(String[] args) {
		new Timer().scheduleAtFixedRate(new TimerTask(){
		    @Override
		    public void run(){
		       System.out.println("A Kiss every 5 seconds");
		    }
		},0,1000);

	}

}
