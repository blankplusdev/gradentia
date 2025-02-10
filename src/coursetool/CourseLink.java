package coursetool;

import java.security.InvalidParameterException;

public class CourseLink extends CourseDependency
{
    
    protected CourseTemplate linkDependency;

    public CourseLink(Course origin, CourseTemplate dependency) throws InvalidParameterException
    {
        super(origin);
        if(dependency == null) {throw new InvalidParameterException("Dependency course cannot be a null reference.");}

        if(this.linkDependency == dependency) {throw new InvalidParameterException("Origin can not reference itself as a dependency.");} 
    
        this.linkDependency = dependency;
    }

    public CourseTemplate getDependency()
    {
        return this.linkDependency;
    }

    
}

