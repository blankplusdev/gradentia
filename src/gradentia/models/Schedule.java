package gradentia.models;

import java.util.ArrayList;

public class Schedule
{
    ArrayList<ScheduleTerm> scheduleTerms;
    Term scheduleStartTerm;
    public double scheduleScore = 1.0;

    public Schedule(ArrayList<ScheduleTerm> scheduleTerms)
    {
        this.scheduleTerms = scheduleTerms;
        this.scheduleStartTerm = this.scheduleTerms.get(0).getTerm();
        this. scheduleScore = scheduleScore / this.scheduleTerms.size();
    }

    public String toString()
    {
        String output = "Schedule: ";
        for(ScheduleTerm term : this.scheduleTerms)
        {
            output += ("\n"+term.toString()+"\n");
        }
        return output;
    }
    
    public int getLength()
    {
        return this.scheduleTerms.size();
    }

}
