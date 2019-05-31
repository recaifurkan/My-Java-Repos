package console;
/*
 * 
 * author Recai Furkn Bostanc�
 * Tcdd Kars bilet kesici_v.0.1.1
 * v_0.1.2 de m�zik ile uyar� eklendi 
 * 
 * 
 * 
 */

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;

import javazoom.jl.decoder.JavaLayerException;

/**
 *
 * @author ByRfb
 */
public class Main {

	/**
	 *
	 * @param args
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 * @throws JavaLayerException
	 */
	public static void main(String[] args) throws InterruptedException, FileNotFoundException, JavaLayerException {

		MusicPlayer music = new MusicPlayer();

		System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY, "libs//selenium//geckodriver.exe");
		System.setProperty("webdriver.firefox.bin", "E:\\Program Files\\Mozilla Firefox\\firefox.exe");

		WebDriver browser = new FirefoxDriver();
		JavascriptExecutor jsExec = (JavascriptExecutor) browser;
		Scanner sc = new Scanner(System.in);
		System.out.println("Moruk ka� ki�i istiyon?\n>");
		int kisiSayisi = sc.nextInt();
		int konum = 0;
		while (!(konum == 1 || konum == 2)) {
			System.out.println("Nereden kalkar? Ankara gar i�in 1'e bas Eryaman i�in 2'ye bas\n>");
			konum = sc.nextInt();

		}

		String kalkisYeri = null;

		switch (konum) {
		case 1:
			kalkisYeri = "Ankara Gar";
			break;
		case 2:
			kalkisYeri = "ERYAMAN YHT";
			break;
		}
		System.out.println("Karde� ka� saniyede bi fi�ekliyam\n>");
		int sure = sc.nextInt();

		// Firefox's proxy driver executable is in a folder already
		// on the host system's PATH environment variable.
		browser.get("https://ebilet.tcddtasimacilik.gov.tr/view/eybis/tnmGenel/tcddWebContent.jsf");
//		    browser.get("https://facebook.com");

		WebElement nereden = browser.findElement(By.xpath("//*[@id=\"nereden\"]"));
		WebElement nereye = browser.findElement(By.xpath("//*[@id=\"nereye\"]"));
		WebElement tarih = browser.findElement(By.xpath("//*[@id=\"trCalGid_input\"]"));
		WebElement yolcuSayisi = browser.findElement(By.xpath("//*[@id=\"syolcuSayisi_input\"]"));
		WebElement btnAra = browser.findElement(By.xpath("//*[@id=\"btnSeferSorgula\"]"));

		// *[@id="btnSeferSorgula"]

		String pageUrl1 = "https://ebilet.tcddtasimacilik.gov.tr/view/eybis/tnmGenel/tcddWebContent.jsf";
		String pageUrl2 = "https://ebilet.tcddtasimacilik.gov.tr/view/eybis/tnmGenel/tcddWebContent.jsf?expired=true";

		String page = browser.getCurrentUrl();
		while (page.equals(pageUrl1) || page.equals(pageUrl2)) {

			try {
				nereden = browser.findElement(By.xpath("//*[@id=\"nereden\"]"));
				nereye = browser.findElement(By.xpath("//*[@id=\"nereye\"]"));
				tarih = browser.findElement(By.xpath("//*[@id=\"trCalGid_input\"]"));
				yolcuSayisi = browser.findElement(By.xpath("//*[@id=\"syolcuSayisi_input\"]"));
				btnAra = browser.findElement(By.xpath("//*[@id=\"btnSeferSorgula\"]"));

				nereden.clear();
				nereden.sendKeys(kalkisYeri);
				nereye.clear();
				nereye.sendKeys("Kars");
				tarih.clear();
				tarih.sendKeys("21.02.2019");
				jsExec.executeScript("arguments[0].value='" + kisiSayisi + "';", yolcuSayisi);
				jsExec.executeScript("arguments[0].click();", btnAra);

			} catch (Exception e) {
				System.err.println(e.getStackTrace());
				System.out.println("yap�lamad�");

			}
			page = browser.getCurrentUrl();
			System.out.println(page);
			Thread.sleep(sure * 1000);

		}

//		    browser.close();

	}

}
