package ir.aytensoft.androidtemplate.model;

import java.util.HashSet;
import java.util.Set;

import ir.yooneskh.yutil.json.YJsonArray;
import ir.yooneskh.yutil.json.YJsonObject;

/**
 * Created by YoonesKh on 2016/11/11.
 */
public class User {

    private int id = -1;
    private String fname = "-";
    private String lname = "-";
    private String email = "-";
    private String picturePath = "-";
    private String coverPhotoPath = "-";
    private String createdAt = "-";

    public User() { }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getName() {
        return getFname() + " " + getLname();
    }

    public String getEmail() {
        return email;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public String getCoverPhotoPath() {
        return coverPhotoPath;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public static User fromJson(YJsonObject jsonObject) {

        User user = new User();

        user.id = jsonObject.getInteger("id");
        user.fname = jsonObject.getString("fname");
        user.lname = jsonObject.getString("lname");
        user.email = jsonObject.getString("email");
        user.picturePath = jsonObject.getString("picture");
        user.coverPhotoPath = jsonObject.getString("cover_photo");
        user.createdAt = jsonObject.getString("created_at");

        return user;

    }

}
