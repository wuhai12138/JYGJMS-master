package com.summ.model.request;

/**
 * Created by summ on 17/11/17.
 */
public class TestReq extends BaseReq {
    private int id;
    private String name;
    private String aa;
    private String ab;
    private String ac;
    private String aca;
    private String acb;


    @Override
    public String toString() {
        return "TestReq{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", aa='" + aa + '\'' +
                ", ab='" + ab + '\'' +
                ", ac='" + ac + '\'' +
                ", aca='" + aca + '\'' +
                ", acb='" + acb + '\'' +
                '}';
    }

    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }

    public String getAb() {
        return ab;
    }

    public void setAb(String ab) {
        this.ab = ab;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getAca() {
        return aca;
    }

    public void setAca(String aca) {
        this.aca = aca;
    }

    public String getAcb() {
        return acb;
    }

    public void setAcb(String acb) {
        this.acb = acb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
