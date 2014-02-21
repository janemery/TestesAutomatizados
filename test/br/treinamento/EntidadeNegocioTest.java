package br.treinamento;

//deve ter 100% de cobertura de teste

import static org.junit.Assert.*;

import java.util.Calendar;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EntidadeNegocioTest {

	private EntidadeNegocio classeNegocio;
	private EntidadeDAOInterface persistencia;
	
	@Before
  	public void setUp() throws Exception {
  		persistencia = EasyMock.createMock(EntidadeDAOInterface.class);
  		classeNegocio = new EntidadeNegocio();
  		classeNegocio.setPersistencia(persistencia);
  	}
  
  	@After
  	public void tearDown() throws Exception {
  		EasyMock.reset(persistencia);
  	}
	
	@Test
	public void DeveriaValidarCamposObrigatorios() throws Exception {
		Entidade entidadeAtual;
		Entidade entidadeEsperada;
		Entidade entidadeEntrada;
		
		//cenario1: deveria salvar com sucesso
		entidadeEntrada = getEntidadeValida();
		
		entidadeEsperada = getEntidadeValida();
		entidadeEsperada.setId((long)1);
		
		EntidadeDAO persistencia = EasyMock.createMock(EntidadeDAO.class);
		EasyMock.reset(persistencia);
		EasyMock.expect(persistencia.salvar(entidadeEntrada)).andReturn(entidadeEsperada);
		EasyMock.replay(persistencia);
		
		entidadeAtual = classeNegocio.salvar(entidadeEntrada);
		
		assertNotNull("Cenario1: Salvo com sucesso", entidadeAtual.getId());
		EasyMock.verify(persistencia);
		
		
		//cenario2:tenta salvar com nome não preenchido
		entidadeEntrada = getEntidadeValida();
		entidadeEntrada.setNome(null);
		
		EasyMock.reset(persistencia);
		EasyMock.replay(persistencia);
		
		try{
			entidadeAtual = classeNegocio.salvar(entidadeEntrada);
			
			fail("cenário2: Não deve salvar com campo nome não preenchido. Tentar salvar com nome nulo");
		} catch (Exception e) {
			assertEquals("cenário2: Não deve salvar com campo nome não preenchido.", "O nome é obrigatório", e.getMessage());			
		}
		
		EasyMock.verify(persistencia);
		
		
		//cenario3:tenta salvar com numero de documento não preenchido
		entidadeEntrada = getEntidadeValida();
		entidadeEntrada.setNumeroDocumento(null);
		
		EasyMock.reset(persistencia);
		EasyMock.replay(persistencia);
		
		try{
			entidadeAtual = classeNegocio.salvar(entidadeEntrada);
			
			fail("Cenario3:Não deve salvar com campo numero de documento não preenchido. Tentar salvar com numero de documento nulo.");
		} catch (Exception e) {
			assertEquals("cenário3: Não deve salvar com campo numero de documento não preenchido.", "O numero de documento é obrigatório", e.getMessage());
		}
		
		EasyMock.verify(persistencia);
		
		
		//cenario4:tenta salvar com tipo de documento não preenchido
		entidadeEntrada = getEntidadeValida();
		entidadeEntrada.setTipoDocumento(null);
		
		EasyMock.reset(persistencia);
		EasyMock.replay(persistencia);
		
		try{
			entidadeAtual = classeNegocio.salvar(entidadeEntrada);
			
			fail("Cenario4: Não deve salvar com campo tipo de documento não preenchido. Tentar salvar com tipo de documento nulo.");
		} catch (Exception e) {
			assertEquals("Cenario4: Não deve salvar com campo tipo de documento não preenchido.", "O tipo de documento é obrigatório", e.getMessage());			
		}
		
		EasyMock.verify(persistencia);
		
		
		//cenario5: tenta salvar com email não preenchido
		entidadeEntrada = getEntidadeValida();
		entidadeEntrada.setEmail(null);
		
		EasyMock.reset(persistencia);
		EasyMock.replay(persistencia);
		
		try{
			entidadeAtual = classeNegocio.salvar(entidadeEntrada);
			
			fail("Cenario5: Não deve salvar com campo email não preenchido. Tentar salvar com email nulo.");
		} catch (Exception e) {
			assertEquals("Cenario5: Não deve salvar com campo email não preenchido.", "O email é obrigatório", e.getMessage());
		}
		
		EasyMock.verify(persistencia);
	}
	
	@Test
	public void testValidarRegras() throws Exception {
		Entidade entidadeAtual;
		Entidade entidadeEntrada;
		Entidade entidadeEsperada;
		Calendar calendario;
		
		//Cenario1:deveria Salvar Com Sucesso
		entidadeEntrada = getEntidadeValida();
		
		entidadeEsperada = getEntidadeValida();
		entidadeEsperada.setId((long)1);
		
		EasyMock.reset(persistencia);
		//EasyMock.expect(persistencia.VerificarUnicidadeDoNome(entidadeEntrada)).andReturn(true);
		EasyMock.expect(persistencia.salvar(entidadeEntrada)).andReturn(entidadeEsperada);
		EasyMock.replay(persistencia);
		
		entidadeAtual = classeNegocio.salvar(entidadeEntrada);
		
		assertNotNull("Cenario1: deveria salvar com sucesso", entidadeAtual.getId());
		
		EasyMock.verify(persistencia);
		
		//Cenario2:deveria Validar Campo Nome Com Mais De 30 Caracteres
		entidadeEntrada = getEntidadeValida();
		entidadeEntrada.setNome("Jane Mery Chaves Ferreira Gondim Sampaio de Alencar");
		
		EasyMock.reset(persistencia);
		EasyMock.replay(persistencia);
		
		try {
  			entidadeAtual = classeNegocio.salvar(entidadeEntrada);
  			
  			fail("Cenario2: deveria Validar Campo Nome Com Mais De 30 Caracteres. O campo nome não deve ter mais de 30 caracteres.");
  		} catch (Exception e) {
  			assertEquals("Cenario2: deveria Validar Campo Nome Com Mais De 30 Caracteres.", "O nome não pode ter mais de 30 caracteres", e.getMessage());
  		}
		
		EasyMock.verify(persistencia);
		
		// Cenário 3: deveria Validar Campo Nome Com Menos De 5 Caracteres.
  		entidadeEntrada = getEntidadeValida();
  		entidadeEntrada.setNome("Jane");
  		
  		EasyMock.reset(persistencia);
  		EasyMock.replay(persistencia);
  		
  		try {
  			entidadeAtual = classeNegocio.salvar(entidadeEntrada);
  			
  			fail("Cenario3: Deveria Validar Campo Nome Com Menos De 5 Caracteres. O campo nome não deve ter menos de 5 caracteres.");
  		} catch (Exception e) {
  			assertEquals("Cenario3: Deveria Validar Campo Nome Com Menos De 5 Caracteres.", "O nome não pode ter menos de 5 caracteres", e.getMessage());
  		}
  		
  		EasyMock.verify(persistencia);
		
  		// Cenário 4: Deveria Validar Numero De Documento Menor ou Igual a Zero.
  		entidadeEntrada = getEntidadeValida();
  		entidadeEntrada.setNumeroDocumento((long)-1);
  		
  		EasyMock.reset(persistencia);
  		EasyMock.replay(persistencia);
  		
  		try {
  			entidadeAtual = classeNegocio.salvar(entidadeEntrada);
  			
  			fail("Cenario4: Deveria Validar Numero De Documento Menor ou Igual a Zero. O campo Numero De Documento não deve ser menor ou igual a zero.");
  		} catch (Exception e) {
  			assertEquals("Cenario4: Deveria Validar Campo Numero De Documento Menor ou Igual a Zero.", "O Numero De Documento não pode ser menor ou igual a zero", e.getMessage());
  		}
  		
  		EasyMock.verify(persistencia);
		
  		// Cenario5: Deveria Validar Data Inicial Menor Que Data Atual Não Permitida.
  		entidadeEntrada = getEntidadeValida();
  		calendario = Calendar.getInstance();
  		calendario.add(Calendar.DAY_OF_MONTH,-1);
  		entidadeEntrada.setDataInicial(calendario.getTime());
  		
  		EasyMock.reset(persistencia);
  		EasyMock.replay(persistencia);
  		
  		try {
  			entidadeAtual = classeNegocio.salvar(entidadeEntrada);
  			
  			fail("Cenario5: Deveria Validar Data Inicial Menor Que Data Atual Não Permitida. O campo Data Inicial não deve ser menor que a data atual.");
  		} catch (Exception e) {
  			assertEquals("Cenario5: Deveria Validar Data Inicial Menor Que Data Atual Não Permitida.", "A Data Inicial não pode ser menor que a data atual.", e.getMessage());
  		}
  		
  		EasyMock.verify(persistencia);
  		
  		// Cenario6: Deveria Validar Data Final Menor Que Data Inicial Não Permitida.
  		entidadeEntrada = getEntidadeValida();
  		calendario = Calendar.getInstance();
  		calendario.add(Calendar.DAY_OF_MONTH,-1);
  		entidadeEntrada.setDataFinal(calendario.getTime());
  		
  		EasyMock.reset(persistencia);
  		EasyMock.replay(persistencia);
  		
  		try {
  			entidadeAtual = classeNegocio.salvar(entidadeEntrada);
  			
  			fail("Cenario6: Deveria Validar Data Final Menor Que Data Inicial Não Permitida. O campo Data Final não deve ser menor que a data inicial.");
  		} catch (Exception e) {
  			assertEquals("Cenario6: Deveria Validar Data Final Menor Que Data Inicial Não Permitida.", "A Data Final não pode ser menor que a data inicial.", e.getMessage());
  		}
  		
  		EasyMock.verify(persistencia);
  		
  		// Cenario7: Deveria Validar Tipo de Documento Invalido.
  		entidadeEntrada = getEntidadeValida();
  		entidadeEntrada.setDataFinal(calendario.getTime());
  		
  		EasyMock.reset(persistencia);
  		EasyMock.replay(persistencia);
  		
  		try {
  			entidadeAtual = classeNegocio.salvar(entidadeEntrada);
  			
  			fail("Cenario7: Deveria Validar Tipo de Documento Invalido. O campo Tipo de Documento não deve ser diferente de 1,2.");
  		} catch (Exception e) {
  			assertEquals("Cenario7: Deveria Validar Tipo de Documento Invalido.", "O Tipo de Documento não pode ser ser diferente de 1,2.", e.getMessage());
  		}
  		
  		EasyMock.verify(persistencia);
	}
	
	private Entidade getEntidadeValida() {
		
		// dado uma condicao
		Entidade entidade = new Entidade();
		// nome minimo 5 caracteres
		entidade.setNome("Jane Mery");
		entidade.setNumeroDocumento(123456789L);
		// tipos de documento: [1,2]
		entidade.setTipoDocumento(1);
		Calendar dataInicial = Calendar.getInstance();
		dataInicial.set(2014, 02, 01);
		entidade.setDataInicial(dataInicial.getTime());
				
		Calendar dataFinal = Calendar.getInstance();
		dataFinal.set(2014, 02, 20);
		entidade.setDataFinal(dataFinal.getTime());
		return entidade;
	}

}
