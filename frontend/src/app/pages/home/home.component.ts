import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterLink],
  template: `
    <div class="hero">
      <h1>🎓 IndicaAI</h1>
      <p class="slogan">Conectando talentos da UFC ao mercado de tecnologia</p>
      <div class="actions">
        <a routerLink="/alunos" class="btn btn-primary">👤 Alunos</a>
        <a routerLink="/vagas" class="btn btn-secondary">💼 Vagas</a>
        <a routerLink="/recomendacoes" class="btn btn-accent">🔍 Recomendações</a>
      </div>
    </div>
  `,
  styles: [`
    .hero {
      text-align: center;
      padding: 80px 20px;
    }
    h1 { font-size: 3rem; margin-bottom: 10px; }
    .slogan { font-size: 1.2rem; color: #666; margin-bottom: 40px; }
    .actions { display: flex; gap: 16px; justify-content: center; flex-wrap: wrap; }
  `]
})
export class HomeComponent {}
