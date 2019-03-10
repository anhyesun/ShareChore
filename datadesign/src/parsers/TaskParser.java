package parsers;

import model.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import parsers.exceptions.ParsingException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

// Represents Task parser
public class TaskParser extends Parser {

  // EFFECTS: iterates over every JSONObject in the JSONArray represented by the input
  // string and parses it as a task; each parsed task is added to the list of tasks.
  // Any task that cannot be parsed due to malformed JSON data is not added to the
  // list of tasks.
  // Note: input is a string representation of a JSONArray
  public List<Task> parse(String input) throws JSONException {
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
          t = parsePoint(taskJson, t);
          t = parseStatus(taskJson, t);
          t = parseMember(taskJson, t);
          tasks.add(t);
        }
      }
    }

    return tasks;   // stub
  }

  private boolean isValid(JSONObject taskJson) throws JSONException {
    if (isPointKey(taskJson) && isDueDateKey(taskJson)
            && isStatusKey(taskJson)) {
      return true;
    }
    return false;
  }

    private boolean isPointKey(JSONObject taskJson) throws JSONException {
      String pointJson = taskJson.getString("point");
      if (pointJson.equals("1") || pointJson.equals("2")
              || pointJson.equals("3") || pointJson.equals("4") || pointJson.equals("5")) {
                return true;
      }
      return false;
    }

    private Task parseTags(JSONArray tagJson, Task t) throws JSONException {
    for (Object obj : tagJson) {
      JSONObject tag = (JSONObject) obj;
      t.addTag(new Tag(tag.getString("name")));
    }
    return t;
  }

  private Task parseDueDate(JSONObject taskJson, Task t) throws JSONException {
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

  private Task parseMember(JSONObject taskJson, Task t) {
    String memberJson = taskJson.getString("member");
    if (memberJson == null) {
      t.setId("not assigned");
    } else {
      t.setId(memberJson);
    }

    return t;
  }

  private Task parsePoint(JSONObject taskJson, Task t) throws JSONException {
    String pointJson = taskJson.getString("point");
    if (pointJson.equals("1")) {
        t.setPoint(1);
    } else if (pointJson.equals("2")) {
        t.setPoint(2);
    } else if (pointJson.equals("3")) {
        t.setPoint(3);
    } else if (pointJson.equals("4")) {
        t.setPoint(4);
    } else {
        t.setPoint(5);
    }

      return t;
  }

  private Task parseStatus(JSONObject taskJson, Task t) throws JSONException {
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

  private boolean isDueDateKey(JSONObject taskJson) throws JSONException {
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


  private boolean isStatusKey(JSONObject taskJson) throws JSONException {
    String statusJson = taskJson.getString("status");
    if (statusJson.equals("IN_PROGRESS") || statusJson.equals("DONE")
            || statusJson.equals("TODO") || statusJson.equals("UP_NEXT")) {
      return true;
    }
    return false;
  }

    @Override
    public void parse(String input, Task task) throws ParsingException {

    }
}
