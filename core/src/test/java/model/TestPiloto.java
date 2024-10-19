package model;

import exception.ExceptionAtributosNulo;
import exception.ExceptionPilotoMenorDeEdad;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

public class TestPiloto {
    @Test
    void AtributosFaltantes() {
        Exception e;
        e = Assertions.assertThrows(ExceptionAtributosNulo.class,()->Piloto.Instance(UUID.randomUUID(),null,"50123321", LocalDate.MIN));
        Assertions.assertEquals("Atributos faltantes",e.getMessage());
    }
    @Test
    void PilotoMenorDeEdad() {
        Exception e;
        e = Assertions.assertThrows(ExceptionPilotoMenorDeEdad.class,()->Piloto.Instance(UUID.randomUUID(),"Franco","50123321", LocalDate.MAX));
        Assertions.assertEquals("el piloto es menor de edad",e.getMessage());
    }
}
