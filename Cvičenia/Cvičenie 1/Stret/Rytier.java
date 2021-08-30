package Stret;

public class Rytier {
    int energia;

    // sekcia na generovanie randomneho cisla pre odobratie energie
    double max = 1;
    double min = 0.1;
    double range = max - min;
    double rand = ((double)Math.round((Math.random() * range) * 100d) / 100d);  // vygeneruje a zaokruhli nahodne cislo

    void utok(Obor obor) {
        System.out.println(rand);
        obor.energia = (int) (rand * obor.energia); // (int) len pretypuje float vysledok na int
        obor.odveta(this); // this predstavuje odkaz na aktualny objekt rytiera
        System.out.println(this); // vypis identifikatora aktualneho objektu
    }
}
