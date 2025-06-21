package gradentia.models;

import gradentia.models.CourseConstants.LinkType;

public interface CourseLink
{
    CourseTemplate getOriginCourse();
    CourseTemplate getTargetCourse();
    void updateTargetCourse(Course replacementCourse);

    boolean isDirectional();
    LinkType getLinkType();

}
