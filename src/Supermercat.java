import java.util.Random;

public class Supermercat {
    public static int PASSEIG_TEMPS_MIN=300;
    public static int PASSEIG_TEMPS_MAX=600;

    public int COMPRA_TEMPS_MIN=300;
    public int COMPRA_TEMPS_MAX=1800;

    public int REPOSICIO_TEMPS_MIN=600;
    public int REPOSICIO_TEMPS_MAX=3600;

    public int COMPRA_UNITATS_MIN=0;
    public int COMPRA_UNITATS_MAX=10;

    public int ESTOC_MAX=10;

    public static int CLIENTS_TEMPS_CADENCIA_ENTRADA=1200;

    public static int SUPERMERCAT_TEMPS_OBERT=43200;
    public int CLIENTS_TEMPS_REINTENT_COMPRA=10;

    public static void main(String[] args) throws InterruptedException {
        ActivitatSupermercat monitor= new ActivitatSupermercat();
        Reposador reposador=new Reposador("Reposador",monitor);
        Thread t1=new Thread(reposador);
        t1.start();
        monitor.obrir();
        long time=0;
        long tim2=0;

        while(time<SUPERMERCAT_TEMPS_OBERT){
           if(time-tim2>=CLIENTS_TEMPS_CADENCIA_ENTRADA||tim2==0) {
                System.out.println("entra");
                Client client = new Client( monitor);
                Thread ct = new Thread(client);
                ct.start();

                tim2 =System.currentTimeMillis();
           }
            time=System.currentTimeMillis();

        }
        monitor.tancar();
    }
    public static int getNumeroAleatori(int min, int max){
        Random random=new Random();
        int rand=random.nextInt(min,max);
        return rand;
    }
    public void missatge(String msg){
        System.out.println(msg);

    }
    public static void missatge(String msg, int temps) throws InterruptedException {
        System.out.println(msg);
        Thread.sleep(temps);
    }
    public void missatge(String msg,int min,int max){
    getNumeroAleatori(min,max);
        System.out.println(msg);

    }
}