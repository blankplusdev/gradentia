package coursetool;

//Wrapper class for CourseLinks relating two courses with one as a prerequisite to the other.
public class CoursePrerequisite extends CourseLink
{
    public CoursePrerequisite(Course origin, CourseTemplate dependency)
    {   
        super(origin, dependency);
        this.dependencyType = CourseDependencyType.PREREQUISITE; 
    }
}