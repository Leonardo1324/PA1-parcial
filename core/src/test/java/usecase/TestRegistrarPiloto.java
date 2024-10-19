package usecase;

import exception.ExceptionFalloElRegistro;
import exception.ExceptionPilotConMismoNombre;
import model.Piloto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import output.Persistence;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestRegistrarPiloto {
    @Mock
    Persistence BD;
    @Test
    void PilotoConMismoNombre() {
        Exception e;
        RegistrarPiloto rp = new RegistrarPiloto(BD);
        when(BD.ExistePiloto("Franco")).thenReturn(Boolean.TRUE);
        e = Assertions.assertThrows(ExceptionPilotConMismoNombre.class,()-> rp.RegistrarPiloto(Piloto.Instance(UUID.randomUUID(),"Franco","50123321", LocalDate.MIN)));
        verify(BD,never()).GuardarPiloto(Piloto.Instance(UUID.randomUUID(),"Franco","50123321",LocalDate.MIN));
    }
    @Test
    void PilotoConDistintoNombre() {
        UUID id = UUID.randomUUID();
        Piloto p1 = Piloto.Instance(id,"Alex","50123321",LocalDate.MIN);
        RegistrarPiloto rp = new RegistrarPiloto(BD);
        when(BD.ExistePiloto("Alex")).thenReturn(Boolean.FALSE);
        when(BD.GuardarPiloto(p1)).thenReturn(p1.getID());

        Assertions.assertDoesNotThrow(()-> rp.RegistrarPiloto(p1));
        Assertions.assertEquals(id,rp.RegistrarPiloto(p1));
    }
    @Test
    void PilotoConDistintoNombreFallaCarga() {
        Exception e;
        UUID id = UUID.randomUUID();
        Piloto p1 = Piloto.Instance(id,"Alex","50123321",LocalDate.MIN);
        RegistrarPiloto rp = new RegistrarPiloto(BD);
        when(BD.ExistePiloto("Alex")).thenReturn(Boolean.FALSE);
        when(BD.GuardarPiloto(p1)).thenReturn(null);

        e = Assertions.assertThrows(ExceptionFalloElRegistro.class,()-> rp.RegistrarPiloto(p1));
        Assertions.assertEquals("Fallo el registro del Piloto",e.getMessage());
    }
}
