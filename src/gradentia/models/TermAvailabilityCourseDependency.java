package gradentia.models;

import java.util.ArrayList;

import gradentia.models.CourseConstants.Semester;
import gradentia.models.CourseConstants.Year;

public class TermAvailabilityCourseDependency extends CourseDependency
{
    protected boolean dependencySatisfied;

    ArrayList<Semester> semestersOffered;
    Year yearsOffered;
    
    public TermAvailabilityCourseDependency(ArrayList<Semester> semestersOffered, Year yearsOffered)
    {
        this.dependencyType = CourseConstants.CourseDependencyType.TERM;
        this.semestersOffered = semestersOffered;
        this.yearsOffered = yearsOffered;
    }

    public boolean isSatisfied()
    {
        return this.dependencySatisfied;
    }

    public ArrayList<Semester> getSemestersOffered()
    {
        return this.semestersOffered;
    }

    public Year getYearsOffered()
    {
        return this.yearsOffered;
    }

    public boolean isValidFor(Term targetTerm)
    {
        if(this.semestersOffered.contains(targetTerm.getSemester()))
        {
            boolean yearIsEven = (targetTerm.getYear() % 2 == 0);
            
            switch (this.yearsOffered)
            {
                case ALL:
                return true;
                case EVEN:
                return yearIsEven;
                case ODD:
                return (!yearIsEven);
            }
        }
        return false;
    }
}
