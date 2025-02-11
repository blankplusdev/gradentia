package coursetool.models;

public class CoursePlaceholder extends CourseTemplate
{
    private boolean resolved = false;

    public CoursePlaceholder()
    {
        super();
    }

    public void replace(Course replacementCourse)
    {
        //Modify all references to this object with the new Course object.
    }

    public void close()
    {
        //Remove all references to this object.
    }
}
