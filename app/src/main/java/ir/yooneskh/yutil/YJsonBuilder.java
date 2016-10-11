package ir.yooneskh.yutil;


import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yoones on 11/18/2015.
 */
public class YJsonBuilder {

    Map<String, Object> args;

    public YJsonBuilder() {
        args = new HashMap<>();
    }

    public YJsonBuilder put(String name, Object data) {
        args.put(name, data);
        return this;
    }

    public YJsonBuilder put(String name, Character data) {
        args.put(name, data);
        return this;
    }

    public YJsonBuilder put(String name, Number data) {
        args.put(name, data);
        return this;
    }

    public YJsonBuilder put(String name, String data) {
        args.put(name, data);
        return this;
    }

    public YJsonBuilder put(String name, Boolean data) {
        args.put(name, data);
        return this;
    }

    public JsonObject buildJsonObject() {

        JsonObject result = new JsonObject();

        for (Map.Entry<String, Object> entry : args.entrySet()) {

            Object value = entry.getValue();

            if (value instanceof Character) result.addProperty(entry.getKey(), (Character)value);
            if (value instanceof Number) result.addProperty(entry.getKey(), (Number)value);
            if (value instanceof String) result.addProperty(entry.getKey(), (String)value);
            if (value instanceof Boolean) result.addProperty(entry.getKey(), (Boolean)value);

        }

        return result;

    }

    public JSONObject buildJSONObject() {

        JSONObject result = new JSONObject();

        for (Map.Entry<String, Object> entry : args.entrySet()) {
            try {
                result.put(entry.getKey(), entry.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return result;

    }

    public static JSONObject instantJSONObject(Object... args) {

        YJsonBuilder builder = new YJsonBuilder();

        int count = 0;
        String key = "";

        for (Object arg : args) {
            if ( ((count++) % 2) == 0) {
                key = (String)arg;
            }
            else {
                builder.put(key, arg);
            }
        }

        return builder.buildJSONObject();

    }

    public static JsonObject instantJsonObject(Object... args) {

        YJsonBuilder builder = new YJsonBuilder();

        int count = 0;
        String key = "";

        for (Object arg : args) {
            if ( ((count++) % 2) == 0) {
                key = (String)arg;
            }
            else {
                builder.put(key, arg);
            }
        }

        return builder.buildJsonObject();

    }

}
