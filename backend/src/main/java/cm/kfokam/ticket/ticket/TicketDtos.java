package cm.kfokam.ticket.ticket;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
public final class TicketDtos {
 private TicketDtos() {}
 public record Creation(@NotBlank(message="Le titre est obligatoire.") @Size(max=100,message="Le titre ne doit pas dépasser 100 caractères.") String titre, String description) {}
 public record Reponse(Long id,String titre,String description,Statut statut,LocalDateTime dateCreation) {
  public static Reponse depuis(Ticket t){return new Reponse(t.getId(),t.getTitre(),t.getDescription(),t.getStatut(),t.getDateCreation());}
 }
}
