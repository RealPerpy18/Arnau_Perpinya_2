import javax.management.monitor.Monitor;

public class Client implements Runnable{
    private int unitats=-1;
    private String nom;
    private ActivitatSupermercat monitor;

    public Client(String nom,ActivitatSupermercat monitor) {
        this.nom=nom;
        this.monitor=monitor;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public void run() {

            try {
                monitor.entrar();
                Supermercat.missatge(nom+" entra");

                Supermercat.missatge(nom+" passejant");
                Thread.sleep(Supermercat.getNumeroAleatori(Supermercat.PASSEIG_TEMPS_MIN, Supermercat.PASSEIG_TEMPS_MAX));
                boolean comprat=false;
                while(!comprat) {
                    int rand = Supermercat.getNumeroAleatori(Supermercat.COMPRA_UNITATS_MIN, Supermercat.COMPRA_UNITATS_MAX);
                    Supermercat.missatge(nom + " comprant " + rand);
                    Thread.sleep(Supermercat.getNumeroAleatori(Supermercat.COMPRA_TEMPS_MIN, Supermercat.COMPRA_TEMPS_MAX));
                    comprat= monitor.comprar(rand);
                }


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }



    }
}
