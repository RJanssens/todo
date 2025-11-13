import { Routes } from '@angular/router';
import { ProjectListComponent } from './components/project-list/project-list.component';
import { ProjectDetailComponent } from './components/project-detail/project-detail.component';
import { TicketDetailComponent } from './components/ticket-detail/ticket-detail.component';

export const routes: Routes = [
  { path: '', component: ProjectListComponent },
  { path: 'projects/:id', component: ProjectDetailComponent },
  { path: 'tickets/:id', component: TicketDetailComponent },
  { path: '**', redirectTo: '' }
];
