package supoj.th.ac.ms.supojreadcode.utility;

/**
 * Created by sombut on 22/3/2561.
 */

public class MyConstant {

    // About URL
    private String urlGetAllUserString = "http://androidthai.in.th/mar/getAllUser.php";
    private String urlPostString = "http://androidthai.in.th/mar/postUser.php";

    // About Arry
    private String[] loginstring = new String[]{"id","Name","User","Password"};


    public String[] getLoginstring() {


        return loginstring;
    }

    public String getUrlGetAllUserString() {
        return urlGetAllUserString;
    }

    public String getUrlPostString() {
        return urlPostString;
    }
}   //main class
