package es.upm.dit.isst.tfg.tfgwebapp.model;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class TFG {

    public TFG (){

    }

    @Email

    private String email;

    private String pass;

    @NotEmpty

    private String nombre;

    @NotEmpty

    private String titulo;

    @Email

    private String tutor;

    private int status;

    private byte[] memoria;

    private double nota;

    public String getEmail() {
        return email;
    }

    public Object getTutor() {
        return tutor;
    }


    public String getPass() {
        return pass;
    }


    public String getNombre() {
        return nombre;
    }


    public String getTitulo() {
        return titulo;
    }
    

    public int getStatus() {
        return status;
    }


    public byte[] getMemoria() {
        return memoria;
    }


    public double getNota() {
        return nota;
    }

    public void setEmail(String _email) {
        email = _email;
    }

    public void setPass(String _pass) {
        pass = _pass;
    }

    public void setNombre(String _nombre) {
        nombre = _nombre;
    }

    public void setTitulo(String _titulo) {
        titulo = _titulo;
    }

    public void setStatus(int _status) {
        status = _status;
    }

    public void setMemoria(byte[] _memoria) {
        memoria = _memoria;
    }

    public void setNota(double _nota) {
        nota = _nota;
    }

    public void setTutor(String _tutor) {
        tutor = _tutor;
    }

    
}
