package br.unb.cic.ti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestValorMonetario {

	private ValorMonetario usd05; 
	private ValorMonetario usd24; 
	private ValorMonetario usd29; 
	private ValorMonetario brl12; 
	private ValorMonetario eur50;
	
	@Before
	public void configure() {
		usd05 = new ValorMonetario(5.0, "USD"); 
		usd24 = new ValorMonetario(24.0, "USD");
		usd29 = new ValorMonetario(29.0, "USD"); 
		brl12 = new ValorMonetario(12.0, "BRL");
		eur50 = new ValorMonetario(50.0, "EUR");
	}
	@Test
	public void testAdicionaValoresMoedasIguais() {
		ValorMonetario usd12 = new ValorMonetario(12.0, "USD");
		IValorMonetario usd36 = usd24.adiciona(usd12); 
		
		assertEquals(new ValorMonetario(36.0, "USD"), usd36);
		assertNotEquals(new ValorMonetario(36.0, "BRL"), usd36);
	}
	
	@Test
	public void testAdicionaValoresMoedasDiferentes() {
		IValorMonetario carteira =  usd24.adiciona(brl12);
		ValorMonetario[] valores = {usd24, brl12};
		IValorMonetario esperado = new CarteiraValorMonetario(valores);
		
		assertEquals(esperado, carteira); 
	}
	
	@Test 
	public void testAdicionaValorExistenteEmCarteira() {
		IValorMonetario carteira = usd24.adiciona(brl12);
		IValorMonetario esperado = new CarteiraValorMonetario(new ValorMonetario[]{usd29, brl12});
		
		assertEquals(esperado, carteira.adiciona(usd05));		
	}
	
	@Test
	public void testAdicionaCarteiraEmValor() {
		IValorMonetario carteira = usd24.adiciona(brl12); 
		IValorMonetario esperado = new CarteiraValorMonetario(new ValorMonetario[]{usd29, brl12});
		assertEquals(esperado, usd05.adiciona(carteira)); 
	}

	@Test
	public void testAdicionaValorInexistenteEmCarteira() {
		IValorMonetario carteira = usd24.adiciona(brl12); 
		
		IValorMonetario esperado = new CarteiraValorMonetario(new ValorMonetario[]{usd24, brl12, eur50});
		
		assertEquals(esperado, carteira.adiciona(eur50));
	}
	
	@Test 
	public void testAdicionaCarteiraEmCarteira() {
		IValorMonetario carteira1 = usd24.adiciona(brl12);
		IValorMonetario carteira2 = usd24.adiciona(usd05).adiciona(brl12).adiciona(eur50);
		
		IValorMonetario esperado = new CarteiraValorMonetario(new ValorMonetario[] {
			new ValorMonetario(53.0, "USD"),
			new ValorMonetario(24.0, "BRL"), 
			new ValorMonetario(50.0, "EUR")
		});
		
		assertEquals(esperado, carteira1.adiciona(carteira2)); 
	}
}
