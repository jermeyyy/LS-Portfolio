package pl.looksoft.lsportfolio.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jermey on 2015-08-18.
 */
public class AppItem {

    private List<Slide> slide = new ArrayList<>();
    private List<Portfolio> portfolio = new ArrayList<>();
    private List<Person> person = new ArrayList<>();
    private List<Work> work = new ArrayList<>();

    public List<Slide> getSlide() {
        return slide;
    }

    public void setSlide(List<Slide> slide) {
        this.slide = slide;
    }

    public List<Portfolio> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(List<Portfolio> portfolio) {
        this.portfolio = portfolio;
    }

    public List<Person> getPerson() {
        return person;
    }

    public void setPerson(List<Person> person) {
        this.person = person;
    }

    public List<Work> getWork() {
        return work;
    }

    public void setWork(List<Work> work) {
        this.work = work;
    }

}
