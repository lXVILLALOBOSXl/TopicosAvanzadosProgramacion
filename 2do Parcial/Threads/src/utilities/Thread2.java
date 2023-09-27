package utilities;

public class Thread2 extends Thread{
    public void run(){
        for (char c :
                Abecedary.MINUS) {
            try{
                Thread.sleep(200);
            }
            catch (Exception e){}

            System.out.println("Thread 2 (minus): " + c);
        }
    }
}