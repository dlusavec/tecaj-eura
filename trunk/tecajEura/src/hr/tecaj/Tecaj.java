package hr.tecaj;

import java.math.BigDecimal;

public class Tecaj {
    private int mjesec;
    private int dan;
    private String link;
    private BigDecimal kupovni;
    private BigDecimal srednji;
    private BigDecimal prodajni;

    public Tecaj() {
        super();
    }


    public void setMjesec(int mjesec) {
        this.mjesec = mjesec;
    }

    public int getMjesec() {
        return mjesec;
    }

    public void setDan(int dan) {
        this.dan = dan;
    }

    public int getDan() {
        return dan;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setKupovni(BigDecimal kupovni) {
        this.kupovni = kupovni;
    }

    public BigDecimal getKupovni() {
        return kupovni;
    }

    public void setSrednji(BigDecimal srednji) {
        this.srednji = srednji;
    }

    public BigDecimal getSrednji() {
        return srednji;
    }

    public void setProdajni(BigDecimal prodajni) {
        this.prodajni = prodajni;
    }

    public BigDecimal getProdajni() {
        return prodajni;
    }

}
