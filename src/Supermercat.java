import java.util.Random;

public class Supermercat {
    public static int PASSEIG_TEMPS_MIN = 300;
    public static int PASSEIG_TEMPS_MAX = 600;
    public static int COMPRA_TEMPS_MIN = 30;
    public static int COMPRA_TEMPS_MAX = 180;
    public static int REPOSICIO_TEMPS_MIN = 60;
    public static int REPOSICIO_TEMPS_MAX = 360;
    public static int COMPRA_UNITATS_MIN = 0;
    public static int COMPRA_UNITATS_MAX = 10;
    public static int ESTOC_MAX = 10;
    public static int CLIENTS_TEMPS_CADENCIA_ENTRADA = 120;
    public static int SUPERMERCAT_TEMPS_OBERT = 4320;
    public static int CLIENTS_TEMPS_REINTENT_COMPRA = 10;

    public static void main(String[] args) throws InterruptedException {
        ActivitatSupermercat monitor = new ActivitatSupermercat();
        Reposador reposador = new Reposador("Reposador", monitor);
        Thread t1 = new Thread(reposador,"Reposador");
        t1.start();
        monitor.obrir();


        for (int i = 0; i < SUPERMERCAT_TEMPS_OBERT / CLIENTS_TEMPS_CADENCIA_ENTRADA; i++) {
                Client client = new Client("Client " + (i + 1), monitor);
                Thread ct = new Thread(client,client.getNom());
                ct.start();
                Thread.sleep(CLIENTS_TEMPS_CADENCIA_ENTRADA);
            }
                while (monitor.persones!=0){
                    System.out.println(monitor.persones);
                    missatge("Esperant a que marxin els clients");
                    //reposador.run();
                    Thread.sleep(getNumeroAleatori(REPOSICIO_TEMPS_MIN, REPOSICIO_TEMPS_MAX));
                }
        monitor.tancar();

        missatge("Compres "+monitor.compres);
        missatge("Reposicions "+monitor.reposicions);


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