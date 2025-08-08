package gradentia.models;

/**
 * Framework for {@link Course} dependencies.
 * @see Course
 */
public abstract class CourseDependency
{
    protected CourseConstants.CourseDependencyType dependencyType;

    public CourseConstants.CourseDependencyType getDependencyType()
    {
        return this.dependencyType;
    }
}
