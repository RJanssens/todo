import { User } from './user.model';
import { Project } from './project.model';
import { Tag } from './tag.model';

export enum TicketStatus {
  TODO = 'TODO',
  IN_PROGRESS = 'IN_PROGRESS',
  READY_FOR_REVIEW = 'READY_FOR_REVIEW',
  DONE = 'DONE'
}

export enum TicketPriority {
  LOWEST = 'LOWEST',
  LOW = 'LOW',
  MEDIUM = 'MEDIUM',
  HIGH = 'HIGH',
  HIGHEST = 'HIGHEST'
}

export interface Ticket {
  id?: number;
  title: string;
  description?: string;
  ticketNumber?: string;
  status: TicketStatus;
  priority: TicketPriority;
  project: Project;
  assignee?: User;
  reporter: User;
  tags?: Tag[];
  dueDate?: string;
  createdAt?: string;
  updatedAt?: string;
}
