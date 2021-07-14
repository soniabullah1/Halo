package com.example.halo;

public class CategoryDURL {

    public static String getCatUrlD(String category){

        String url;
        switch(category){
            case "Food":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/D_Food.php";
                break;

            case "Clothing":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/D_Clothing.php";
                break;

            case "Sanitary":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/D_Hygiene.php";
                break;

            case "Stationery":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/D_Stationery.php";
                break;

            case "Animals":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/D_Animal_Care.php";
                break;

            case "Other":
                url = "https://lamp.ms.wits.ac.za/home/s1853416/D_Other.php";
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + category);
        }
        return url;
    }
}