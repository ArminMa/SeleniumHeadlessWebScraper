package springBootMain;

import org.openqa.selenium.WebElement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import selenium.HtmlAPI;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@SpringBootApplication
public class SpringBootSeleniumMain {


	public static void main(String[] args) {

		SpringApplication.run(SpringBootSeleniumMain.class, args);


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
