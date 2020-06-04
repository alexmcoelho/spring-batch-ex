package br.com.springbatch.model;

public abstract class GenericId {
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}