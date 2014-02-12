package br.treinamento;

import static org.junit.Assert.*;

import java.sql.Date;
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
		System.out.println("Inicio do teste");
		negocio = new ExercicioJUnit();
	}
	
	@After
	public void SetupAfter(){
		System.out.println("final do teste");
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
		Assert.assertFalse(negocio.validaCPF("00000000001"));
		Assert.assertFalse(negocio.validaCPF("00000000002"));
		Assert.assertFalse(negocio.validaCPF("0039837289"));
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

		try {
			// quando ocorre uma acao
			boolean isValid = negocio.verificarEntidadeValida(entidade);

			// entao espera um resultado
			Assert.assertTrue("Deveria verificar os campos obrigatorios", isValid);
		} catch (Exception e) {
			Assert.fail("Erro inesperado: " + e.getMessage());
		}		
	}
	
	
}
