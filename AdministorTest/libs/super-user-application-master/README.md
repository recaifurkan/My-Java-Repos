# Java SuperUser Runner
Execute Jar's with Administrator privileges

*Note:* On some versions of windows, elevated applications don't always have access to 
removable drives that were mounted by users. Applications that need to be elevated on windows
should be run from a non-removable hard drive. 

## Installation

import with gradle and build and package jar



### Example Usage:

```
package com.vnetpublishing.java;

import SU;
import SuperUserApplication;

public class TestAdmin extends SuperUserApplication {
	
	public static void main(String[] args) {
		SU.run(new TestAdmin(), args);
	}
	
	// when run application is administor
	public int runAdministor(String[] args) {
		System.out.println("RUN AS ADMIN! YAY!");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	// when run application is not administor
	@Override
        public int runNotAdministor() {
            System.out.println("Not administor");
            return 0;
        }
}


```
