package Controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class AdminControllerDispatchHandler implements HttpHandler{
	
	UserControl userControlInstance;
	VideoControl videoControlInstance;
	
	@Override
	public void handle(HttpExchange exchangeResponse) throws IOException{

		String requestMethod = exchangeResponse.getRequestMethod();
		

		
		userControlInstance = new UserControl();
		videoControlInstance = new VideoControl();
		if(requestMethod.equalsIgnoreCase("GET")){

			Headers responseHeaders = exchangeResponse.getResponseHeaders();

			responseHeaders.set("Content-Type", "application/json");

			exchangeResponse.sendResponseHeaders(200, 0);
			

			OutputStream responseBody = exchangeResponse.getResponseBody();
			

			Map<String, Object> parametersMap = new HashMap<String, Object>();

			URI requestedUri = exchangeResponse.getRequestURI();

			String query = requestedUri.getRawQuery();

            RestParameter.parseQuery(query, parametersMap);
			
			String response = "";
			if(parametersMap.get("Method").equals("Login")){

				String userName = (String)parametersMap.get("userName");
				String password = (String)parametersMap.get("password");
				

				response = userControlInstance.login(userName, password);
			}
			else if(parametersMap.get("Method").equals("Register")){

				String userName = (String)parametersMap.get("userName");
				String password = (String)parametersMap.get("password");
				String email = (String)parametersMap.get("email");
				String age = (String)parametersMap.get("age");
				String sex = (String)parametersMap.get("sex");
				

				response = userControlInstance.register(userName, password, email, age, sex);
			}
			else if(parametersMap.get("Method").equals("PlayVideo")){

				String videoID = (String)parametersMap.get("videoID");
				

				response = videoControlInstance.playVideo(videoID);
			}
			

			responseBody.write(response.getBytes());

			responseBody.close();
		}
	}
}