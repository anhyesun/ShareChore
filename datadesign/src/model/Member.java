package model;

import java.util.Objects;

public class Member {



    private String id;
    private int pointsSoFar;

    public Member(String id) {
        pointsSoFar = 0;

    }

    public int addPoint(int point) {
        this.pointsSoFar += point;
        return pointsSoFar;
    }

    public int getPoints(){
        return this.pointsSoFar;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
