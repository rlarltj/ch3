package com.fastcampus.practice;

import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class Car{

   Engine engine;
}
class SportCar extends Car{}
class Truck extends Car{}
class Engine{
    void run(){

    };
}
class AppContext{
    Map map;
    AppContext(){
        try{
            Properties p = new Properties();
            p.load(new FileReader("config.txt"));

//            properties의 내용을 map에 저장
            map = new HashMap(p);

            for(Object key: p.keySet()){
            Class clazz = Class.forName((String)map.get(key));
            map.put(key, clazz.newInstance());

            }

        }catch(Exception e){

        }
    }
    Object getBean(String key){
        return map.get(key);
    }
    Object getBean(Class clazz){
        for(Object obj: map.values()){
            if(clazz.isInstance(obj))
                return obj;
        }
        return null;
    }
}

public class Main1 {
    public static void main(String[] args) throws Exception{
        AppContext ac = new AppContext();
        Car car = (Car)ac.getBean("car");
        Engine engine = (Engine) ac.getBean(Engine.class);
        System.out.println("car = " + car);
        System.out.println("engine = " + engine);
//        System.out.println("car2 = " + car2);


    }


}
