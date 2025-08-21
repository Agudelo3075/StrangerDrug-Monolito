package com.example.strangerDrug.terminosYCondiciones.model;

import java.time.LocalDateTime;

public class Accepted {
    private Long id;
    private User user;
    private LocalDateTime dateAndTimeAccepted;
    private TermAndCondition termAccept;
    private String ipAddres;

    public Accepted(User user, TermAndCondition termAccept, String ip) {
        this(null, user, LocalDateTime.now(), termAccept, ip);
    }

    public Accepted(Long id, User user, LocalDateTime dateAndTimeAccepted, TermAndCondition termAccept, String ipAddres){
        this.id = id;
        this.user = user;
        this.dateAndTimeAccepted = dateAndTimeAccepted;
        this.termAccept = termAccept;
        this.ipAddres = ipAddres;

    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public LocalDateTime getDateAndTimeAccepted(){
        return dateAndTimeAccepted;
    }

    public void setDateAndTimeAccepted(LocalDateTime dateAndTimeAccepted){
        this.dateAndTimeAccepted = dateAndTimeAccepted;
    }

    public TermAndCondition getTermAccept(){
        return termAccept;
    }

    public void setTermAccept(TermAndCondition termAccept){
        this.termAccept = termAccept;
    }

    public String getIpAddres(){
        return ipAddres;
    }

    public void setIpAddres(String ipAddres){
        this.ipAddres = ipAddres;
    }
}