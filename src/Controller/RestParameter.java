package Controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

public class RestParameter { 
	//@SuppressWarnings("unchecked")
    public static void parseQuery(String query, Map<String, Object> parameters) throws UnsupportedEncodingException { 
        if (query != null) { 
            String pairs[] = query.split("[&]"); 
            for (String pair : pairs) { 
                String param[] = pair.split("[=]"); 
                String key = null; String value = null; 
                if (param.length > 0) { 
                    key = URLDecoder.decode(param[0], System.getProperty("file.encoding")); 
                } 
                if (param.length > 1) { 
                    value = URLDecoder.decode(param[1], System.getProperty("file.encoding")); 
                } 
                if (parameters.containsKey(key)) { 
                    Object obj = parameters.get(key); 
                    if (obj instanceof List<?>) { 
                        //List<String> values = (List<String>) obj; 
						List<String> values = castList(obj, String.class); 
						values.add(value); 
                    } 
                    else if (obj instanceof String) { 
                        List<String> values = new ArrayList<String>(); 
                        values.add((String) obj); 
						values.add(value); 
                        parameters.put(key, values);
                    }   
                } 
                else { parameters.put(key, value); } 
            } 
        } 
    }

	public static <T> List<T> castList(Object obj, Class<T> clazz){
		List<T> result = new ArrayList<T>();
		if(obj instanceof List<?>){
			for (Object o : (List<?>) obj){
				result.add(clazz.cast(o));
			}
			return result;
		}
		return null;
}
}