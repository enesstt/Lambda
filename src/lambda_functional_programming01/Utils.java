package lambda_functional_programming01;

public class Utils {


    public static void ayniSatirdaBosluklaYazdir(Object obj) {
        System.out.print(obj + " ");
    }


    public static boolean ciftElemaniSec(Integer x) {
        return x % 2 == 0;
    }

    public static boolean tekElemaniSec(Integer x) {
        return x % 2 != 0;
    }

    public static int karesiniAl(Integer x) {
        return x * x;
    }

    public static int kupunuAl(Integer x) {
        return x * x * x;
    }

    public static double ikiyeBol(Integer x) {

        return (x/2.0);
    }

    public static char sonKarakteriAl(String str) {
        return  str.charAt(str.length()-1);
    }


    public static char ilkKarakteriAl(String str) {
        return str.charAt(0);
    }

    public static int rakamlarToplaminiAl(int x){

        int toplam=0;
        while(x>0){
            toplam += x%10;

            x /= 10;
        }
        return toplam;
    }
}
