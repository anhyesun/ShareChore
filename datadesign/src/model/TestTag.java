package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class TestTag {
  private Tag tag;
  private Set<Tag> tags;

  @BeforeEach
  void runBefore() {
    tag = new Tag("Clean the room");
    tags = new HashSet<>();
  }

  @Test
  void testSameId() {

  }

}
