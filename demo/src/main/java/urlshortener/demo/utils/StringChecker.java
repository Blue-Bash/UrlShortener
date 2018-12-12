package urlshortener.demo.utils;

public class StringChecker {

    public static int checkString2Int(String string){

        if (string == null){
            return 500;
        }

        int num = Integer.parseInt(string);

        if(num < 30){
            return 500;
        }

        if(num > 2048){
            return 500;
        }

        return num;
    }

    private StringChecker() {
        //Let's hide the implicit public constructor as this class should never be instantiated.
    }
}