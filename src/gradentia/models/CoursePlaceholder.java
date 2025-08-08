package gradentia.models;


/**
 * Temporary placeholder in lieu of an information complete {@link Course} object.
 * <p> Utilized to allow course structuring even when relevant Course objects might be missing.</p>
 * 
 * @see Course
 * @see CourseTemplate
 */
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
