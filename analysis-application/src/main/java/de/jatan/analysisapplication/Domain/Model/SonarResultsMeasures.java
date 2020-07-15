package de.jatan.analysisapplication.Domain.Model;

public class SonarResultsMeasures {
    private String metric;

    private String bestValue;

    private String value;

    public String getMetric ()
    {
        return metric;
    }

    public void setMetric (String metric)
    {
        this.metric = metric;
    }

    public String getBestValue ()
    {
        return bestValue;
    }

    public void setBestValue (String bestValue)
    {
        this.bestValue = bestValue;
    }

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [metric = "+metric+", bestValue = "+bestValue+", value = "+value+"]";
    }
}
