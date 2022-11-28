package cipds9.uf2.practica;

public class Reposador implements Runnable {
   private String nom;
   private static ActivitatSupermercat monitor;

    public Reposador(String nom, ActivitatSupermercat monitor) {
        this.nom = nom;
        this.monitor = monitor;
    }

    /**
     *Executa la funcio del reposador
     */
    @Override

    public void run() {
        try {
            Supermercat.missatge(nom+" entra");
            Supermercat.missatge(nom+" descansar");
            while (monitor.esObert()) {
                monitor.reposar();
            }
            monitor.reposar();// un cop tancat executa la funcio
            Supermercat.missatge(nom+" surt"); //hauria de sortir del while


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}
