package model;

import exception.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

public class TestPiloto {
    @Test
    void AtributosFaltantes() {
        Exception e;
        e = Assertions.assertThrows(ExceptionAtributosNulo.class,()->Piloto.Instance(UUID.randomUUID(),null,"123456789", LocalDate.of(2010,10,25)));
        Assertions.assertEquals("Atributos faltantes",e.getMessage());
    }
    @Test
    void PilotoMenorDeEdad() {
        Exception e;
        e = Assertions.assertThrows(ExceptionPilotoMenorDeEdad.class,()->Piloto.Instance(UUID.randomUUID(),"Franco","123456789", LocalDate.of(2010,10,25)));
        Assertions.assertEquals("el piloto es menor de edad",e.getMessage());
    }
    @Test
    void PilotoSinNombre() {
        Exception e;
        e = Assertions.assertThrows(ExceptionNombreVacio.class,()->Piloto.Instance(UUID.randomUUID(),"","123456789", LocalDate.of(2002,10,25)));
        Assertions.assertEquals("El nombre no puede estar vacio",e.getMessage());
    }
    @Test
    void PilotoSinDNI() {
        Exception e;
        e = Assertions.assertThrows(ExceptionDniVacio.class,()->Piloto.Instance(UUID.randomUUID(),"Franco","", LocalDate.of(2005,10,25)));
        Assertions.assertEquals("el DNI no puede estar vacio",e.getMessage());
    }
    @Test
    void PilotoDniNoValido() {
        Exception e;
        e = Assertions.assertThrows(ExceptionDniNoValido.class,()->Piloto.Instance(UUID.randomUUID(),"Franco","123456", LocalDate.of(2005,10,25)));
        Assertions.assertEquals("El dni debe tener 9 caracteres",e.getMessage());
    }
}
