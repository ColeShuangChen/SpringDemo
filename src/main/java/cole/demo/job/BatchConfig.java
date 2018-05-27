package cole.demo.job;

import cole.demo.model.City;
import cole.demo.service.CityService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    private CityService cityService;
    @Autowired
    private RestTemplate rest;
    @Autowired
    private EntityManagerFactory emf;
    @Bean
    public ItemReader<City> reader() throws Exception {
        CityItemReader cityItemReader = new CityItemReader(cityService, rest);
        return cityItemReader;
    }

    @Bean
    public org.springframework.batch.item.ItemProcessor processor() {
        ItemProcessor processor = new ItemProcessor();//1使用我们自己定义的ItemProcessor的实现ItemProcessor。
        processor.setValidator(BeanValidator());//2为processor指定校验器为BeanValidator；
        return processor;
    }

    @Bean
    public ItemWriter<City> writer(DataSource dataSource) {
        JpaItemWriter<City> writer=new JpaItemWriter<>();
        writer.setEntityManagerFactory(emf);

        return writer;
    }

    @Bean
    public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager)
            throws Exception {
        //jobRepository的定义需要dataSource和transactioManager，Spring Boot已为我们自动配置了这两个类，Spring可通过方法注入已有的Bean。
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setDatabaseType("mysql");
        return jobRepositoryFactoryBean.getObject();
    }

    @Bean
    public SimpleJobLauncher jobLauncher(DataSource dataSource, PlatformTransactionManager transactionManager)
            throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository(dataSource, transactionManager));
        return jobLauncher;
    }

    @Bean
    public Job importJob(JobBuilderFactory jobs, Step s1) {
        return jobs.get("importJob")
                .incrementer(new RunIdIncrementer())
                .flow(s1) //1为Job指定Step。
                .end()
                .listener(JobListener()) //2绑定监听器JobListener。
                .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<City> reader, ItemWriter<City> writer,
                      org.springframework.batch.item.ItemProcessor processor) {
        return stepBuilderFactory
                .get("step1")
                .<City, City>chunk(2)//1批处理每次提交65000条数据。
                .reader(reader)//2给step绑定reader。
                .processor(processor)//3给step绑定processor。
                .writer(writer)//4给step绑定writer。
                .build();
    }

    @Bean
    public JobListener JobListener() {
        return new JobListener();
    }

    @Bean
    public Validator<City> BeanValidator() {
        return new BeanValidator<City>();
    }
}