package cipds9.uf2.practica;

public class ActivitatSupermercat {
int ESTOC_MAX=Supermercat.ESTOC_MAX;
    static int stock=10;
    boolean comprar=true;
    static boolean  obert=false;
    static int compres;
    static int reposicions=0;
    int  persones=0;


    public boolean esObert(){
        return obert;
    }
    public void obrir(){
        this.obert=true;
    }

    /**
     * converteix en false les booleanes obert i la de comprar
     */
    public void tancar(){
        this.obert=false;
        this.comprar=false;
    }

    /**
     * suma les persones que entren
     * @throws InterruptedException
     */
    public synchronized void entrar() throws InterruptedException {
        persones++;
    }
    /**
     * resta les persones que surten
     * @throws InterruptedException
     */
    public synchronized void sortir() {
        persones--;
    }

    /**
     * Si hi ha estoc, realitza la compra, si no notifica al reposador i espera a la reposició
     * @param unitats la quantitat de productes que compra el client
     * @return si ha efectuat la compra true/ no la ha efectuat false
     * @throws InterruptedException
     */
    public synchronized boolean comprar(int unitats) throws InterruptedException {
        if(stock<unitats){
            Supermercat.missatge(Thread.currentThread().getName()+" demana reposició");
            comprar = false;
            notify();   //notifica al reposador peruqe empleni
        }
        while (!comprar) {
            Supermercat.missatge(Thread.currentThread().getName()+" esperant reposició");
            wait();     //espera a la reposició
        }
            stock = stock - unitats;
            compres++;
        return true;
    }

    /**
     * En el moment que es crida es queda dins del bucle en el wait.
     * Quan rep la notificació del client, emplena l'estoc i notifica als clients per que segueixin comprant
     * @throws InterruptedException
     */
    public synchronized void reposar() throws InterruptedException {
        while (comprar&&this.esObert()) {
              wait();                           //espera a que un client demani
          }
            Supermercat.missatge(Thread.currentThread().getName() + " inicia reposició");
            Thread.sleep(Supermercat.getNumeroAleatori(Supermercat.REPOSICIO_TEMPS_MIN, Supermercat.REPOSICIO_TEMPS_MAX));
            comprar = true;
            this.stock = ESTOC_MAX;
            reposicions++;
            Supermercat.missatge(Thread.currentThread().getName() + " acaba reposició");
            if(this.esObert()) {
                notifyAll();                //activa a tots els clients que esperaven
            }
    }
}
