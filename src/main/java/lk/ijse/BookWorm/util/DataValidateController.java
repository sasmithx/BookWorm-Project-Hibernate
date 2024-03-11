package lk.ijse.BookWorm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidateController {
    public static boolean userIdValidate(String userId){
        String userRegex = "^(U)[0-9]{3}$";
        Pattern pattern = Pattern.compile(userRegex);
        Matcher matcher = pattern.matcher(userId);
        return matcher.matches();
    }

    public static boolean userNameValidate(String userName){
        String userRegex = "^[A-z\\s]{4,15}$";
        Pattern pattern = Pattern.compile(userRegex);
        Matcher matcher = pattern.matcher(userName);
        return matcher.matches();
    }
}
