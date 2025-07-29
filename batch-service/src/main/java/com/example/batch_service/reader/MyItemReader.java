package com.example.batch_service.reader;

import com.example.batch_service.feign.ExternalFeignClient;
import com.example.batch_service.feign.InternalFeignClient;
import jakarta.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MyItemReader implements ItemReader<String> {

  private final InternalFeignClient internalClient;
  private final ExternalFeignClient externalClient;

  private List<String> targets;
  private int currentIndex = 0;

  @PostConstruct
  public void init() {
    // Internal API에서 대상 조회
    this.targets = internalClient.getBatchTargets();
    if (targets == null) {
      targets = Collections.emptyList();
    }
  }

  @Override
  public String read() {
    if (currentIndex >= targets.size()) {
      return null;
    }

    String id = targets.get(currentIndex++);
    return externalClient.getData(id); // External API 호출 결과 반환
  }
}
