<h1 align="center">
  Apocalypse Simulator (as-spring-boot)
</h1>

<p align="center">
  <img src="https://res.cloudinary.com/dcstkilp1/image/upload/v1741287186/as-sprites/ato6kcxq7mvcz3impspr.png" alt="Apocalypse Simulator" width="100" />
</p>

## 📝 Description
**Apocalypse Simulator** is an interactive question-and-action game where your choices affect your stats.

**This Backend** is the server that powers the **Apocalypse Simulator**, an interactive decision-making game. The backend is built with **Spring Boot** and handles the game logic, user authentication via **OAuth2 with Google**, and the persistent storage of data regarding characters, events, and their effects on the character stats.

This backend also interacts with a **PostgreSQL** database (hosted on **Supabase**), though it can be easily configured to work with any other database.

## 🚀 Live Demo
🔗 [Play Now](https://as.gorocode.dev/)

### 🚨 Backend Information (Render Deployment)
The backend of this project is deployed on **Render** and may enter **sleep mode** after a period of inactivity. The first request after the backend is in sleep mode may take a few moments to start up.

#### Please be patient while it initializes.

## 📦 Related Repositories
- 🔗 **Frontend:** [as-react](https://github.com/gorocode/as-react)
- 🔗 **Backend:** [as-spring-boot](https://github.com/gorocode/as-spring-boot)

## ⚡ Features
- **User Authentication**: Supports **OAuth2** with Google to sign in and manage profiles.
- **Character Management**: Stores the information of created characters, their stats, and their progress.
- **Events**: Manages game events (questions and decisions) and their effects on character stats.
- **Database**: Uses **PostgreSQL** (on **Supabase**, but easily configurable for other providers).
- **RESTful API**: Exposes endpoints for the frontend to interact with the game and users.

## 🛠️ Technologies Used
- **Backend Framework**: Spring Boot
- **Database**: PostgreSQL
- **Authentication**: OAuth2 with Google
- **API**: RESTful APIs with **Spring Data JPA**
- **Email**: Integration with **SMTP** (e.g., Gmail)
- **Containerization**: Docker

## 📂 Installation and Configuration

### 🔧 Prerequisites
- **Java 21** or higher
- **Maven** (or another build tool)
- **PostgreSQL Database** (can be replaced with another database)
- **Google OAuth2 Credentials** for user authentication

### 📥 Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/gorocode/as-spring-boot.git
   cd as-spring-boot
   ```

2. Set up the environment variables:
   Create an `.env` file (or use `application.properties` or `application.yml`) at the root of the project with the following variables:
   ```env
   FRONTEND_URL=<YOUR_FRONTEND_URL>
   SPRING_DATASOURCE_URL=<YOUR_DATABASE_URL>
   SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_GOOGLE_CLIENT_ID=<YOUR_GOOGLE_CLIENT_ID>
   SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_GOOGLE_CLIENT_SECRET=<YOUR_GOOGLE_CLIENT_SECRET>
   SPRING_MAIL_USERNAME=<YOUR_EMAIL>
   SPRING_MAIL_PASSWORD=<YOUR_PASSWORD>
   ```

3. Install dependencies:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

   The server will be available at `http://localhost:8080` by default.

### ⚙️ Database Configuration
The project is set up to use **PostgreSQL**, but you can change the configurations in `application.properties` and the driver in `pom.xml` to connect to any other database supported by JPA. 


### 🛠️ Docker Deployment
This project is configured for deployment using **Docker**. The `Dockerfile` allows building and running the application in an isolated environment.

1. Build the Docker image:
   ```bash
   docker build -t apocalypse-simulator-backend .
   ```

2. Run the Docker container:
   ```bash
   docker run -p 8080:8080 apocalypse-simulator-backend
   ```

   The application will run on port `8080` within the container.

## 🎮 Game API

The backend exposes several APIs to interact with the game and users. Here are some important endpoints:

### Player:
- `POST /player/new`: Create a new player.
  - **Body**: `name`, `userId (Long)`
- `PUT /player`: Update a player's details.
  - **Body**: `{id: Long, name: String, health: Integer, food: Integer, moral: Integer, survivedDays, finished: Boolean}` (complete player object)
- `GET /player/user/{userId}`: Get the list of players for a specific user.
  - **Params**: `userId (Long)`
- `DELETE /player/{playerId}`: Delete a player.
  - **Params**: `playerId (Long)`
- `POST /player/action`: Send an action for a player.
  - **Body**: `{playerId: Long, eventId: Long, option: Integer}` (action data)

### Event:
- `GET /event/random`: Get a random event.
  
### User:
- `PUT /user`: Update the authenticated user's details.
  - **Body**: `{id: Long, name: String, username: String, email: String, profilePicture: String}` (user data)
- `GET /user`: Get the authenticated user's details.
- `GET /user/email-in-use/{email}`: Check if an email is already in use by another user.
  - **Params**: `email (String)`
- `GET /oauth2/authorization/google`: Log in with Google OAuth2.
- `POST /user/logout`: Log out the user.


### Contact Form:
- `POST /email/contact`: Send a contact form submission.
  - **Body**: `{name: String, email: String, message: String}` (contact form data)


## 👥 Contributing

Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a branch for your feature (`git checkout -b feature-new`).
3. Make your changes (`git commit -m "Add new feature"`).
4. Push your branch (`git push origin feature-new`).
5. Open a Pull Request.

## 📝 License

This project is licensed under the **MIT** License. See the `LICENSE` file for more details.

