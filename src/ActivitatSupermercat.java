public class ActivitatSupermercat {
int ESTOC_MAX=Supermercat.ESTOC_MAX;
    static int stock=10;
    boolean comprar=false;
    boolean obert=true;
    static int compres;
    static int reposicions;
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
    public synchronized void sortir(){
        persones--;


    }
    public synchronized void comprar(int unitats) throws InterruptedException {


        while (!comprar) {
            stock = stock - unitats;
            compres++;
            wait();
            comprar = true;

        }notifyAll();

    }
    public synchronized void reposar() throws InterruptedException {
        while (comprar) {
            stock = ESTOC_MAX;
            reposicions++;
            comprar = false;
            wait();
        }


        notifyAll();
    }

}
