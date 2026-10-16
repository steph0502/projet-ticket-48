package cm.kfokam.ticket.ticket;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="tickets", uniqueConstraints=@UniqueConstraint(name="uk_ticket_titre", columnNames="titre"))
public class Ticket {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false, length=100, unique=true) private String titre;
 @Column(columnDefinition="text") private String description;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private Statut statut;
 @Column(nullable=false) private LocalDateTime dateCreation;
 protected Ticket() {}
 public Ticket(String titre,String description){this.titre=titre;this.description=description;this.statut=Statut.OUVERT;this.dateCreation=LocalDateTime.now();}
 public Long getId(){return id;} public String getTitre(){return titre;} public String getDescription(){return description;} public Statut getStatut(){return statut;} public LocalDateTime getDateCreation(){return dateCreation;}
 public void fermer(){this.statut=Statut.FERME;}
}
