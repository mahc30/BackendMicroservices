package backendJava.client.presentation.exception.Foto;

public class FotoFileConversionErrorException extends RuntimeException{
    public FotoFileConversionErrorException(){
        super("No se pudo convertir la imagen");
    }
}
