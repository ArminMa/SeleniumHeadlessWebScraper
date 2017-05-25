package selenium;



import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ConfirmHandler;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.WebWindowEvent;
import com.gargoylesoftware.htmlunit.WebWindowListener;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;


public class HtmlUnitDownloadFile {

	protected static WebDriver driver;
	protected static String filePath;
	protected static String filename;

	public HtmlUnitDownloadFile() {
		driver = new CustomHtmlUnitDriver();
		((HtmlUnitDriver) driver).setJavascriptEnabled(true);
	}

	boolean changeUrl(String url){
		if(url != null && !url.isEmpty()) {
			String currentUrl = getCurrentUrl();

			try{
				driver.get(  url);
			}catch (WebDriverException w){
				System.out.println("HtmlUnitDownloadFile.could not navigate to page: " + url);
			}

			String newUrl = getCurrentUrl();
			if(currentUrl != null && newUrl != null) {
				return !currentUrl.equals(newUrl);
			}
			else if(currentUrl == null && newUrl != null){
				return true;
			}
		}
		return false;
	}

	synchronized String getCurrentUrl() {
		String urlString = null;
		try{
			urlString = driver.getCurrentUrl();
		}catch (NoSuchElementException | NullPointerException n){
			return null;
		}
		return urlString;
	}

	public boolean downloadAFile(String currentUrl, String filePathh, String filenamee) {
		if(currentUrl != null && !currentUrl.isEmpty()
				&&filePathh != null && !filePathh.isEmpty()
				&&filenamee != null && !filenamee.isEmpty()){
			changeUrl(currentUrl);
		}else{
			System.out.println("1.downloadAFile");
			return false;
		}
		filePath = filePathh;
		filename = filenamee;
//		List<WebElement> elements = driver.findElements(By.id("aligner"));
//		if(!elements.isEmpty()){
//			elements = driver.findElements(By.id("buttons"));
//			if(!elements.isEmpty()){
//				elements = elements.get(0).findElements(By.id("download"));
//				if(!elements.isEmpty()){
//					elements = elements.get(0).findElements(By.id("icon"));
//					if(!elements.isEmpty()){
//						elements = elements.get(0).findElements(By.id("icon"));
//						if(!elements.isEmpty()){
//							elements.get(0).click();
//							System.out.println("click on the page");
//						}else{
//							System.out.println("2.downloadAFile");
//							return false;
//						}
//					}else{
//						System.out.println("3.downloadAFile");
//						return false;
//					}
//				}else{
//					System.out.println("4.downloadAFile");
//					return false;
//				}
//			}else{
//				System.out.println("5.downloadAFile");
//				return false;
//			}
//		}else{
//			System.out.println("6.downloadAFile");
//			return false;
//		}

		return true;
	}

	public class CustomHtmlUnitDriver extends HtmlUnitDriver {
		public CustomHtmlUnitDriver() {
			super(BrowserVersion.CHROME, true);
			this.getWebClient().setCssErrorHandler(new SilentCssErrorHandler());
			this.getWebClient().setJavaScriptErrorListener(new JavaScriptErrorLisnerImp());
			this.getWebClient().setCookieManager(new CookieManager());
			java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
			java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
			LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
		}


		// This is the magic. Keep a reference to the client instance
		protected WebClient modifyWebClient(WebClient client) {
			ConfirmHandler okHandler = new ConfirmHandler(){
				@Override
				public boolean handleConfirm(Page page, String message) {
					return true;
				}
			};
			client.setConfirmHandler(okHandler);

			client.addWebWindowListener(new WebWindowListener() {

				@Override
				public void webWindowOpened(WebWindowEvent event) {
					// TODO Auto-generated method stub
				}

				@Override
				public void webWindowContentChanged(WebWindowEvent event) {
					WebResponse response = event.getWebWindow().getEnclosedPage().getWebResponse();
//					System.out.println(response.getLoadTime());
//					System.out.println(response.getStatusCode());
//					System.out.println(response.getContentType());

					List<NameValuePair> headers = response.getResponseHeaders();
//					for(NameValuePair header: headers){
//						System.out.println(header.getName() + " : " + header.getValue());
//					}

					// Change or add conditions for content-types that you would to like
					// receive like a file.
					//https://stackoverflow.com/questions/12997871/download-file-using-htmlunit/25369473#25369473
					// Eduardo Fabricio
					if(response.getContentType().toLowerCase().equals("application/pdf")){
						getFileResponse(response, filePath+ filename);
					}else if(response.getContentType().toLowerCase().equals("application/x-pdf")){
						getFileResponse(response, filePath+ filename);
					}else if(response.getContentType().toLowerCase().equals("application/x-javascript")){
						getFileResponse(response, filePath+ filename);
						System.out.println("HtmlUnitDownloadFile.modifyWebClient.webWindowContentChanged url: " + driver.getCurrentUrl());
						System.out.println(response.getLoadTime());
						System.out.println(response.getStatusCode());
						System.out.println(response.getContentType());
					}
				}
				public void webWindowClosed(WebWindowEvent event) {
				}
			});

//			client.setJavaScriptErrorListener(new JavaScriptErrorListener() {
//
//				@Override
//				public void scriptException(InteractivePage page, ScriptException scriptException) {
//
//				}
//
//				@Override
//				public void timeoutError(InteractivePage page, long allowedTime, long executionTime) {
//
//				}
//
//				@Override
//				public void malformedScriptURL(InteractivePage page, String url, MalformedURLException malformedURLException) {
//
//				}
//
//				@Override
//				public void loadScriptError(InteractivePage page, URL scriptUrl, Exception exception) {
//
//				}
//
//			});


			return client;
		}


	}

	public static void getFileResponse(WebResponse response, String fileName){

		InputStream inputStream = null;

		// write the inputStream to a FileOutputStream
		OutputStream outputStream = null;

		try {
			inputStream = response.getContentAsStream();
			outputStream = new FileOutputStream(new File(fileName));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {outputStream.write(bytes, 0, read);}
//			System.out.println("Done!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					// outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

	}

}