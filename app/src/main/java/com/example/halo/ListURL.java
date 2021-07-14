package com.example.halo;

public class ListURL {

    public static String getListUrl(String category){

        String url;
        switch(category){
            case "FOOD":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/getlist1.php";
                break;

            case "CLOTHING":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/getlist2.php";
                break;

            case "SANITARY & HYGIENE":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/getlist3.php";
                break;

            case "STATIONERY":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/getlist4.php";
                break;

            case "ANIMAL CARE":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/getlist5.php";;
                break;

            case "OTHER":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/getlist6.php";;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + category);
        }
        return url;
    }
}
