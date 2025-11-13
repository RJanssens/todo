# Project Manager - Linear & Jira Style Application

A modern project management application inspired by Linear and Jira, built with Spring Boot and Angular.

## Features

- **Project Management**: Create and manage multiple projects with unique keys
- **Ticket/Issue Tracking**: Full ticket lifecycle management with customizable statuses
- **Kanban Board**: Visual board with drag-and-drop columns (To Do, In Progress, Ready for Review, Done)
- **Priority Levels**: Five priority levels (Lowest, Low, Medium, High, Highest)
- **User Management**: Assign tickets to team members
- **Tags/Labels**: Organize tickets with colored tags
- **Comments**: Add remarks and discussions to tickets
- **Issue Linking**: Link related tickets together
- **Due Dates**: Set and track ticket deadlines
- **RESTful API**: Complete REST API for all operations
- **Modern UI**: Clean, responsive interface similar to Linear and Jira

## Technology Stack

### Backend
- **Spring Boot 3.2.0** - Java framework
- **Spring Data JPA** - Database access
- **H2 Database** - In-memory database (development)
- **PostgreSQL** - Production database support
- **Lombok** - Reduce boilerplate code
- **Maven** - Build tool

### Frontend
- **Angular 17** - Frontend framework
- **TypeScript** - Type-safe JavaScript
- **RxJS** - Reactive programming
- **Standalone Components** - Modern Angular architecture

## Project Structure

```
.
├── backend/                  # Spring Boot backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/projectmanager/app/
│   │   │   │   ├── controller/      # REST controllers
│   │   │   │   ├── service/         # Business logic
│   │   │   │   ├── repository/      # Data access
│   │   │   │   ├── model/           # Domain entities
│   │   │   │   ├── dto/             # Data transfer objects
│   │   │   │   ├── config/          # Configuration
│   │   │   │   └── exception/       # Exception handling
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   └── pom.xml
│
└── frontend/                 # Angular frontend
    ├── src/
    │   ├── app/
    │   │   ├── components/          # UI components
    │   │   │   ├── project-list/
    │   │   │   ├── project-detail/
    │   │   │   ├── ticket-board/
    │   │   │   └── ticket-detail/
    │   │   ├── services/            # API services
    │   │   ├── models/              # TypeScript interfaces
    │   │   ├── app.component.ts
    │   │   ├── app.routes.ts
    │   │   └── app.config.ts
    │   ├── index.html
    │   ├── main.ts
    │   └── styles.css
    ├── angular.json
    ├── package.json
    └── tsconfig.json
```

## Prerequisites

- **Java 17** or higher
- **Node.js 18** or higher
- **npm** or **yarn**
- **Maven 3.6+**

## Installation & Setup

### Backend Setup

1. Navigate to the backend directory:
   ```bash
   cd backend
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

   Or run the JAR directly:
   ```bash
   java -jar target/project-manager-backend-1.0.0.jar
   ```

4. The backend will start on `http://localhost:8080`

5. Access the H2 Console (development): `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:projectmanagerdb`
   - Username: `sa`
   - Password: (leave empty)

### Frontend Setup

1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the development server:
   ```bash
   npm start
   ```

4. The frontend will start on `http://localhost:4200`

5. Open your browser and navigate to `http://localhost:4200`

## API Documentation

### Base URL
```
http://localhost:8080/api
```

### Endpoints

#### Users
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `GET /api/users/username/{username}` - Get user by username
- `POST /api/users` - Create new user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

#### Projects
- `GET /api/projects` - Get all projects
- `GET /api/projects/{id}` - Get project by ID
- `GET /api/projects/key/{key}` - Get project by key
- `POST /api/projects` - Create new project
- `PUT /api/projects/{id}` - Update project
- `DELETE /api/projects/{id}` - Delete project

#### Tickets
- `GET /api/tickets` - Get all tickets
- `GET /api/tickets/{id}` - Get ticket by ID
- `GET /api/tickets/number/{ticketNumber}` - Get ticket by number
- `GET /api/tickets/project/{projectId}` - Get tickets by project
- `GET /api/tickets/assignee/{assigneeId}` - Get tickets by assignee
- `POST /api/tickets` - Create new ticket
- `PUT /api/tickets/{id}` - Update ticket
- `DELETE /api/tickets/{id}` - Delete ticket

#### Tags
- `GET /api/tags` - Get all tags
- `GET /api/tags/{id}` - Get tag by ID
- `POST /api/tags` - Create new tag
- `PUT /api/tags/{id}` - Update tag
- `DELETE /api/tags/{id}` - Delete tag

#### Comments
- `GET /api/comments/ticket/{ticketId}` - Get comments by ticket
- `POST /api/comments` - Create new comment
- `PUT /api/comments/{id}` - Update comment
- `DELETE /api/comments/{id}` - Delete comment

#### Ticket Links
- `GET /api/ticket-links/source/{sourceTicketId}` - Get links by source ticket
- `GET /api/ticket-links/target/{targetTicketId}` - Get links by target ticket
- `POST /api/ticket-links` - Create new ticket link
- `DELETE /api/ticket-links/{id}` - Delete ticket link

## Sample Data

The application comes with pre-loaded sample data:

### Users
- John Doe (john_doe)
- Jane Smith (jane_smith)
- Bob Wilson (bob_wilson)

### Projects
- WEB - Web Application
- MOB - Mobile App

### Tickets
Various sample tickets across different statuses and priorities

## Configuration

### Backend Configuration (`application.properties`)

```properties
# Server Configuration
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:h2:mem:projectmanagerdb
spring.datasource.username=sa
spring.datasource.password=

# JPA Configuration
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
```

### Frontend Configuration

The API base URL is configured in `src/app/services/api.service.ts`:
```typescript
private baseUrl = 'http://localhost:8080/api';
```

## Production Deployment

### Backend

1. Update `application.properties` for PostgreSQL:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/projectmanager
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```

2. Build production JAR:
   ```bash
   mvn clean package -DskipTests
   ```

3. Run the application:
   ```bash
   java -jar target/project-manager-backend-1.0.0.jar
   ```

### Frontend

1. Build for production:
   ```bash
   npm run build
   ```

2. The build artifacts will be stored in `dist/`

3. Deploy the contents to your web server (nginx, Apache, etc.)

## Development

### Adding New Features

1. **Backend**: Add entities → repositories → services → controllers
2. **Frontend**: Add models → services → components

### Code Style

- Backend: Follow Java naming conventions and Spring Boot best practices
- Frontend: Follow Angular style guide and TypeScript best practices

## Testing

### Backend Tests
```bash
cd backend
mvn test
```

### Frontend Tests
```bash
cd frontend
npm test
```

## Troubleshooting

### Backend Issues

- **Port already in use**: Change `server.port` in `application.properties`
- **Database connection issues**: Check H2 console or PostgreSQL connection

### Frontend Issues

- **API connection issues**: Verify backend is running and API URL is correct
- **CORS errors**: CORS is configured in controllers with `@CrossOrigin(origins = "*")`

## Future Enhancements

- [ ] User authentication and authorization
- [ ] Real-time updates with WebSockets
- [ ] File attachments for tickets
- [ ] Email notifications
- [ ] Sprint planning
- [ ] Burndown charts
- [ ] Export functionality
- [ ] Mobile app
- [ ] Dark mode theme

## License

This project is open source and available under the MIT License.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Support

For issues and questions, please open an issue on the repository.
