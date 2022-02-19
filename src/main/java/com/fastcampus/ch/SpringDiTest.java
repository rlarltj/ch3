//package com.fastcampus.ch;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.GenericXmlApplicationContext;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//@Component class Door{}
//@Component("engine") class Engine{}    // Component태그는 config.xml에서 <bean id="engine" class="com.fastcampus.ch"/>와 같음
//@Component class TurboEngine extends Engine{}
//@Component class SuperEngine extends Engine{} // 이처럼 Engine 타입이 여러개일때, byType으로 객체를 찾으면 에러가 발생한다.
////이름은 맵의 key이므로 중복이 없기 때문에 byName으로 검색하자.
//@Component
//class Car{
//    String color;
//    int oil;
//    @Autowired Engine engine;   // Autowired는 byType으로 객체를 검색한다. 객체가 여러개일 경우 byName으로 검색한다.
////    검색하는 name은 타입의 첫 글자가 소문자인 engine이며, 해당하는 객체가 존재하므로 에러는 발생하지 않는다.
////    만약 class Engine에 @Component 에너테이션이 없다면(빈에 등록되지 않았다면) 당연히 에러가 발생한다(super, turbo 중 무엇을 호출하는지 알수 없으므로)
////    이럴때는 @Qualifier("SuperEngine") 어노테이션을 이용해 한 개를 콕 찝어 지정해주어야 에러가 발생하지 않는다.
////    @Autowired , @Qualifier 두 줄을 @Resource(name="SuperEngine") 한 줄로 대체 가능하다. Resource는 byName으로 검색한다..
//    Door[] doors;
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    public void setOil(int oil) {
//        this.oil = oil;
//    }
//
//    public void setEngine(Engine engine) {
//        this.engine = engine;
//    }
//
//    public void setDoors(Door[] doors) {
//        this.doors = doors;
//    }
//
//    @Override
//    public String toString() {
//        return "Car{" +
//                "color='" + color + '\'' +
//                ", oil=" + oil +
//                ", engine=" + engine +
//                ", doors=" + Arrays.toString(doors) +
//                '}';
//    }
//}
//public class SpringDiTest {
//    public static void main(String[] args) {
//        //GenericXmlApplicationContext는 XML문서를 설정파일로 사용한다.
//        ApplicationContext ac= new GenericXmlApplicationContext("config.xml");
//        // config.xml파일에 클래스 정보를 설정하였다.
////        이후 ApplicationContext라는 저장소에 객체가 맵 형태로 저장되며 byName방식으로 Bean을 가져옴.
////          byType형식으로 가져오고 싶다면 (Car) ac.getBean(Car.class);
//        Car car =   (Car)ac.getBean("car");
////      Car car =   ac.getBean("car", Car.class);  두 문장은 동일하다.
//        Engine engine = (Engine)ac.getBean("engine");
//        Door door = (Door) ac.getBean("door");
//
//        car.setColor("red");
//        car.setOil(100);
//        car.setEngine(engine);
//        car.setDoors(new Door[]{ac.getBean("door",Door.class), ac.getBean("door", Door.class)});
//
//        System.out.println("car = " + car);
//        System.out.println("engine = " + engine);
//        System.out.println("door = " + door);
//
////        Setter를 이용하기
////  다른 방법으로는 config.xml에서 property 태그를 활용할 수 있다.
////  <property name="color" value="red" /> 등. property 태그는 setter를 호출한다. 따라서 setter가 반드시 있어야 한다.
////  property 태그는 setter와 짝꿍이라면, <constructor-arg name=".." /> 태그는 생성자와 짝꿍이다(생성자를 이용해서 초기화한다).
//
//
//    }
//}
