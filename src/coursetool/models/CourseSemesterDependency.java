package coursetool.models;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class CourseSemesterDependency extends CourseDependency
{
    protected ArrayList<Semester> availableSemesters;
    protected Year availableYears = Year.ALL;
    
    public CourseSemesterDependency(Course origin, ArrayList<Semester> semesters) throws InvalidParameterException
    {
        super(origin);

        if(semesters == null) {throw new InvalidParameterException("Semesters ArrayList cannot be a null reference.");}
        if(semesters.size() < 1) {throw new InvalidParameterException("Semesters ArrayList cannot be empty.");}
        
        this.dependencyType = CourseDependencyType.SEMESTER;
        this.availableSemesters = semesters;
    }

    public CourseSemesterDependency(Course origin, ArrayList<Semester> semesters, Year years)
    {
        this(origin,semesters);
        this.availableYears = years;
    }
}
