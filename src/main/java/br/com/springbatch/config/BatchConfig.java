package br.com.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
 
@Configuration
@EnableBatchProcessing
public class BatchConfig  
{ 
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
     
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    
    @Value("file:data/in/*.dat")
    private Resource[] inputResources;
    
    @Bean
    public Job readCSVFilesJob() {
        return jobBuilderFactory
                .get("readCSVFilesJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }
 
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").<Object, Object>chunk(5)
                .reader(multiResourceItemReader())
                .writer(writer())
                .build();
    }
    
    @Bean
    public MultiResourceItemReader<Object> multiResourceItemReader() {
    	MultiResourceItemReader<Object> resourceItemReader = new MultiResourceItemReader<Object>();
    	resourceItemReader.setResources(inputResources);
        resourceItemReader.setDelegate(reader());
        return resourceItemReader;
    }
 
    @Bean
    public FlatFileItemReader<Object> reader() 
    {
        //Create reader instance
        FlatFileItemReader<Object> reader = new FlatFileItemReader<Object>();
        //Configure how each line will be parsed and mapped to different values
        reader.setLineMapper(lineMapper());
        return reader;
    }
    
    @Bean
    public LineMapper<Object> lineMapper() {
        final DefaultLineMapper<Object> defaultLineMapper = new DefaultLineMapper<>();
        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter("ç");
        lineTokenizer.setStrict(false);
        final ObjectFielSetMapper fieldSetMapper = new ObjectFielSetMapper();
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }
     
    @Bean
    public ConsoleItemWriter<Object> writer() 
    {
        return new ConsoleItemWriter<Object>();
    }
}
