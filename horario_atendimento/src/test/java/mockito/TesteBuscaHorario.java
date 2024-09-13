package mockito;

import br.inatel.c214.BuscaHorario;
import br.inatel.c214.Horarios;
import br.inatel.c214.HorariosService;
import constante.HorariosConst;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)
public class TesteBuscaHorario {

    @Mock
    private HorariosService service;
    private BuscaHorario buscaHorario;

    @Before
    public void setup(){
        buscaHorario = new BuscaHorario(service);
    }

    @Test
    public void testeBuscaHorarioChris(){
        Mockito.when(service.busca(55)).thenReturn(HorariosConst.C214);

        Horarios chris = buscaHorario.buscaHorarios(55);
        //Faz assertion
        assertEquals("Chris Lima", chris.getNome());
        assertEquals("17:30", chris.getHorarioDeAtendimento());
        assertEquals("integral", chris.getPeriodo());
        assertEquals("3", chris.getSala());
        assertEquals("1", chris.getPredio());
    }

    @Test
    public void testeBuscaHorarioExistente(){

        Mockito.when(service.horarioExistente(10)).thenReturn(true);
        //Faz a busca de um horário válido
        boolean horarioValido = service.horarioExistente(10);

        assertTrue(horarioValido);
    }


    @Test
    public void testeSalaValida(){

        Mockito.when(service.busca(10)).thenReturn(HorariosConst.C214);

        Horarios chris = buscaHorario.buscaHorarios(10);

        boolean salaValida = buscaHorario.verificaSala(chris.getSala());

        assertTrue(salaValida);
    }


    @Test
    public void testePredioValido(){

        Mockito.when(service.busca(10)).thenReturn(HorariosConst.C214);

        Horarios chris = buscaHorario.buscaHorarios(10);

        boolean predioValido = buscaHorario.verificaPredio(chris.getPredio());

        assertTrue(predioValido);
    }


    @Test
    public void testeSalaPredioValido(){

        Mockito.when(service.busca(10)).thenReturn(HorariosConst.C214);

        Horarios chris = buscaHorario.buscaHorarios(10);

        boolean salaValida = buscaHorario.verificaSalaPredio(chris.getSala(),chris.getPredio());

        assertTrue(salaValida);
    }


    @Test
    public void testeHoraAtendimentoValido(){

        Mockito.when(service.busca(10)).thenReturn(HorariosConst.C207);

        Horarios renzo = buscaHorario.buscaHorarios(10);

        boolean salaValida = buscaHorario.verificaHoraAtendimento(renzo.getHorarioDeAtendimento());

        assertTrue(salaValida);
    }


    @Test
    public void testeTurnoValido(){

        Mockito.when(service.busca(10)).thenReturn(HorariosConst.S202);

        Horarios marcelinho = buscaHorario.buscaHorarios(10);

        boolean turnoValido = buscaHorario.verificaTurno(marcelinho.getPeriodo());

        assertTrue(turnoValido);
    }

    @Test
    public void testeVerificaNomeValido(){

        Mockito.when(service.busca(10)).thenReturn(HorariosConst.M210);

        Horarios yvo = buscaHorario.buscaHorarios(10);

        boolean nomeValido = buscaHorario.verificaNome(yvo.getNome());

        assertTrue(nomeValido);
    }


    @Test
    public void testeVerificaSemConflito(){

        Mockito.when(service.busca(10)).thenReturn(HorariosConst.S202);
        Mockito.when(service.busca(11)).thenReturn(HorariosConst.T202);
        Mockito.when(service.busca(12)).thenReturn(HorariosConst.M210);

        Horarios[] professores = {buscaHorario.buscaHorarios(10),buscaHorario.buscaHorarios(11),buscaHorario.buscaHorarios(12)};

        boolean comConflito = buscaHorario.verificaConflito(professores);

        assertFalse(comConflito);
    }

    @Test
    public void testeVerificaHorarioTurnoValido(){

        Mockito.when(service.busca(10)).thenReturn(HorariosConst.C214);

        Horarios chris = buscaHorario.buscaHorarios(10);

        boolean horaTurnoValido = buscaHorario.verificaHoraTurno(chris.getHorarioDeAtendimento(), chris.getPeriodo());

        assertTrue(horaTurnoValido);
    }

}
