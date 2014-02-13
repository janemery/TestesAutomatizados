package br.treinamento;

import static org.junit.Assert.*;

import java.util.Calendar;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExercicioJUnitTest {
	private ExercicioJUnit negocio;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void SetupBefore(){
		negocio = new ExercicioJUnit();
	}
	
	@After
	public void SetupAfter(){
		negocio = new ExercicioJUnit();
	}
	
	//validando CPF valido
	@Test
	public void deveriaVerificarCPFValido(){
		Assert.assertTrue(negocio.validaCPF("00398372896"));
		Assert.assertTrue(negocio.validaCPF("28595339660"));
		Assert.assertTrue(negocio.validaCPF("60424242893"));
	}
	
	//validando CPF invalido
	@Test
	public void deveriaVerificarCPFsInvalidos(){
		Assert.assertFalse(negocio.validaCPF("00000000000"));
		Assert.assertFalse(negocio.validaCPF("11111111111"));
		Assert.assertFalse(negocio.validaCPF("22222222222"));
		Assert.assertFalse(negocio.validaCPF("33333333333"));
		Assert.assertFalse(negocio.validaCPF("44444444444"));
		Assert.assertFalse(negocio.validaCPF("55555555555"));
		Assert.assertFalse(negocio.validaCPF("66666666666"));
		Assert.assertFalse(negocio.validaCPF("77777777777"));
		Assert.assertFalse(negocio.validaCPF("88888888888"));
		Assert.assertFalse(negocio.validaCPF("99999999999"));
		Assert.assertFalse(negocio.validaCPF("0039837289"));
		Assert.assertFalse(negocio.validaCPF("96257426101"));
	}
	
	@Test
	public void deveriaVerificarCamposObrigatorios() {
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
		
		try {
			// quando ocorre uma acao
			boolean isValid = negocio.verificarEntidadeValida(entidade);

			// entao espera um resultado
			Assert.assertTrue("Deveria verificar os campos obrigatorios", isValid);
		} catch (Exception e) {
			Assert.fail("Erro inesperado: " + e.getMessage());
		}		
	}
	
	@Test
	public void deveriaVerificarNomeVazio(){
		//dada a entidade com nome vazio
		Entidade entidade = new Entidade();
		entidade.setNome("");
		entidade.setNumeroDocumento(123456789L);
		entidade.setTipoDocumento(1);
		//quando verificar entidade valida
		
		try {
			// quando ocorre uma acao
			negocio.verificarEntidadeValida(entidade);

			// deveria gerar uma validação da exceção
			Assert.fail("Deveria verificar nome não informado");
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}
	
	@Test
	public void deveriaVerificarNomeNulo(){
		//dada a entidade com nome vazio
		Entidade entidade = new Entidade();
		entidade.setNome(null);
		entidade.setNumeroDocumento(123456789L);
		entidade.setTipoDocumento(1);
		//quando verificar entidade valida
		
		try {
			// quando ocorre uma acao
			negocio.verificarEntidadeValida(entidade);

			// deveria gerar uma validação da exceção
			Assert.fail("Deveria verificar campo nome nulo");
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}
	}
	
	@Test
	public void deveriaVerificarNomeMuitoPequeno(){
		//dada a entidade com nome pequeno
			Entidade entidade = new Entidade();
			entidade.setNome("Jane");
			entidade.setNumeroDocumento(123456789L);
			entidade.setTipoDocumento(1);
			//quando verificar entidade valida
				
			try {
			// quando ocorre uma acao
			negocio.verificarEntidadeValida(entidade);

			// deveria gerar uma validação da exceção
			Assert.fail("Deveria verificar nome muito pequeno");
			} catch (Exception e) {
			}
	}
	
	@Test
	public void deveriaVerificarNomeMuitoGrande(){
		//dada a entidade com nome pequeno
			Entidade entidade = new Entidade();
			entidade.setNome("Jane Mery Chaves Ferreira Gondim Sampaio de Alencar Furtado");
			entidade.setNumeroDocumento(123456789L);
			entidade.setTipoDocumento(1);
			//quando verificar entidade valida
				
			try {
			// quando ocorre uma acao
			negocio.verificarEntidadeValida(entidade);

			// deveria gerar uma validação da exceção
			Assert.fail("Deveria verificar nome muito grande");
			} catch (Exception e) {
				assertNotNull(e.getMessage());
			}
	}
	
	@Test
	public void deveriaVerificarNumeroDeDocumentoValido() {
		// dado uma condicao
		Entidade entidade = new Entidade();
		// nome minimo 5 caracteres
		entidade.setNome("Jane Mery");
		entidade.setNumeroDocumento(5600098756L);
		// tipos de documento: [1,2]
		entidade.setTipoDocumento(1);

		try {
			// quando ocorre uma acao
			boolean isValid = negocio.verificarEntidadeValida(entidade);

			// entao espera um resultado
			Assert.assertTrue("Deveria verificar o numero de documento válido", isValid);
		} catch (Exception e) {
			Assert.fail("Erro inesperado: " + e.getMessage());
		}		
	}
	
	@Test
	public void deveriaVerificarNumeroDeDocumentoVazio() {
		// dado uma condicao
		Entidade entidade = new Entidade();
		// nome minimo 5 caracteres
		entidade.setNome("Jane Mery");
		entidade.setNumeroDocumento(null);
		// tipos de documento: [1,2]
		entidade.setTipoDocumento(1);

		try {
			// quando ocorre uma acao
			negocio.verificarEntidadeValida(entidade);

			// entao espera um resultado
			Assert.fail("Deveria verificar o numero de documento vazio");
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}		
	}
	
	//deveriaVerificarDataInicialInvalida()
	@Test
	public void deveriaVerificarDataInicialInvalida() {
		// dado uma condicao
		Entidade entidade = new Entidade();
		// nome minimo 5 caracteres
		entidade.setNome("Jane Mery");
		entidade.setNumeroDocumento(5600098756L);
		// tipos de documento: [1,2]
		entidade.setTipoDocumento(1);
		
		Calendar dataInicial = Calendar.getInstance();
		dataInicial.set(2014, 02, 01);
		entidade.setDataInicial(dataInicial.getTime());

		try {
			// quando ocorre uma acao
			negocio.verificarEntidadeValida(entidade);

			// entao espera um resultado
			Assert.fail("Deveria verificar a data inicial inválida");
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}		
	}
	
	@Test
	public void deveriaVerificarDataFinalInvalida() {
		// dado uma condicao
		Entidade entidade = new Entidade();
		// nome minimo 5 caracteres
		entidade.setNome("Jane Mery");
		entidade.setNumeroDocumento(5600098756L);
		// tipos de documento: [1,2]
		entidade.setTipoDocumento(1);
		
		Calendar dataInicial = Calendar.getInstance();
		dataInicial.set(2014, 02, 02);
		entidade.setDataInicial(dataInicial.getTime());

		Calendar dataFinal = Calendar.getInstance();
		dataFinal.set(2014, 02, 01);
		entidade.setDataFinal(dataFinal.getTime());

		try {
			// quando ocorre uma acao
			negocio.verificarEntidadeValida(entidade);

			// entao espera um resultado
			Assert.fail("Deveria verificar a data final inválida");
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}		
	}

	@Test
	public void deveriaVerificarTipoDeDocumentoNulo() {
		// dado uma condicao
		Entidade entidade = new Entidade();
		// nome minimo 5 caracteres
		entidade.setNome("Jane Mery");
		entidade.setNumeroDocumento(5600098756L);
		// tipos de documento: [1,2]
		entidade.setTipoDocumento(null);
		
		Calendar dataFinal = Calendar.getInstance();
		dataFinal.set(2014, 02, 01);
		entidade.setDataFinal(dataFinal.getTime());

		try {
			// quando ocorre uma acao
			negocio.verificarEntidadeValida(entidade);

			// entao espera um resultado
			Assert.fail("Deveria verificar a data final inválida");
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}		
	}

	@Test
	public void deveriaVerificarTipoDeDocumentoInvalido() {
		// dado uma condicao
		Entidade entidade = new Entidade();
		// nome minimo 5 caracteres
		entidade.setNome("Jane Mery");
		entidade.setNumeroDocumento(5600098756L);
		// tipos de documento: [1,2]
		entidade.setTipoDocumento(0);
		
		Calendar dataFinal = Calendar.getInstance();
		dataFinal.set(2014, 02, 01);
		entidade.setDataFinal(dataFinal.getTime());
		
		try {
			// quando ocorre uma acao
			negocio.verificarEntidadeValida(entidade);
			
			// entao espera um resultado
			Assert.fail("Deveria verificar tipo do documento valido: 1 ou 2");
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}		
	}
	//deveriaVerificarEmailValido()
	//deveriaVerificarEmailInvalido()
	//deveriaVerificarCPFDigitoVerificadorInvalido()

	@Test
	public void deveriaVerificarDataInicialMenorQueAtual() {
		// dado uma condicao
		Entidade entidade = new Entidade();
		// nome minimo 5 caracteres
		entidade.setNome("Jane Mery");
		entidade.setNumeroDocumento(5600098756L);
		// tipos de documento: [1,2]
		entidade.setTipoDocumento(1);
		
		Calendar dataInicial = Calendar.getInstance();
		dataInicial.set(2013, 02, 02);
		entidade.setDataInicial(dataInicial.getTime());

		Calendar dataFinal = Calendar.getInstance();
		dataFinal.set(2014, 02, 01);
		entidade.setDataFinal(dataFinal.getTime());

		try {
			// quando ocorre uma acao
			negocio.verificarEntidadeValida(entidade);

			// entao espera um resultado
			Assert.fail("Deveria verificar a data inicial maior que atual");
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}		
	}

	@Test
	public void deveriaVerificarEmailComArroba() {
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
		entidade.setEmail("teste.gmail.com");
		
		Calendar dataFinal = Calendar.getInstance();
		dataFinal.set(2014, 02, 20);
		entidade.setDataFinal(dataFinal.getTime());
		
		try {
			// quando ocorre uma acao
			boolean isValid = negocio.verificarEntidadeValida(entidade);

			// entao espera um resultado
			Assert.assertTrue("Deveria verificar os campos obrigatorios", isValid);
		} catch (Exception e) {
			Assert.fail("Erro inesperado: " + e.getMessage());
		}		
	}

	@Test
	public void deveriaVerificarEmailComPonto() {
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
		entidade.setEmail("testegmailcom");
		
		Calendar dataFinal = Calendar.getInstance();
		dataFinal.set(2014, 02, 20);
		entidade.setDataFinal(dataFinal.getTime());
		
		try {
			negocio.verificarEntidadeValida(entidade);
			Assert.fail("Deveria validar formato do email");
		} catch (Exception e) {
			assertNotNull(e.getMessage());
		}		
	}
}
