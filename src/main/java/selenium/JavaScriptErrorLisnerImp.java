package selenium;

import com.gargoylesoftware.htmlunit.InteractivePage;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;

import java.net.MalformedURLException;
import java.net.URL;

public class JavaScriptErrorLisnerImp implements JavaScriptErrorListener {
	@Override
	public void scriptException(InteractivePage page, ScriptException scriptException) {
//		System.out.println("JavaScriptErrorLisnerImp > scriptException > turn me of: scriptException: " +page.asText() + Util.nLin +"scriptException: " + scriptException.getMessage());
	}
	@Override
	public void timeoutError(InteractivePage page, long allowedTime, long executionTime) {
//		page.getHtmlPageOrNull().getBody();
//		System.out.println("JavaScriptErrorLisnerImp > timeoutError > turn me of: timeoutError: " +page.asText() );
	}

	@Override
	public void malformedScriptURL(InteractivePage page, String url, MalformedURLException malformedURLException) {
		System.out.println("JavaScriptErrorLisnerImp > malformedScriptURL > turn me of: scriptException: "
				+page.asText() + System.lineSeparator() +"malformedURLException: " +
				malformedURLException.getMessage());
	}

	@Override
	public void loadScriptError(InteractivePage page, URL scriptUrl, Exception exception) {
//		System.out.println("JavaScriptErrorLisnerImp > loadScriptError > turn me of: scriptException: " +page.asText() +
//				Util.nLin +"scriptUrl: " + scriptUrl.getPath() +
//				Util.nLin + "exception: " + exception.getMessage());
	}
}
