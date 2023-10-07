package app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GestorCita {

    private final Map<String, Cita> citas = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);
    private final GestorDoctor doctores;
    private final GestorPacientes pacientes;
    GestorCita(GestorDoctor doctores, GestorPacientes pacientes) {
        this.doctores = doctores;
        this.pacientes = pacientes;
    }

    GestorCita() {
        this.doctores = new GestorDoctor();
        this.pacientes = new GestorPacientes();
    }

    public boolean nuevaCita() {
        try {
            System.out.print("Fecha (yyyy-mm-ddThh:mm:ss): ");
            String fechayHora = scanner.nextLine();
            LocalDateTime date = LocalDateTime.parse(fechayHora);
            System.out.print("Motivo: ");
            String motivo = scanner.nextLine();
            System.out.print("Identificador de la Cita: ");
            String idCita = scanner.nextLine();
            System.out.print("Identificador del doctor: ");
            String idDoctor = scanner.nextLine();
            System.out.print("Identificador del paciente: ");
            String idPaciente = scanner.nextLine();

            citas.put(idCita, new Cita(idCita, doctores.getDoctor(idDoctor), pacientes.getPaciente(idPaciente), motivo, date));
            return true;
        } catch (Exception e) {
            System.out.println("\n No se pudo insertar cita");
            return false;
        }
    }

    public void mostrarCitas() {
        citas.forEach((id, cita) -> cita.imprimir());
    }

    public void crearCSV(){
        File archivo = new File("\\db\\citas.csv");
        try {
            if(!archivo.exists()){
                File carpeta = archivo.getParentFile();
                carpeta.mkdirs();
                archivo.createNewFile();
            }else{
                //borrar el contenido del archivo
                new FileWriter(archivo, false).close();
            }
            FileWriter escritor = new FileWriter(archivo, true);
            escritor.append("#ID;Doctor;Paciente;Motivo;Fecha\n");
            citas.forEach((id, cita) -> {
                try {
                    escritor.append(cita.generaLineaCSV());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            escritor.close();
        } catch (IOException e) {
            System.out.println(String.format("Error con manejo del archivo: %s", archivo.getAbsolutePath()));
        }
    }

    class Cita {
        public String identificadorUnico;
        public String motivo;
        public LocalDateTime fechayHora;
        public GestorDoctor.Doctor doctor;
        public GestorPacientes.Paciente paciente;

        Cita(String identificadorUnico, GestorDoctor.Doctor doctor, GestorPacientes.Paciente paciente, String motivo, LocalDateTime fecha) {
            this.identificadorUnico = identificadorUnico;
            this.doctor = doctor;
            this.paciente = paciente;
            this.motivo = motivo;
            this.fechayHora = fecha;
        }

        protected void imprimir() {
            System.out.println(this);
        }

        @Override
        public String toString() {
            return String.format("{Identificador: %s, Doctor: %s, Paciente: %s, Motivo: %s, Fecha: %s, Hora: %s}", identificadorUnico, doctor.nombre, paciente.nombre, motivo, fechayHora.toLocalDate(), fechayHora.toLocalTime());
        }

        public String generaLineaCSV() {
            return String.format("%s;%s;%s", identificadorUnico, doctor.nombre, paciente.nombre,motivo,fechayHora.toString());
        }
    }
}
