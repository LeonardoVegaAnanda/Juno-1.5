package mx.com.ananda.cronos.juno.exception;

public class UsuarioNoFoundException extends Exception{
    public UsuarioNoFoundException(){
        super("EL USUARIO CON ESE USERNAME NO EXISTE");
    }

    public UsuarioNoFoundException(String mensaje){
        super(mensaje);
    }
}
