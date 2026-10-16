import { useState } from 'react';
export default function TicketForm({ onCreate }) {
  const [titre, setTitre] = useState('');
  const [description, setDescription] = useState('');
  const [erreur, setErreur] = useState('');
  const [envoi, setEnvoi] = useState(false);
  async function submit(e) {
    e.preventDefault(); setErreur(''); setEnvoi(true);
    try { await onCreate({ titre: titre.trim(), description: description.trim() || null }); setTitre(''); setDescription(''); }
    catch (err) { setErreur(err.message); }
    finally { setEnvoi(false); }
  }
  return <form className="card" onSubmit={submit}>
    <h2>Créer un ticket</h2>
    <label>Titre<input value={titre} onChange={e=>setTitre(e.target.value)} required placeholder="Ex. Impossible de se connecter" /></label>
    <label>Description (facultative)<textarea value={description} onChange={e=>setDescription(e.target.value)} rows="3" /></label>
    {erreur && <p className="error" role="alert">{erreur}</p>}
    <button disabled={!titre.trim() || envoi}>{envoi ? 'Création…' : 'Créer le ticket'}</button>
  </form>;
}
