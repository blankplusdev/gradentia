public class CoursePrerequisite extends CourseLink
{
    public CoursePrerequisite(Course origin, Course dependency)
    {
        super(origin, dependency);
        this.linkType = CourseLinkType.PREREQUISITE; 
    }
}