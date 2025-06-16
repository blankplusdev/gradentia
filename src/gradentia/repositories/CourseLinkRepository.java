package gradentia.repositories;

import java.util.ArrayList;
import java.util.HashMap;

import gradentia.models.Course;
import gradentia.models.CourseLink;
import gradentia.models.CourseTemplate;

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
        //TODO: Add check that updateCourse is in the map (not a current issue)
        for(CourseLink existingLink : this.courseLinkMap.get(updateCourse.getID()))
        {
            existingLink.updateTargetCourse(updateCourse);
        }
        return;
    }

}
