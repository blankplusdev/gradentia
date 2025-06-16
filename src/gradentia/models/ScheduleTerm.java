package gradentia.models;

import java.util.ArrayList;

public class ScheduleTerm
{
 
    private Term timeFrame;
    private ArrayList<CourseTemplate> termCourses;

    public ScheduleTerm(Term timeFrame)
    {
        this.timeFrame = timeFrame;
        this.termCourses = new ArrayList<CourseTemplate>();
    }

    public boolean hasCourse(CourseTemplate targetCourse)
    {
        return this.termCourses.contains(targetCourse);
    }

    public void addCourse(CourseTemplate targetCourse)
    {
        if(!hasCourse(targetCourse)) this.termCourses.add(targetCourse);
    }

    public void removeCourse(CourseTemplate targetCourse)
    {
        if(hasCourse(targetCourse)) this.termCourses.remove(targetCourse);
    }

    public void changeTerm(Term replacementTerm)
    {
        if(!(replacementTerm.equals(this.timeFrame))) this.timeFrame = replacementTerm;
    }
}
