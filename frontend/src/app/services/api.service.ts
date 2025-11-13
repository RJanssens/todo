import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { Project } from '../models/project.model';
import { Ticket } from '../models/ticket.model';
import { Tag } from '../models/tag.model';
import { Comment } from '../models/comment.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private baseUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  // User endpoints
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}/users`);
  }

  getUser(id: number): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/users/${id}`);
  }

  createUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/users`, user);
  }

  updateUser(id: number, user: User): Observable<User> {
    return this.http.put<User>(`${this.baseUrl}/users/${id}`, user);
  }

  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/users/${id}`);
  }

  // Project endpoints
  getProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.baseUrl}/projects`);
  }

  getProject(id: number): Observable<Project> {
    return this.http.get<Project>(`${this.baseUrl}/projects/${id}`);
  }

  getProjectByKey(key: string): Observable<Project> {
    return this.http.get<Project>(`${this.baseUrl}/projects/key/${key}`);
  }

  createProject(project: any): Observable<Project> {
    return this.http.post<Project>(`${this.baseUrl}/projects`, project);
  }

  updateProject(id: number, project: any): Observable<Project> {
    return this.http.put<Project>(`${this.baseUrl}/projects/${id}`, project);
  }

  deleteProject(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/projects/${id}`);
  }

  // Ticket endpoints
  getTickets(): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.baseUrl}/tickets`);
  }

  getTicket(id: number): Observable<Ticket> {
    return this.http.get<Ticket>(`${this.baseUrl}/tickets/${id}`);
  }

  getTicketByNumber(ticketNumber: string): Observable<Ticket> {
    return this.http.get<Ticket>(`${this.baseUrl}/tickets/number/${ticketNumber}`);
  }

  getTicketsByProject(projectId: number): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.baseUrl}/tickets/project/${projectId}`);
  }

  getTicketsByAssignee(assigneeId: number): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.baseUrl}/tickets/assignee/${assigneeId}`);
  }

  createTicket(ticket: any): Observable<Ticket> {
    return this.http.post<Ticket>(`${this.baseUrl}/tickets`, ticket);
  }

  updateTicket(id: number, ticket: any): Observable<Ticket> {
    return this.http.put<Ticket>(`${this.baseUrl}/tickets/${id}`, ticket);
  }

  deleteTicket(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/tickets/${id}`);
  }

  // Tag endpoints
  getTags(): Observable<Tag[]> {
    return this.http.get<Tag[]>(`${this.baseUrl}/tags`);
  }

  getTag(id: number): Observable<Tag> {
    return this.http.get<Tag>(`${this.baseUrl}/tags/${id}`);
  }

  createTag(tag: Tag): Observable<Tag> {
    return this.http.post<Tag>(`${this.baseUrl}/tags`, tag);
  }

  updateTag(id: number, tag: Tag): Observable<Tag> {
    return this.http.put<Tag>(`${this.baseUrl}/tags/${id}`, tag);
  }

  deleteTag(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/tags/${id}`);
  }

  // Comment endpoints
  getCommentsByTicket(ticketId: number): Observable<Comment[]> {
    return this.http.get<Comment[]>(`${this.baseUrl}/comments/ticket/${ticketId}`);
  }

  createComment(comment: any): Observable<Comment> {
    return this.http.post<Comment>(`${this.baseUrl}/comments`, comment);
  }

  updateComment(id: number, content: string): Observable<Comment> {
    return this.http.put<Comment>(`${this.baseUrl}/comments/${id}`, { content });
  }

  deleteComment(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/comments/${id}`);
  }
}
