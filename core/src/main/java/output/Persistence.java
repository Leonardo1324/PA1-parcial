package output;

import model.Piloto;

import java.util.UUID;

public interface Persistence {
    boolean ExistePiloto(String name);

    UUID GuardarPiloto(Piloto p1);
}
