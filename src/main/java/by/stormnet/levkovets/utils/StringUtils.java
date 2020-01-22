package by.stormnet.levkovets.utils;

public class StringUtils {

    private StringUtils() {
        throw new AssertionError("Class contains static methods only. You should not instantiate it!");
    }

    public static boolean isEmpty(String str) {
        if (str != null) {
            return str.equals("");
        }

        return true;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isBlank(String str) {
        if (isNotEmpty(str)) {
            return str.trim().equals("");
        }

        return true;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }


    public static String simplePhoneNumber(String str) {
        String number = str.replaceAll("\\D+", "");
        int num = number.length();
        if (num <= 9){
            return number;
        }
        String substring = number.substring(num - 9, num);
        return substring;
    }

    public static String simpleDiameterSize(String diameter){
        if (diameter.length() > 3){
            return diameter.substring(0, 3);
        }
        return diameter;
    }


}
