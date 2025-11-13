import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { Ticket, TicketStatus } from '../../models/ticket.model';

@Component({
  selector: 'app-ticket-board',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './ticket-board.component.html',
  styleUrls: ['./ticket-board.component.css']
})
export class TicketBoardComponent implements OnInit {
  @Input() projectId!: number;
  tickets: Ticket[] = [];
  loading = true;

  todoTickets: Ticket[] = [];
  inProgressTickets: Ticket[] = [];
  reviewTickets: Ticket[] = [];
  doneTickets: Ticket[] = [];

  TicketStatus = TicketStatus;

  constructor(private apiService: ApiService) {}

  ngOnInit(): void {
    this.loadTickets();
  }

  loadTickets(): void {
    this.loading = true;
    this.apiService.getTicketsByProject(this.projectId).subscribe({
      next: (tickets) => {
        this.tickets = tickets;
        this.organizeTickets();
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading tickets:', error);
        this.loading = false;
      }
    });
  }

  organizeTickets(): void {
    this.todoTickets = this.tickets.filter(t => t.status === TicketStatus.TODO);
    this.inProgressTickets = this.tickets.filter(t => t.status === TicketStatus.IN_PROGRESS);
    this.reviewTickets = this.tickets.filter(t => t.status === TicketStatus.READY_FOR_REVIEW);
    this.doneTickets = this.tickets.filter(t => t.status === TicketStatus.DONE);
  }

  getPriorityClass(priority: string): string {
    return `priority-${priority.toLowerCase()}`;
  }
}
