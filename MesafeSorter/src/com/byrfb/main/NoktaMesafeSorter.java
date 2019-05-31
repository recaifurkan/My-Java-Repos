package com.byrfb.main;

import java.util.ArrayList;
import java.util.List;

import com.byrfb.objects.Nokta;

public class NoktaMesafeSorter {
	
	List<Nokta> unsortedList ;
	List<Nokta> sortedList = new ArrayList<>();

	public List<Nokta> getSortedList() {
		return sortedList;
	}

	public NoktaMesafeSorter(List<Nokta> unsortedList) {
		super();
		this.unsortedList = unsortedList;
	}
	// direk s�ralamay� ba�lat�r
	public void sort() {
		int randomNokta = (int)(Math.random()*(unsortedList.size()-1));
//		unsortedList.sort(null);
		Nokta n1 = unsortedList.get(randomNokta);
//		Nokta n1 = unsortedList.get(0);
		System.out.println(n1);
		sort(n1);
		
		
	}
	// verilen noktaya en yakin noktaya g�re s�ralama yapar
	public void sort(Nokta n) {
		sortedList.add(n);
		unsortedList.remove(n);
		Nokta enYakin = null;
		double enYakinMesafe = 0;
		boolean ilkTur = true;
		/*
		 * 
		 * s�ralanmam�� noktalardan en yak�n� belirlemek i�in di�er noktalar d�n�l�yor
		 */
		for(Nokta n2 : unsortedList) {
						
			double mesafe = n.distance(n2);
			
			// alttaki if ilk d�ng�de mesafe ayarlamak i�in var
			if(ilkTur) {
				enYakinMesafe = mesafe;
				ilkTur = false;
			}
			
			if(mesafe <= enYakinMesafe) {
				enYakinMesafe = mesafe;
				
				enYakin = n2;
				
			}
		}
		if(enYakin != null) {
			System.out.print(enYakinMesafe + " - ");
			System.out.println(enYakin);
			/*
			 * bizim noktaya en yak�n nokta tespit edildikten sonra tespit
			 * edilen noktaya en yak�n nokta tespit edilmesi i�in bu
			 * fonksiyon �a�r�l�yor
			 */
			sort(enYakin);
		}
		
		
		
	}

}
