package gradentia.models;

//Definitions for the different kinds of dependencies courses can have - key to their relationships.

public class CourseConstants
{
    public static enum CourseDependencyType
    {
        PREREQUISITE,
        TERM
    }

    //More verbose definition for a course's semester availability.
    public static enum Semester
    {
        UNDEFINED,
        AUTUMN,
        SPRING,
        SUMMER
    }

    //More verbose definition for a course's year availability.
    public static enum Year
    {
        ALL,
        EVEN,
        ODD
    }

    //Course department categorization values.
    public static enum Department
    {
        UNDEFINED,
        MATH,
        ASTRON,
        PHYSICS,
        CSE,
        STAT
    }

    public static enum LinkType
    {
        ASSOCIATION_CONCURRENT,
        ASSOCIATION_PENDING,
        DEPENDENCY_PREREQUISITE,
        DEPENDENCY_PENDING

    }
}