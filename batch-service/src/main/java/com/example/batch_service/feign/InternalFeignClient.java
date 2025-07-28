package com.example.batch_service.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "budgetClient", url = "${internal.budget-api.url}")
public interface InternalFeignClient {
  @GetMapping("/api/batch-on")
  List<String> getBatchTargets();
}
