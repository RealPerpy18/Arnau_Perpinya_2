public class ActivitatSupermercat {
int ESTOC_MAX=Supermercat.ESTOC_MAX;
    static int stock=10;
    boolean comprar=false;
    boolean obert=true;
    static int compres;
    static int reposicions;
    static int persones=0;


    public boolean esObert(){
        return obert;
    }
    public void obrir(){
        obert=true;
    }
    public void tancar(){
        obert=false;
    }
    public void entrar() throws InterruptedException {
        persones++;
    }
    public void sortir(){
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
        System.out.println("bbb"+stock);

        while (comprar) {
            Supermercat.missatge("Reposador inicia la reposicio");
            Thread.sleep(Supermercat.getNumeroAleatori(Supermercat.REPOSICIO_TEMPS_MIN, Supermercat.REPOSICIO_TEMPS_MAX));
            stock = ESTOC_MAX;
            reposicions++;
            Supermercat.missatge("Reposador acaba la reposicio");
            comprar = false;
            wait();
        }


        notifyAll();
    }

}
