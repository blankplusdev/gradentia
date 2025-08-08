package gradentia.core.schedulebuilder;

import java.util.ArrayList;

import gradentia.models.*;

/**
 * <p>Generates schedules based on provided scheduling parameters.</p>
 * <p>Initial configuration is achieved through the constructor, assembles <code>Schedule</code> objects via <code>buildSchedules</code>.</p>
 * 
 */
public class ScheduleBuilder
{
    double minTermCredit;
    double maxTermCredit;
    int maxTerms;
    Term startingTerm;

    /**
     * <p> Creates a Scheduler with specified parameters. </p>
     * 
     * @param minTermCredit minimum number of credits to schedule per term
     * @param maxTermCredit maximum number of credits to schedule per term
     * @param maxTerms maximum number of terms to attempt scheduling across
     * @param startingTerm term from which to begin scheduling (inclusive)
     */
    public ScheduleBuilder(double minTermCredit, double maxTermCredit, int maxTerms, Term startingTerm) //Add any additional config to constructor
    {
        this.minTermCredit = minTermCredit;
        this.maxTermCredit = maxTermCredit;
        this.maxTerms = maxTerms;
        this.startingTerm = startingTerm;
    }
    
    /**
     * Generates an ArrayList of <code>Schedule</code> objects according to ScheduleBuilder instance parameters and provided <code>Course</code> objects.
     * 
     * @param coursesToSchedule ArrayList of <code>Course</code> objects to attempt scheduling.
     * @return ArrayList of <code>Schedule</code> objects that meet all scheduling critera. Returns an empty ArrayList if no viable Schedules are found.
     * 
     */
    public ArrayList<Schedule> buildSchedules(ArrayList<Course> coursesToSchedule)
    {
        System.out.println("Build called.");

        ArrayList<ScheduleTerm> result = generateScheduleTerms(coursesToSchedule, this.maxTermCredit, this.startingTerm, null); //Main helper-method!!
        System.out.println("buildSchedules recieves: "+result.size());
        
        ArrayList<Schedule> generatedSchedules = new ArrayList<>();
        for(ScheduleTerm lastScheduleTerm : result)
        {
            ArrayList<ScheduleTerm> orderedScheduleTerms = new ArrayList<>();
            orderedScheduleTerms.add(lastScheduleTerm);
            ScheduleTerm targetScheduleTerm = lastScheduleTerm;
            while(targetScheduleTerm.getPreviousScheduleTerm() != null) //Traces backwards from last ScheduleTerm to first, and orders them appropriately into one ArrayList
            {
                targetScheduleTerm = targetScheduleTerm.getPreviousScheduleTerm();
                orderedScheduleTerms.add(0,targetScheduleTerm);
            }
            generatedSchedules.add(new Schedule(orderedScheduleTerms));
        }
        return generatedSchedules; //Unsorted output
    }
    
/**
 * <p> Generates an ArrayList of <code>ScheduleTerm</code> objects and stores it in <code>!UPDATE</code></p>
 * 
 * @param coursesToSchedule ArrayList of <code>Course</code> objects to attempt scheduling.
 * @param termCreditLimit
 * @param timeFrame
 * @param previousScheduleTerm
 */
    private ArrayList<ScheduleTerm> generateScheduleTerms(ArrayList<Course> coursesToSchedule, double termCreditLimit, Term timeFrame, ScheduleTerm previousScheduleTerm)
    {
        System.out.println("Generating...");
        ArrayList<ScheduleTerm> finalizedScheduleTerms = new ArrayList<>();
        ArrayList<Course> validCourses = getValidCourses(coursesToSchedule, previousScheduleTerm,timeFrame);
        ArrayList<ArrayList<Course>> lists = createCourseCombinations(validCourses);
        for(ArrayList<Course> courseCombination : lists)
        {
            ScheduleTerm newScheduleTerm = new ScheduleTerm(timeFrame, courseCombination,previousScheduleTerm);
            if(newScheduleTerm.validateAgainst(coursesToSchedule))
            {
                newScheduleTerm.markComplete();
                finalizedScheduleTerms.add(newScheduleTerm);
            }
            else
            {
                Term nextTerm = newScheduleTerm.getTerm().getFollowingTerm();
                if (nextTerm == null) {
                    //System.out.println("No following term after " + newScheduleTerm.getTerm());
                    continue; // or return
                }
                finalizedScheduleTerms.addAll(generateScheduleTerms(coursesToSchedule, termCreditLimit, nextTerm, newScheduleTerm));
            }
        }
        System.out.println("generateScheduleTerms returns: "+finalizedScheduleTerms.size()); //This ArrayList doesn't get returned correctly...?
        return finalizedScheduleTerms;
    }

    /**
     * <p>Parses incoming <code>Courses</code> to identify eligible courses for scheduling in a given term.</p>
     * 
     * @param coursesToSchedule ArrayList of <code>Courses</code> to check
     * @param previousScheduleTerm prior <code>ScheduleTerm</code> object
     * @param timeFrame <code>Term</code> object for the corresponding time frame 
     * @return ArrayList of <code>Course</code> objects that are elligible for scheduling during <code>timeFrame</code>
     */
    private ArrayList<Course> getValidCourses(ArrayList<Course> coursesToSchedule, ScheduleTerm previousScheduleTerm, Term timeFrame)
    {
       System.out.println("Validating...");
        ArrayList<Course> previousCourses = new ArrayList<>();
        
       boolean isNotFirstTerm = (previousScheduleTerm != null);
       if(isNotFirstTerm) {previousCourses = previousScheduleTerm.getAllCourses(true);} //Fetches all previously scheduled courses
       
        ArrayList<Course> viableStartingCourses = new ArrayList<>();
        courseIteration:
        for(Course course : coursesToSchedule) //Consider outsourcing to a hleper method?
        {
            if(isNotFirstTerm && previousCourses.contains(course)) continue; //Skips Course if it's been scheduled already

            for(CourseDependency dependency : course.getCourseDependencies()) //TODO: Consider consolidating into a seperate method
            {
                switch(dependency.getDependencyType())
                {
                    case PREREQUISITE:
                    if(isNotFirstTerm && previousCourses.contains(((PrerequisiteCourseDependency)dependency).getTargetCourse())) {continue;}
                    continue courseIteration;
                    
                    case TERM:
                    //TEST System.out.println(course + " in: " + timeFrame + " = "+ ((TermAvailabilityCourseDependency)dependency).isValidFor(timeFrame));
                    if(((TermAvailabilityCourseDependency)dependency).isValidFor(timeFrame)) {continue;}
                    else {continue courseIteration;}
                }
            }
           viableStartingCourses.add(course);
        }
        return viableStartingCourses;
    }

    private ArrayList<ArrayList<Course>> createCourseCombinations(ArrayList<Course> courses)
    {
        System.out.println("Parsing combinator.");
        ArrayList<ArrayList<Course>> combinationArrayList = new ArrayList<>();
        ArrayList<Course> currentCombination = new ArrayList<>();

        powerSetCourseCombinator(courses, 0, currentCombination, 0, combinationArrayList,creditSumLessThanMin(courses));
        
        //Combinations are good as of here : what's this mean?
        return combinationArrayList;
    }

    /**
     * <p> Creates an ArrayList of potential <code>Course</code> object combinations to schedule in a term.</p>
     * <p> Generates the power set of <code>courses</code> and adds any viable course combinations to <code>results</code> if it meets the credit criteria.</p>
     * 
     * @param courses
     * @param startIndex
     * @param currentCombo
     * @param currentCredits
     * @param results
     * @param lowCreditFlag
     * 
     * @see <a href="https://en.wikipedia.org/wiki/Power_set">powerset</a>
     */
    private void powerSetCourseCombinator(ArrayList<Course> courses, int startIndex, ArrayList<Course> currentCombo, double currentCredits, ArrayList<ArrayList<Course>> results, boolean lowCreditFlag)
    {
        System.out.println("PowerSet call");
        if(currentCredits <= this.maxTermCredit) //Checks if credit count criteria is still met
        {
            if(currentCredits >= this.minTermCredit || (!currentCombo.isEmpty() && lowCreditFlag))
            {
                results.add(new ArrayList<>(currentCombo));
            }
        }
        else {return;}
        

        //tries adding each remaining course starting from startIndex
        for (int i = startIndex; i < courses.size(); i++)
        {
            Course course = courses.get(i);
            currentCombo.add(course);

            //recursive call with new state
            powerSetCourseCombinator(courses, i + 1, currentCombo, currentCredits + course.getCreditHours(),results,lowCreditFlag);

            //removes the last added course in order to allow proper backtracking
            currentCombo.remove(currentCombo.size() - 1);
        }
    }

    /**
     * <p> Checks if the credit sum of an ArrayList of <code>Course</code> objects is less than the miniumum specified in <code>this.minTermCredit</code></p>
     * @param courses ArrayList of <code>Course</code> objects to sum the credits of.
     * @return <p><code>true</code> if the sum of all credits from the provided Courses is less than the minimum.</p>
     */
    private boolean creditSumLessThanMin(ArrayList<Course> courses)
    {
        double sum = 0;
        for(Course entry : courses)
        {
            sum += entry.getCreditHours();
        }
        return (sum < this.minTermCredit);
    }
}
