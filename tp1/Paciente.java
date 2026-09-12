import java.time.LocalDate;

public class Paciente extends Persona implements Atendible {
    private LocalDate fechaTurno;

    public Paciente(String id, String nombre, String apellido) {
        super(id, nombre, apellido);
    }

    public LocalDate getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(LocalDate fechaTurno) {
        this.fechaTurno = fechaTurno;
    }

    @Override
    public void atender() {
        System.out.println("Atendiendo al paciente: " + getNombre() + " " + getApellido() 
                + " (Turno: " + fechaTurno + ")");
    }

    @Override
    public String toString() {
        return super.toString() + " - Turno: " + fechaTurno;
    }
}