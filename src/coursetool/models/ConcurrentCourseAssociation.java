package coursetool.models;

public class ConcurrentCourseAssociation extends CourseAssociation
{
    protected CourseTemplate originCourse;
    protected CourseTemplate targetCourse;
    protected final LinkType linkType = LinkType.ASSOCIATION_CONCURRENT;
    protected final boolean isDirectional = false;
    
    public ConcurrentCourseAssociation(CourseTemplate originCourse, CourseTemplate targetCourse)
    {
        this.originCourse = originCourse;
        this.targetCourse = targetCourse;
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
