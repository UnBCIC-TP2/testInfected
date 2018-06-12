package br.unb.cic.ti;

import java.util.Hashtable;

public class CarteiraValorMonetario implements IValorMonetario {

	private Hashtable<String, ValorMonetario> valores; 
	
	public CarteiraValorMonetario(ValorMonetario[] valores) {
		this.valores = new Hashtable<>();
		for(ValorMonetario vm: valores) {
			this.valores.put(vm.getMoeda(), vm);
		}
	}
	
	public IValorMonetario adiciona(IValorMonetario vm) {
		if(vm instanceof ValorMonetario) {
			ValorMonetario valor = (ValorMonetario)vm; 
			ValorMonetario soma = null;
			if(valores.contains(valor.getMoeda())) {
				ValorMonetario existente = valores.get(valor.getMoeda());
				soma = new ValorMonetario(existente.getValor() + valor.getValor(), valor.getMoeda());		
			} else {
				soma = valor; 
			}
			valores.put(soma.getMoeda(), soma);
			return this; 
		}
		throw new RuntimeException("ainda nao implementado");
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof CarteiraValorMonetario) {
			CarteiraValorMonetario carteira = (CarteiraValorMonetario)other;
			
			return this.valores.keySet()
			       .stream()
			       .allMatch(moeda -> carteira.valores.containsKey(moeda) 
			    		   && carteira.valores.get(moeda).equals(this.valores.get(moeda)));
		
		}
		return false; 
	}
}
