package lambda_functional_programming01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Fp02 {
    /*
    1) t -> "Logic" , (t,u) -> "Logic"
        Bu yapıya "Lambda Expression"

     2) Functional Programming kapsamında "Lambda Expression" kullanılabilir ama önerilmez.
        "Lambda Expression" yerine "Method Reference" tercih edilir.

     3) "Method Reference" kullanımı "Class Name :: Method Name"

        Aynı zamanda kendi class'larımızı da kullanabiliriz.
        Örneğin bir Animal class'ımız var ve bu class "eat()" methoduna sahip ==> "Animal :: eat"

     */

    public static void main(String[] args) {
        List<Integer> liste = new ArrayList<>();
        liste.add(8);
        liste.add(9);
        liste.add(131);
        liste.add(10);
        liste.add(9);
        liste.add(10);
        liste.add(2);
        liste.add(8);
        System.out.println(liste);


        listElemanlariniYazdirFunctional(liste);
        System.out.println();
        ciftElemanlariYazdirFunctional(liste);
        System.out.println();
        tekElemanlarinKaresiniYazdirFunctional(liste);
        System.out.println();
        tekElemanlarinKupunuYazdirFunctional(liste);
        System.out.println();
        tekrarsizCiftElemanlarinKareToplami(liste);
        tekrarsizCiftElemanlarinKuplerininCarpimi(liste);
        getMaxEleman(liste);
        yedidenBuyukCiftMin(liste);
        getTersSiralamaylaTekrarsizElemanlarinYarisi(liste);
    }

    /*
    1) Ardışık list elemanlarını aynı satırda aralarında boşluk bırakarak
    yazdıran bir method oluşturun.(Functional ve method reference)
     */

    public static void listElemanlariniYazdirFunctional(List<Integer> list) {

        //list.stream().forEach( System.out::print); // aldıklarını yazdır diyor
        list.stream().forEach(Utils::ayniSatirdaBosluklaYazdir);

    }

    /*
    Ardışık çift list elementlerini aynı satırda aralarında boşluk bırakarak
    yazdıran bir method oluşturun.(Functional)
     */
    public static void ciftElemanlariYazdirFunctional(List<Integer> list) {

        list.stream().filter(Utils::ciftElemaniSec).forEach(Utils::ayniSatirdaBosluklaYazdir);

    }

     /*
    3) Ardışık tek list elemanlarının karelerini
    aynı satırda aralarında boşluk bırakarak yazdıran bir method oluşturun.(Functional)
     */

    public static void tekElemanlarinKaresiniYazdirFunctional(List<Integer> list) {

        list.stream().filter(Utils::tekElemaniSec).map(Utils::karesiniAl).
                forEach(Utils::ayniSatirdaBosluklaYazdir);
    }


    /*
     4) tek list elemanlarının küplerini tekrarsız olarak
       aynı satırda aralarında boşluk bırakarak yazdıran bir method oluşturun.
  */
    public static void tekElemanlarinKupunuYazdirFunctional(List<Integer> liste) {
        liste.stream().distinct().filter(Utils::tekElemaniSec).map(Utils::kupunuAl).
                forEach(Utils::ayniSatirdaBosluklaYazdir);

        // Elemanların değerleri değişecekse map() methodu kullanılır.
    }

    /*
    5) Tekrarsız çift elemanların karelerinin toplamını hesaplayan bir method oluşturun.
     */
    public static void tekrarsizCiftElemanlarinKareToplami(List<Integer> liste) {
        Integer toplam = liste.stream().distinct().filter(Utils::ciftElemaniSec).map(Utils::karesiniAl)
                .reduce(0, Math::addExact); // addExact toplamayı yapıyor.

        // Integer toplam = liste.stream().distinct().filter(Utils::ciftElemaniSec).map(Utils::karesiniAl)
        //        .reduce(Math::addExact).get(); //  get ile de sayıyı getiriyor. üstteki gibi başlangıç  değeri
        //list                                       vermeye ( 0 ) gerek yok .


        //  Integer toplam = liste.stream().distinct().filter(Utils::ciftElemaniSec).map(Utils::karesiniAl)
        //        .reduce(0,Integer::sum); // Integer class'ındaki sum() ile de yapılabilir.


        System.out.println(toplam);
    }

    /*
    6) Tekrarsız çift elemanların küpünün çarpımını hesaplayan bir method oluşturun.
     */
    public static void tekrarsizCiftElemanlarinKuplerininCarpimi(List<Integer> list) {

        Integer carpim = list.stream().distinct().filter(Utils::ciftElemaniSec).map(Utils::kupunuAl).
                reduce(1, Math::multiplyExact);

        System.out.println(carpim);
    }

    /*
        7) List elemanları arasından en büyük değeri bulan bir method oluşturun.
    */
    public static void getMaxEleman(List<Integer> list) {
        Integer max = list.stream().distinct().reduce(Math::max).get();
        System.out.println("max = " + max);
    }

    /*
Ödev
        8)List elemanları arasından en küçük değeri bulan bir method oluşturun.(2 Yol ile)
    */
    public static void enKucukEleman(List<Integer> list) {
        Integer min = list.stream().distinct().reduce(Math::min).get();
        System.out.println("min = " + min); //min = 2
    }


    /*
     9) List elemanları arasından 7'den büyük, çift, en küçük değeri bulan bir method oluşturun.
    */
    public static void yedidenBuyukCiftMin(List<Integer> list) {
        Integer yedidenBuyukCiftMin = list.stream().distinct().filter(t -> t > 7).filter(Utils::ciftElemaniSec).sorted().findFirst().get();
        // Integer yedidenBuyukCiftMin = list.stream().distinct().filter(t-> t > 7).filter(Utils::ciftElemaniSec).reduce(Math::min).get();
        System.out.println("yedidenBuyukCiftMin = " + yedidenBuyukCiftMin);
    }

    /*
  10) Ters sıralama ile tekrarsız ve 5'ten büyük elemanların
  yarı değerlerini(elamanın ikiye bölüm sonucunu) bulan  ve list'e atan bir method oluşturun.
   */
    public static void getTersSiralamaylaTekrarsizElemanlarinYarisi(List<Integer> liste) {

        List<Double> yarisiList = liste.stream().distinct().filter(t -> t > 5).map(Utils::ikiyeBol).
                sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        //List<Double> yarisiList = liste.stream().distinct().filter(t -> t > 5).map(Utils::ikiyeBol).
        //                sorted(Comparator.reverseOrder()).toList(); // collect kullanmadan
        //                toList() methodu kullanarak list hale getirebiliriz.

        System.out.println("yarisiList = " + yarisiList);
    }
}
