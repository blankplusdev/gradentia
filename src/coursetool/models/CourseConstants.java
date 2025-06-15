package coursetool.models;

//Definitions for the different kinds of dependencies courses can have - key to their relationships.
/* LEGACY ENUM
enum CourseDependencyType
{
    UNDEFINED,
    SEMESTER,
    PREREQUISITE,
    CONCURRENT
}
*/

//More verbose definition for a course's semester availability.
enum Semester
{
    UNDEFINED,
    AUTUMN,
    SPRING,
    SUMMER
}

//More verbose definition for a course's year availability.
enum Year
{
    ALL,
    EVEN,
    ODD
}

//Course department categorization values.
enum Department
{
    UNDEFINED,
    MATH,
    ASTRON,
    PHYSICS,
    CSE,
    STAT
}

enum LinkType
{
    ASSOCIATION_CONCURRENT,
    DEPENDENCY_PREREQUISITE

}