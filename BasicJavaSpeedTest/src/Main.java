


public class Main {

	public static void main(String[] args) {
		long i = 0;
		long limit = 1000000000l;
		
		while(true) {
			i++;
			if(i % limit == 0) {
				System.out.println(i);
			}
		}

	}

}
