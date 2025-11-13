import { User } from './user.model';
import { Ticket } from './ticket.model';

export interface Comment {
  id?: number;
  content: string;
  ticket: Ticket;
  author: User;
  createdAt?: string;
  updatedAt?: string;
}
