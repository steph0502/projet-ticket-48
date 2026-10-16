const date = value => new Date(value).toLocaleString('fr-FR');
export default function TicketList({ tickets, loading, error, retry, onClose }) {
  if (loading) return <p className="notice" role="status">Chargement des tickets…</p>;
  if (error) return <div className="errorBox"><p>Serveur injoignable ou erreur de chargement : {error}</p><button onClick={retry}>Réessayer</button></div>;
  if (!tickets.length) return <p className="notice">Aucun ticket</p>;
  return <div className="tableWrap"><table><thead><tr><th>Titre</th><th>Statut</th><th>Date de création</th><th>Action</th></tr></thead>
    <tbody>{tickets.map(t=><tr key={t.id}><td>{t.titre}</td><td><span className={`badge ${t.statut.toLowerCase()}`}>{t.statut.replace('_',' ')}</span></td><td>{date(t.dateCreation)}</td><td>{t.statut !== 'FERME' && <button className="secondary" onClick={()=>onClose(t)}>Fermer</button>}</td></tr>)}</tbody>
  </table></div>;
}
