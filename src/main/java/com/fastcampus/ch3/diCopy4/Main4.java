package com.fastcampus.ch3.diCopy4;


import com.google.common.reflect.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Component class Engine{

}

class AppContext{
    Map map; //객체 저장소
    AppContext(){
        map = new HashMap();
        doComponentScan();
        doAutoWired();
        doResource();
    }

    private void doResource() {
//        map에 저장된 객체의 iv중에 @Resource가 붙어있으면
//        map에서 iv의 이름에 맞는 객체를 찾아서 연결해준다(객체의 주소를 iv에 저장)
        try {
            for(Object bean : map.values()){
                for(Field fld : bean.getClass().getDeclaredFields()){
                    if(fld.getAnnotation(Resource.class)!= null){  //byName으로 객체를 찾는다.
                        fld.set(bean, getBean(fld.getName()));  // car.engine = obj;  set은 대입연산을 수행한다.
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void doAutoWired () {
//        map에 저장된 객체의 iv중에 @AutoWired가 붙어있으면
//        map에서 iv의 타입에 맞는 객체를 찾아서 연결해준다(객체의 주소를 iv에 저장)

        try {
            for(Object bean : map.values()){
                for(Field fld : bean.getClass().getDeclaredFields()){
                    if(fld.getAnnotation(Autowired.class)!= null){  //byType으로 객체를 찾는다.
                        fld.set(bean, getBean(fld.getType()));  // car.engine = obj;  set은 대입연산을 수행한다.
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void doComponentScan() {
// 패키지 내의 클래스 목록을 가져온다.
// 반복문으로 클래스를 하나씩 읽은 뒤  @Component 애너테이션이 있는지 확인한다.
//        애너테이션이 있다면 객체를 생성해서 Map에 저장한다.
        try {
            ClassLoader classLoader = AppContext.class.getClassLoader();
            ClassPath classpath = ClassPath.from(classLoader);
            Set<ClassPath.ClassInfo> set = classpath.getTopLevelClasses("com.fastcampus.ch3.diCopy4");

            for(ClassPath.ClassInfo classInfo: set){
                Class clazz = classInfo.load();
                Component component = (Component)clazz.getAnnotation(Component.class);
                if(component != null){
                   String id= StringUtils.uncapitalize( classInfo.getSimpleName());
                   map.put(id, clazz.newInstance());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
//    byName
    Object getBean(String key){
        return map.get(key);
    }
    Object getBean(Class clazz){
        for(Object obj: map.values()){

//            맵의 모든 value를 가져온 뒤, 지정된 타입의 인스턴스이면 return한다. 객체를 검색할때
//            by Type으로 객체를 찾는 방식
            if(clazz.isInstance(obj)){
                return obj;
        }
        }
        return null;
    }
}




public class Main4 {
    public static void main(String[] args) throws Exception{
        AppContext ac = new AppContext();

//        ByName으로 객체를 검색하는 방식
        Car car = (Car)ac.getBean("car");
        Door door = (Door) ac.getBean("door");
        Engine e = (Engine)ac.getBean(Engine.class);

//        car.door = door;
//        car.engine = e;

        System.out.println("car = " + car);
//        ByType으로 객체를 검색
        System.out.println("e = " + e);

//      수동으로 객체를 연결


        System.out.println("door = " + door);
    }
    static Car getCar() throws Exception{
        Properties p = new Properties();
        p.load(new FileReader(("config.txt")));

        Class clazz = Class.forName(p.getProperty("car"));   //키가 car인 데이터의 value를 얻어온다.

        return (Car)clazz.newInstance();
    }


}

@Component class Car{
    @Override
    public String toString() {
        return "Car{" +
                "door=" + door +
                ", engine=" + engine +
                '}';
    }
    @Autowired
    Door door;

    @Resource
    Engine engine;
}
@Component class Door{

}
@Component class Truck extends Car{

}

@Component class SportCar extends Car{

}