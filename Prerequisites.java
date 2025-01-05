import java.util.ArrayList;

public class Prerequisites
{
    private ArrayList<Course> unsortedPrerequisites;
    private ArrayList<Course> immediatePrerequisites;
    private ArrayList<Course> secondaryPrerequisites;

    public Prerequisites()
    {
        this.unsortedPrerequisites = new ArrayList<Course>();
        this.immediatePrerequisites = new ArrayList<Course>();
        this.secondaryPrerequisites = new ArrayList<Course>();
    }
    
    public void addPrerequisite(Course course)
    {
        unsortedPrerequisites.add(course);
    }

    public ArrayList<Course> getImmediate()
    {
        return this.immediatePrerequisites;
    }

    public ArrayList<Course> getSecondary()
    {
        return this.secondaryPrerequisites;
    }
}