package br.com.springbatch.config;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.springbatch.manip.ManipText;
import br.com.springbatch.manip.ManipValues;
import br.com.springbatch.model.Client;
import br.com.springbatch.model.Sale;
import br.com.springbatch.model.Salesman;

@Component
public class WriterArchive {
	
	private StringBuilder builder = new StringBuilder();
	private static final String uploadingDir = System.getProperty("user.dir") + "/data/out/resultado.done.data";
	
	public void writeArchiveSalesman(List<Salesman> list) {
		builder.append("Quantidade de clientes no arquivo de entrada: ")
			.append(list.size())
			.append("\n");
	}
	
	public void writeArchiveClient(List<Client> list) {
		builder.append("Quantidade de vendedor no arquivo de entrada: ")
			.append(list.size())
			.append("\n");
	}
	
	public void writeArchiveSale(List<Sale> list) {
		if(list != null && list.size() > 0) {
			builder.append("ID da venda mais cara: ")
			.append(ManipValues.maxVenda(list))
			.append("\n");
		}
		if(list != null && list.size() > 0) {
			Salesman salesman = ManipValues.mixVenda(list);
			builder.append("O pior vendedor: ")
			.append(salesman.getName())
			.append("\n");
		}
	}
	
	public void writer() {
		if(builder.toString().length() > 0) {
			ManipText.writer(uploadingDir, builder.toString());
		}
	}
	
	public void clean() {
		if(builder != null) {
			builder.setLength(0);
		}
	}
	

}
