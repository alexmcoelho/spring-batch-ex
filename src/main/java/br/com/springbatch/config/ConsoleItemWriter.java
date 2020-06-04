package br.com.springbatch.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.springbatch.model.Client;
import br.com.springbatch.model.Sale;
import br.com.springbatch.model.Salesman;
 
public class ConsoleItemWriter<T> implements ItemWriter<T> { 
	
	@Autowired
	private WriterArchive writerArchive;
	
    @Override
    public void write(List<? extends T> items) throws Exception { 
    	List<Salesman> listSalesman = new ArrayList<Salesman>();
    	List<Client> listClient = new ArrayList<Client>();
    	List<Sale> listSales = new ArrayList<Sale>();
        for (T item : items) {
        	if(item instanceof Salesman) {
        		listSalesman.add((Salesman) item);
        	}
        	if(item instanceof Client) {
        		listClient.add((Client) item);
        	}
        	if(item instanceof Sale) {
        		listSales.add((Sale) item);
        	}
        } 
        writerArchive.clean();
        writerArchive.writeArchiveSalesman(listSalesman);
        writerArchive.writeArchiveClient(listClient);
        writerArchive.writeArchiveSale(listSales);
        writerArchive.writer();
    } 
    
    
    
}
