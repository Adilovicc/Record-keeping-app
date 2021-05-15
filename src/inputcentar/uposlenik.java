/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputcentar;

/**
 *
 * @author hikme
 */
public class uposlenik {
    int uposlenik_id;
    String ime;
    String prezime;
    int spol;
    String datum_rodjenja;
    String datum_zaposlenja;
    String grad;
    String broj_telefona;
    String email;
    String lozinka;
    int tim_id;
    int admin;

    public uposlenik() {
    }
    
    public uposlenik(String ime, String prezime, int spol, String datum_rodjenja, String datum_zaposlenja, String grad, String broj_telefona, String email, String lozinka, int tim_id, int admin) {
        this.ime = ime;
        this.prezime = prezime;
        this.spol = spol;
        this.datum_rodjenja = datum_rodjenja;
        this.datum_zaposlenja = datum_zaposlenja;
        this.grad = grad;
        this.broj_telefona = broj_telefona;
        this.email = email;
        this.lozinka = lozinka;
        this.tim_id = tim_id;
        this.admin = admin;
    }

    
    public int getUposlenik_id() {
        return uposlenik_id;
    }

    public void setUposlenik_id(int uposlenik_id) {
        this.uposlenik_id = uposlenik_id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public int getSpol() {
        return spol;
    }

    public void setSpol(int spol) {
        this.spol = spol;
    }

    public String getDatum_rodjenja() {
        return datum_rodjenja;
    }

    public void setDatum_rodjenja(String datum_rodjenja) {
        this.datum_rodjenja = datum_rodjenja;
    }

    public String getDatum_zaposlenja() {
        return datum_zaposlenja;
    }

    public void setDatum_zaposlenja(String datum_zaposlenja) {
        this.datum_zaposlenja = datum_zaposlenja;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getBroj_telefona() {
        return broj_telefona;
    }

    public void setBroj_telefona(String broj_telefona) {
        this.broj_telefona = broj_telefona;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public int getTim_id() {
        return tim_id;
    }

    public void setTim_id(int tim_id) {
        this.tim_id = tim_id;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
    
    
}
