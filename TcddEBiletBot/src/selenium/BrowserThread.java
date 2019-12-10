package selenium;

import java.io.FileNotFoundException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import console.MusicPlayer;
import gui.mainviewcomponent.MainPageController;
import javafx.application.Platform;
import javazoom.jl.decoder.JavaLayerException;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserThread implements Runnable {

	private String tcddPage = "https://ebilet.tcddtasimacilik.gov.tr/view/eybis/tnmGenel/tcddWebContent.jsf";

	WebDriver browser;
	JavascriptExecutor jsExec;

	WebElement nereden;
	WebElement nereye;
	WebElement tarih;
	WebElement yolcuSayisi;
	WebElement btnAra;

	String kalkisYeri;
	String varisYeri;
	int kisiSayisi;
	String yolculukTarihi;

	MainPageController cont;

	private int sure = 2; // karde� 2 sn de bir yeniler

	public BrowserThread(String kalkisYeri, String varisYeri, int kisiSayisi, String yolculukTarihi,
			MainPageController cont) {
		super();
		this.kalkisYeri = kalkisYeri;
		this.varisYeri = varisYeri;
		this.kisiSayisi = kisiSayisi;
		this.yolculukTarihi = yolculukTarihi;
		this.cont = cont;
	}

	@Override
	public void run() {
		setProperties();

		try {
			browser = new ChromeDriver();
		}
		catch (Exception e){
			if(browser != null){
				browser.close();
			}

		}


		jsExec = (JavascriptExecutor) browser;

		// Firefox's proxy driver executable is in a folder already
		// on the host system's PATH environment variable.
		browser.get(tcddPage);
//	    browser.get("https://facebook.com");
		

		// *[@id="btnSeferSorgula"]

		// a�a��daki sayfalar anasayfan�n farkl� kombinasyonlar�

		String pageUrl1 = "https://ebilet.tcddtasimacilik.gov.tr/view/eybis/tnmGenel/tcddWebContent.jsf";
		String pageUrl2 = "https://ebilet.tcddtasimacilik.gov.tr/view/eybis/tnmGenel/tcddWebContent.jsf?expired=true";

		String page = browser.getCurrentUrl();
		while (page.equals(pageUrl1) || page.equals(pageUrl2)) {

			try {

				// a�a��daki metot sayfan�n i�indeki istenen �nputlar� set eder
				setInputAdress();

				// a�a��daki metottda yaz�lacaklar� yazar

				sendInputsValues();

			} catch (Exception e) {
				System.err.println(e.getStackTrace());
				System.out.println("yap�lamad�");

			}
			page = browser.getCurrentUrl();
			System.out.println(page);
			try {
				Thread.sleep(sure * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if (!cont.getIsPlayProperty().getValue()) {
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					cont.getIsPlayProperty().set(true);

				}
			});

		}

//	    browser.close();

	}

	private void sendInputsValues() {
		nereden.clear();
		nereden.sendKeys(kalkisYeri);
		nereye.clear();
		nereye.sendKeys(varisYeri);
		tarih.clear();
		tarih.sendKeys(yolculukTarihi);
		jsExec.executeScript("arguments[0].value='" + kisiSayisi + "';", yolcuSayisi);
		jsExec.executeScript("arguments[0].click();", btnAra);

	}

	private void setInputAdress() {
		nereden = browser.findElement(By.xpath("//*[@id=\"nereden\"]"));
		nereye = browser.findElement(By.xpath("//*[@id=\"nereye\"]"));
		tarih = browser.findElement(By.xpath("//*[@id=\"trCalGid_input\"]"));
		yolcuSayisi = browser.findElement(By.xpath("//*[@id=\"syolcuSayisi_input\"]"));
		btnAra = browser.findElement(By.xpath("//*[@id=\"btnSeferSorgula\"]"));

	}

	private void setProperties() {
		System.setProperty("webdriver.chrome.driver", "libs//selenium//chromedriver.exe");

	}

}
