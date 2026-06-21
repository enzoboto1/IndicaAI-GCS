import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Aluno } from '../../models/aluno.model';
import { Vaga } from '../../models/vaga.model';
import { Recomendacao } from '../../models/recomendacao.model';
import { AlunoService } from '../../services/aluno.service';
import { VagaService } from '../../services/vaga.service';
import { RecomendacaoService } from '../../services/recomendacao.service';

@Component({
  selector: 'app-recomendacoes',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="page">
      <h2>🔍 Recomendações</h2>

      <div class="tabs">
        <button [class.active]="modo === 'aluno'" (click)="modo = 'aluno'; resultados = []">Vagas para um Aluno</button>
        <button [class.active]="modo === 'vaga'" (click)="modo = 'vaga'; resultados = []">Alunos para uma Vaga</button>
      </div>

      <div class="form-card">
        <div *ngIf="modo === 'aluno'">
          <label>Selecione o aluno:</label>
          <select [(ngModel)]="alunoId" name="alunoId">
            <option value="">-- Selecione --</option>
            <option *ngFor="let a of alunos" [value]="a.id">{{ a.nome }} ({{ a.curso }})</option>
          </select>
          <button class="btn btn-accent" (click)="buscarVagas()" [disabled]="!alunoId">Buscar Vagas</button>
        </div>
        <div *ngIf="modo === 'vaga'">
          <label>Selecione a vaga:</label>
          <select [(ngModel)]="vagaId" name="vagaId">
            <option value="">-- Selecione --</option>
            <option *ngFor="let v of vagas" [value]="v.id">{{ v.titulo }} - {{ v.empresa }}</option>
          </select>
          <button class="btn btn-accent" (click)="buscarAlunos()" [disabled]="!vagaId">Buscar Alunos</button>
        </div>
      </div>

      <div class="list" *ngIf="resultados.length > 0">
        <div class="card result-card" *ngFor="let r of resultados">
          <div class="compat-bar">
            <div class="compat-fill" [style.width.%]="r.compatibilidade"></div>
          </div>
          <div class="card-header">
            <strong>{{ modo === 'aluno' ? r.vagaTitulo : r.alunoNome }}</strong>
            <span class="badge compat-badge">{{ r.compatibilidade | number:'1.0-0' }}%</span>
          </div>
          <p *ngIf="modo === 'aluno'">🏢 {{ r.empresa }}</p>
          <p *ngIf="modo === 'vaga'">👤 {{ r.alunoNome }}</p>
        </div>
      </div>

      <p *ngIf="buscou && resultados.length === 0" class="empty">Nenhuma recomendação encontrada. Verifique se há habilidades em comum.</p>
    </div>
  `,
  styles: [`
    .tabs { display: flex; gap: 8px; margin-bottom: 20px; }
    .tabs button { padding: 8px 20px; border: 2px solid #3b82f6; background: white; border-radius: 20px; cursor: pointer; }
    .tabs button.active { background: #3b82f6; color: white; }
    .compat-bar { height: 6px; background: #e5e7eb; border-radius: 3px; margin-bottom: 10px; overflow: hidden; }
    .compat-fill { height: 100%; background: linear-gradient(90deg, #3b82f6, #10b981); border-radius: 3px; transition: width 0.5s; }
    .compat-badge { background: #10b981 !important; }
    select { width: 100%; padding: 8px; border: 1px solid #d1d5db; border-radius: 6px; margin: 8px 0 12px; }
    label { font-weight: 600; }
  `]
})
export class RecomendacoesComponent implements OnInit {
  alunos: Aluno[] = [];
  vagas: Vaga[] = [];
  resultados: Recomendacao[] = [];
  modo: 'aluno' | 'vaga' = 'aluno';
  alunoId: number | '' = '';
  vagaId: number | '' = '';
  buscou = false;

  constructor(
    private alunoService: AlunoService,
    private vagaService: VagaService,
    private recService: RecomendacaoService
  ) {}

  ngOnInit() {
    this.alunoService.listar().subscribe(d => this.alunos = d);
    this.vagaService.listar().subscribe(d => this.vagas = d);
  }

  buscarVagas() {
    this.buscou = true;
    this.recService.vagasParaAluno(Number(this.alunoId)).subscribe(d => this.resultados = d);
  }

  buscarAlunos() {
    this.buscou = true;
    this.recService.alunosParaVaga(Number(this.vagaId)).subscribe(d => this.resultados = d);
  }
}
