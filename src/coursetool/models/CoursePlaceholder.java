package coursetool.models;

public class CoursePlaceholder extends CourseTemplate
{
    private boolean resolved = false;
    private Course replacement = null;

    public CoursePlaceholder(Integer ID)
    {
        super(ID);
    }

    public void markResolved(Course replacement)
    {
        this.resolved = true;
        this.replacement = replacement;
    }

    public Course getReplacement() //Returns Replaced Course Reference; testing tool.
    {
        return replacement;
    }

    public String toString()
    {
        String returnString = super.toString()+" | ";
        if(resolved) {returnString+= "#Resolved "+replacement;}     else {returnString+= "#Unresolved";}
        return returnString;
    }
}
