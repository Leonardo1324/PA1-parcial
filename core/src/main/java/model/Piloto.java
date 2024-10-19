package model;

import exception.*;

import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

public class Piloto {
    private UUID id;
    private String name;
    private String dni;
    private LocalDate bdate;

    private Piloto(UUID id, String name, String dni, LocalDate bdate) {
        this.id = id;
        this.name = name;
        this.dni = dni;
        this.bdate = bdate;
    }
    public static Piloto Instance(UUID id, String name, String dni, LocalDate bdate){
        if (id == null || name == null || dni == null || bdate == null) {
            throw new ExceptionAtributosNulo("Atributos faltantes");
        }
        if (name.isEmpty()){
            throw new ExceptionNombreVacio("El nombre no puede estar vacio");
        }
        if (dni.isEmpty()) {
            throw new ExceptionDniVacio("el DNI no puede estar vacio");
        }
        if (dni.length() != 9) {
            throw new ExceptionDniNoValido("El dni debe tener 9 caracteres");
        }
        if (LocalDate.now().minusYears(bdate.getYear()).getYear() < 18){
            throw new ExceptionPilotoMenorDeEdad("el piloto es menor de edad");
        }
        return new Piloto(id,name,dni,bdate);
    }

    public UUID getID() {
        return this.id;
    }

    public String getDni() {
        return dni;
    }
}
