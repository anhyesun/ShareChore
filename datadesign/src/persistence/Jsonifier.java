package persistence;


import model.DueDate;
import model.Tag;
import model.Task;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.List;

// Converts model elements to JSON objects
public class Jsonifier {

  // EFFECTS: returns JSON representation of tag
  public static JSONObject tagToJson(Tag tag) throws JSONException {
    JSONObject tagJson = new JSONObject();
    tagJson.put("name", tag.getName());
    return tagJson;
  }

  // EFFECTS: returns JSON representation of Member
  public static JSONObject memberToJson(Task task) throws JSONException {
    JSONObject memberJson = new JSONObject();

    memberJson.put("id", task.getId());
    return memberJson;
  }

  // EFFECTS: returns JSON representation of priority
  public static JSONObject pointToJson(int points) {
    JSONObject pointToJson = new JSONObject();
    return pointToJson;
  }

  // EFFECTS: returns JSON respresentation of dueDate
  public static JSONObject dueDateToJson(DueDate dueDate) throws JSONException {
    JSONObject dueDateJson = new JSONObject();

    if (dueDate == null) {
      return null;
    }
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(dueDate.getDate());
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);

    dueDateJson.put("year", year);
    dueDateJson.put("month", month);
    dueDateJson.put("day", day);
    dueDateJson.put("hour", hour);
    dueDateJson.put("minute", minute);
    return dueDateJson;
  }

  // EFFECTS: returns JSON representation of task
  public static JSONObject taskToJson(Task task) throws JSONException {
    JSONObject taskJson = new JSONObject();
    JSONArray tags = new JSONArray();
    for (Tag t : task.getTags()) {
      tags.put(tagToJson(t));
    }
    taskJson.put("tags", tags);
    taskJson.put("description", task.getDescription());
    if (null == dueDateToJson(task.getDueDate())) {
      taskJson.put("due-date", JSONObject.NULL);
    } else {
      taskJson.put("due-date", dueDateToJson(task.getDueDate()));
    }
    taskJson.put("points", pointToJson(task.getPoint()));
    taskJson.put("status", task.getStatus());
    taskJson.put("member", task.getId());
    return taskJson;
  }

  // EFFECTS: returns JSON array representing list of tasks
  public static JSONArray taskListToJson(List<Task> tasks) {
    JSONArray tasksArray = new JSONArray();
    for (Task task : tasks) {
      tasksArray.put(taskToJson(task));
    }
    System.out.println(tasksArray);
    return tasksArray;
  }
}
