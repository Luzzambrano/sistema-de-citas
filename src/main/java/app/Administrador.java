package app;

public class Administrador {
    private String nombre;
    private String password;

    public Administrador(String nombre, String password){
        this.nombre = nombre;
        this.password = password;
    }

    @Override
    public boolean equals(Object object) {
        if(object instanceof Administrador) {
            Administrador otroAdmin = (Administrador) object;
            return (nombre.equals(otroAdmin.nombre) && password.equals(otroAdmin.password));
        }

        return false;
    }
}
