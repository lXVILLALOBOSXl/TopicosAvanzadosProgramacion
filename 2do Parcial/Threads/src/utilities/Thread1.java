package utilities;

public class Thread1 extends Thread{
    public void run(){
        for (char c :
                Abecedary.MAYUS) {
            try{
                Thread.sleep(200);
            }
            catch (Exception e){}

            System.out.println("Thread 1 (mayus): " + c);
        }
    }
}