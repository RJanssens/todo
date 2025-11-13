import { User } from './user.model';

export interface Project {
  id?: number;
  name: string;
  projectKey: string;
  description?: string;
  owner: User;
  createdAt?: string;
  updatedAt?: string;
}
