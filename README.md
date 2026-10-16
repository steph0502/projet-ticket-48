# Module Ticket — KFOKAM48

Application de suivi des demandes de support : API Spring Boot/PostgreSQL et interface React.

## Démarrage

1. `docker compose up -d` — PostgreSQL est publié sur `localhost:5433` (le port local 5432 est déjà occupé).
2. `cd backend && mvn spring-boot:run` — API sur `http://localhost:8080`.
3. `cd frontend && npm install && npm run dev` — interface sur `http://localhost:5173`.
4. Swagger : `http://localhost:8080/swagger-ui.html`.

## API

- `POST /api/tickets` — créer avec `{ "titre": "...", "description": "..." }`.
- `GET /api/tickets` — lister; filtre facultatif `?statut=OUVERT|EN_COURS|FERME`.
- `PATCH /api/tickets/{id}/fermer` — fermer un ticket.

Les erreurs renvoient un objet JSON avec un champ `message`.

## Vérifier un ticket dans PostgreSQL

`docker exec -it ticket-postgres psql -U ticket -d tickets -c "SELECT id, titre, statut, date_creation FROM tickets;"`
