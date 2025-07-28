package com.example.batch_service.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyItemMapper {
  void insertItems(List<String> items);
}