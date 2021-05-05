package org.arsonal.test;

public class TestSynchronization2 {
    public static void main(String args[]){
        Table1 obj = new Table1();//only one object
        Thread t1=new Thread(() ->obj.printTable(5));
        Thread t2=new Thread(() ->obj.printTable(100));
        t1.start();
        t2.start();
    }
}

class Table1 {
    synchronized void printTable(int n){//synchronized method
        for(int i=1;i<=5;i++){
            System.out.println(n*i);
            try{
                Thread.sleep(400);
            }catch(Exception e){System.out.println(e);}
        }

    }
}

class MyThread3 extends Thread{
    Table1 t;
    MyThread3(Table1 t){
        this.t=t;
    }
    public void run(){
        t.printTable(5);
    }

}
class MyThread4 extends Thread{
    Table1 t;
    MyThread4(Table1 t){
        this.t=t;
    }
    public void run(){
        t.printTable(100);
    }
}
