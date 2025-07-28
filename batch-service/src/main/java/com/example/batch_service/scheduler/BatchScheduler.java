package com.example.batch_service.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BatchScheduler {

  private final JobLauncher jobLauncher;
  private final Job myJob;

  /**
   * 매일 새벽 2시 실행
   */
  @Scheduled(cron = "0 0 2 * * *")
  public void runJob() throws Exception {
    JobParameters jobParameters = new JobParametersBuilder()
        .addLong("time", System.currentTimeMillis()) // 매번 다른 파라미터 → 재실행 가능
        .toJobParameters();

    jobLauncher.run(myJob, jobParameters);
  }
}
