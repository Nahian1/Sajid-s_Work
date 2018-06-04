/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.pojo;

public class SendMessageModel {
    private String sender;

    private String id;

    private String text;

    private String sender_picture;

    private String receiver;

    private String thread;

    private String created_at;

    private String sender_name;

    private String receiver_picture;

    private String receiver_name;

    public String getSender ()
    {
        return sender;
    }

    public void setSender (String sender)
    {
        this.sender = sender;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    public String getSender_picture ()
    {
        return sender_picture;
    }

    public void setSender_picture (String sender_picture)
    {
        this.sender_picture = sender_picture;
    }

    public String getReceiver ()
    {
        return receiver;
    }

    public void setReceiver (String receiver)
    {
        this.receiver = receiver;
    }

    public String getThread ()
    {
        return thread;
    }

    public void setThread (String thread)
    {
        this.thread = thread;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getSender_name ()
    {
        return sender_name;
    }

    public void setSender_name (String sender_name)
    {
        this.sender_name = sender_name;
    }

    public String getReceiver_picture ()
    {
        return receiver_picture;
    }

    public void setReceiver_picture (String receiver_picture)
    {
        this.receiver_picture = receiver_picture;
    }

    public String getReceiver_name ()
    {
        return receiver_name;
    }

    public void setReceiver_name (String receiver_name)
    {
        this.receiver_name = receiver_name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [sender = "+sender+", id = "+id+", text = "+text+", sender_picture = "+sender_picture+", receiver = "+receiver+", thread = "+thread+", created_at = "+created_at+", sender_name = "+sender_name+", receiver_picture = "+receiver_picture+", receiver_name = "+receiver_name+"]";
    }
}
