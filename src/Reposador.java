public class Reposador implements Runnable {
   private String nom;
   private ActivitatSupermercat monitor;

    public Reposador(String nom, ActivitatSupermercat monitor) {
        this.nom = nom;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        /*try {
                monitor.reposar();
                System.out.println("--------------------------------------------------------");



        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
    }



}
