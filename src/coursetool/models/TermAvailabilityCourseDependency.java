package coursetool.models;

public class TermAvailabilityCourseDependency extends CourseDependency
{
    protected boolean dependencySatisfied;

    Semester semestersOffered;
    Year yearsOffered;
    
    public TermAvailabilityCourseDependency(Semester semestersOffered, Year yearsOffered)
    {
        
    }

    public boolean isSatisfied()
    {
        return this.dependencySatisfied;
    }

    public Semester getSemestersOffered()
    {
        return this.semestersOffered;
    }

    public Year getYearsOffered()
    {
        return this.yearsOffered;
    }
}
