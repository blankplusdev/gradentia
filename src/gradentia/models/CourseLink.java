package gradentia.models;

import gradentia.models.CourseConstants.LinkType;



/**
 * Directional associatator interface between two {@link CourseTemplate} objects.
 * <p> Used for dependencies, associations, or other notable relationships between two Course objects.</p>
 * 
 * @see CourseTemplate
 * @see Course
 */
public interface CourseLink
{
    CourseTemplate getOriginCourse();
    CourseTemplate getTargetCourse();
    void updateTargetCourse(Course replacementCourse);

    boolean isDirectional();
    LinkType getLinkType();
}
