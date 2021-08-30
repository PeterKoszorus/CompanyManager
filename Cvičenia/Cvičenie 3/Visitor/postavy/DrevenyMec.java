package postavy;

public class DrevenyMec extends Mec{

    public DrevenyMec(int vyrobneCislo){
        super(vyrobneCislo);
    }

    public void udri(Obor obor, Rytier rytier) {
        if (rytier.zistiEnergiu() >= obor.zistiEnergiu())
            obor.nastavEnergiu((1 * obor.zistiEnergiu()));
        System.out.println("r-om");
    }

    public void udri(Obor obor, StatocnyRytier rytier) {
        obor.nastavEnergiu((1 * obor.zistiEnergiu()));
        System.out.println("sr-om");
    }

    public void udri(Obor obor, SilnyRytier rytier) {
        obor.nastavEnergiu((1 * obor.zistiEnergiu()));
        System.out.println("silnym rytierom");
    }
}
