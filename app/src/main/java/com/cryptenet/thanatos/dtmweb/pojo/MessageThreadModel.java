package com.cryptenet.thanatos.dtmweb.pojo;

public class MessageThreadModel
{
    private ThreadInv[] results;

    private String previous;

    private String count;

    private String next;

    public ThreadInv[] getResults ()
    {
        return results;
    }

    public void setResults (ThreadInv[] results)
    {
        this.results = results;
    }

    public String getPrevious ()
    {
        return previous;
    }

    public void setPrevious (String previous)
    {
        this.previous = previous;
    }

    public String getCount ()
    {
        return count;
    }

    public void setCount (String count)
    {
        this.count = count;
    }

    public String getNext ()
    {
        return next;
    }

    public void setNext (String next)
    {
        this.next = next;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [results = "+results+", previous = "+previous+", count = "+count+", next = "+next+"]";
    }
}