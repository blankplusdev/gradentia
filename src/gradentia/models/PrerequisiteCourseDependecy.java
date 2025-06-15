package gradentia.models;

public class PrerequisiteCourseDependecy extends CourseDependency implements CourseLink
{
    protected boolean dependencySatisfied;
    
    protected CourseTemplate originCourse;
    protected CourseTemplate targetCourse;
    protected final LinkType linkType = LinkType.DEPENDENCY_PREREQUISITE;
    protected final boolean isDirectional = true;

    public PrerequisiteCourseDependecy(CourseTemplate originCourse, CourseTemplate targetCourse)
    {
        this.dependencySatisfied = false;
        this.originCourse = originCourse;
        this.targetCourse = targetCourse;
    }
    public boolean isSatisfied()
    {
        return this.dependencySatisfied;
    }
    
    public CourseTemplate getOriginCourse()
    {
        return this.originCourse;
    }

    public CourseTemplate getTargetCourse()
    {
        return this.targetCourse;
    }

    public void updateTargetCourse(Course replacementCourse) //Should only ever be accessed by a CoursePlaceholder replacing it's own references.
    {
        this.targetCourse = replacementCourse;
    }

    public LinkType getLinkType()
    {
        return this.linkType;
    }
    
    public boolean isDirectional()
    {
        return this.isDirectional;
    }
}
