package parsers;

import model.Task;
import parsers.exceptions.ParsingException;

// Represents a parser
public abstract class Parser {
  protected String description;

  // MODIFIES: this, task
  // EFFECTS: parses the input to extract properties such as priority or deadline
  //    and updates task with those extracted properties
  //  throws ParsingException if description does not contain the tag deliminator
  public abstract void parse(String input, Task task) throws ParsingException;

  // REQUIRES: parse has been called prior to calling getDescription
  // EFFECTS: returns description
  public String getDescription() {
    return description;
  }
}
