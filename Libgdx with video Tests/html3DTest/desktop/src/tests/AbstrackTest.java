package tests;

public class AbstrackTest {
	public static void main(String[] args) {
		Test test = new Deneme();
		test.isimBas();
		System.out.println(test.getID());
	}

}

abstract class Test{
	int id = 5;
	public abstract String getName();
	public void isimBas() {
		System.out.println("isim");
	}
	public int getID() {
		return id;
	}
}

class Deneme extends Test{

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return super.getID();
	}
	
}
