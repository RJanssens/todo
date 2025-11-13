import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { Ticket } from '../../models/ticket.model';
import { Comment } from '../../models/comment.model';

@Component({
  selector: 'app-ticket-detail',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './ticket-detail.component.html',
  styleUrls: ['./ticket-detail.component.css']
})
export class TicketDetailComponent implements OnInit {
  ticket: Ticket | null = null;
  comments: Comment[] = [];
  loading = true;
  error: string | null = null;

  constructor(
    private route: ActivatedRoute,
    private apiService: ApiService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const ticketId = +params['id'];
      this.loadTicket(ticketId);
      this.loadComments(ticketId);
    });
  }

  loadTicket(id: number): void {
    this.loading = true;
    this.apiService.getTicket(id).subscribe({
      next: (ticket) => {
        this.ticket = ticket;
        this.loading = false;
      },
      error: (error) => {
        console.error('Error loading ticket:', error);
        this.error = 'Failed to load ticket';
        this.loading = false;
      }
    });
  }

  loadComments(ticketId: number): void {
    this.apiService.getCommentsByTicket(ticketId).subscribe({
      next: (comments) => {
        this.comments = comments;
      },
      error: (error) => {
        console.error('Error loading comments:', error);
      }
    });
  }

  getStatusDisplayName(status: string): string {
    const statusMap: { [key: string]: string } = {
      'TODO': 'To Do',
      'IN_PROGRESS': 'In Progress',
      'READY_FOR_REVIEW': 'Ready for Review',
      'DONE': 'Done'
    };
    return statusMap[status] || status;
  }

  getPriorityClass(priority: string): string {
    return `priority-${priority.toLowerCase()}`;
  }

  formatDate(dateString: string): string {
    const date = new Date(dateString);
    return date.toLocaleDateString('en-US', { year: 'numeric', month: 'long', day: 'numeric' });
  }
}
