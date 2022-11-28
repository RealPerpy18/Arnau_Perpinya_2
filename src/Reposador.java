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
            monitor.entrar();
            Supermercat.missatge(nom+" entra");
            Supermercat.missatge(nom+" descansa");
        while (!monitor.esObert()){

        }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}
