package util;

public class StringUtil {
    //判断是否为空
    public static boolean isEmpty(String string){
        if (string==null||"".equals(string.trim())){
            return true;
        }
        return false;
    }
}
