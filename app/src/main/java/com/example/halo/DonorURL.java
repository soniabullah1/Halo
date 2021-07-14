package com.example.halo;

public class DonorURL {

    public static String getUrl(String category){

        String url;
        switch(category){
            case "FOOD":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/fd.php";
                break;

            case "CLOTHING":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/cd.php";
                break;

            case "SANITARY & HYGIENE":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/hd.php";
                break;

            case "STATIONERY":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/sd.php";
                break;

            case "ANIMAL CARE":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/acd.php";
                break;

            case "OTHER":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/od.php";
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + category);
        }
        return url;
    }
}

