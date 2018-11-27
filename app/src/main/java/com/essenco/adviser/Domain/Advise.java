package com.essenco.adviser.Domain;

import java.io.Serializable;

public class Advise {
    private int adviseId;
    private String advise;

    public Advise(int adviseId, String advise) {
        this.adviseId = adviseId;
        this.advise = advise;
    }
    public int getAdviseId() {
        return adviseId;
    }

    public void setAdviseId(int adviseId) {
        this.adviseId = adviseId;
    }

    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise;
    }
    @Override
    public String toString() {
        return advise;
    }
}
