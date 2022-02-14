package com.fastcampus.ch3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;

class Car{
    String color;
    int oil;
    Engine engine;
    Door[] doors;

    public void setColor(String color) {
        this.color = color;
    }

    public void setOil(int oil) {
        this.oil = oil;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setDoors(Door[] doors) {
        this.doors = doors;
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", oil=" + oil +
                ", engine=" + engine +
                ", doors=" + Arrays.toString(doors) +
                '}';
    }
}
class Door{}
class Engine{}
public class SpringDiTest {
    public static void main(String[] args) {
        ApplicationContext ac= new GenericXmlApplicationContext("config.xml");
        // config.xml파일에 클래스 정보를 설정하였다.
//        이후 ApplicationContext라는 저장소에 객체가 맵 형태로 저장되며 byName방식으로 Bean을 가져옴.
//          byType형식으로 가져오고 싶다면 (Car) ac.getBean(Car.class);
        Car car =   (Car)ac.getBean("car");
//      Car car =   ac.getBean("car", Car.class);  두 문장은 동일하다.
        Engine engine = (Engine)ac.getBean("engine");
        Door door = (Door) ac.getBean("door");

        System.out.println("car = " + car);
        System.out.println("engine = " + engine);
        System.out.println("door = " + door);
        
//        Setter를 이용하기
        car.setColor("red");
        car.setOil(100);
        car.setEngine(engine);
        car.setDoors(new Door[]{ac.getBean("door",Door.class), ac.getBean("door", Door.class)});
//  다른 방법으로는 config.xml에서 property 태그를 활용할 수 있다.
//  <property name="color" value="red" /> 등. property 태그는 setter를 호출한다. 따라서 setter가 반드시 있어야 한다.
//  property 태그는 setter와 짝꿍이라면, <constructor-arg name=".." /> 태그는 생성자와 짝꿍이다(생성자를 이용해서 초기화한다).


    }
}
