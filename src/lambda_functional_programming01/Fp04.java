package lambda_functional_programming01;

import java.util.stream.IntStream;

public class Fp04 {
    public static void main(String[] args) {

        System.out.println(get7den100eToplam());
        System.out.println("get7den100eToplam2" + get7den100eToplam2());
        System.out.println("get2den11eCarpim" + get2den11eCarpim());
        System.out.println(fakrtoriyelHesapla(5));
        System.out.println(verilenIkiSayiArasindakiSayilarinToplami(2, 5));
        System.out.println(ikiSayiArasindakiTumSayilarinRakamlariToplami(11,13));
    }

    /*
        1) 7'den 100'e kadar integer değerlerinin toplamını bulan bir method oluşturun.
        */
//1. Yol
    public static int get7den100eToplam() {
        return IntStream.range(7, 101).reduce(0, Math::addExact);

        //IntStream.range(7,101).reduce(Math::addExact).getAsInt()
    }

    // 2. Yol
    public static int get7den100eToplam2() {
        return IntStream.rangeClosed(7, 100).reduce(Math::addExact).getAsInt();

        //IntStream.range(7,101).reduce(Math::addExact).getAsInt()
    }

    //range yaptigimizda son rakdamdan bir fazlasini yazmamiz gerekiyor cunku yazdigimiz son degeri almadan calisiyor
//fakat rangeClosed kullandigimizda son rakami da dahil ediyor, o yuzden bir fazlasini yazmamiza gerek kalmiyor.

    //2'den 11'e kadar integer değerlerinin çarpımını bulan bir method oluşturun.
    public static int get2den11eCarpim() {
        return IntStream.rangeClosed(2, 11).reduce(Math::multiplyExact).getAsInt();
    }


    // 3) Verilen bir sayının faktoriyelini hesaplayan bir method oluşturun.
    // (5 factorial = 1*2*3*4*5  ==> 5! = 1*2*3*4*5)
    public static int fakrtoriyelHesapla(int x) {
        if (x > 0) {
            return IntStream.rangeClosed(1, x).reduce(Math::multiplyExact).getAsInt();
        }
        System.out.println("0'dan buyuk deger giriniz");
        return 0; // bir return çalışırsa ikincisi çalışmaz
    }

    /*
    4) Verilen iki sayı arasındaki çift sayıların toplamını bulan bir method oluşturun.
    */
    public static int verilenIkiSayiArasindakiSayilarinToplami(int x, int y) {

        if (y > x) {
            return IntStream.rangeClosed(x, y).filter(Utils::ciftElemaniSec).reduce(Math::addExact).getAsInt();
            // return IntStream.rangeClosed(x, y).filter(Utils::ciftElemaniSec).sum();
        } else {
            return IntStream.rangeClosed(y, x).filter(Utils::ciftElemaniSec).reduce(Math::addExact).getAsInt();

        }

    }


    /*
    5) Verilen iki sayı arasındaki tüm sayıların rakamlarının toplamını hesaplayan bir method oluşturun.
    //  23 and 32 ==> 2+3  +  2+4  +  2+5  +  2+6  +  2+7  +   2+8  +   2+9  +   3+0  +   3+1  +   3+2 ==> 68
     */
    public static int ikiSayiArasindakiTumSayilarinRakamlariToplami(int x, int y){
        return IntStream.rangeClosed(x,y).map(Utils::rakamlarToplaminiAl).sum();
    }

}
