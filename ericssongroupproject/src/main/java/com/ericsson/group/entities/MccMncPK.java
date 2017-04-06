package com.ericsson.group.entities;

import java.io.Serializable;

/**
 * Created by mypc1 on 04/04/2017.
 */
public class MccMncPK implements Serializable {
    protected Integer mcc;
    protected Integer mnc;

    public MccMncPK(Integer mcc, Integer mnc) {
        this.mcc = mcc;
        this.mnc = mnc;
    }

    public MccMncPK() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MccMncPK mccMncPK = (MccMncPK) o;

        if (!mcc.equals(mccMncPK.mcc)) return false;
        return mnc.equals(mccMncPK.mnc);
    }

    @Override
    public int hashCode() {
        int result = mcc.hashCode();
        result = 31 * result + mnc.hashCode();
        return result;
    }
}
