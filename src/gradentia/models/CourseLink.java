package gradentia.models;

public interface CourseLink
{
    CourseTemplate getOriginCourse();
    CourseTemplate getTargetCourse();
    void updateTargetCourse(Course replacementCourse);

    boolean isDirectional();
    LinkType getLinkType();

}
