package br.treinamento;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BasicoTest {
	private Basico negocio;
	
	@Rule
	public ExpectedException thrown= ExpectedException.none();
	
	@BeforeClass
	public static void setUpBeforeClass(){
		System.out.println("SetUp do BeforeClass: uma ?nica vez");
	}
	
	@AfterClass
	public static void tearDownAfterClass(){
		System.out.println("Tear Down do AfterClass: uma ?nica vez");
	}
	
	@Before
	public void setUpBefore(){
		System.out.println("SetUp do Before");
		negocio = new Basico();
	}
	
	@After
	public void tearDownAfter(){
		System.out.println("Tear Down After");
		negocio = null;
	}

	@Test
	public void testSomar(){
		assertEquals(negocio.somar(1, 1), 2);
		assertThat(negocio.somar(1, 1), is(2));
		assertThat(negocio.somar(1, 1), is(not(3)));
	}
	
	@Test
	public void testDividir() throws Exception{
		assertEquals(((double)10 / 3), 3.33, 0.009);
	}
	
	@Test(expected = Exception.class)
	public void testDividirPorZero_elegante() throws Exception{
		negocio.dividir("10", "0");
	}
	
	@Test
	public void testDividirPorZero_robusto(){
		try{
			assertEquals(negocio.dividir("10", "0"), 10d, 0d);
			fail("N?o deveria chegar nesta linha");
		} catch (Exception e) {
			assertEquals("/ by zero", e.getMessage());
		}
	}
	
	@Test
	public void testDividirPorZero_regra(){
		thrown.expect(Exception.class);
		thrown.expectMessage(is("/ by zero"));
		
		assertEquals(negocio.dividir("10", "0"), 10d, 0d);
	}
	
	@Ignore("N?o lembro como faz isso")
	@Test
	public void testMDC(){
		assertEquals(negocio.mdc(1, 2, 3), 100);
	}
}
