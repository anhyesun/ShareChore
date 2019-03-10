package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMember {

  private Member member1;
  private Member member2;
  private Set<Member> members;

  @BeforeEach
  void runBefore() {
    member1 = new Member("Alice");
    members = new HashSet<>();
  }

  @Test
  void testSameId() {
    members.add(member1);
    member2 = new Member("Alice");
    members.add(member2);
    assertTrue(members.contains(member1));
    assertEquals(member1, member2);
  }



}
