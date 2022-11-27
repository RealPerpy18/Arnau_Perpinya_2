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
        if(monitor.esObert()){
            try {
                monitor.entrar();
                Supermercat.missatge(nom+" entra");

                Supermercat.missatge(nom+" passejant");
                Thread.sleep(Supermercat.getNumeroAleatori(Supermercat.PASSEIG_TEMPS_MIN, Supermercat.PASSEIG_TEMPS_MAX));

                int rand=Supermercat.getNumeroAleatori(Supermercat.COMPRA_UNITATS_MIN,Supermercat.COMPRA_UNITATS_MAX);
                System.out.println("---> "+nom+" "+rand);

                    if (rand<=monitor.stock) {
                        Thread.sleep(Supermercat.getNumeroAleatori(Supermercat.COMPRA_TEMPS_MIN, Supermercat.COMPRA_TEMPS_MAX));
                        monitor.comprar(rand);

                        Supermercat.missatge(nom + " comprant " + rand);
                        unitats=rand;
                    } else {
                        monitor.reposar();
                        Supermercat.missatge(nom + " esperant reposició ");
                    }



                monitor.sortir();
                Supermercat.missatge(nom+" surt");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
