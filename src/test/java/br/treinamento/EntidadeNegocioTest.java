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
	Entidade entidadeAtual;
	Entidade entidadeEsperada;
	Entidade entidadeEntrada;
	Calendar calendario;
	
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

	  	
	//cenario1: deveria salvar com sucesso
  	@Test
  	public void DeveriaSalvarComSucesso() throws Exception{
  		
		entidadeEntrada = getEntidadeValida();
		
		entidadeEsperada = getEntidadeValida();
		entidadeEsperada.setId((long)1);
		
		//EntidadeDAO persistencia = EasyMock.createMock(EntidadeDAO.class);
		EasyMock.reset(persistencia);
  		EasyMock.expect(persistencia.verificarUnicidadeNome(entidadeEntrada)).andReturn(true);
  		EasyMock.expect(persistencia.salvar(entidadeEntrada)).andReturn(entidadeEsperada);
  		EasyMock.replay(persistencia);
  		
  		entidadeAtual = classeNegocio.salvar(entidadeEntrada);
  		assertNotNull(entidadeAtual.getId());

  	}

  	//cenario2:Deveria validar nome não preenchido
  	@Test
  	public void DeveriaValidarNomeNaoPreenchido(){
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
  	}
		
	//cenario3:Deveria validar numero de documento não preenchido
  	@Test
  	public void DeveriaValidarNumeroDeDocumentoNaoPreenchido(){
		entidadeEntrada = getEntidadeValida();
		entidadeEntrada.setNumeroDocumento(null);
		
		EasyMock.reset(persistencia);
		EasyMock.replay(persistencia);
		
		try{
			entidadeAtual = classeNegocio.salvar(entidadeEntrada);
			
			fail("Cenario3:Não deve salvar com campo numero de documento não preenchido. Tentar salvar com numero de documento nulo.");
		} catch (Exception e) {
			assertEquals("cenário3: Não deve salvar com campo numero de documento não preenchido.", "O número do documento é obrigatório", e.getMessage());
		}
		
		EasyMock.verify(persistencia);
  	}
		
	//cenario4:Deveria validar tipo de documento não preenchido
  	@Test
  	public void DeveriaValidarTipoDeDocumentoNaoPreenchido(){
		entidadeEntrada = getEntidadeValida();
		entidadeEntrada.setTipoDocumento(null);
		
		EasyMock.reset(persistencia);
		EasyMock.replay(persistencia);
		
		try{
			entidadeAtual = classeNegocio.salvar(entidadeEntrada);
			
			fail("Cenario4: Não deve salvar com campo tipo de documento não preenchido. Tentar salvar com tipo de documento nulo.");
		} catch (Exception e) {
			assertEquals("Cenario4: Não deve salvar com campo tipo de documento não preenchido.", "O tipo do documento é obrigatório", e.getMessage());			
		}
		
		EasyMock.verify(persistencia);
  	}
		
	//cenario5: Deveria validar email não preenchido
  	@Test
  	public void DeveriaValidarEmailNaoPreenchido() throws Exception {
		entidadeEntrada = getEntidadeValida();
		entidadeEntrada.setEmail(null);
		
		EasyMock.reset(persistencia);
		EasyMock.replay(persistencia);
		
		try{
			entidadeAtual = classeNegocio.salvar(entidadeEntrada);
			
			fail("Cenario5: Não deve salvar com campo email não preenchido. Tentar salvar com email nulo.");
		} catch (Exception e) {
			assertEquals("Cenario5: Não deve salvar com campo email não preenchido.", "Endereço de email inválido", e.getMessage());
		}
		
		EasyMock.verify(persistencia);
	}
		
	//Cenario2:Deveria validar nome com mais de 30 caracteres
  	@Test
	public void DeveriaValidarNomeMuitoGrande() throws Exception {
		entidadeEntrada = getEntidadeValida();
		entidadeEntrada.setNome("Jane Mery Chaves Ferreira Gondim Sampaio de Alencar");
		
		EasyMock.reset(persistencia);
		EasyMock.replay(persistencia);
		
		try {
  			entidadeAtual = classeNegocio.salvar(entidadeEntrada);
  			
  			fail("Cenario2: deveria Validar Campo Nome Com Mais De 30 Caracteres. O campo nome não deve ter mais de 30 caracteres.");
  		} catch (Exception e) {
  			assertEquals("Cenario2: deveria Validar Campo Nome Com Mais De 30 Caracteres.", "O nome não pode ter mais que 30 caracteres", e.getMessage());
  		}
		
		EasyMock.verify(persistencia);
  	}
	
  	// Cenário 3: Deveria validar nome com menos de 5 caracteres.
  	@Test
	public void DeveriaValidarNomeMuitoPequeno() throws Exception {
  		entidadeEntrada = getEntidadeValida();
  		entidadeEntrada.setNome("Jane");
  		
  		EasyMock.reset(persistencia);
  		EasyMock.replay(persistencia);
  		
  		try {
  			entidadeAtual = classeNegocio.salvar(entidadeEntrada);
  			
  			fail("Cenario3: Deveria Validar Campo Nome Com Menos De 5 Caracteres. O campo nome não deve ter menos de 5 caracteres.");
  		} catch (Exception e) {
  			assertEquals("Cenario3: Deveria Validar Campo Nome Com Menos De 5 Caracteres.", "O nome não pode ter menos que 5 caracteres", e.getMessage());
  		}
  		
  		EasyMock.verify(persistencia);
  	}
  		
  	// Cenário 4: Deveria validar numero de documento menor ou igual a zero.
  	@Test
	public void DeveriaValidarNumeroDeDocumentoInvalido() throws Exception {
  		entidadeEntrada = getEntidadeValida();
  		entidadeEntrada.setNumeroDocumento((long)-1);
  		
  		EasyMock.reset(persistencia);
  		EasyMock.replay(persistencia);
  		
  		try {
  			entidadeAtual = classeNegocio.salvar(entidadeEntrada);
  			
  			fail("Cenario4: Deveria Validar Numero De Documento Menor ou Igual a Zero. O campo Numero De Documento não deve ser menor ou igual a zero.");
  		} catch (Exception e) {
  			assertEquals("Cenario4: Deveria Validar Campo Numero De Documento Menor ou Igual a Zero.", "O número do documento deve ser maior que zero", e.getMessage());
  		}
  		
  		EasyMock.verify(persistencia);
  	}
		
  	// Cenario5: Deveria validar data inicial menor que data atual não permitida.
  	@Test
	public void DeveriaValidarDataInicialInvalida() throws Exception {
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
  			assertEquals("Cenario5: Deveria Validar Data Inicial Menor Que Data Atual Não Permitida.", "A data inicial não pode ser menor que a data atual", e.getMessage());
  		}
  		
  		EasyMock.verify(persistencia);
  	}
  		
  	// Cenario6: Deveria validar data final menor que data inicial não permitida.
  	@Test
	public void DeveriaValidarDataFinalInvalida() throws Exception {
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
  			assertEquals("Cenario6: Deveria Validar Data Final Menor Que Data Inicial Não Permitida.", "A data final não pode ser menor que a data inicial", e.getMessage());
  		}
  		
  		EasyMock.verify(persistencia);
  	}
  	//teste inserido depois
  	@Test
	public void DeveriaValidarSemDataFinalInformada() throws Exception {
  		entidadeEntrada = getEntidadeValida();
  		calendario = Calendar.getInstance();
  		entidadeEntrada.setDataInicial(calendario.getTime());
  		entidadeEntrada.setDataFinal(null);
  		
  		
  		EasyMock.reset(persistencia);
  		EasyMock.replay(persistencia);
  		
  		try {
  			entidadeAtual = classeNegocio.salvar(entidadeEntrada);
  			
  			fail("CenarioDepois: Deveria Validar Data Final não informada. O campo Data final não pode ser nulo.");
  		} catch (Exception e) {
  			assertEquals("CenarioDepois: Deveria Validar Data Final não informada.", "O período deve ser informado por completo", e.getMessage());
  		}
  		
  		EasyMock.verify(persistencia);
  	}
  	
  	// Cenario7: Deveria validar tipo de documento invalido.
  	@Test
	public void DeveriaValidarTipoDeDocumentoInvalido() throws Exception {
  		entidadeEntrada = getEntidadeValida();
  		entidadeEntrada.setTipoDocumento(4);
  		
  		EasyMock.reset(persistencia);
  		EasyMock.replay(persistencia);
  		
  		try {
  			entidadeAtual = classeNegocio.salvar(entidadeEntrada);
  			
  			fail("Cenario7: Deveria Validar Tipo de Documento Invalido. O campo Tipo de Documento não deve ser diferente de 1,2.");
  		} catch (Exception e) {
  			assertEquals("Cenario7: Deveria Validar Tipo de Documento Invalido.", "Tipo de documento inválido", e.getMessage());
  		}
  		
  		EasyMock.verify(persistencia);
  	}

  	// Cenario8: Deveria validar email invalido.
  	@Test
	public void DeveriaValidarEmailInvalido() throws Exception {
  		entidadeEntrada = getEntidadeValida();
  		entidadeEntrada.setEmail("teste.gmail.com");
  		
  		EasyMock.reset(persistencia);
  		EasyMock.replay(persistencia);
  		
  		try {
  			entidadeAtual = classeNegocio.salvar(entidadeEntrada);
  			
  			fail("Cenario8: Deveria Validar Email Invalido. O campo Email não pode ser preenchido sem arroba.");
  		} catch (Exception e) {
  			assertEquals("Cenario8: Deveria Validar Email Invalido", "Endereço de email inválido", e.getMessage());
  		}
  		
  		EasyMock.verify(persistencia);
	}
  		  	  		
  	// Cenário 1: Tenta salvar, mas unidicidade retorna false.
  	@Test
   	public void NaoPermiteGravarDuplicado() throws Exception {
  		entidadeEntrada = getEntidadeValida();

  		EasyMock.reset(persistencia);
  		EasyMock.expect(persistencia.verificarUnicidadeNome(entidadeEntrada)).andReturn(false);
  		EasyMock.replay(persistencia);

  		try {
  			entidadeAtual = classeNegocio.salvar(entidadeEntrada);

  			fail("Cenário 1: Tenta salvar, mas unidicidade retorna false. Deveria retornar uma exceção.");
  		} catch(Exception e) {
  			assertEquals("Cenário 1: Tenta salvar, mas unidicidade retorna false.", "Já existe entidade cadastrada com este nome.", e.getMessage());
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
		entidade.setEmail("teste@gmail.com");
		return entidade;
	}

}
