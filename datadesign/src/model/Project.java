package model;

import model.exceptions.EmptyStringException;
import model.exceptions.NullArgumentException;

import java.util.*;

// Represents a Project, a collection of zero or more Tasks
// Class Invariant: no duplicated task; order in which tasks are added to project is preserved
public class Project {
  private Task task;
  private String description;
  private List<Task> tasks;


  // REQUIRES: description is non-empty
  // MODIFIES: this
  // EFFECTS: constructs a project with the given description
  //     the constructed project shall have no tasks.
  public Project(String description) throws NullArgumentException {
    if (description == null || description.equals("")) {
      throw new EmptyStringException("description must be none-empty string");
    }
    this.description = description;
    this.task = new Task(description);
    this.tasks = new ArrayList<>();
  }

  // REQUIRES: task != null
  // MODIFIES: this
  // EFFECTS: task is added to this project (if it was not already part of it)
  public void add(Task task) throws NullArgumentException {
    if (task == null) {
      throw new NullArgumentException("Task must be none empty");
    }
    this.task = task;
    if (!tasks.contains(task)) {
      tasks.add(task);
    }
  }

  // REQUIRES: task != null
  // MODIFIES: this
  // EFFECTS: removes task from this project
  public void remove(Task task) throws NullArgumentException {
    if (task == null) {
      throw new NullArgumentException("Task must be none empty");
    }
    this.task = task;
    tasks.remove(task);
  }

  // EFFECTS: returns the description of this project
  public String getDescription() {
    return description;
  }

  // EFFECTS: returns an unmodifiable list of tasks in this project.
  public List<Task> getTasks() {
    return Collections.unmodifiableList(tasks);
  }

  // EFFECTS: returns an integer between 0 and 100 which represents
  //     the percentage of completed tasks (rounded down to the closest integer).
  //     returns 100 if this project has no tasks!
  public int getProgress() {
    if (getNumberOfTasks() == 0) {
      return 100;
    }
    int progress = (int)(((double)numOfCompleted() / (double)getNumberOfTasks()) * 100);
    return progress;
  }

  // EFFECTS: returns the number of tasks in this project
  public int getNumberOfTasks() {
    return tasks.size();
  }

  // EFFECTS: returns true if every task in this project is completed, and false otherwise
  //     If this project has no tasks, return false.
  public boolean isCompleted() {
    if (tasks.size() == 0) {
      return false;
    }
    int num = 0;
    for (Task task : tasks) {
      if (task.getStatus() == Status.DONE) {
        num++;
      }
    }
    if (tasks.size() == num) {
      return true;
    }
    return false;
  }

  //EFFECTS: returns number of completed task in this project

  private int numOfCompleted() {
    int numDone = 0;
    for (Task t : tasks) {
      if (t.getStatus() == Status.DONE) {
        numDone++;
      }
    }
    return numDone;
  }

  public int getNumOfCompleted() {
    return numOfCompleted();
  }


  // REQUIRES: task != null
  // EFFECTS: returns true if this project contains the task
  public boolean contains(Task task) throws NullArgumentException {
    if (task == null) {
      throw new NullArgumentException("Task must be none empty");
    }
    return tasks.contains(task);
  }
}