package gradentia.models;

public class CoursePlaceholder extends CourseTemplate
{
    private boolean resolved = false;
    private Course replacementCourse = null;

    public CoursePlaceholder(Integer ID)
    {
        super(ID);
    }

    public void markResolved(Course replacementCourse)
    {
        
        this.replacementCourse = replacementCourse;
        
        for(CourseLink existingLink : this.courseLinks) //Replace all Links to this CoursePlaceholder object with the updated replacement Course object.
        {
            existingLink.updateTargetCourse(this.replacementCourse);
        }


        /*
        TODO: Update central registries with replacement Course object (this.replacementCourse) 
        This Includes:
        - Course Registry
        - CourseLink registry
        */

        this.resolved = true;
    }

    public Course getreplacementCourse() //Returns Replaced Course Reference; testing tool.
    {
        return replacementCourse;
    }

    public String toString()
    {
        String returnString = super.toString()+" | ";
        if(resolved) {returnString+= "#Resolved "+replacementCourse;}     else {returnString+= "#Unresolved";}
        return returnString;
    }
}
