public class ActivitatSupermercat {
int ESTOC_MAX=Supermercat.ESTOC_MAX;
    static int stock=10;
    boolean comprar=true;
    boolean obert=true;
    static int compres;
    static int reposicions=0;
     int  persones=0;


    public boolean esObert(){
        return obert;
    }
    public void obrir(){
        obert=true;
    }
    public void tancar(){
        obert=false;
    }
    public synchronized void entrar() throws InterruptedException {
        persones++;
    }
    public synchronized void sortir() {
        persones--;
    }
    public synchronized boolean comprar(int unitats) throws InterruptedException {
        if(stock<unitats){
            comprar = false;
            notify();
        }
        while (!comprar) {
            Supermercat.missatge(Thread.currentThread().getName()+" esperant reposició");
                wait();
            }
            stock = stock - unitats;
            compres++;
            sortir();
        Supermercat.missatge(Thread.currentThread().getName()+" surt");

        return true;
    }
    public synchronized void reposar() throws InterruptedException {
           while (comprar){
            wait();
           }
        Supermercat.missatge(Thread.currentThread().getName()+" inicia reposició");
        Thread.sleep(Supermercat.getNumeroAleatori(Supermercat.REPOSICIO_TEMPS_MIN, Supermercat.REPOSICIO_TEMPS_MAX));
        comprar = true;
        this.stock = ESTOC_MAX;
        reposicions++;
        Supermercat.missatge(Thread.currentThread().getName()+" acaba reposició");
        notifyAll();
    }

}
