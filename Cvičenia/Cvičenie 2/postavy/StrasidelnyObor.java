package postavy;

public class StrasidelnyObor extends ZlyObor {

    public StrasidelnyObor(int energia, boolean hladny) {
        super(energia, hladny);
    }

    void zarev(){
        System.out.println("HAAAAAAAAAAAA");
    }

    void odveta(Rytier rytier){

        super.zjedz(rytier);
        zarev();
    }

}
