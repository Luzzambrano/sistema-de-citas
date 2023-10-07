package app;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GestorCita {
    class Cita{
        public String identificadorUnico;
        public String motivo;
        public LocalDateTime fechayHora;

        Cita(String identificadorUnico, String motivo, LocalDateTime fecha){
            this.identificadorUnico = identificadorUnico;
            this.motivo = motivo;
            this.fechayHora = fecha;
        }
        protected void imprimir(){
            System.out.println(this.toString());
        }
        @Override
        public String toString(){
            return String.format("{Identificador: %s, Motivo: %s, Fecha: %s}", identificadorUnico, motivo, fechayHora.toString());
        }
    }

    private Map<String, Cita> citas = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void nuevaCita(){
        System.out.print("Fecha (yyyy-mm-ddThh:mm:ss): ");
        String fechayHora = scanner.nextLine();
        LocalDateTime date = LocalDateTime.parse(fechayHora);
        System.out.print("Especialidad: ");
        String motivo = scanner.nextLine();
        System.out.print("Identificador: ");
        String identificadorUnico = scanner.nextLine();

        citas.put(identificadorUnico, new Cita(identificadorUnico, motivo, date));
    }

    public void mostrarCitas(){
        citas.forEach((id, cita) -> cita.imprimir());
    }
}
