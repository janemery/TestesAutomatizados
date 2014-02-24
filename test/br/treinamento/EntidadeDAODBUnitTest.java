package br.treinamento;

import java.io.FileInputStream;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Ignore;

@Ignore
public class EntidadeDAODBUnitTest extends DatabaseTestCase {
	//private EntidadeDAODBUnitTest persistencia;
	
	//private final static String TABELA_ENTIDADE = "entidade";
	//@Test

	
	
	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		return new DatabaseConnection(ConnectionFactory.getConnection());
	}


	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("NewFile.xml"));
	}
}
