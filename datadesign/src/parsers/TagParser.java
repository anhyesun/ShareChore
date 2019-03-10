package parsers;

import model.DueDate;
import model.Status;
import model.Task;
import parsers.exceptions.ParsingException;

import java.util.Calendar;


public class TagParser extends Parser {
  private String[] arrayTags;
  private Task task;
  private DueDate dueDate;
  private int point;
  private Boolean isDueDateSet;
  private Boolean isStatusSet;
  private Boolean isPointSet;
  private String description;

  @Override
  public void parse(String input, Task task) throws ParsingException {
    init(task);
    if (!input.contains("##")) {
      super.description = input;
      this.task.setDescription(input);
      throw new ParsingException("input is assigned for description");
    }
    //init(task);
    if (isTagExist(input)) {
      arrayTags = splitTag(input);
      for (int i = 0; i < arrayTags.length; i++) {
        arrayTags[i] = arrayTags[i].trim();
        update(arrayTags[i], i);
        addTag(i);
      }
    } else { // only desc and no tags
      if (isDescriptionExist(input)) {
        setDelimiter(input);
      }
    }
  }

  private void setDelimiter(String input) {
    String descriptionSpace = input.split("##")[0];
    task.setDescription(descriptionSpace);
    super.description = descriptionSpace;
  }

  private boolean isDescriptionExist(String input) {
    String[] splittedInput = input.split("##");
    if (splittedInput.length != 0 && !input.equals("##")) {
      String descriptionSpace = input.split("##")[0].trim();
      return !descriptionSpace.equals("");
    }
    return false;
  }

  private Boolean isTagExist(String input) {
    String[] splittedInput = input.split("##");
    if (splittedInput.length != 1 && !input.equals("##")) {
      String tagSpace = splittedInput[1].trim();
      if (tagSpace.equals("")) {
        return false;
      }
      return true;
    }
    return false;
  }

  private void addTag(int index) {
    if (!arrayTags[index].equals("")) {
      task.addTag(arrayTags[index]);
    }
  }

  private void removeDuplicate(String tag) {
    for (int i = 0; i < arrayTags.length; i++) {
      if (arrayTags[i].equals(tag)) {
        arrayTags[i] = "";
      }
    }
  }

  private void update(String tag, int index) {
    if (isDueDate(tag) && !isDueDateSet) {
      setDueDate(tag);
      arrayTags[index] = "";
      removeDuplicate(tag);
    } else if (isStatus(tag) && !isStatusSet) {
      setStatus(tag);
      arrayTags[index] = "";
      removeDuplicate(tag);
    } else if (isPoint(tag) && !isPointSet) {
      setPoint(tag);
      arrayTags[index] = "";
      removeDuplicate(tag);

    }
  }

  private void init(Task task) {
    this.task = task;
    this.isDueDateSet = false;
    this.isStatusSet = false;
    this.point = 1;
  }

  private String[] splitTag(String input) {
    description = input.split("##")[0];
    task.setDescription(description);
    super.description = description;

    String tags = input.split("##")[1];
    if (tags.trim().equals("")) {
      throw new ArrayIndexOutOfBoundsException("Tags are empty");
    } else {
      arrayTags = tags.split(";");
      for (int i = 0; i < arrayTags.length; i++) {
        arrayTags[i] = arrayTags[i].trim();
      }
      return arrayTags;
    }
  }


  // EFFECTS: returns true if tag is DueDate
  private boolean isDueDate(String tag) {
    tag = tag.toLowerCase();
    if (tag.equals("today") || tag.equals("tomorrow")) {
      return true;
    }
    return false;
  }

  // EFFECTS: sets DueDate, if there is no due date, do nothing
  private void setDueDate(String tag) {
    tag = tag.toLowerCase();
    Calendar today = Calendar.getInstance();
    dueDate = new DueDate(today.getTime());

    if (tag.equals("today")) {
      task.setDueDate(dueDate);
    } else if (tag.equals("tomorrow")) {
      dueDate.postponeOneDay();
      task.setDueDate(dueDate);
    }
    isDueDateSet = true;
  }

  // EFFECTS: returns true if tag is Status
  private boolean isStatus(String tag) {
    tag = tag.toLowerCase();
    if (tag.equals("to do") || tag.equals("up next")
            || tag.equals("in progress") || tag.equals("done")) {
      return true;
    }
    return false;
  }

  // EFFECTS: sets Status, if there is no status, sets it to do
  private void setStatus(String tag) {
    tag = tag.toLowerCase();

    if (tag.equals("up next")) {
      task.setStatus(Status.UP_NEXT);
    } else if (tag.equals("in progress")) {
      task.setStatus(Status.IN_PROGRESS);
    } else if (tag.equals("done")) {
      task.setStatus(Status.DONE);
    } else if (tag.equals("to do")) {
      task.setStatus(Status.TODO);
    }
    isStatusSet = true;

  }

  private boolean isPoint(String tag) {
    if (tag.equals("1") || tag.equals("2") || tag.equals("3") || tag.equals("4")
            || tag.equals("5")) {
      return true;
    }
    return false;
  }

  private void setPoint(String tag) {
    if (tag.equals("1")) {
      task.setPoint(1);
    } else if (tag.equals("2")) {
      task.setPoint(2);
    } else if (tag.equals("3")) {
      task.setPoint(3);
    } else {
      task.setPoint(4);
    }
    isPointSet = true;
  }

}
