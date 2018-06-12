package br.unb.cic.ti;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestValorMonetario {

	@Test
	public void testAdicionaValoresMoedasIguais() {
		ValorMonetario usd24 = new ValorMonetario(24.0, "USD");
		ValorMonetario usd12 = new ValorMonetario(12.0, "USD");
		IValorMonetario usd36 = usd24.adiciona(usd12); 
		
		assertEquals(new ValorMonetario(36.0, "USD"), usd36);
		assertNotEquals(new ValorMonetario(36.0, "BRL"), usd36);
	}
	
	@Test
	public void testAdicionaValoresMoedasDiferentes() {
		ValorMonetario usd24 = new ValorMonetario(24.0, "USD");
		ValorMonetario brl12 = new ValorMonetario(12.0, "BRL");
		IValorMonetario carteira =  usd24.adiciona(brl12);
		ValorMonetario[] valores = {usd24, brl12};
		IValorMonetario esperado = new CarteiraValorMonetario(valores);
		
		assertEquals(esperado, carteira); 
	}
	
	@Test 
	public void testAdicionaValorEmCarteira() {
		ValorMonetario usd24 = new ValorMonetario(24.0, "USD");
		ValorMonetario usd05 = new ValorMonetario(5.0, "USD"); 
		ValorMonetario brl12 = new ValorMonetario(12.0, "BRL");
		ValorMonetario usd29 = new ValorMonetario(29.0, "USD"); 
		IValorMonetario carteira = usd24.adiciona(brl12);
		IValorMonetario esperado = new CarteiraValorMonetario(new ValorMonetario[]{usd29, brl12});
		

		assertEquals(esperado, carteira.adiciona(usd05));		
	}

}
