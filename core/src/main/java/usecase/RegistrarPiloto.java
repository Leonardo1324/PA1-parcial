package usecase;

import exception.ExceptionFalloElRegistro;
import exception.ExceptionPilotConMismoNombre;
import model.Piloto;
import output.Persistence;

import java.util.UUID;

public class RegistrarPiloto implements input.RegistrarPiloto {
    private Persistence myBD;

    public RegistrarPiloto(Persistence myBD) {
        this.myBD = myBD;
    }

    @Override
    public UUID RegistrarPiloto(Piloto p1) {
        if (myBD.ExistePiloto(p1.getName())){
            throw new ExceptionPilotConMismoNombre("Ese piloto Ya existe");
        }
        if (myBD.GuardarPiloto(p1) == null) {
            throw new ExceptionFalloElRegistro("Fallo el registro del Piloto");
        }
        else{
            return p1.getID();
        }
    }
}
