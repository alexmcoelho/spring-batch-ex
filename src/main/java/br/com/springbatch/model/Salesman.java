package br.com.springbatch.model;

import java.math.BigDecimal;

public class Salesman extends GenericId{
	private String cpf;
	private String name;
	private BigDecimal salary;
	
	public Salesman(String name) {
		super();
		this.name = name;
	}
	public Salesman() {
		super();
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	
}
