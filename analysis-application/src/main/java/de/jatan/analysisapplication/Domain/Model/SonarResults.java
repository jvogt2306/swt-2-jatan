package de.jatan.analysisapplication.Domain.Model;

public class SonarResults {
    private SonarResultsComponents component;

    public SonarResultsComponents getComponent ()
    {
        return component;
    }

    public void setComponent (SonarResultsComponents component)
    {
        this.component = component;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [component = "+component+"]";
    }
}
