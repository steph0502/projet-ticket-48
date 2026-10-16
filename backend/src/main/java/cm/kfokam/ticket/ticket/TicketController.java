package cm.kfokam.ticket.ticket;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService service;
    public TicketController(TicketService service) { this.service = service; }

    @Operation(summary = "Créer un ticket")
    @ApiResponses({
        @ApiResponse(responseCode="201", description="Ticket créé"),
        @ApiResponse(responseCode="400", description="Titre absent ou trop long"),
        @ApiResponse(responseCode="409", description="RG-02 : titre déjà utilisé")
    })
    @PostMapping
    public ResponseEntity<TicketDtos.Reponse> creer(@Valid @RequestBody TicketDtos.Creation dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.creer(dto));
    }

    @Operation(summary = "Lister les tickets, éventuellement filtrés par statut")
    @ApiResponse(responseCode="200", description="Liste des tickets")
    @GetMapping
    public List<TicketDtos.Reponse> lister(@RequestParam(required=false) Statut statut) {
        return service.lister(statut);
    }

    @Operation(summary = "Fermer un ticket ouvert ou en cours")
    @ApiResponses({
        @ApiResponse(responseCode="200", description="Ticket fermé"),
        @ApiResponse(responseCode="404", description="Ticket introuvable"),
        @ApiResponse(responseCode="409", description="RG-03 : ticket déjà fermé")
    })
    @PatchMapping("/{id}/fermer")
    public TicketDtos.Reponse fermer(@PathVariable Long id) { return service.fermer(id); }
}
