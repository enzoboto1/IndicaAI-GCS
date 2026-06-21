import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { AlunosComponent } from './pages/alunos/alunos.component';
import { VagasComponent } from './pages/vagas/vagas.component';
import { RecomendacoesComponent } from './pages/recomendacoes/recomendacoes.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'alunos', component: AlunosComponent },
  { path: 'vagas', component: VagasComponent },
  { path: 'recomendacoes', component: RecomendacoesComponent },
  { path: '**', redirectTo: '' }
];
