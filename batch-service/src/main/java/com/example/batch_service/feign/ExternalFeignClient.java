package com.example.batch_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "externalClient", url = "${external.api.url}")
public interface ExternalFeignClient {
  @GetMapping("/api/external-data/{id}")
  String getData(@PathVariable("id") String id);
}