package lambda_functional_programming01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Fp01 {

    /*
    1) Lambda (Functional Programming ) Java 8 ile kullanılmaya başlanmıştır.
    2) Functional Programming 'de "Ne yapılacak" ( What to do) üzerine yoğunlaşılır.
        Structed Programming "Nasıl yapılacak"  ( How to do )üzerine yoğunlaşır.

    3) Functional Programming Arrays ve Collections ile kullanılır.
    4) "entrySet()" methodu ile Map, Set'e dönüştürülerek Functional Programming'de kullanılabilir.



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

        /*
        1) Ardışık list elementlerini aynı satırda aralarında boşluk
        bırakarak yazdıran bir method oluşturun.(Structured)
         */
        listElemanlariniYazdirStructured(liste);
        System.out.println();

        listElemanlariniYazdirFunctional(liste);
        System.out.println();

        ciftElemanlariYazdirStructured(liste);
        System.out.println();

        ciftElemanlariYazdirFunctional(liste);
        System.out.println();

        tekElemanlarinKaresiniYazdirFunctional(liste);
        System.out.println();

        tekElemanlarinKupunuYazdirFunctional(liste);
        System.out.println();

        tekrarsizCiftElemanlarinKareToplami(liste);
        System.out.println();

        tekrarsizCiftElemanlarinKupunuToplami(liste);
        System.out.println();

        getMaxEleman(liste);
        System.out.println();

        getMaxEleman02(liste);
        System.out.println();

        minValue(liste);
        getYedidenBuyukCiftMin(liste);
        getYedidenBuyukCiftMin02(liste);
        getYedidenBuyukCiftMin03(liste);
        System.out.println();
        getTersSiralamaylaTekrarsizElemanlarinYarisi(liste);
    }


    public static void listElemanlariniYazdirStructured(List<Integer> list) {
        for (Integer w : list) {
            System.out.print(w + " ");
        }
    }

    /*
    1)Ardışık list elementlerini aynı satırda aralarında
      boşluk bırakarak yazdıran bir method oluşturun.(Functional)
     */

    public static void listElemanlariniYazdirFunctional(List<Integer> list) {
        list.stream().forEach(t -> System.out.print(t + " "));
        // stream() metohdu collection'dan elementleri akışa dahil etmek için
        // ve methodlara ulaşmak için kullanılır.
        // t bir variable'dır. y de yazılabilir.
    }


    /*
    2) çift list elemanlarını aynı satırda aralarında
       boşluk bırakarak yazdıran bir method oluşturun.(Structured)
     */
    public static void ciftElemanlariYazdirStructured(List<Integer> liste) {
        for (Integer w : liste) {
            if (w % 2 == 0) {
                System.out.print(w + " ");
            }
        }

    }

      /*
    2) list'in  çift  elemanlarını aynı satırda aralarında
       boşluk bırakarak yazdıran bir method oluşturun.(Functional)
     */

    public static void ciftElemanlariYazdirFunctional(List<Integer> liste) {
        liste.stream().filter(t -> t % 2 == 0).forEach(t -> System.out.print(t + " "));

    }

    /*
    3) Ardışık tek list elemanlarının karelerini
    aynı satırda aralarında boşluk bırakarak yazdıran bir method oluşturun.(Functional)
     */
    public static void tekElemanlarinKaresiniYazdirFunctional(List<Integer> liste) {
        liste.stream().filter(t -> t % 2 != 0).map(t -> t * t).forEach(t -> System.out.print(t + " "));
        // Elemanların değerleri değişecekse map() methodu kullanılır.
    }



    /*
    4) tek list elemanlarının küplerini tekrarsız olarak
      aynı satırda aralarında boşluk bırakarak yazdıran bir method oluşturun.
     */

    public static void tekElemanlarinKupunuYazdirFunctional(List<Integer> list) {
        list.stream().distinct().filter(t -> t % 2 != 0).map(t -> t * t * t).forEach(t -> System.out.print(t + " "));
        //distinct() tekrarlilari aldirmamak için kullanilir
        //tekrarlari eler
    }


    /*
    5) Tekrarsız çift elemanların karelerinin toplamını hesaplayan bir method oluşturun.
     */
    public static void tekrarsizCiftElemanlarinKareToplami(List<Integer> liste) {
        Integer toplam = liste.stream().distinct().filter(t -> t % 2 == 0).map(t -> t * t).
                reduce(0, (t, u) -> t + u);

        //reduce() toplam yapabilmek icin iki degişken kullanacagımız için bunu yaptık
        //reduce listedeki cok olan elemanlari teke indirir
        //yani elemanlari alir toplar carpar cikariri boler ne yaparsa yapar tek satira indirger
        System.out.println(toplam);
    }


    /*
    6) Tekrarsız çift elemanların küpünün çarpımını hesaplayan bir method oluşturun.
     */
    public static void tekrarsizCiftElemanlarinKupunuToplami(List<Integer> liste) {
        Integer carpim = liste.stream().distinct().filter(t -> t % 2 == 0)
                .map(t -> t * t * t).reduce(1, (t, u) -> t * u);

        System.out.print(carpim);
    }


    /*
    7) List elemanları arasından en büyük değeri bulan bir method oluşturun.
     */

    // 1.Yol
    public static void getMaxEleman(List<Integer> liste) {
        Integer max = liste.stream().distinct().
                reduce(Integer.MIN_VALUE, (t, u) -> t > u ? t : u); // ternary yazımı var

        // Integer max =liste.stream().distinct().reduce(liste.get(0),(t, u) -> t > u ? t : u);

        System.out.print("Max Yol 1 : " + max);
    }

    // 2.Yol
    public static void getMaxEleman02(List<Integer> liste) {
        Integer max = liste.stream().distinct().sorted().reduce(Integer.MIN_VALUE, (t, u) -> u);
        // sorted() Küçükten büyüğe sıralar.

        System.out.print("Max Yol 2 : " + max);
    }

    /*
Ödev
    8)List elemanları arasından en küçük değeri bulan bir method oluşturun.(2 Yol ile)
     */
    public static void minValue(List<Integer> liste) {
        Integer min = liste.stream().distinct().reduce(liste.get(0), (t, u) -> t < u ? t : u);
        System.out.print("8. soru min = " + min);
    }


    /*
        9) List elemanları arasından 7'den büyük, çift, en küçük değeri bulan bir method oluşturun.
    */
    // 1. Yol
    public static void getYedidenBuyukCiftMin(List<Integer> liste) {
        Integer min = liste.stream().distinct().filter(t -> t % 2 == 0).filter(t -> t > 7).
                reduce(Integer.MAX_VALUE, (t, u) -> t < u ? t : u);

        System.out.println();
        System.out.println("9. soru min = " + min);
    }

    // 2. Yol
    public static void getYedidenBuyukCiftMin02(List<Integer> liste) {

        Integer min = liste.stream().distinct().filter(t -> t % 2 == 0).filter(t -> t > 7).
                sorted(Comparator.reverseOrder()).reduce(Integer.MAX_VALUE, (t, u) -> u);

        System.out.println("9. soru 2. yol min = " + min);
    }

    // 3. Yol
    public static void getYedidenBuyukCiftMin03(List<Integer> liste) {

        Integer min = liste.stream().distinct().filter(t -> t % 2 == 0).filter(t -> t > 7)
                .sorted().findFirst().get();

        System.out.println("9. soru 3. yol min = " + min);
    }


    /*
    10) Ters sıralama ile tekrarsız ve 5'ten büyük elemanların
    yarı değerlerini(elamanın ikiye bölüm sonucunu) bulan  ve list'e atan bir method oluşturun.
     */
    public static void getTersSiralamaylaTekrarsizElemanlarinYarisi(List<Integer> liste) {

        List<Double> list = liste.stream().distinct().filter(t -> t > 5).map(t -> t / 2.0)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        // List<Double> list2= list.stream().distinct().filter(t -> t > 5).map(t -> t / 2.0)
        // .sorted(Comparator.reverseOrder()).toList();
        System.out.println(list);
    }

}


/*
filter()
map()
distinct()
sorted()
limit()
skip()
flatMap()
peek()
 */
