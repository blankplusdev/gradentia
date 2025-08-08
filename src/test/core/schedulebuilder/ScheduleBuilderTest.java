package test.core.schedulebuilder;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Test;

import gradentia.core.schedulebuilder.ScheduleBuilder;
import gradentia.models.*;
import gradentia.models.CourseConstants.Semester;

public class ScheduleBuilderTest
{
    //public static void main(String[] args)
    @Test
    public void scheduleAssembler()
    {
        Term term1 = new Term(Semester.AUTUMN, 2025);
        Term term2 = new Term(Semester.SPRING, 2026);
        Term term3 = new Term(Semester.AUTUMN, 2026);
        Term term4 = new Term(Semester.SPRING, 2027);
        term1.setFollowingTerm(term2);
        term2.setPreviousTerm(term1);
        term2.setFollowingTerm(term3);
        term3.setPreviousTerm(term2);
        term3.setFollowingTerm(term4);
        term4.setPreviousTerm(term3);

        ArrayList<Course> coursesToSchedule = new ArrayList<>();

        Course calc1 = new Course(1);
        calc1.setName("Calculus I");
        calc1.setCourseCode("MATH1151");
        calc1.setCreditHours(5);
        coursesToSchedule.add(calc1);

        Course calc2 = new Course(2);
        calc2.setName("Calculus II");
        calc2.setCourseCode("MATH1152");
        calc2.setCreditHours(5);
        coursesToSchedule.add(calc2);

        PrerequisiteCourseDependency calcTestDependency = new PrerequisiteCourseDependency(calc2, calc1);
        calc2.addNewCourseDependency(calcTestDependency);
        calc1.addNewCourseLink(calcTestDependency);
        calc2.addNewCourseLink(calcTestDependency);


        ScheduleBuilder testBuilder = new ScheduleBuilder(1, 18, 0, term1);

        ArrayList<Schedule> builtSchedules = testBuilder.buildSchedules(coursesToSchedule);
        for(Schedule resultSchedule : builtSchedules)
        {
            System.out.println(resultSchedule);
        }
        assertEquals(1,builtSchedules.size());
    }
}
