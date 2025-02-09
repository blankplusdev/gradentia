package coursetool;

import java.security.InvalidParameterException;

public class CourseDependency
{
    protected Course linkOrigin;
    protected CourseDependencyType dependencyType = CourseDependencyType.UNDEFINED;

    public CourseDependency(Course origin) throws InvalidParameterException
    {
        if(origin == null) {throw new InvalidParameterException("Origin course cannot be a null reference.");}

        this.linkOrigin = origin;
    }

    public Course getOrigin()
    {
        return this.linkOrigin;
    }

    public CourseDependencyType getDependencyType()
    {
        return dependencyType;
    }
}
