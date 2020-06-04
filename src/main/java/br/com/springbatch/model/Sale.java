package br.com.springbatch.model;

import java.math.BigDecimal;
import java.util.List;

public class Sale extends GenericId{
	
	private Long idSale;
	private List<ItemSale> lisItens;
	private Salesman salesman;
	private BigDecimal total;//genarate
	
	public Sale() {
		super();
		this.salesman = new Salesman();
	}
	
	public Long getIdSale() {
		return idSale;
	}
	public void setIdSale(Long idSale) {
		this.idSale = idSale;
	}
	public List<ItemSale> getLisItens() {
		return lisItens;
	}
	public void setLisItens(List<ItemSale> lisItens) {
		this.lisItens = lisItens;
	}
	public Salesman getSalesman() {
		return salesman;
	}
	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}

	public BigDecimal getTotal() {
		total = lisItens.stream()
				.map(ItemSale::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericId other = (GenericId) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
