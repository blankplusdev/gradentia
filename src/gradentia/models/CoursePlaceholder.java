package gradentia.models;

public class CoursePlaceholder extends CourseTemplate
{
    private boolean resolved = false;

    public CoursePlaceholder(Integer ID)
    {
        super(ID);
    }

    public void resolve()
    {
        this.resolved = true;
    }

    public boolean isResolved()
    {
        return this.resolved;
    }
}
