package gradentia.repositories;

import java.util.HashMap;

import gradentia.models.Course;
import gradentia.models.CoursePlaceholder;
import gradentia.models.CourseTemplate;

public class CourseRepository
{
    protected HashMap<Integer, CourseTemplate> courseMap;
    private Integer nextID = 0;
    private CourseLinkRepository courseLinkRepository;

    public CourseRepository(CourseLinkRepository courseLinkRepository)
    {
        this.courseMap = new HashMap<Integer,CourseTemplate>();
        this.courseLinkRepository = courseLinkRepository;
    }

    public void createNewCourse()
    {
        Course newCourse = new Course(nextID);
        this.courseMap.put(nextID,newCourse);
        courseLinkRepository.registerNewCourse(newCourse);
        nextID++;
    }

    public void createNewCoursePlaceholder()
    {
        CoursePlaceholder newCoursePlaceholder = new CoursePlaceholder(nextID);
        this.courseMap.put(nextID,newCoursePlaceholder);
        courseLinkRepository.registerNewCourse(newCoursePlaceholder);
        nextID++;
    }

    public void replacePlaceholderWithCourse(CoursePlaceholder oldPlaceholder)
    {
        Course replacementCourse = new Course(oldPlaceholder);
        Integer ID = oldPlaceholder.getID();
        this.courseLinkRepository.updateCourseLinks(replacementCourse); //Calls Link repository to update links.
        courseMap.replace(ID,replacementCourse);
        return;
    }
}
