package com.example.demo;

import java.util.Optional;

public class Proposal {
    private int cost;
    private String type;
    private Optional<Capybara> capybaraValue = Optional.empty();
    private Optional<Envel> envelValue = Optional.empty();

    public int getCost(){
        return cost;
    }

    public String getType(){
        return type;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Optional<Capybara> getCapybaraValue() {
        return capybaraValue;
    }

    public void setCapybaraValue(Capybara capybaraValue) {
        this.capybaraValue = Optional.of(capybaraValue);
        this.envelValue = Optional.empty();
    }

    public Optional<Envel> getEnvelValue() {
        return envelValue;
    }

    public void setEnvelValue(Envel envelValue) {
        this.envelValue = Optional.of(envelValue);
        this.capybaraValue = Optional.empty();
    }
}
