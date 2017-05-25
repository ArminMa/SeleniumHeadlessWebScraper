package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class WebElementHandler extends HeadlessHtmlDriver {

	WebElementHandler() {}

	static HtmlUnitDriver getWebDriver() {
		return webDriver;
	}

	static List<WebElement> getWebElementsByClasse(String url, String className){
		List<WebElement> webElements = new LinkedList<>();
		if(url != null && changeUrl(url));
		try {
			webElements = webDriver.findElements(By.className(className));
		}catch (NoSuchElementException n){
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return webElements;
	}


	static WebElement getWebElementByClasse(String url, String className) {
		WebElement webElement = null;
		if(url != null && changeUrl(url));
		try {
			webElement = webDriver.findElement( By.className(className) );

		}catch (NoSuchElementException n){
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return webElement;
	}

	static WebElement getWebElementByCss(String url, String css){
		WebElement webElement = null;
		if(url != null && changeUrl(url));
		try {
			webElement = webDriver.findElement( By.cssSelector(css) );

		}catch (NoSuchElementException n){
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return webElement;
	}

	static List<WebElement> webElements = new ArrayList<>();
	static List<WebElement> getWebElementsByCss(String url, String cssId) {



		if(url != null && changeUrl(url));
		try {
			webElements = webDriver.findElements( By.cssSelector(cssId) );

		}catch (NoSuchElementException n){
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return webElements;
	}

	static WebElement getWebElementById(String url, String id) {
//		webDriver.manage().window().setSize(new Dimension(1124, 850));
		WebElement webElement = null;
		if(url != null && changeUrl(url));
		try {
			webElement = webDriver.findElement( By.id(id) );

		}catch (NoSuchElementException n){
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return webElement;
	}

	static List<WebElement> getWebElementsById(String url, String id) {
//		webDriver.manage().window().setSize(new Dimension(1124, 850));
		List<WebElement> webElements = null;

		if(url != null && changeUrl(url));
		try {
			webElements = webDriver.findElements( By.id(id) );

		}catch (NoSuchElementException n){
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return webElements;
	}

	static boolean pressNextButton(String url, String css) {
		WebElement webElement = null;

		if(url != null && changeUrl(url));
		try {
			webElement = webDriver.findElement( By.cssSelector(css) );
			String currentUrl = getCurrentUrl();
			webElement.click();
			String nextUrl = getCurrentUrl();
			if(currentUrl.equals(nextUrl)){
				return false;
			}else return true;
		}catch (NoSuchElementException n){
			return false;
		}catch (WebDriverException n){
			System.out.println("WebelementHandler.pressNextButton.WebDriverException: " + getCurrentUrl());
			return false;
		}
		catch (Exception e) {
//				e.printStackTrace();
			return false;
		}
	}
	private static String currentUrl;
	private static String newUrl;
	static boolean changeUrl(String url){

		if(url == null || url.isEmpty()){
			return false;
		}

		currentUrl = getCurrentUrl();

		try{
			webDriver.get(  url);
		}catch (WebDriverException w){
			newUrl = getCurrentUrl();
			if(currentUrl != null && newUrl != null) {
				return !currentUrl.equals(newUrl);
			}
			try {
				webDriver.navigate().to(url);
			}catch (Exception ww){
				System.out.println("2.could not navigate to page: " + url);
				ww.printStackTrace();
			}
		}

		newUrl = getCurrentUrl();
		if(currentUrl != null && newUrl != null) {
			return !currentUrl.equals(newUrl);
		}
		else if(currentUrl == null && newUrl != null){
			System.out.println("WebelementHandler.changeUrl new url was null?");
			return true;
		}

		return false;
	}


	private static String urlString;
	static String getCurrentUrl() {
		urlString = null;
		try{
			urlString = webDriver.getCurrentUrl();
		}catch (NoSuchElementException | NullPointerException n){
			return null;
		}
		return urlString;
	}

	//be sure to delete file after working with it. filePath ~ "test_", file extension ~ ".jpg", include the "."
	static boolean downloadFile(String url,String filePath, String filename ) {
//		String url = "http://www.fda.gov/downloads/Drugs/GuidanceComplianceRegulatoryInformation/EnforcementActivitiesbyFDA/WarningLettersandNoticeofViolationLetterstoPharmaceuticalCompanies/UCM166308.pdf";
//		String filename = "UCM166308.pdf";
//		String filePath = "D:\\FDA.warningLetters\\";
		HtmlUnitDownloadFile htmlpdfUnitPrinter = new HtmlUnitDownloadFile();
		return htmlpdfUnitPrinter.downloadAFile(url, filePath, filename);
	}



}
