package gradentia.repositories;

import java.util.ArrayList;
import java.util.HashMap;

import gradentia.models.*;

public class CourseLinkRepository
{
    protected HashMap<Integer, ArrayList<CourseLink>> courseLinkMap;
    
    public CourseLinkRepository()
    {
        this.courseLinkMap = new HashMap<Integer, ArrayList<CourseLink>>();
    }

    protected void registerNewCourse(CourseTemplate courseTemplate)
    {
        courseLinkMap.put(courseTemplate.getID(),courseTemplate.getCourseLinks());
        return;
    }

    protected void updateCourseLinks(Course updateCourse)
    {
        for(CourseLink existingLink : this.courseLinkMap.get(updateCourse.getID()))
        {
            existingLink.updateTargetCourse(updateCourse);
        }
        return;
    }

}
