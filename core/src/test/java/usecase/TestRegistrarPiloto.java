package usecase;

import exception.ExceptionFalloElRegistro;
import exception.ExceptionPilotConMismoDni;
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
    void PilotoConMismoDni() {
        Exception e;
        RegistrarPiloto rp = new RegistrarPiloto(BD);
        when(BD.ExistePiloto("123456789")).thenReturn(Boolean.TRUE);

        e = Assertions.assertThrows(ExceptionPilotConMismoDni.class,()-> rp.RegistrarPiloto(Piloto.Instance(UUID.randomUUID(),"Franco","123456789", LocalDate.of(2002,10,25))));
        verify(BD,never()).GuardarPiloto(Piloto.Instance(UUID.randomUUID(),"Franco","123456789",LocalDate.of(2002,10,25)));
    }
    @Test
    void PilotoConDistintoDni() {
        UUID id = UUID.randomUUID();
        Piloto p1 = Piloto.Instance(id,"Alex","123456789",LocalDate.of(2002,10,25));
        RegistrarPiloto rp = new RegistrarPiloto(BD);
        when(BD.ExistePiloto("123456789")).thenReturn(Boolean.FALSE);
        when(BD.GuardarPiloto(p1)).thenReturn(p1.getID());

        Assertions.assertDoesNotThrow(()-> rp.RegistrarPiloto(p1));
        Assertions.assertEquals(id,rp.RegistrarPiloto(p1));
    }
    @Test
    void PilotoConDistintoDniFallaCarga() {
        Exception e;
        UUID id = UUID.randomUUID();
        Piloto p1 = Piloto.Instance(id,"Alex","123456789",LocalDate.of(2002,10,25));
        RegistrarPiloto rp = new RegistrarPiloto(BD);
        when(BD.ExistePiloto("123456789")).thenReturn(Boolean.FALSE);
        when(BD.GuardarPiloto(p1)).thenReturn(null);

        e = Assertions.assertThrows(ExceptionFalloElRegistro.class,()-> rp.RegistrarPiloto(p1));
        Assertions.assertEquals("Fallo el registro del Piloto",e.getMessage());
    }
}
