package model;

import model.exceptions.InvalidPointException;

public class Points {
  private int point;

  public void Points() {
    this.point = 1;
  }

  public void Points(int point) throws InvalidPointException {
    if (point < 0 || point > 4){
      throw new InvalidPointException();
    }
    this.point = point;

  }

  public int getPoint(){
    return point;
  }


}
