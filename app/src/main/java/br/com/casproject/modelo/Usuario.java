
package br.com.casproject.modelo;


import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = -5998434779602343501L;

    private String id;
    private String name;
    private String status;
    private String email;
    private String phone;
    private String datapicture;
    private String mimetype;
    private String org_id;

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDatapicture() {
        return datapicture;
    }

    public void setDatapicture(String datapicture) {
        this.datapicture = datapicture;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }
}
