package com.example.batch_service.writer;

import com.example.batch_service.repository.MyItemMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MyItemWriter implements ItemWriter<String> {

  private final MyItemMapper myItemMapper;

  @Override
  public void write(Chunk<? extends String> chunk) {
    // Chunk -> List 변환 후 bulk insert
    myItemMapper.insertItems(new ArrayList<>(chunk.getItems()));
  }
}