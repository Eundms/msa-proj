package com.example.batch_service.config;

import com.example.batch_service.reader.MyItemReader;
import com.example.batch_service.writer.MyItemWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

  private final JobRepository jobRepository;
  private final PlatformTransactionManager transactionManager;

  @Bean
  public Step myStep(MyItemReader reader, MyItemWriter writer) {
    return new StepBuilder("myStep", jobRepository)
        .<String, String>chunk(10, transactionManager)
        .reader(reader)
        .writer(writer)
        .build();
  }

  @Bean
  public Job myJob(Step myStep) {
    return new JobBuilder("myJob", jobRepository)
        .start(myStep)
        .build();
  }
}
