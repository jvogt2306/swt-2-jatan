package de.jatan.analysisapplication.Domain.Model;

public class SonarResultsComponents {
    private SonarResultsMeasures[] measures;

    private String qualifier;

    private String name;

    private String id;

    private String key;

    public SonarResultsMeasures[] getMeasures ()
    {
        return measures;
    }

    public void setMeasures (SonarResultsMeasures[] measures)
    {
        this.measures = measures;
    }

    public String getQualifier ()
    {
        return qualifier;
    }

    public void setQualifier (String qualifier)
    {
        this.qualifier = qualifier;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getKey ()
    {
        return key;
    }

    public void setKey (String key)
    {
        this.key = key;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [measures = "+measures+", qualifier = "+qualifier+", name = "+name+", id = "+id+", key = "+key+"]";
    }
}
