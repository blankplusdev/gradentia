package gradentia.core.schedulebuilder;

import java.util.ArrayList;

import gradentia.models.*;

public class ScheduleBuilder
{
    double minTermCredit;
    double maxTermCredit;
    int maxTerms;
    Term startingTerm;
    ArrayList<ScheduleTerm> finalizedScheduleTerms;
    
    public ScheduleBuilder(double minTermCredit, double maxTermCredit, int maxTerms, Term startingTerm) //Add any additional config to constructor
    {
        this.minTermCredit = minTermCredit;
        this.maxTermCredit = maxTermCredit;
        this.maxTerms = maxTerms;
        this.startingTerm = startingTerm;
        this.finalizedScheduleTerms = new ArrayList<>();
    }
    
    public ArrayList<Schedule> buildSchedules(ArrayList<Course> coursesToSchedule)
    {
        generateScheduleTerms(coursesToSchedule, maxTermCredit, startingTerm, null);

        ArrayList<Schedule> generatedSchedules = new ArrayList<>();
        for(ScheduleTerm lastScheduleTerm : finalizedScheduleTerms)
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
        //System.out.println("Finalized ScheduleTerms: " + finalizedScheduleTerms.size());
        return generatedSchedules; //Unsorted output
    }
    
    private void generateScheduleTerms(ArrayList<Course> coursesToSchedule, double termCreditLimit, Term timeFrame, ScheduleTerm previousScheduleTerm)
    {
        ArrayList<Course> validCourses = getValidCourses(coursesToSchedule, previousScheduleTerm,timeFrame);
        //if(validCourses.size() == 0 ) {System.out.println("NO VALID COURSES LEFT");}
        ArrayList<ArrayList<Course>> lists = createCourseCombinations(validCourses);
        for(ArrayList<Course> courseCombination : lists)
        {
            ScheduleTerm newScheduleTerm = new ScheduleTerm(timeFrame, courseCombination,previousScheduleTerm);
            if(newScheduleTerm.validateAgainst(coursesToSchedule))
            {
                newScheduleTerm.markComplete();
                this.finalizedScheduleTerms.add(newScheduleTerm);
            }
            else
            {
                Term nextTerm = newScheduleTerm.getTerm().getFollowingTerm();
                if (nextTerm == null) {
                    //System.out.println("No following term after " + newScheduleTerm.getTerm());
                    continue; // or return
                }
                generateScheduleTerms(coursesToSchedule, termCreditLimit, nextTerm, newScheduleTerm);
            }
        }
    }

    private ArrayList<Course> getValidCourses(ArrayList<Course> coursesToSchedule, ScheduleTerm previousScheduleTerm, Term timeFrame)
    {
       ArrayList<Course> previousCourses = new ArrayList<>();
        
       boolean isNotFirstTerm = (previousScheduleTerm != null);
       if(isNotFirstTerm) {previousCourses = previousScheduleTerm.getAllCourses(true);}
       
        ArrayList<Course> viableStartingCourses = new ArrayList<>();
        courseIteration:
        for(Course course : coursesToSchedule)
        {
            if(isNotFirstTerm && previousCourses.contains(course)) continue;

            for(CourseDependency dependency : course.getCourseDependencies())
            {
                switch(dependency.getDependencyType())
                {
                    case PREREQUISITE:
                    if(isNotFirstTerm && previousCourses.contains(((PrerequisiteCourseDependency)dependency).getTargetCourse())) {continue;}
                    continue courseIteration;
                    
                    case TERM:
                    //System.out.println(course + " in: " + timeFrame + " = "+ ((TermAvailabilityCourseDependency)dependency).isValidFor(timeFrame));
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
        ArrayList<ArrayList<Course>> combinationArrayList = new ArrayList<>();
        ArrayList<Course> currentCombination = new ArrayList<>();

        recursiveCourseCombinator(courses, 0, currentCombination, 0, combinationArrayList,creditSumLessThanMin(courses));
        
        //Combinations are good as of here
        return combinationArrayList;
    }

    private void recursiveCourseCombinator(ArrayList<Course> courses, int startIndex, ArrayList<Course> currentCombo, double currentCredits, ArrayList<ArrayList<Course>> results, boolean lowCreditFlag)
    {
        
        if(currentCredits <= this.maxTermCredit)
        {
            if(currentCredits >= this.minTermCredit || (!currentCombo.isEmpty() && lowCreditFlag)) {results.add(new ArrayList<>(currentCombo));}
        }
        else {return;}
        

        //tries adding each remaining course starting from startIndex
        for (int i = startIndex; i < courses.size(); i++)
        {
            Course course = courses.get(i);
            currentCombo.add(course);

            //recursive call with new state
            recursiveCourseCombinator(courses, i + 1, currentCombo, currentCredits + course.getCreditHours(),results,lowCreditFlag);

            //removes the last added course in order to allow proper backtracking
            currentCombo.remove(currentCombo.size() - 1);
        }
    }

    private boolean creditSumLessThanMin(ArrayList<Course> courses)
    {
        double sum = 0;
        for(Course entry : courses)
        {
            sum += entry.getCreditHours();
        }
        return (sum < this.minTermCredit);
    }

    private void pause()
    {
        try
        {
            System.in.read();
        }
        catch(Exception e) {}
    }
}
