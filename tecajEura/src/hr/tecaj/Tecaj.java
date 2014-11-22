package hr.tecaj;

public class Tecaj {  
    private String mjesec;
    private String dan;
    private String link;
    private String kupovni;
    private String srednji;
    private String prodajni;
    public Tecaj() {
        super();
    }


    public void setMjesec(String mjesec) {
        this.mjesec = mjesec;
    }

    public String getMjesec() {
        return mjesec;
    }

    public void setDan(String dan) {
        this.dan = dan;
    }

    public String getDan() {
        return dan;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setKupovni(String kupovni) {
        this.kupovni = kupovni;
    }

    public String getKupovni() {
        return kupovni;
    }

    public void setSrednji(String srednji) {
        this.srednji = srednji;
    }

    public String getSrednji() {
        return srednji;
    }

    public void setProdajni(String prodajni) {
        this.prodajni = prodajni;
    }

    public String getProdajni() {
        return prodajni;
    }

}
