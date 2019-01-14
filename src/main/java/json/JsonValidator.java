package json;

import org.everit.json.schema.loader.SchemaLoader;
import org.everit.json.schema.Schema;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsonValidator {

    public List<String> getElements(String json, String element){
        List<String> list = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonElements = (JSONArray) jsonObject.get(element);

        for(int i = 0; i < jsonElements.length(); i++){
            list.add(jsonElements.getJSONObject(i).toString());
        }
        return list;
    }

    public String getElement(String json, String element, String field, String name){
        String jsonElement = "";
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonElements = (JSONArray) jsonObject.get(element);

        for(int i = 0; i < jsonElements.length(); i++){
            if(jsonElements.getJSONObject(i).getString(field).equals(name)) {
                jsonElement = jsonElements.getJSONObject(i).toString();
                break;
            }
        }
        return jsonElement;
    }

    public String getStringField(String json, String field){
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject.getString(field);
    }

    public Integer getIntegerField(String json, String field){
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject.getInt(field);
    }

    public Boolean validateJson(String json, String schemaname) {

        ClassLoader classLoader = getClass().getClassLoader();

        try {
            File file = new File(classLoader.getResource("jsonschemas/" + schemaname).getFile());
            InputStream inputStream = new FileInputStream(file);
            JSONObject jsonSchema = new JSONObject(inputStream);
            JSONObject jsonSubject = new JSONObject(json);

            Schema schema = SchemaLoader.load(jsonSchema);
            schema.validate(jsonSubject);
            return true;
        } catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}