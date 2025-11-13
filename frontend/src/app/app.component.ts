import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  template: `
    <div class="app-container">
      <header class="app-header">
        <div class="header-content">
          <h1 class="app-title">
            <a routerLink="/">Project Manager</a>
          </h1>
          <p class="app-subtitle">Manage your projects and tickets efficiently</p>
        </div>
      </header>
      <main class="app-main">
        <router-outlet></router-outlet>
      </main>
    </div>
  `,
  styles: [`
    .app-container {
      min-height: 100vh;
      background: #f9fafb;
    }

    .app-header {
      background: white;
      border-bottom: 1px solid #e5e7eb;
      padding: 1.5rem 2rem;
      box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
    }

    .header-content {
      max-width: 1400px;
      margin: 0 auto;
    }

    .app-title {
      margin: 0;
      font-size: 1.5rem;
      font-weight: 700;
      color: #1a1a1a;
    }

    .app-title a {
      color: inherit;
      text-decoration: none;
      transition: color 0.2s;
    }

    .app-title a:hover {
      color: #3b82f6;
    }

    .app-subtitle {
      margin: 0.25rem 0 0 0;
      font-size: 0.875rem;
      color: #6b7280;
    }

    .app-main {
      min-height: calc(100vh - 100px);
    }
  `]
})
export class AppComponent {
  title = 'Project Manager';
}
