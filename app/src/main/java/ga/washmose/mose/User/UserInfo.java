package ga.washmose.mose.User;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by leejaebeom on 2016. 10. 9..
 */

public class UserInfo implements Serializable{
    public static String PREF_USER = "user";
    public static String PREF_SUB_USER_APIKEY = "api_key";
    public static int TYPE_FACEBOOK = 1;
    public static int TYPE_KAKAO = 2;

    public static int userID;
    public static String openID;
    public static int loginType;
    public static int user_level;
    public static String apiKey;
    public static String name;
    public static String profileURL;
    public static String phone;
    public static String address;

    public static ArrayList<Integer> orders = new ArrayList<>();
    public static boolean isSeller = false;
}
