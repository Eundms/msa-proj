package com.example.batch_service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BatchJobIntegrationTest {

  @Autowired
  private JobLauncher jobLauncher;

  @Autowired
  private Job myJob;

  @Test
  void testBatchJob() throws Exception {
    JobParameters params = new JobParametersBuilder()
        .addLong("time", System.currentTimeMillis()) // 재실행 위해 파라미터 고유값 필요
        .toJobParameters();

    JobExecution execution = jobLauncher.run(myJob, params);

    assertEquals(BatchStatus.COMPLETED, execution.getStatus());
  }
}
