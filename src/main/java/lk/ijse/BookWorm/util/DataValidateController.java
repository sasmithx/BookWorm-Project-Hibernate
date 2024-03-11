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
}
