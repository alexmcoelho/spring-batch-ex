package br.com.springbatch.manip;

import java.util.Comparator;
import java.util.List;

import br.com.springbatch.model.Sale;
import br.com.springbatch.model.Salesman;

public class ManipValues {
	
	public static Long maxVenda(List<Sale> sales) {
		Long max = sales
			      .stream()
			      .max(Comparator.comparing(Sale::getTotal))
			      .map(s -> s.getIdSale())
			      .orElseThrow(null);
		return max;
	}
	
	public static Salesman mixVenda(List<Sale> sales) {
		Salesman max = sales
			      .stream()
			      .min(Comparator.comparing(Sale::getTotal))
			      .map(s -> s.getSalesman())
			      .orElseThrow(null);
		return max;
	}

}
