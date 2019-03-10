package model;

// Represents the status of a Task
//     Status depicts the stage of Task on a Personal Kanban Board
//     https://en.wikipedia.org/wiki/Kanban_board
public enum Status {
  TODO("TODO"),
  UP_NEXT("UP NEXT"),
  IN_PROGRESS("IN PROGRESS"),
  DONE("DONE");

  private String description;

  // EFFECTS: sets description of Status
  Status(String description) {
    this.description = description;
  }

  // EFFECTS: returns description of Status
  public String getDescription() {
    return description;
  }

  // EFFECTS: returns description of Status
  @Override
  public String toString() {
    return description;
  }
}
