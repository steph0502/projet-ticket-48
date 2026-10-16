const BASE = 'http://localhost:8080/api/tickets';
async function request(url, options) {
  const response = await fetch(url, { headers: { 'Content-Type': 'application/json' }, ...options });
  if (!response.ok) {
    let message = `Erreur HTTP ${response.status}`;
    try { message = (await response.json()).message || message; } catch {}
    throw new Error(message);
  }
  return response.status === 204 ? null : response.json();
}
export const ticketsApi = {
  lister: statut => request(`${BASE}${statut ? `?statut=${encodeURIComponent(statut)}` : ''}`),
  creer: ticket => request(BASE, { method: 'POST', body: JSON.stringify(ticket) }),
  fermer: id => request(`${BASE}/${id}/fermer`, { method: 'PATCH' })
};
