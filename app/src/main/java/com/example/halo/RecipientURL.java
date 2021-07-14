package com.example.halo;

public class RecipientURL {

    public static String getUrl(String category){

        String url;
        switch(category){
            case "FOOD":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/fr.php";
                break;

            case "CLOTHING":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/cr.php";
                break;

            case "SANITARY & HYGIENE":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/hr.php";
                break;

            case "STATIONERY":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/sr.php";
                break;

            case "ANIMAL CARE":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/acr.php";
                break;

            case "OTHER":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/or.php";
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + category);
        }
        return url;
    }

}
