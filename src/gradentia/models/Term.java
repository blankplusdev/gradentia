package gradentia.models;

public class Term
{
    private final Semester termSemester;
    private final int termYear; 

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

    @Override
    public boolean equals(Object o)
    {
        if(o == this) return true;
        if(!(o instanceof Term)){return false;}
        Term targetTerm = (Term)o;

        return ((this.termSemester == targetTerm.getSemester()) && (this.termYear == targetTerm.getYear()));
    }
}
