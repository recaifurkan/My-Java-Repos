# Java SuperUser Runner

Execute Jar's with Administrator privileges

_Note:_ On some versions of windows, elevated applications don't always have access to
removable drives that were mounted by users. Applications that need to be elevated on windows
should be run from a non-removable hard drive.

## Installation

import with gradle and build and package jar

### Example Usage:

```


public class AdministorTest extends SuperUserApplication {

    public static void main(String[] args) {

        SUDO.run(new AdministorTest(), args);

    }

    public int runAdministor(String[] args) {

        System.out.println("RUN AS ADMIN! YAY!");
    	try {
    		Thread.sleep(5000);
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    	return 0;

    }

    @Override
    public int runNotAdministor() {
        System.out.println("Not administor");
        return 0;
    }

}

```
