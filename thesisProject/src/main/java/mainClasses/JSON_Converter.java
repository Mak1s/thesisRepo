/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainClasses;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gerry
 */
public class JSON_Converter {
    
    public String getJSONFromAjax(BufferedReader reader) throws IOException{
	StringBuilder buffer = new StringBuilder();
	String line;
	while ((line = reader.readLine()) != null) {
		buffer.append(line);
	}
	String data = buffer.toString();
	return data;
    }
    public String convertToJSON(Collection<String> classes, Collection<String> properties) {
        Map<String, Collection<String>> jsonMap = new HashMap<>();
        jsonMap.put("classes", classes);
        jsonMap.put("properties", properties);
        Gson gson = new Gson();
        return gson.toJson(jsonMap);
    }
    
    
}
