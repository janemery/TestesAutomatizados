package br.treinamento;

//deve ter 100% de cobertura de teste

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Test;

public class EntidadeNegocioTest {

	EntidadeNegocio classeNegocio;
	
	@Test
	public void DeveriaValidarCamposObrigatorios() throws Exception {
		Entidade entidadeAtual;
		Entidade entidadeEsperada;
		Entidade entidadeEntrada;
		
		//cenario1: deveria salvar com sucesso
		entidadeEntrada = getEntidadeValida();
		
		entidadeEsperada = getEntidadeValida();
		entidadeEsperada.setId((long)1);
		
		EntidadeDAO persistencia = new EntidadeDAO();
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
			assertEquals("Cenario4: Não deve salvar com campo email não preenchido.", "O email é obrigatório", e.getMessage());
		}
		
	}
	private Entidade getEntidadeValida() {
		// TODO Auto-generated method stub
		return null;
	}

}
