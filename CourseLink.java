import java.security.InvalidParameterException;

public class CourseLink extends CourseDependency
{
    
    protected Course linkDependency;

    public CourseLink(Course origin, Course dependency) throws InvalidParameterException
    {
        super(origin);
        if(dependency == null) {throw new InvalidParameterException("Dependency course cannot be a null reference.");}

        if(this.linkOrigin == dependency) {throw new InvalidParameterException("Origin can not reference itself as a dependency.");} 
    
        this.linkDependency = dependency;
    }

    public Course getDependency()
    {
        return this.linkDependency;
    }

    
}

