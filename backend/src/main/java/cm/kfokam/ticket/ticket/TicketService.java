package cm.kfokam.ticket.ticket;
import cm.kfokam.ticket.common.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
public class TicketService {
 private final TicketRepository repository;
 public TicketService(TicketRepository repository){this.repository=repository;}
 @Transactional public TicketDtos.Reponse creer(TicketDtos.Creation dto){if(repository.existsByTitre(dto.titre()))throw new ConflitException("RG-02 : un ticket avec ce titre existe déjà.");return TicketDtos.Reponse.depuis(repository.save(new Ticket(dto.titre().trim(),dto.description())));}
 @Transactional(readOnly=true) public List<TicketDtos.Reponse> lister(Statut statut){List<Ticket> tickets=statut==null?repository.findAllByOrderByDateCreationDesc():repository.findAllByStatutOrderByDateCreationDesc(statut);return tickets.stream().map(TicketDtos.Reponse::depuis).toList();}
 @Transactional public TicketDtos.Reponse fermer(Long id){Ticket t=repository.findById(id).orElseThrow(()->new NotFoundException("Ticket "+id+" introuvable."));if(t.getStatut()==Statut.FERME)throw new ConflitException("RG-03 : un ticket fermé ne peut pas changer d’état.");t.fermer();return TicketDtos.Reponse.depuis(t);}
}
