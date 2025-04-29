const BASE_URL = 'https://furia-chatbot-production.up.railway.app';

function sendMessage() {
  const inputBox = document.getElementById('user-input');
  const message = inputBox.value.trim();
  if (!message) return;

  appendMessage(message, 'user');
  inputBox.value = '';

  const typing = showTypingIndicator(document.querySelector('.chat-container'));

  fetch(`${BASE_URL}/api/chat`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ message })
  })
  .then(res => res.json())
  .then(data => {
    removeTypingIndicator(typing);
    appendMessage(data.response, 'bot');
  })
  .catch(() => {
    removeTypingIndicator(typing);
    appendMessage('😵 Opa! Algo deu errado ao falar com o bot da FURIA.', 'bot');
  });
}

function appendMessage(text, sender) {
  const chatBox = document.getElementById('chat-box');
  const msg = document.createElement('div');
  msg.classList.add(`${sender}-message`);
  msg.textContent = text;
  chatBox.appendChild(msg);
  chatBox.scrollTop = chatBox.scrollHeight;
}

function showTypingIndicator(container) {
  const indicator = document.createElement('div');
  indicator.className = 'typing-indicator';
  indicator.innerHTML = '<span></span><span></span><span></span>';
  const inputArea = container.querySelector('.input-area');
  container.insertBefore(indicator, inputArea);
  return indicator;
}

function removeTypingIndicator(indicator) {
  indicator.remove();
}

document.getElementById('send-btn').addEventListener('click', sendMessage);

document.getElementById('user-input').addEventListener('keydown', e => {
  if (e.key === 'Enter') sendMessage();
});

const chatContainer = document.querySelector('.chat-container');
const initTyping = showTypingIndicator(chatContainer);
setTimeout(() => {
  removeTypingIndicator(initTyping);
  appendMessage('E aí, guerreiro! Pronto pra próxima batalha? 🔥', 'bot');
}, 2000);
