import java.util.Random;

public class Supermercat {
    public static int PASSEIG_TEMPS_MIN = 300;
    public static int PASSEIG_TEMPS_MAX = 600;

    public static int COMPRA_TEMPS_MIN = 300;
    public static int COMPRA_TEMPS_MAX = 1800;

    public static int REPOSICIO_TEMPS_MIN = 600;
    public static int REPOSICIO_TEMPS_MAX = 3600;

    public static int COMPRA_UNITATS_MIN = 0;
    public static int COMPRA_UNITATS_MAX = 10;

    public static int ESTOC_MAX = 10;

    public static int CLIENTS_TEMPS_CADENCIA_ENTRADA = 1200;

    public static int SUPERMERCAT_TEMPS_OBERT = 43200;

    public static int CLIENTS_TEMPS_REINTENT_COMPRA = 10;

    public static void main(String[] args) throws InterruptedException {
        ActivitatSupermercat monitor = new ActivitatSupermercat();
        Reposador reposador = new Reposador("Reposador", monitor);
        Thread t1 = new Thread(reposador);
        t1.start();
        monitor.obrir();

        if (monitor.esObert()) {
            for (int i = 0; i < SUPERMERCAT_TEMPS_OBERT / CLIENTS_TEMPS_CADENCIA_ENTRADA; i++) {
                Client client = new Client("Client " + (i + 1), monitor);
                Thread ct = new Thread(client);
                ct.start();

                Thread.sleep(CLIENTS_TEMPS_CADENCIA_ENTRADA);
            }




            missatge("Compres "+monitor.compres);

            missatge("Reposicions "+monitor.reposicions);


        }
    }
    public static int getNumeroAleatori(int min, int max){
        Random random=new Random();
        int rand=random.nextInt(min,max);
        return rand;
    }
    public static void missatge(String msg){
        System.out.println(msg);

    }


}