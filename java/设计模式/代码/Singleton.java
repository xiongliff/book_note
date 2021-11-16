/**
 * @author xiongliff
 * @version V1.0 @Package PACKAGE_NAME
 * @date 2021/11/16 15:31
 */

/**
 * 单例设计模式：懒加载
 *
 * 懒汉式 ， 在调用getInstance时候，才会创建实例
 */
public class Singleton {
    private static Singleton singletonInstance = null;
    /* 私有化构造方法*/
    private Singleton(){
    }
    public static Singleton getSingletonInstance(){
        if(null == singletonInstance){
            synchronized(singletonInstance){
                if(null == singletonInstance){
                    singletonInstance = new Singleton();
                }

            }
        }
        return singletonInstance;
    }
}

/**
 *
 * 饿汉式，在类加载时候就会创建
 */
 class SingletonDemo{
     private  SingletonDemo(){
     }
     public static SingletonDemo singletonInstance = new SingletonDemo();
     public static SingletonDemo getInstance(){
         return singletonInstance;
     }





}

