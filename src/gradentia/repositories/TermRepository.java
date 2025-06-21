package gradentia.repositories;

import java.util.HashMap;

import gradentia.models.Term;

public class TermRepository
{
    HashMap<Integer, Term> termMap;
    
    public TermRepository()
    {
        this.termMap = new HashMap<Integer,Term>();
    }
}
