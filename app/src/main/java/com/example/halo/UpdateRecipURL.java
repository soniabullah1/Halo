package com.example.halo;

public class UpdateRecipURL {

    public static int numItems(int num){
        int number = num;
        return number;
    }

    public static String updateUrl(String category){

        String url;
        switch(category){
            case "FOOD":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/updateRecip1.php";
                break;

            case "CLOTHING":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/updateRecip2.php";
                break;

            case "SANITARY & HYGIENE":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/updateRecip3.php";
                break;

            case "STATIONERY":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/updateRecip4.php";
                break;

            case "ANIMAL CARE":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/updateRecip5.php";;
                break;

            case "OTHER":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/updateRecip6.php";;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + category);
        }
        return url;
    }

}
