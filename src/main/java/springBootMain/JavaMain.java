package springBootMain;

import org.openqa.selenium.WebElement;

import selenium.HtmlAPI;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class JavaMain {


	public static void main(String[] args) {


		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar calendar = new GregorianCalendar(2013,0,31);
		calendar = Calendar.getInstance();
		Date date =  calendar.getTime();

		System.out.println("Server started: " +dateFormat.format(calendar.getTime()));

		HtmlAPI.changeUrl("https://github.com/ArminMa/SeleniumHeadlessWebScraper");

		WebElement webElement = HtmlAPI.getWebElementById(null,"js-repo-pjax-container");

		System.out.println(webElement.getText());

	}

}
