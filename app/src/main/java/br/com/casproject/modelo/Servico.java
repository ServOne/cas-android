
package br.com.casproject.modelo;


import java.io.Serializable;

public class Servico implements Serializable {
    private static final long serialVersionUID = -5998434779602343501L;
    public String id;
    public String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
