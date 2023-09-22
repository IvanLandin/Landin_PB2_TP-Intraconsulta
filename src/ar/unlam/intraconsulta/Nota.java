package ar.unlam.intraconsulta;

public class Nota {

	private Integer valor;
	private TipoDeNota tipo;
	
	public Nota(Integer valor, TipoDeNota tipo) {
		this.valor = valor;
		this.tipo = tipo;
	}

	public Integer getValor() {
		return valor;
	}

	public TipoDeNota getTipo() {
		return tipo;
	}
	
	@Override
	public String toString() {
		return valor.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Nota)
			return valor.equals(((Nota)obj).getValor()) && tipo.equals(((Nota)obj).getTipo());
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return valor + tipo.ordinal();
	}
}
