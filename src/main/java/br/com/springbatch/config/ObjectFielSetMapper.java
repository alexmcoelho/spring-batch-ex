package br.com.springbatch.config;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import br.com.springbatch.manip.ReturnObject;
import br.com.springbatch.model.Client;
import br.com.springbatch.model.ItemSale;
import br.com.springbatch.model.Sale;
import br.com.springbatch.model.Salesman;
import br.com.springbatch.validation.ValidCNPJ;
import br.com.springbatch.validation.ValidCPF;

@Component
public class ObjectFielSetMapper implements FieldSetMapper<Object> {
	@Override
	public Object mapFieldSet(FieldSet fieldSet) {
		if (fieldSet.getValues().length == 4 && ValidCPF.isCPF(ValidCPF.stripPointTrace(fieldSet.getValues()[1]))) {
			Salesman obj = new Salesman();
			obj.setId(Long.parseLong(fieldSet.getValues()[0]));
			obj.setCpf(fieldSet.getValues()[1]);
			obj.setName(fieldSet.getValues()[2]);
			obj.setSalary(new BigDecimal(fieldSet.getValues()[3]));
			return obj;
		}
		if (fieldSet.getValues().length == 4 && ValidCNPJ.isCNPJ(ValidCNPJ.stripPointTrace(fieldSet.getValues()[1]))) {
			Client obj = new Client();
			obj.setId(Long.parseLong(fieldSet.getValues()[0]));
			obj.setCnpj(fieldSet.getValues()[1]);
			obj.setName(fieldSet.getValues()[2]);
			obj.setBusinessArea(fieldSet.getValues()[3]);
			return obj;
		}
		if (fieldSet.getValues().length == 4 && fieldSet.getValues()[2].contains("[")) {
			Sale obj = new Sale();
			String[] itens;
			List<ItemSale> list = new ArrayList<ItemSale>();
			try {
				obj.setId(Long.parseLong(fieldSet.getValues()[0]));
				obj.setIdSale(Long.parseLong(fieldSet.getValues()[1]));
				itens = ReturnObject.separateString(fieldSet.getValues()[2]);
				for (String string : itens) {
					ItemSale itemSale = (ItemSale) ReturnObject.bindClass(ItemSale.class, 
							new String[] { "id", "quatityItem", "price" },
							ReturnObject.separateStringOnlyValues(string));
					list.add(itemSale);
				}
				obj.setLisItens(list);
				obj.setSalesman(new Salesman(fieldSet.getValues()[3]));
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return obj;
		}
		return null;
	}
	
}
