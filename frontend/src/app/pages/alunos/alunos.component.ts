import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Aluno } from '../../models/aluno.model';
import { AlunoService } from '../../services/aluno.service';

@Component({
  selector: 'app-alunos',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="page">
      <h2>👤 Alunos</h2>

      <form (ngSubmit)="salvar()" class="form-card">
        <h3>{{ editando ? 'Editar Aluno' : 'Cadastrar Aluno' }}</h3>
        <div class="form-grid">
          <input [(ngModel)]="form.nome" name="nome" placeholder="Nome *" required />
          <input [(ngModel)]="form.email" name="email" placeholder="E-mail *" required />
          <input [(ngModel)]="form.curso" name="curso" placeholder="Curso *" required />
          <input [(ngModel)]="form.semestre" name="semestre" type="number" placeholder="Semestre *" required />
          <input [(ngModel)]="form.habilidades" name="habilidades" placeholder="Habilidades (ex: Java, Angular, SQL)" class="span2" />
          <input [(ngModel)]="form.github" name="github" placeholder="GitHub" />
          <input [(ngModel)]="form.linkedin" name="linkedin" placeholder="LinkedIn" />
          <input [(ngModel)]="form.portfolio" name="portfolio" placeholder="Portfólio" class="span2" />
        </div>
        <div class="form-actions">
          <button type="submit" class="btn btn-primary">{{ editando ? 'Atualizar' : 'Salvar' }}</button>
          <button type="button" class="btn btn-outline" (click)="cancelar()" *ngIf="editando">Cancelar</button>
        </div>
      </form>

      <div class="list">
        <div class="card" *ngFor="let aluno of alunos">
          <div class="card-header">
            <strong>{{ aluno.nome }}</strong>
            <span class="badge">{{ aluno.curso }} - {{ aluno.semestre }}º sem</span>
          </div>
          <p class="skills">🛠 {{ aluno.habilidades }}</p>
          <div class="card-links">
            <a *ngIf="aluno.github" [href]="aluno.github" target="_blank">GitHub</a>
            <a *ngIf="aluno.linkedin" [href]="aluno.linkedin" target="_blank">LinkedIn</a>
          </div>
          <div class="card-actions">
            <button class="btn btn-sm btn-outline" (click)="editar(aluno)">✏️ Editar</button>
            <button class="btn btn-sm btn-danger" (click)="deletar(aluno.id!)">🗑 Excluir</button>
          </div>
        </div>
        <p *ngIf="alunos.length === 0" class="empty">Nenhum aluno cadastrado.</p>
      </div>
    </div>
  `
})
export class AlunosComponent implements OnInit {
  alunos: Aluno[] = [];
  form: Aluno = this.novo();
  editando = false;

  constructor(private service: AlunoService) {}

  ngOnInit() { this.carregar(); }

  carregar() {
    this.service.listar().subscribe(data => this.alunos = data);
  }

  salvar() {
    if (this.editando && this.form.id) {
      this.service.atualizar(this.form.id, this.form).subscribe(() => {
        this.carregar(); this.cancelar();
      });
    } else {
      this.service.salvar(this.form).subscribe(() => {
        this.carregar(); this.form = this.novo();
      });
    }
  }

  editar(aluno: Aluno) {
    this.form = { ...aluno };
    this.editando = true;
  }

  cancelar() {
    this.form = this.novo();
    this.editando = false;
  }

  deletar(id: number) {
    if (confirm('Excluir aluno?')) {
      this.service.deletar(id).subscribe(() => this.carregar());
    }
  }

  novo(): Aluno {
    return { nome: '', email: '', curso: '', semestre: 1, habilidades: '' };
  }
}
