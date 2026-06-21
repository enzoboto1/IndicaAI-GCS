import { Component } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  template: `
    <nav class="navbar">
      <a routerLink="/" class="brand">🎓 IndicaAI</a>
      <div class="nav-links">
        <a routerLink="/alunos" routerLinkActive="active">Alunos</a>
        <a routerLink="/vagas" routerLinkActive="active">Vagas</a>
        <a routerLink="/recomendacoes" routerLinkActive="active">Recomendações</a>
      </div>
    </nav>
    <main>
      <router-outlet />
    </main>
  `,
  styleUrl: './app.scss'
})
export class App {}
