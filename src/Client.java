public class Client implements Runnable{
    private int unitats;
    private String nom;
    private ActivitatSupermercat monitor;
    public Client(ActivitatSupermercat monitor) {

        this.monitor=monitor;
    }

    @Override
    public void run() {
        if(monitor.esObert()){
            try {
                monitor.entrar();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
    }
}
