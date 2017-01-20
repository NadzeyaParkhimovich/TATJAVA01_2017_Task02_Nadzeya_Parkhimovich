package com.epam.task2;


public class Runner {

    public static void main(String[] args) {

        String shopFileName = "ShopFileName";
        String rentFileName = "RentFileName";
        Shop shop = new Shop(shopFileName, rentFileName);

        shop.printShopReport();
        shop.printRentReport();


    }


}
