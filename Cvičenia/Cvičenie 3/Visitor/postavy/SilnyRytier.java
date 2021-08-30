package postavy;

public class SilnyRytier extends Rytier{

    public SilnyRytier(int energia, Mec mec){
        super(energia, mec);
    }

    public void utoc(Obor obor) {

        mec.udri(obor, this);

        obor.odveta(this);
    }
}
