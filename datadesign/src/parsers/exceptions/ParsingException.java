package parsers.exceptions;

public class ParsingException extends IllegalArgumentException {

  public ParsingException(){

  }

  public ParsingException(String msg) {
    super(msg);
  }

}
