package utils;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.IOException;


public class JsonReader {
    public static JSONObject getJsonData() throws IOException, ParseException {
        //pass the path of the testdata.json file
        File filename = new File("resources//TestData//testdata.json");
        //convert json file into string
        String json = FileUtils.readFileToString(filename, "UTF-8");
        //parse the string into object
        Object obj = new JSONParser().parse(json);
        //give jsonobj so it return a fuction when called
        JSONObject jsonObject = (JSONObject) obj;

        return jsonObject;
    }

public static String getTestData(String key) throws IOException, ParseException{
        String testDataValue;
        return testDataValue = (String) getJsonData().get(key);
    }

    public static JSONArray getJsonArray(String key) throws IOException, ParseException{
        JSONObject jsonObject = getJsonData();
        JSONArray jsonArray = (JSONArray) jsonObject.get(key);
        return jsonArray;
    }
}