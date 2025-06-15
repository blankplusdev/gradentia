package coursetool.models;

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

    public LinkType getLinkType()
    {
        return this.linkType;
    }
    
    public boolean isDirectional()
    {
        return this.isDirectional;
    }
}
