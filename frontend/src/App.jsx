import { useCallback, useEffect, useState } from 'react';
import { ticketsApi } from './api/tickets';
import TicketForm from './components/TicketForm';
import TicketList from './components/TicketList';
import './App.css';
export default function App() {
  const [tickets,setTickets]=useState([]), [filtre,setFiltre]=useState(''), [loading,setLoading]=useState(true), [error,setError]=useState(''), [actionError,setActionError]=useState('');
  const charger=useCallback(async()=>{setLoading(true);setError('');try{setTickets(await ticketsApi.lister(filtre));}catch(e){setError(e.message);}finally{setLoading(false);}},[filtre]);
  useEffect(()=>{charger();},[charger]);
  async function creer(data){await ticketsApi.creer(data); await charger();}
  async function fermer(ticket){if(!window.confirm(`Fermer le ticket « ${ticket.titre} » ?`))return;setActionError('');try{const updated=await ticketsApi.fermer(ticket.id);setTickets(xs=>xs.map(x=>x.id===updated.id?updated:x));}catch(e){setActionError(e.message);}}
  return <main className="page"><header><p className="eyebrow">SERVICE SUPPORT</p><h1>Gestion des tickets</h1><p>Créez, consultez et suivez les demandes.</p></header>
    <TicketForm onCreate={creer}/><section className="listSection"><div className="listHeading"><h2>Tickets</h2><label className="filter">Filtrer par statut<select value={filtre} onChange={e=>setFiltre(e.target.value)}><option value="">Tous</option><option>OUVERT</option><option>EN_COURS</option><option>FERME</option></select></label></div>
    {actionError&&<p className="error" role="alert">{actionError}</p>}<TicketList tickets={tickets} loading={loading} error={error} retry={charger} onClose={fermer}/></section>
    <footer>Module Ticket · KFOKAM48</footer></main>;
}
