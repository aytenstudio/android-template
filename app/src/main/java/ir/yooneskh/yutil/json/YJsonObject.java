package ir.yooneskh.yutil.json;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by YoonesKh on 2016/11/15.
 */
public class YJsonObject {

    JSONObject _object;

    public YJsonObject(String data) {
        try {
            _object = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public YJsonObject(JSONObject data) {
        _object = data;
    }

    public boolean getBoolean(String key) {
        try {
            return _object.getBoolean(key);
        } catch (JSONException e) {
            e.printStackTrace();
        } return false;
    }

    public int getInteger(String key) {
        try {
            return _object.getInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
        } return -1;
    }

    public double getDouble(String key) {
        try {
            return _object.getDouble(key);
        } catch (JSONException e) {
            e.printStackTrace();
        } return -1;
    }

    public String getString(String key) {
        try {
            return _object.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        } return null;
    }

    public Object get(String key) {
        try {
            return _object.get(key);
        } catch (JSONException e) {
            e.printStackTrace();
        } return null;
    }

    public YJsonObject getYJsonObject(String key) {
        try {
            return new YJsonObject(_object.getJSONObject(key));
        } catch (JSONException e) {
            e.printStackTrace();
        } return null;
    }

    public YJsonArray getYJsonArray(String key) {
        try {
            return new YJsonArray(_object.getJSONArray(key));
        } catch (JSONException e) {
            e.printStackTrace();
        } return null;
    }

}
