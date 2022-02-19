//package com.fastcampus.ch3.diCopy3;
//
//
//import com.google.common.reflect.ClassPath;
//import org.springframework.util.StringUtils;
//
//import java.awt.*;
//import java.io.FileReader;
//import java.util.*;
//
//import org.springframework.stereotype.Component;
//
//@Component class Engine{
//
//}
//
//class AppContext{
//    Map map; //객체 저장소
//    AppContext(){
//        map = new HashMap();
//        doComponentScan();
//    }
//
//    private void doComponentScan() {
//// 패키지 내의 클래스 목록을 가져온다.
//// 반복문으로 클래스를 하나씩 읽은 뒤  @Component 애너테이션이 있는지 확인한다.
////        애너테이션이 있다면 객체를 생성해서 Map에 저장한다.
//        try {
//            ClassLoader classLoader = AppContext.class.getClassLoader();
//            ClassPath classpath = ClassPath.from(classLoader);
//            Set<ClassPath.ClassInfo> set = classpath.getTopLevelClasses("com.fastcampus.ch3.diCopy3");
//
//            for(ClassPath.ClassInfo classInfo: set){
//                Class clazz = classInfo.load();
//                Component component = (Component)clazz.getAnnotation(Component.class);
//                if(component != null){
//                   String id= StringUtils.uncapitalize( classInfo.getSimpleName());
//                   map.put(id, clazz.newInstance());
//                }
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
////    byName
//    Object getBean(String key){
//        return map.get(key);
//    }
//    Object getBean(Class clazz){
//        for(Object obj: map.values()){
//
////            맵의 모든 value를 가져온 뒤, 지정된 타입의 인스턴스이면 return한다. 객체를 검색할때
////            by Type으로 객체를 찾는 방식
//            if(clazz.isInstance(obj)){
//                return obj;
//        }
//        }
//        return null;
//    }
//}
//
//
//
//
//public class Main3 {
//    public static void main(String[] args) throws Exception{
//        AppContext ac = new AppContext();
//
////        ByName으로 객체를 검색하는 방식
//        Car car = (Car)ac.getBean("car");
//        System.out.println("car = " + car);
//
////        ByType으로 객체를 검색
//        Engine e = (Engine)ac.getBean(Engine.class);
//        System.out.println("e = " + e);
//
//
//
//    }
//    static Car getCar() throws Exception{
//        Properties p = new Properties();
//        p.load(new FileReader(("config.txt")));
//
//        Class clazz = Class.forName(p.getProperty("car"));   //키가 car인 데이터의 value를 얻어온다.
//
//        return (Car)clazz.newInstance();
//    }
//
//
//}
//
//@Component class Car{
//
//}
//@Component class Truck extends Car{
//
//}
//
//@Component class SportCar extends Car{
//
//}