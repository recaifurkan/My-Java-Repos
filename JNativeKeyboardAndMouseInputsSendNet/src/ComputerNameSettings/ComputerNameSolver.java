package ComputerNameSettings;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ComputerNameSolver {
	static String computerName;

	public static String getComputerName() {
		return computerName;
	}

	public ComputerNameSolver() {

		setUpComputerName();

	}

	/*
	 * kardeþ burada iþte pc ismi alýnýyor ona göre iþlem yapýlacak iþte
	 * 
	 */
	private void setUpComputerName() {
		try {
			InetAddress addr;
			addr = InetAddress.getLocalHost();
			computerName = addr.getHostName();
		} catch (UnknownHostException ex) {
			System.out.println("Hostname can not be resolved");
		}
		System.out.println(computerName);

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return computerName;
	}

}
