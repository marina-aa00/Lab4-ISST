package es.upm.dit.isst.tfgapi.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;



@Entity public class TFG {

    @Id private String email;
    private String pass;
    private String nombre;
    private String titulo;
    private int status;
    @Lob private byte[] memoria;
    private double nota;
    private String tutor;

    public TFG (){

    }

    public String getEmail () {
        return email;
    }

    public String getPass () {
        return pass;
    }

    public String getNombre () {
        return nombre;
    }

    public String getTitulo () {
        return titulo;
    }

    public int getStatus () {
        return status;
    }

    public byte[] getMemoria () {
        return memoria;
    }

    public double getNota () {
        return nota;
    }

    public String getTutor () {
        return tutor;
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

    

  
    /*public static void main(String [] args) {
        TFG tfg = new TFG();
    }*/
       
    
}
    


    

