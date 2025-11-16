const API = 'http://localhost:8080/api/transactions';

async function load() {
  const res = await fetch(API);
  const list = await res.json();
  const div = document.getElementById('list');
  div.innerHTML = list.map(e => `
    <div class="txn">
      <strong>${e.title}</strong> (${e.type}) - ₹${e.amount} <br>
      <small>${e.date} • ${e.category || ''}</small>
      <p>${e.note || ''}</p>
      <button onclick="del(${e.id})">Delete</button>
    </div>
  `).join('');
  renderSummary(list);
}

function renderSummary(list) {
  const sum = list.reduce((acc, t) => {
    acc.total = (acc.total || 0) + (t.type === 'INCOME' ? t.amount : -t.amount);
    acc.income = (acc.income || 0) + (t.type === 'INCOME' ? t.amount : 0);
    acc.expense = (acc.expense || 0) + (t.type === 'EXPENSE' ? t.amount : 0);
    return acc;
  }, {});
  document.getElementById('summary').innerHTML = `
    <h3>Summary</h3>
    <p>Total: ₹${(sum.total||0).toFixed(2)} | Income: ₹${(sum.income||0).toFixed(2)} | Expense: ₹${(sum.expense||0).toFixed(2)}</p>
  `;
}

async function del(id) {
  await fetch(API + '/' + id, { method:'DELETE' });
  load();
}

document.getElementById('txnForm').addEventListener('submit', async (ev)=>{
  ev.preventDefault();
  const data = Object.fromEntries(new FormData(ev.target));
  if (!data.date) data.date = new Date().toISOString().slice(0,10);
  data.amount = parseFloat(data.amount);
  await fetch(API, { method:'POST', headers:{'Content-Type':'application/json'}, body:JSON.stringify(data) });
  ev.target.reset();
  load();
});

load();
