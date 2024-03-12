package lk.ijse.BookWorm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidateController {

    /////////////////////////////// USER//////////////////////////////////////////////////
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

    public static boolean userPasswordValidate(String password){
        String userRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$";
        Pattern pattern = Pattern.compile(userRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean userMobileValidate(String userMobile){
        String userRegex = "^(?:7|0|(?:\\\\\\\\+94))[0-9]{9,10}$";
        Pattern pattern = Pattern.compile(userRegex);
        Matcher matcher = pattern.matcher(userMobile);
        return matcher.matches();
    }

    public static boolean emailCheck(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean addressValidate(String address) {
        String userRegex = "[A-z @ 0-9]{4,20}";
        Pattern pattern = Pattern.compile(userRegex);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }



    //////////////////////////////// BOOK ////////////////////////////////////////////////

    public static boolean bookIdValidate(String bookId){
        String bookRegex = "^(B)[0-9]{3}$";
        Pattern pattern = Pattern.compile(bookRegex);
        Matcher matcher = pattern.matcher(bookId);
        return matcher.matches();
    }

    public static boolean bookNameValidate(String bookName){
        String bookRegex = "^[A-z\\s]{4,15}$";
        Pattern pattern = Pattern.compile(bookRegex);
        Matcher matcher = pattern.matcher(bookName);
        return matcher.matches();
    }

    public static boolean authorNameValidate(String authorName){
        String bookRegex = "^[A-z\\s]{4,15}$";
        Pattern pattern = Pattern.compile(bookRegex);
        Matcher matcher = pattern.matcher(authorName);
        return matcher.matches();
    }

    public static boolean genreValidate(String genre){
        String bookRegex = "^[A-z\\s]{4,15}$";
        Pattern pattern = Pattern.compile(bookRegex);
        Matcher matcher = pattern.matcher(genre);
        return matcher.matches();
    }

    public static boolean qtyValidate(String qty){
        String bookRegex = "^[1-9][0-9]*$";
        Pattern pattern = Pattern.compile(bookRegex);
        Matcher matcher = pattern.matcher(qty);
        return matcher.matches();
    }

    //////////////////////////////// BRANCH ////////////////////////////////////////////////


}
