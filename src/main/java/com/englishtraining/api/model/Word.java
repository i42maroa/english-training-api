package com.englishtraining.api.model;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class Word {
  OffsetDateTime creationDate;
  OffsetDateTime lastModificationDate;
  String name;
  String pronunciation;
  List<WordDefinition> definitions;
}