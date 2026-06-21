import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Vaga } from '../../models/vaga.model';
import { VagaService } from '../../services/vaga.service';

@Component({
  selector: 'app-vagas',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="page">
      <h2>💼 Vagas</h2>

      <form (ngSubmit)="salvar()" class="form-card">
        <h3>{{ editando ? 'Editar Vaga' : 'Cadastrar Vaga' }}</h3>
        <div class="form-grid">
          <input [(ngModel)]="form.titulo" name="titulo" placeholder="Título da vaga *" required />
          <input [(ngModel)]="form.empresa" name="empresa" placeholder="Empresa *" required />
          <textarea [(ngModel)]="form.descricao" name="descricao" placeholder="Descrição *" class="span2" rows="3" required></textarea>
          <input [(ngModel)]="form.requisitos" name="requisitos" placeholder="Requisitos (ex: Java, Spring Boot, SQL)" class="span2" required />
          <select [(ngModel)]="form.modalidade" name="modalidade">
            <option value="">Modalidade</option>
            <option>Remoto</option>
            <option>Presencial</option>
            <option>Híbrido</option>
          </select>
          <select [(ngModel)]="form.nivel" name="nivel">
            <option value="">Nível</option>
            <option>Estágio</option>
            <option>Trainee</option>
            <option>Júnior</option>
          </select>
        </div>
        <div class="form-actions">
          <button type="submit" class="btn btn-primary">{{ editando ? 'Atualizar' : 'Salvar' }}</button>
          <button type="button" class="btn btn-outline" (click)="cancelar()" *ngIf="editando">Cancelar</button>
        </div>
      </form>

      <div class="list">
        <div class="card" *ngFor="let vaga of vagas">
          <div class="card-header">
            <strong>{{ vaga.titulo }}</strong>
            <span class="badge">{{ vaga.empresa }}</span>
          </div>
          <p>{{ vaga.descricao }}</p>
          <p class="skills">🛠 {{ vaga.requisitos }}</p>
          <div class="tags">
            <span class="tag" *ngIf="vaga.modalidade">{{ vaga.modalidade }}</span>
            <span class="tag" *ngIf="vaga.nivel">{{ vaga.nivel }}</span>
          </div>
          <div class="card-actions">
            <button class="btn btn-sm btn-outline" (click)="editar(vaga)">✏️ Editar</button>
            <button class="btn btn-sm btn-danger" (click)="deletar(vaga.id!)">🗑 Excluir</button>
          </div>
        </div>
        <p *ngIf="vagas.length === 0" class="empty">Nenhuma vaga cadastrada.</p>
      </div>
    </div>
  `
})
export class VagasComponent implements OnInit {
  vagas: Vaga[] = [];
  form: Vaga = this.novo();
  editando = false;

  constructor(private service: VagaService) {}

  ngOnInit() { this.carregar(); }

  carregar() {
    this.service.listar().subscribe(data => this.vagas = data);
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

  editar(vaga: Vaga) {
    this.form = { ...vaga };
    this.editando = true;
  }

  cancelar() {
    this.form = this.novo();
    this.editando = false;
  }

  deletar(id: number) {
    if (confirm('Excluir vaga?')) {
      this.service.deletar(id).subscribe(() => this.carregar());
    }
  }

  novo(): Vaga {
    return { titulo: '', empresa: '', descricao: '', requisitos: '', modalidade: 'Remoto', nivel: 'Estágio' };
  }
}
