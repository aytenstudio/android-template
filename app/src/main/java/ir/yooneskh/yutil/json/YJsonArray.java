package ir.yooneskh.yutil.json;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by YoonesKh on 2016/11/15.
 */
public class YJsonArray {

    JSONArray _object;

    public YJsonArray(String data) {
        try {
            _object = new JSONArray(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public YJsonArray(JSONArray _object) {
        this._object = _object;
    }

    public int getLength() {
        return _object.length();
    }

    public int getInteger(int position) {
        try {
            return _object.getInt(position);
        } catch (JSONException e) {
            e.printStackTrace();
        } return -1;
    }

    public String getString(int position) {
        try {
            return _object.getString(position);
        } catch (JSONException e) {
            e.printStackTrace();
        } return null;
    }

    public double getDouble(int position) {
        try {
            return _object.getDouble(position);
        } catch (JSONException e) {
            e.printStackTrace();
        } return -1;
    }

    public YJsonObject getYJsonObject(int position) {
        try {
            return new YJsonObject(_object.getJSONObject(position));
        } catch (JSONException e) {
            e.printStackTrace();
        } return null;
    }

    public YJsonArray getYJsonArray(int position) {
        try {
            return new YJsonArray(_object.getJSONArray(position));
        } catch (JSONException e) {
            e.printStackTrace();
        } return null;
    }

}
