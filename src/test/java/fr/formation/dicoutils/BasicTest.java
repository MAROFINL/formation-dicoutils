package fr.formation.dicoutils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BasicTest {
	private DicoLoader loader;
	private InputStream is;
	
	
	@Before
	public void  initialize() {
		this.loader = new ClasspathDicoLoader();
		this.is = this.getClass().getResourceAsStream("/dictionnaire.txt");
	}

	@After
	public void destroy() throws IOException {
		this.is.close();
	}
	
	
	@Test
	public void firstTest() {
		Assert.assertNotNull("Le fichier n'existe pas",this.is);
		String[] results = loader.loadFile(this.is);
		Assert.assertNotNull("Lecture du fichier KO",results);
		Assert.assertEquals("Le fichier n'a pas le bon nombre de mots", 336532, results.length);
	}
	
	@Test
	public void advancedTest() {
		if (this.is != null) {
			String[] results = loader.loadFile(this.is);
			Assert.assertThat(Arrays.asList(results), CoreMatchers.hasItem("Super"));
		}
		
	}
}
