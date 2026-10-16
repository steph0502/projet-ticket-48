package cm.kfokam.ticket.common;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestControllerAdvice
public class ApiExceptionHandler {
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<?> validation(MethodArgumentNotValidException e){String m=e.getBindingResult().getFieldErrors().stream().findFirst().map(x->x.getDefaultMessage()).orElse("Données invalides.");return ResponseEntity.badRequest().body(Map.of("message",m));}
 @ExceptionHandler(NotFoundException.class) ResponseEntity<?> notFound(RuntimeException e){return ResponseEntity.status(404).body(Map.of("message",e.getMessage()));}
 @ExceptionHandler({ConflitException.class,DataIntegrityViolationException.class}) ResponseEntity<?> conflict(Exception e){String m=e instanceof ConflitException?e.getMessage():"RG-02 : un ticket avec ce titre existe déjà.";return ResponseEntity.status(409).body(Map.of("message",m));}
}
