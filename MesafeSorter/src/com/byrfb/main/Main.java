package com.byrfb.main;

import java.util.ArrayList;
import java.util.List;

import com.byrfb.objects.Nokta;

public class Main {
	
	
	

	public static void main(String[] args) {
		
		/*
		 * ana main s�n�f�m�z bu
		 * burada random veriler belilenip onlar�n en yak�n nokta s�ralamas� yap�l�oyr
		 * 
		 */
		List<Nokta> unsortedList = new ArrayList<>();
		for(int i = 0 ; i < 1000 ; i++) {
			double x = Math.random() * 100;
			double y = Math.random() * 100;
			unsortedList.add(new Nokta(x, y));
			
		}
		
		
		NoktaMesafeSorter sorter = new NoktaMesafeSorter(unsortedList);
		sorter.sort();
		
//		unsortedList.sort(null);
		
		for (Nokta n : sorter.getSortedList()) {
			System.out.println(n);
		}
		
		
		
		
		
		
		

	}

}
