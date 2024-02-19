package com.englishtraining.api.model;

import com.englishtraining.api.domain.WordExample;
import lombok.Data;

import java.util.List;

@Data
public class Word {
  String translation;
  String explanation;
  List<WordExample> examples;
}