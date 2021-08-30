package gui;

import javafx.scene.control.*;
import stret.SledovatelStretu;
import stret.Stret;

public class SucetSerialov extends TextField implements SledovatelStretu{

    private Stret stret;
    private int serial_no;

    public SucetSerialov(Stret stret) {
        super();
        this.stret = stret;
    }

    public void upovedom(){
        serial_no = 0;

        for(int i = 0; i < stret.zistiPocetBojovnikov(); i++){

            serial_no += stret.zistiRytiera(i).ukazMec() + serial_no;
        }

        setText(Integer.toString(serial_no));
    }



}
