package Util;

/**
 * Created by Bruno on 25/10/2017.
 */

public class NegocioException extends Exception{

    public NegocioException(){
        super();
    }

    public NegocioException(Exception e)
    {
        super(e.getMessage());
    }

    public NegocioException(String mensagem)
    {
        super(mensagem);
    }
}
