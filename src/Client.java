public class Client implements Runnable{
    private int unitats=-1;
    private String nom;
    private ActivitatSupermercat monitor;
    public Client(String nom,ActivitatSupermercat monitor) {
        this.nom=nom;
        this.monitor=monitor;
    }
    @Override
    public void run() {

            try {
                monitor.entrar();
                Supermercat.missatge(nom+" entra");

                Supermercat.missatge(nom+" passejant");
                Thread.sleep(Supermercat.getNumeroAleatori(Supermercat.PASSEIG_TEMPS_MIN, Supermercat.PASSEIG_TEMPS_MAX));

                int rand=Supermercat.getNumeroAleatori(Supermercat.COMPRA_UNITATS_MIN,Supermercat.COMPRA_UNITATS_MAX);
                while(unitats==-1) {
                    Supermercat.missatge(nom + " comprant " + rand);
                    if (rand <= monitor.stock) {
                        Thread.sleep(Supermercat.getNumeroAleatori(Supermercat.COMPRA_TEMPS_MIN, Supermercat.COMPRA_TEMPS_MAX));

                        monitor.comprar(rand);
                        monitor.sortir();
                        unitats = rand;
                        Supermercat.missatge(nom+" surt");


                    } else {
                        monitor.reposar();
                        Thread.sleep(Supermercat.getNumeroAleatori(Supermercat.REPOSICIO_TEMPS_MIN, Supermercat.REPOSICIO_TEMPS_MAX));

                        Supermercat.missatge(nom + " esperant reposició ");
                    }
                }


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }



    }
}
