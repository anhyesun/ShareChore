package parsers;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

// Represents Task parser
public class TaskParser {

  // EFFECTS: iterates over every JSONObject in the JSONArray represented by the input
  // string and parses it as a task; each parsed task is added to the list of tasks.
  // Any task that cannot be parsed due to malformed JSON data is not added to the
  // list of tasks.
  // Note: input is a string representation of a JSONArray
  public List<Task> parse(String input) {
    List<Task> tasks = new ArrayList<>();
    JSONArray tasksArray = new JSONArray(input);

    for (Object object : tasksArray) {
      JSONObject taskJson = (JSONObject) object;
      if (isValidTask(taskJson)) {
        JSONArray tagJson = taskJson.getJSONArray("tags");
        if (isTagsKey(tagJson) && isValid(taskJson)) {
          String description = taskJson.getString("description");
          Task t = new Task(description);
          t = parseTags(tagJson, t);
          t = parseDueDate(taskJson, t);
          t = parsePriority(taskJson, t);
          t = parseStatus(taskJson, t);
          tasks.add(t);
        }
      }
    }

    return tasks;   // stub
  }

  private boolean isValid(JSONObject taskJson) {
    if (isPriorityKey(taskJson) && isDueDateKey(taskJson)
            && isStatusKey(taskJson)) {
      return true;
    }
    return false;
  }

  private Task parseTags(JSONArray tagJson, Task t) {
    for (Object obj : tagJson) {
      JSONObject tag = (JSONObject) obj;
      t.addTag(new Tag(tag.getString("name")));
    }
    return t;
  }

  private Task parseDueDate(JSONObject taskJson, Task t) {
    if (taskJson.isNull("due-date")) {
      t.setDueDate(null);
    } else {
      JSONObject dueDateJson = taskJson.getJSONObject("due-date");
      int year = dueDateJson.getInt("year");
      int month = dueDateJson.getInt("month");
      int day = dueDateJson.getInt("day");
      int hour = dueDateJson.getInt("hour");
      int minute = dueDateJson.getInt("minute");

      Calendar iniDate = Calendar.getInstance();
      iniDate.set(year, month, day, hour, minute);
      DueDate newDate = new DueDate(iniDate.getTime());
      t.setDueDate(newDate);
    }
    return t;
  }

  private Task parsePriority(JSONObject taskJson, Task t) {
    JSONObject priorityJson = taskJson.getJSONObject("priority");
    boolean important = priorityJson.getBoolean("important");
    boolean urgent = priorityJson.getBoolean("urgent");
    Priority p = new Priority();
    p.setImportant(important);
    p.setUrgent(urgent);
    t.setPriority(p);
    return t;
  }

  private Task parseStatus(JSONObject taskJson, Task t) {
    String statusJson = taskJson.getString("status");
    if (statusJson.equals("IN_PROGRESS")) {
      t.setStatus(Status.IN_PROGRESS);
    } else if (statusJson.equals("DONE")) {
      t.setStatus(Status.DONE);
    } else if (statusJson.equals("UP_NEXT")) {
      t.setStatus(Status.UP_NEXT);
    } else {
      t.setStatus(Status.TODO);
    }
    return t;

  }

  private boolean isValidTask(JSONObject taskJson) {
    if (taskJson.has("priority") && taskJson.has("description")
            && taskJson.has("tags") && taskJson.has("due-date")
            && taskJson.has("status")) {
      return true;
    }
    return false;
  }

  private boolean isTagsKey(JSONArray tagJson) {
    for (Object t : tagJson) {
      JSONObject tag = (JSONObject) t;
      if (!tag.has("name")) {
        return false;
      }
    }
    return true;
  }

  private boolean isDueDateKey(JSONObject taskJson) {
    if (taskJson.isNull("due-date")) {
      return true;
    }
    JSONObject dueDateJson = taskJson.getJSONObject("due-date");
    if (dueDateJson.has("year") && dueDateJson.has("month")
            && dueDateJson.has("day") && dueDateJson.has("hour")
            && dueDateJson.has("minute")) {
      return true;
    }
    return false;
  }

  private boolean isPriorityKey(JSONObject taskJson) {
    JSONObject priorityJson = taskJson.getJSONObject("priority");
    if (!priorityJson.has("urgent") || !priorityJson.has("important")) {
      return false;
    }
    return true;
  }

  private boolean isStatusKey(JSONObject taskJson) {
    String statusJson = taskJson.getString("status");
    if (statusJson.equals("IN_PROGRESS") || statusJson.equals("DONE")
            || statusJson.equals("TODO") || statusJson.equals("UP_NEXT")) {
      return true;
    }
    return false;
  }

}
