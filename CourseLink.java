import java.security.InvalidParameterException;

public class CourseLink
{
    
    protected Course linkOrigin;
    protected Course linkDependency;
    protected CourseLinkType linkType = CourseLinkType.UNDEFINED;

    public CourseLink(Course origin, Course dependency) throws InvalidParameterException
    {
        if(origin == null) {throw new InvalidParameterException("Origin course cannot be a null reference.");}
        if(dependency == null) {throw new InvalidParameterException("Dependency course cannot be a null reference.");}
        
        if(origin == dependency) {throw new InvalidParameterException("Origin can not reference itself as a dependency.");} 
        
        
        this.linkOrigin = origin;
        this.linkDependency = dependency;
    }

    public Course getOrigin()
    {
        return this.linkOrigin;
    }

    public Course getDependency()
    {
        return this.linkDependency;
    }

    public CourseLinkType getLinkType()
    {
        return linkType;
    }
}

