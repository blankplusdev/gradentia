package coursetool.models;

public interface CourseLink
{
    CourseTemplate getOriginCourse();
    CourseTemplate getTargetCourse();

    boolean isDirectional();
    LinkType getLinkType();

}
