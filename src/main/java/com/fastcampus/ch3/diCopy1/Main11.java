package com.fastcampus.ch3.diCopy1;

import java.io.FileReader;
import java.util.Properties;

public class Main11 {
    public static void main(String[] args) throws Exception{

        Car car = getCar();
        System.out.println("car = " + car);



    }
    static Car getCar() throws Exception{
        Properties p = new Properties();
        p.load(new FileReader(("config.txt")));

        Class clazz = Class.forName(p.getProperty("car"));   //키가 car인 데이터의 value를 얻어온다.

        return (Car)clazz.newInstance();
    }


}

class Car{

}
class Truck extends Car{

}

class SportCar extends Car{

}