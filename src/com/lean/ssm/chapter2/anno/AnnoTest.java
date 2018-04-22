package com.lean.ssm.chapter2.anno;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Student(sid = 1, name = "刘德华", sex = Sex.男)
public class AnnoTest {
    @Student(sid = 2)
    private String field;

    @Student(sid = 2, name = "hello", sex = Sex.女)
    public void sayHello() {

    }

    public static void main(String[] args) throws Exception, InstantiationException {
        /*Class c = AnnoTest.class;
        // 判断是否被指定的Annotation修饰
        if (c.isAnnotationPresent(Student.class)){
            Student student = (Student)c.getAnnotation(Student.class);
            System.out.println(student.sid());
            System.out.println(student.name());
            System.out.println(student.sex());
        }

        Field field = c.getDeclaredField("field");
        if (field.isAnnotationPresent(Student.class)){
            Student student = (Student)c.getAnnotation(Student.class);
            System.out.println(student.sid());
            System.out.println(student.name());
            System.out.println(student.sex());
        }

        Method method = c.getDeclaredMethod("sayHello");
        if (method.isAnnotationPresent(Student.class)){
            Student student = (Student)c.getAnnotation(Student.class);
            System.out.println(student.sid());
            System.out.println(student.name());
            System.out.println(student.sex());
        }*/
        Product product = new Product(1, "IPhone X", 9999.0, java.sql.Date.valueOf("2018-2-30"));
        System.out.println(SqlBuilder.insert(product));
        System.out.println(SqlBuilder.delete(product));
        System.out.println(SqlBuilder.update(product));
        System.out.println(SqlBuilder.select(product));
    }

    @MyJDBC(driver="com.mysql.jdbc.Driver",url="jdbc:mysql://localhost:3306/wdw",username="root",password="root")
    public static Connection getConnection(){
        Class c = AnnoTest.class;
        MyJDBC myJDBC = (MyJDBC) c.getAnnotation(MyJDBC.class);
        String driver = myJDBC.driver();
        String url = myJDBC.url();
        String username = myJDBC.username();
        String password = myJDBC.password();
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
