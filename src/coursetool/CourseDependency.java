package coursetool;

import java.security.InvalidParameterException;

public class CourseDependency
{
    protected Course dependencyOrigin;
    protected CourseDependencyType dependencyType = CourseDependencyType.UNDEFINED;

    public CourseDependency(Course origin) throws InvalidParameterException
    {
        if(origin == null) {throw new InvalidParameterException("Origin course cannot be a null reference.");}
        
        this.dependencyOrigin = origin;
    }

    public Course getOrigin()
    {
        return this.dependencyOrigin;
    }

    public CourseDependencyType getDependencyType()
    {
        return dependencyType;
    }
}
