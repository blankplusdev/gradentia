package gradentia.core.schedulebuilder;

import java.util.ArrayList;

import gradentia.models.*;
import gradentia.models.CourseConstants.Semester;

public class ScheduleBuilderTest
{
    public static void main(String[] args)
    {
        Term term1 = new Term(CourseConstants.Semester.AUTUMN, 2025);
        Term term2 = new Term(CourseConstants.Semester.SPRING, 2026);
        Term term3 = new Term(CourseConstants.Semester.AUTUMN, 2026);
        Term term4 = new Term(CourseConstants.Semester.SPRING, 2027);
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

        Course phys1 = new Course(3);
        phys1.setName("Mechanics, Work and Energy, Thermal Physics");
        phys1.setCourseCode("PHYS1270");
        phys1.setCreditHours(5);
        coursesToSchedule.add(phys1);

        Course phys2 = new Course(4);
        phys2.setName("E&M, Waves, Optics, Modern Physics");
        phys2.setCourseCode("PHYS1271");
        phys2.setCreditHours(5);
        coursesToSchedule.add(phys2);

        PrerequisiteCourseDependency physTestPCDependency = new PrerequisiteCourseDependency(phys2, phys1);
        phys2.addNewCourseDependency(physTestPCDependency);
        phys1.addNewCourseLink(physTestPCDependency);
        phys2.addNewCourseLink(physTestPCDependency);

        ArrayList<CourseConstants.Semester> phys1Semesters = new ArrayList<>();
        phys1Semesters.add(Semester.AUTUMN);
        phys1Semesters.add(Semester.SPRING);

        ArrayList<CourseConstants.Semester> phys2Semesters = new ArrayList<>();
        phys2Semesters.add(Semester.SPRING);

        TermAvailabilityCourseDependency phys1TestTACDependency = new TermAvailabilityCourseDependency(phys1Semesters, CourseConstants.Year.ALL);
        phys1.addNewCourseDependency(phys1TestTACDependency);
        
         
        TermAvailabilityCourseDependency phys2TestTACDependency = new TermAvailabilityCourseDependency(phys2Semesters, CourseConstants.Year.ALL);
        phys2.addNewCourseDependency(phys2TestTACDependency);
        

        Course astron1 = new Course(5);
        astron1.setName("Topics in Astrophysics - Astronomy Seminar");
        astron1.setCourseCode("ASTRON2895");
        astron1.setCreditHours(1);
        coursesToSchedule.add(astron1);

        ArrayList<CourseConstants.Semester> astron1Semesters = new ArrayList<>();
        astron1Semesters.add(Semester.AUTUMN);

        TermAvailabilityCourseDependency astron1TestTACDependency = new TermAvailabilityCourseDependency(astron1Semesters, CourseConstants.Year.ALL);
        astron1.addNewCourseDependency(astron1TestTACDependency);

        

        ScheduleBuilder testBuilder = new ScheduleBuilder(1, 18, 0, term1);

        ArrayList<Schedule> builtSchedules = testBuilder.buildSchedules(coursesToSchedule);

        for(Schedule result : builtSchedules)
        {
            if(result.getLength() <= 2)
            {
                System.out.println(result);
            }
        }
        
    }
}
