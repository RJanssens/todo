import { User } from './user.model';

export interface Project {
  id?: number;
  name: string;
  key: string;
  description?: string;
  owner: User;
  createdAt?: string;
  updatedAt?: string;
}
