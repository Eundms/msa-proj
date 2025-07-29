package com.example.batch_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.batch_service.feign.ExternalFeignClient;
import com.example.batch_service.feign.InternalFeignClient;
import com.example.batch_service.reader.MyItemReader;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
class MyItemReaderTest {

  @Mock
  private InternalFeignClient internalFeignClient;
  @Mock
  private ExternalFeignClient externalFeignClient;

  @InjectMocks
  private MyItemReader reader;

  @Test
  void testReader() {
    Mockito.when(internalFeignClient.getBatchTargets())
        .thenReturn(List.of("1"));

    // init() 직접 호출해서 Mock 데이터 세팅
    reader.init();
    String result = reader.read();

    Assertions.assertEquals("EXTERNAL_DATA", result);
  }
}
