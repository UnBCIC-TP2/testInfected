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
		return vm.adicionar(this);
	}
	
	@Override
	public IValorMonetario adicionar(ValorMonetario vm) {
		ValorMonetario valor = (ValorMonetario)vm; 
		ValorMonetario soma = null;
		if(valores.containsKey(valor.getMoeda())) {
			ValorMonetario existente = valores.get(valor.getMoeda());
			soma = new ValorMonetario(existente.getValor() + valor.getValor(), valor.getMoeda());		
		} else {
			soma = valor; 
		}
		valores.put(soma.getMoeda(), soma);
		return this; 
	}

	@Override
	public IValorMonetario adicionar(CarteiraValorMonetario vm) {
		CarteiraValorMonetario outraCarteira = (CarteiraValorMonetario)vm;
		outraCarteira.valores.values().forEach(v -> this.adiciona(v));
		return this;
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
