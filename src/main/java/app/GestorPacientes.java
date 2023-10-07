package app;

public class GestorPacientes {
    class Paciente{
        public String identificadorUnico;
        public String nombre;

        Paciente(String identificadorUnico, String nombre) {
            this.identificadorUnico = identificadorUnico;
            this.nombre = nombre;
        }
        protected void modificar(String nombre){ this.nombre = nombre;  }
        protected void imprimir(){
            System.out.println(this.toString());
        }
        @Override
        public String toString(){
            return String.format("{Identificador: %s, Nombre: %s}", identificadorUnico, nombre);
        }
    }
}
