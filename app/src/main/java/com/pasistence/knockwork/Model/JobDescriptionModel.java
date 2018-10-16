package com.pasistence.knockwork.Model;

import java.io.Serializable;

public class JobDescriptionModel implements Serializable {

    String name,poasted,quotes,durationimage,duration,durationrange,budgetimage,budget,budgetrange,jobtypeimage,jobtype,poastedtype,description,description1,description2,description3, description4,requiredskills,skill1,skill2,skill3,skill4;

    public JobDescriptionModel(String name, String poasted, String quotes, String durationimage, String duration, String durationrange, String budgetimage, String budget, String budgetrange, String jobtypeimage, String jobtype, String poastedtype, String description, String description1, String description2, String description3, String description4, String requiredskills, String skill1, String skill2, String skill3, String skill4) {
        this.name = name;
        this.poasted = poasted;
        this.quotes = quotes;
        this.durationimage = durationimage;
        this.duration = duration;
        this.durationrange = durationrange;
        this.budgetimage = budgetimage;
        this.budget = budget;
        this.budgetrange = budgetrange;
        this.jobtypeimage = jobtypeimage;
        this.jobtype = jobtype;
        this.poastedtype = poastedtype;
        this.description = description;
        this.description1 = description1;
        this.description2 = description2;
        this.description3 = description3;
        this.description4 = description4;
        this.requiredskills = requiredskills;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
        this.skill4 = skill4;
    }

    public JobDescriptionModel() {
    }

    @Override
    public String toString() {
        return "JobDescriptionModel{" +
                "name='" + name + '\'' +
                ", poasted='" + poasted + '\'' +
                ", quotes='" + quotes + '\'' +
                ", durationimage='" + durationimage + '\'' +
                ", duration='" + duration + '\'' +
                ", durationrange='" + durationrange + '\'' +
                ", budgetimage='" + budgetimage + '\'' +
                ", budget='" + budget + '\'' +
                ", budgetrange='" + budgetrange + '\'' +
                ", jobtypeimage='" + jobtypeimage + '\'' +
                ", jobtype='" + jobtype + '\'' +
                ", poastedtype='" + poastedtype + '\'' +
                ", description='" + description + '\'' +
                ", description1='" + description1 + '\'' +
                ", description2='" + description2 + '\'' +
                ", description3='" + description3 + '\'' +
                ", description4='" + description4 + '\'' +
                ", requiredskills='" + requiredskills + '\'' +
                ", skill1='" + skill1 + '\'' +
                ", skill2='" + skill2 + '\'' +
                ", skill3='" + skill3 + '\'' +
                ", skill4='" + skill4 + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoasted() {
        return poasted;
    }

    public void setPoasted(String poasted) {
        this.poasted = poasted;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

    public String getDurationimage() {
        return durationimage;
    }

    public void setDurationimage(String durationimage) {
        this.durationimage = durationimage;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDurationrange() {
        return durationrange;
    }

    public void setDurationrange(String durationrange) {
        this.durationrange = durationrange;
    }

    public String getBudgetimage() {
        return budgetimage;
    }

    public void setBudgetimage(String budgetimage) {
        this.budgetimage = budgetimage;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getBudgetrange() {
        return budgetrange;
    }

    public void setBudgetrange(String budgetrange) {
        this.budgetrange = budgetrange;
    }

    public String getJobtypeimage() {
        return jobtypeimage;
    }

    public void setJobtypeimage(String jobtypeimage) {
        this.jobtypeimage = jobtypeimage;
    }

    public String getJobtype() {
        return jobtype;
    }

    public void setJobtype(String jobtype) {
        this.jobtype = jobtype;
    }

    public String getPoastedtype() {
        return poastedtype;
    }

    public void setPoastedtype(String poastedtype) {
        this.poastedtype = poastedtype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public String getDescription3() {
        return description3;
    }

    public void setDescription3(String description3) {
        this.description3 = description3;
    }

    public String getDescription4() {
        return description4;
    }

    public void setDescription4(String description4) {
        this.description4 = description4;
    }

    public String getRequiredskills() {
        return requiredskills;
    }

    public void setRequiredskills(String requiredskills) {
        this.requiredskills = requiredskills;
    }

    public String getSkill1() {
        return skill1;
    }

    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }

    public String getSkill2() {
        return skill2;
    }

    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }

    public String getSkill3() {
        return skill3;
    }

    public void setSkill3(String skill3) {
        this.skill3 = skill3;
    }

    public String getSkill4() {
        return skill4;
    }

    public void setSkill4(String skill4) {
        this.skill4 = skill4;
    }
}
