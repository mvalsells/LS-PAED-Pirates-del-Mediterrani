package paedS2.arbresR;

import java.util.ArrayList;

public interface ElementoR {
    void insert(ElementoR elementoR);
    float incremento(ElementoR elementoR);
    ArrayList<Float> posicio();

    ArrayList<ElementoR> getHijos();

    float area(ElementoR elementoR);
}
