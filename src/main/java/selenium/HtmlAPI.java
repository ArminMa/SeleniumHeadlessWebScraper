package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public interface HtmlAPI {


//	public static Browser BROWSER = new Browser();

	WebElementHandler webElementHandler = new WebElementHandler();

//	public static void start(){}

	static void changeWebdriver(){
		webElementHandler.changeWebdriver();
	}

	static List<WebElement> getWebElementsByClasse(String url, String claSsName) {
		return WebElementHandler.getWebElementsByClasse(url, claSsName);
	}
	static WebElement getWebElementByClasse(String url, String claSsName) {
		return WebElementHandler.getWebElementByClasse(url, claSsName);
	}
	static WebElement getWebElementByCss(String url, String cssId){
		return WebElementHandler.getWebElementByCss(url, cssId);
	}
	static List<WebElement> getWebElementsByCss(String url, String cssId) {
		return WebElementHandler.getWebElementsByCss(url, cssId);
	}
	static WebElement getWebElementById(String url, String id){
		return WebElementHandler.getWebElementById(url, id);
	}
	static List<WebElement> getWebElementsById(String url, String id) {
		return WebElementHandler.getWebElementsById(url, id);
	}

	static boolean pressNextButton(String url, String css){
		return WebElementHandler.pressNextButton(url, css);
	}

	static WebDriver getWebDriver(){
		return WebElementHandler.getWebDriver();
	}

	static boolean downloadFile(String url, String filePath, String filename) {
		return WebElementHandler.downloadFile(url, filePath, filename);
	}

	static String getCurrentUrl() {
		return WebElementHandler.getCurrentUrl();
	}
	static boolean changeUrl(String url) {
		return WebElementHandler.changeUrl( url);
	}



}
