package model;

import exception.ExceptionAtributosNulo;
import exception.ExceptionPilotoMenorDeEdad;

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
        if (2024-bdate.getYear() < 18){
            throw new ExceptionPilotoMenorDeEdad("el piloto es menor de edad");
        }
        return new Piloto(id,name,dni,bdate);
    }

    public UUID getID() {
        return this.id;
    }

    public String getName() {
        return name;
    }
}
