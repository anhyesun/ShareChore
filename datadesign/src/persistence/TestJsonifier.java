package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestJsonifier {
    private Jsonifier js;

    @BeforeEach
    void runBefore() {
        js = new Jsonifier();
    }

    @Test
    void testConstructor() {
        js.toString();
        // stub
    }

    @Test
    void testTagToJson() {
        Tag tag = new Tag("cpsc210");
        System.out.println(Jsonifier.tagToJson(tag).toString());
    }


    @Test
    void testDueDateToJson() {
        DueDate dueDate = new DueDate();
        dueDate.postponeOneDay();
        System.out.println(Jsonifier.dueDateToJson(dueDate).toString());
        assertTrue(dueDate.isDueTomorrow());
    }

    @Test
    void testTaskToJson() {
        Task task = new Task("Clean the room.");
        Tag tag1 = new Tag("cpsc210");
        Tag tag2 = new Tag("cpsc310");
        task.addTag(tag1);
        task.addTag(tag2);

        DueDate dueDate = new DueDate();
        dueDate.postponeOneDay();
        task.setDueDate(dueDate);
        task.setPoint(1);
        task.setStatus(Status.IN_PROGRESS);

        System.out.print(Jsonifier.taskToJson(task).toString(1));
    }

    @Test
    void testTaskListToJson() {
        Task task = new Task("Register for the course. ");
        Tag tag1 = new Tag("cpsc210");
        Tag tag2 = new Tag("cpsc310");
        task.addTag(tag1);
        task.addTag(tag2);

        DueDate dueDate = new DueDate();
        dueDate.postponeOneDay();
        task.setDueDate(dueDate);
        task.setPoint(2);
        task.setStatus(Status.IN_PROGRESS);

        Task taskTest = new Task("Download the syllabus. ");
        taskTest.addTag(tag1);
        taskTest.setDueDate(null);
        taskTest.setStatus(Status.UP_NEXT);

        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        tasks.add(taskTest);
        System.out.println(tasks.toString());


        System.out.println(Jsonifier.taskListToJson(tasks).toString(1));

    }
}
