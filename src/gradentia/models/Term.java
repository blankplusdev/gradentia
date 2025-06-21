package gradentia.models;

import gradentia.models.CourseConstants.Semester;

public class Term
{
    private final Semester termSemester;
    private final int termYear; 
    private Term followingTerm;
    private Term previousTerm;

    public Term(Semester termSemester, int termYear)
    {
        this.termSemester = termSemester;
        this.termYear = termYear;
    }

    public Semester getSemester()
    {
        return this.termSemester;
    }

    public int getYear()
    {
        return this.termYear;
    }

    public Term getFollowingTerm()
    {
        return this.followingTerm;
    }

    public void setFollowingTerm(Term followingTerm)
    {
        this.followingTerm = followingTerm;
    }

    public Term getPreviousTerm()
    {
        return this.previousTerm;
    }

    public void setPreviousTerm(Term previousTerm)
    {
        this.previousTerm = previousTerm;
    }

    @Override
    public boolean equals(Object o)
    {
        if(o == this) return true;
        if(!(o instanceof Term)){return false;}
        Term targetTerm = (Term)o;

        return ((this.termSemester == targetTerm.getSemester()) && (this.termYear == targetTerm.getYear()));
    }

    public String toString()
    {
        String output = "";
        switch(this.termSemester)
        {
            case AUTUMN:
            output += "Autumn";
            break;
            case SPRING:
            output += "Spring";
            break;
            case SUMMER:
            output += "Summer";
            break;
            case UNDEFINED:
            default:
            output += "Undefined";
            break;
        }

        output += (", " + this.termYear);
        return output;
    }
}
