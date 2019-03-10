package model;

import model.exceptions.InvalidTimeException;
import model.exceptions.NullArgumentException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

// Note: Any library in JDK 8 may be used to implement this class, however,
//     you must not use any third-party library in your implementation
// Hint: Explore https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html

// Represents the due date of a Task
public class DueDate {
  Calendar dueDate;

  // MODIFIES: this
  // EFFECTS: creates a DueDate with deadline at end of day today (i.e., today at 11:59 PM)
  public DueDate() {
    dueDate = Calendar.getInstance();
    dueDate.set(Calendar.HOUR_OF_DAY, 23);
    dueDate.set(Calendar.MINUTE, 59);
  }

  // REQUIRES: date != null
  // MODIFIES: this
  // EFFECTS: creates a DueDate with deadline of the given date
  public DueDate(Date date) throws NullArgumentException {
    if (date == null) {
      throw new NullArgumentException("Date must not be a null");
    }
    dueDate = Calendar.getInstance();
    dueDate.setTime(date);
  }

  // REQUIRES: date != null
  // MODIFIES: this
  // EFFECTS: changes the due date to the given date
  public void setDueDate(Date date) throws NullArgumentException {
    if (date == null) {
      throw new NullArgumentException("Date must not be a null");
    }
    dueDate.setTime(date);
  }

  // REQUIRES: 0 <= hh <= 23 && 0 <= mm <= 59
  // MODIFIES: this
  // EFFECTS: changes the due time to hh:mm leaving the month, day and year the same
  public void setDueTime(int hh, int mm) throws InvalidTimeException {
    if (hh < 0 || hh > 23 || mm < 0 || mm > 59) {
      throw new InvalidTimeException("Due Time must be valid");
    }
    dueDate.set(Calendar.HOUR_OF_DAY, hh);
    dueDate.set(Calendar.MINUTE, mm);
  }

  // MODIFIES: this
  // EFFECTS: postpones the due date by one day (leaving the time the same as
  //     in the original due date) based on the rules of the Gregorian calendar.
  public void postponeOneDay() {
    int day = dueDate.get(Calendar.DATE);
    dueDate.set(Calendar.DATE, day + 1);
  }

  // MODIFIES: this
  // EFFECTS: postpones the due date by 7 days
  //     (leaving the time the same as in the original due date)
  //     based on the rules of the Gregorian calendar.
  public void postponeOneWeek() {
    int day = dueDate.get(Calendar.DAY_OF_YEAR);
    dueDate.set(Calendar.DAY_OF_YEAR, day + 7);
  }

  // EFFECTS: returns the due date
  public Date getDate() {
    return dueDate.getTime();
  }

  // EFFECTS: returns true if due date (and due time) is passed
  public boolean isOverdue() {
    Calendar currentDate = Calendar.getInstance();
    if (currentDate.after(dueDate)) {
      return true;
    }
    return false;
  }

  // EFFECTS: returns true if due date is at any time today, and false otherwise
  public boolean isDueToday() {
    Calendar today = Calendar.getInstance();
    int newDate = today.get(Calendar.DAY_OF_YEAR);
    int sameYear = dueDate.get(Calendar.YEAR);
    int year = today.get(Calendar.YEAR);
    int due = dueDate.get(Calendar.DAY_OF_YEAR);
    if (due == newDate && sameYear == year) {
      return true;
    }
    return false;
  }

  // EFFECTS: returns true if due date is at any time tomorrow, and false otherwise
  public boolean isDueTomorrow() {
    Calendar today = Calendar.getInstance();
    int newDate = today.get(Calendar.DAY_OF_YEAR);
    int sameYear = dueDate.get(Calendar.YEAR);
    int year = today.get(Calendar.YEAR);
    int due = dueDate.get(Calendar.DAY_OF_YEAR) - 1;
    if (due == newDate && sameYear == year) {
      return true;
    }
    return false;
  }

  // EFFECTS: returns true if due date is within the next seven days, irrespective of time of the day,
  // and false otherwise
  // Example, assume it is 8:00 AM on a Monday
  // then any task with due date between 00:00 AM today (Monday) and 11:59PM the following Sunday is due within a week
  public boolean isDueWithinAWeek() {
    Calendar today = Calendar.getInstance();
    int sameYear = dueDate.get(Calendar.YEAR);
    int year = today.get(Calendar.YEAR);
    int newDate = today.get(Calendar.DAY_OF_YEAR);
    if (newDate - 1 < dueDate.get(Calendar.DAY_OF_YEAR) && newDate + 7 > dueDate.get(Calendar.DAY_OF_YEAR)) {
      if (sameYear == year) {
        return true;
      }
    }
    return false;
  }

  // EFFECTS: returns a string representation of due date in the following format
  //     day-of-week month day year hour:minute
  //  example: Sun Jan 25 2019 10:30 AM
  @Override
  public String toString() {
    if (dueDate == null) {
      return "";
    } else {
      SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy h:mm a");
      return (format.format(dueDate.getTime()));
    }
  }
}