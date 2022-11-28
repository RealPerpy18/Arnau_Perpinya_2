package cipds9.uf2.practica;

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

    public static void main(String[] args) throws InterruptedException {
        ActivitatSupermercat monitor = new ActivitatSupermercat();
        Reposador reposador = new Reposador("cipds9.uf2.practica.Reposador", monitor);
        Thread t1 = new Thread(reposador,"cipds9.uf2.practica.Reposador");
        t1.start();
        monitor.obrir();
        for (int i = 0; i < SUPERMERCAT_TEMPS_OBERT / CLIENTS_TEMPS_CADENCIA_ENTRADA; i++) {
                Client client = new Client("cipds9.uf2.practica.Client " + (i + 1), monitor);
                Thread ct = new Thread(client,client.getNom());
                ct.start();
                Thread.sleep(CLIENTS_TEMPS_CADENCIA_ENTRADA);
            }
                while (monitor.persones!=0){                            //fins que no marxen els clients no segueix
                    missatge("Esperant a que marxin els clients");
                    //reposador.run();
                    Thread.sleep(REPOSICIO_TEMPS_MIN);
                }
                monitor.tancar();
                missatge("cipds9.uf2.practica.Reposador surt");             //no hauria de estar aqui
                missatge("Compres "+monitor.compres);
                missatge("Reposicions "+monitor.reposicions);
    }

    /**
     * Genera un numero aleatori a partir de dos valors
     * @param min Valor minim que pot ser el minim
     * @param max Valor maxim que pot ser el minim
     * @return el numero aleatori generat
     */
    public static int getNumeroAleatori(int min, int max){
        Random random=new Random();
        int rand=random.nextInt(min,max);
        return rand;
    }

    /**
     * Imprimeix igual que un println de java.
     * Ajuda a distingir d'un print normal.
     * @param msg és el contingut del print.
     */
    public static void missatge(String msg){
        System.out.println(msg);
    }
}