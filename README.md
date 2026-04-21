# Estudo de Desenvolvimento Android Moderno: NotesJetPackCompApp

Este projeto é uma aplicação de notas simples construída com o objetivo de estudar e implementar as ferramentas mais recentes do ecossistema Android.

## 🚀 Tecnologias e Conceitos Implementados

### 1. UI com Jetpack Compose
A interface é 100% declarativa, utilizando **Jetpack Compose**.
- **Composables**: Funções que definem a UI (ex: `NoteScreen`, `NoteItem`).
- **State Management**: Uso de `remember`, `mutableStateOf` e `collectAsState` para reagir a mudanças de dados.
- **Layouts**: Uso de `Box` (para sobreposição, como o fundo de ecrã), `Column` (alinhamento vertical) e `LazyColumn` (listas eficientes).

### 2. Persistência com Room Database
O Room é uma camada de abstração sobre o SQLite.
- **Entity**: A classe `Note` define a tabela na base de dados.
- **DAO (Data Access Object)**: A interface `NoteDao` define as operações (Insert, Delete, Update, Query). Utilizamos **Flow** para atualizações em tempo real e **suspend functions** para operações assíncronas.
- **Database**: `NoteDatabase` gere a instância da base de dados e segue o padrão Singleton.

### 3. Injeção de Dependências com Hilt (Dagger)
O Hilt facilita a gestão de instâncias de classes e a sua partilha entre componentes.
- **@HiltAndroidApp**: Obrigatório na classe `Application`.
- **@AndroidEntryPoint**: Utilizado na `MainActivity` para permitir a injeção.
- **@Inject**: Para injetar dependências no Repositório ou ViewModel.
- **AppModule**: Define como fornecer o `NoteDao` e a `NoteDatabase`.

### 4. Arquitetura MVVM (Model-View-ViewModel)
- **Model**: Onde os dados residem (Room/Note).
- **Repository**: Atua como uma camada de abstração, mediando o acesso entre o DAO e o resto da app.
- **ViewModel**: Gere os dados da UI e comunica com o Repositório. Sobrevive a mudanças de configuração (como rodar o ecrã).

### 5. Navegação
- **Navigation Compose**: Gere as transições entre ecrãs.
- **Hilt Navigation**: Integração para obter ViewModels injetados diretamente nas rotas de navegação usando `hiltViewModel()`.

## 📝 Notas de Estudo Pessoais
- **Background**: Para colocar uma imagem de fundo em todo o ecrã, envolve o conteúdo num `Box` e coloca a `Image` como primeiro elemento com `ContentScale.Crop`.
- **Flow**: Sempre que a base de dados muda, o `Flow` emite uma nova lista de notas automaticamente, e o Compose redesenha apenas o necessário.

---
*Este documento serve como base de consulta para futuros projetos.*
