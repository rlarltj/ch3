package com.fastcampus.ch3.diCopy2;


import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
class Engine{

}

class AppContext{
    Map map; //객체 저장소
    AppContext(){
        try{

        Properties p = new Properties();
        p.load(new FileReader("config.txt"));

//      프로퍼티에 저장된 내용을 Map에 저장
        map = new HashMap(p);

//        클래스 이름을 이용하여 객체를 생성하자.
//         반복문으로 클래스 이름을 얻은 뒤, 객체를 생성하여 다시 map에 저장.
            for(Object obj: map.keySet()){
                Class clazz = Class.forName((String)map.get(obj));
                map.put(obj, clazz.newInstance());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    Object getBean(String key){
        return map.get(key);

    }
}




public class Main2 {
    public static void main(String[] args) throws Exception{
        AppContext ac = new AppContext();

        Car car = (Car)ac.getBean("car");
        System.out.println("car = " + car);

        Engine e = (Engine)ac.getBean("engine");
        System.out.println("e = " + e);



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