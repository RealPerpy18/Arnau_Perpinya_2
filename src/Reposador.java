public class Reposador implements Runnable {
   private String nom;
   private ActivitatSupermercat monitor;

    public Reposador(String nom, ActivitatSupermercat monitor) {
        this.nom = nom;
        this.monitor = monitor;
    }


    @Override
    public void run() {
        try {
            Supermercat.missatge(nom+" entra");
            Supermercat.missatge(nom+" descansar");
            while (monitor.esObert()) {
                monitor.reposar();
            }
            Supermercat.missatge(nom+" surt");


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}
