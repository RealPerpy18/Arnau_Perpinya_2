public class ActivitatSupermercat {
    static final int ESTOC_MAX=10;
    boolean comprar=true;
    boolean obert=true;
    static int compres;
    static int reposicions;
    static int persones=1;

    public boolean esObert(){

        return obert;
    }
    public void obrir(){
        obert=true;
    }
    public void tancar(){
        obert=false;
    }
    public void entrar() throws InterruptedException {
        Supermercat.missatge("Client "+persones+" entra",Supermercat.getNumeroAleatori(Supermercat.PASSEIG_TEMPS_MIN, Supermercat.PASSEIG_TEMPS_MAX));
        persones++;
    }
    public void sortir(){

    }
    public boolean comprar(int unitats){

        compres++;
        return  true;
    }
    public void reposar(){

        reposicions++;
    }

}
