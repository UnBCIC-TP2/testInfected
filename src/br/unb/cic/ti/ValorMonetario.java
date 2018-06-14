package br.unb.cic.ti;

public class ValorMonetario implements IValorMonetario {
	private double valor;
	private String moeda; 
	
	public ValorMonetario(double valor, String moeda) {
		this.valor = valor; 
		this.moeda = moeda; 
	}
	
	public IValorMonetario adiciona(IValorMonetario vm) {
		return vm.adicionar(this); 
	}
	
	public IValorMonetario adicionar(ValorMonetario vm) {
		if(((ValorMonetario) vm).getMoeda().equals(this.moeda)) {
			return new ValorMonetario(this.valor + ((ValorMonetario)vm).valor, this.moeda);
		}
		else {
			return new CarteiraValorMonetario(new ValorMonetario[]{this, (ValorMonetario)vm});
		}
	}
	
	public IValorMonetario adicionar(CarteiraValorMonetario vm) {
		return vm.adicionar(this);
	}
	
	public Double getValor() {
		return valor; 
	}

	public String getMoeda() {
		return moeda; 
	}
	@Override
	public String toString() {
		return moeda + ": " + valor; 
	}
	
	@Override
	public boolean equals(Object other) {
		return other instanceof ValorMonetario && 
		  ((ValorMonetario)other).moeda.equals(this.moeda) &&
		  ((ValorMonetario)other).valor == this.valor;
	}
 }
