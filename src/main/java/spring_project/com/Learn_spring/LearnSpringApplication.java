package spring_project.com.Learn_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

@EnableTransactionManagement
@SpringBootApplication
public class LearnSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringApplication.class, args);
//		System.out.println(Arrays.toString(SpringApplication.run(LearnSpringApplication.class, args).getEnvironment().getActiveProfiles()));
//		that will print the active profiles prod or dev or both
	}
	@Bean
	public PlatformTransactionManager fdsd(MongoDatabaseFactory databaseFactory)
	{
		return new MongoTransactionManager(databaseFactory);
	}//this is needed for building @Transactional
}
