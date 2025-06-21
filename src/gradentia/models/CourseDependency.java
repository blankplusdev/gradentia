package gradentia.models;

public abstract class CourseDependency
{
    protected CourseConstants.CourseDependencyType dependencyType;

    public CourseConstants.CourseDependencyType getDependencyType()
    {
        return this.dependencyType;
    }
}
