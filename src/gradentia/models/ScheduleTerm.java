package gradentia.models;

import java.util.ArrayList;

public class ScheduleTerm
{
 
    private Term timeFrame;
    private ArrayList<Course> termCourses;
    private ScheduleTerm previousScheduleTerm;
    private boolean complete = false;

    public ScheduleTerm(Term timeFrame)
    {
        this.timeFrame = timeFrame;
        this.termCourses = new ArrayList<>();
    }

    public ScheduleTerm(Term timeFrame, ArrayList<Course> termCourses)
    {
        this(timeFrame);
        this.termCourses = new ArrayList<>(termCourses);
    }

    public ScheduleTerm(Term timeFrame, ArrayList<Course> termCourses,ScheduleTerm previoScheduleTerm)
    {
        this(timeFrame,termCourses);
        this.previousScheduleTerm = previoScheduleTerm;
    }

    public void setPreviousScheduleTerm(ScheduleTerm previousScheduleTerm)
    {
        this.previousScheduleTerm = previousScheduleTerm;
    }
    
    public ScheduleTerm getPreviousScheduleTerm()
    {
        return this.previousScheduleTerm;
    }

    public boolean hasCourse(CourseTemplate targetCourse)
    {
        return this.termCourses.contains(targetCourse);
    }

    public void addCourse(Course targetCourse)
    {
        if(!hasCourse(targetCourse)) this.termCourses.add(targetCourse);
    }

    public void removeCourse(CourseTemplate targetCourse)
    {
        if(hasCourse(targetCourse)) this.termCourses.remove(targetCourse);
    }

    public void setTerm(Term replacementTerm)
    {
        if(!(replacementTerm.equals(this.timeFrame))) this.timeFrame = replacementTerm;
    }

    public Term getTerm()
    {
        return this.timeFrame;
    }

    public void markComplete()
    {
        this.complete = true;
    }

    public boolean isComplete()
    {
        return this.complete;
    }

    public ArrayList<Course> getAllCourses(boolean recursivelyInherit)
    {
        ArrayList<Course> courses = new ArrayList<>(this.termCourses);
        if(recursivelyInherit && previousScheduleTerm != null)
        {
            for(Course inheritedCourse : this.previousScheduleTerm.getAllCourses(true))
            courses.add(inheritedCourse);
        }

        return courses;
    }

    public boolean validateAgainst(ArrayList<Course> courseList)
    {
        ArrayList<Course> currentCourses = getAllCourses(true);
        //System.out.print("-> NEW VALIDATION: ");
        for(Course requestedCourse : courseList)
        {
            if(!currentCourses.contains(requestedCourse))
            {
                //System.out.println("Failed @ Course: "+requestedCourse);
                return false;
            }
        }
        //System.out.println("Passed");
        return true;
    }

    public String toString()
    {
        String output = "-"+this.timeFrame.toString()+": "+totalCreditHours()+" Credit Hours";
        for(Course course : this.termCourses)
        {
            output += ("\n"+course.toString());
        }
        return output;
    }

    private double totalCreditHours()
    {
        double sum = 0;
        for(Course course : this.termCourses)
        {
            sum += course.getCreditHours();
        }
        return sum;
    }
}
