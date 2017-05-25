package selenium;



import com.gargoylesoftware.htmlunit.BrowserVersion;

import com.gargoylesoftware.htmlunit.Cache;
import com.gargoylesoftware.htmlunit.ConfirmHandler;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.logging.Level;

abstract class  HeadlessHtmlDriver {

	public static HtmlUnitDriver webDriver = new SilentHtmlUnitDriver(BrowserVersion.EDGE);

	public static int browserIndex = 0;

	public static class SilentHtmlUnitDriver extends HtmlUnitDriver {


		SilentHtmlUnitDriver(BrowserVersion browserVersion) {
			super(browserVersion, true);
			this.getWebClient().setCssErrorHandler(new SilentCssErrorHandler());
			this.getWebClient().setJavaScriptErrorListener(new JavaScriptErrorLisnerImp());
			this.getWebClient().waitForBackgroundJavaScript(1000);
			this.getWebClient().waitForBackgroundJavaScriptStartingBefore(500);
			this.getWebClient().setJavaScriptTimeout(3000);

			CookieManager cookieManager = new CookieManager();
			cookieManager.setCookiesEnabled(true);
			this.getWebClient().getOptions().setMaxInMemory(1512000);
			this.getWebClient().getOptions().setThrowExceptionOnScriptError(false);
			this.getWebClient().getOptions().setThrowExceptionOnFailingStatusCode(true);
			this.getWebClient().getOptions().setDoNotTrackEnabled(true);
			this.getWebClient().getOptions().setCssEnabled(true);
			this.getWebClient().setCookieManager(cookieManager);
			Cache cache = new Cache();
			cache.setMaxSize(10000);
			this.getWebClient().setCache(cache);
			manage().deleteAllCookies();
			java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
			modifyWebClient(super.getWebClient());
			manage().window().setSize(new Dimension(1124, 850));

		}


		@Override
		protected WebClient modifyWebClient(WebClient client) {

			WebClient modifiedClient = super.modifyWebClient(client);
			modifiedClient.getOptions().setThrowExceptionOnScriptError(false); // see here
			ConfirmHandler okHandler = (page, message) -> true;
			modifiedClient.setConfirmHandler(okHandler);
			modifiedClient.getCache().setMaxSize(0);
			return modifiedClient;
		}
	}


	void changeWebdriver(){
		if(browserIndex == 0) {
			webDriver =  new SilentHtmlUnitDriver(BrowserVersion.CHROME);
			browserIndex++;
		}
		else if(browserIndex == 1) {
			webDriver =  new SilentHtmlUnitDriver(BrowserVersion.FIREFOX_45);
			browserIndex++;
		}
		else if(browserIndex == 2) {
			webDriver =  new SilentHtmlUnitDriver(BrowserVersion.EDGE);
			browserIndex = 0;
		}
	}

}