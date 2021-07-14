package com.example.halo;

public class CategoryRURL {

    public static String getCatUrlR(String category){

        String url;
        switch(category){
            case "Food":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/R_Food.php";
                break;

            case "Clothing":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/R_Clothing.php";
                break;

            case "Sanitary":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/R_Hygeine.php";
                break;

            case "Stationery":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/R_Stationery.php";
                break;

            case "Animals":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/view2.php";
                break;

            case "Other":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/R_Other.php";
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + category);
        }
        return url;
    }
}