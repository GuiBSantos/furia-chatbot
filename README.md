# 💬 FURIA ChatBot

<img width="100%" src="https://capsule-render.vercel.app/api?type=waving&height=200&color=gradient&fontColor=7700ff"/>

O **FURIA ChatBot** é uma aplicação interativa que utiliza a **Google Gemini API** para gerar respostas dinâmicas e divertidas, com um toque de personalidade fã da FURIA! Desenvolvido com **Java 24** e **Spring Boot**, o chatbot foi projetado para engajar e interagir com os usuários de maneira divertida e informativa.

> Desenvolvido como parte do desafio técnico para o programa de estágio na FURIA, esse projeto tem o objetivo de criar uma experiência única para os fãs da equipe!
---

## 🚀 Tecnologias

- **Java 24**
- **Spring Boot 3.4.5**
- **WebFlux**
- **Google Gemini API**
- **HTML5, CSS3, JavaScript**
- **WebClient**
---

## 🛠️ Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- [Java JDK 24](https://www.oracle.com/java/technologies/javase/jdk-downloads.html)
- [Maven](https://maven.apache.org/install.html)
- **Chave da API do Google Gemini** (Inscreva-se na [Google Cloud](https://cloud.google.com/))

---

## 📁 Configuração Inicial

1. **Clone o repositório**:

```bash
git clone https://github.com/GuiBSantos/furia-chatbot.git
cd furia-chatbot
```

2. **Adicione sua chave da API no arquivo `application.properties`**:
```
gemini.api.key=sua_chave_aqui
```

3. **Compile e execute o projeto**:
```
mvn clean spring-boot:run
Certifique-se de que o WebClient está configurado corretamente em ChatService.java para comunicação com a Google Gemini API.
```

## 📷 Exemplo de Interação

<p align="center">
  <a href="https://ibb.co/sJNk7h3T">
    <a href="https://ibb.co/8Lvt3g5K"><img src="https://i.ibb.co/sdxzS9wm/imagem-2025-04-27-211852990.png" alt="imagem-2025-04-27-211852990" border="0"></a>
  </a>
</p>

## 🤝 Contribuições

Sinta-se à vontade para abrir uma issue, sugerir melhorias, enviar um pull request ou me enviar uma DM :)

<img width="100%" src="https://capsule-render.vercel.app/api?type=waving&height=200&color=gradient&fontColor=d900ff&section=footer"/>
