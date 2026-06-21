import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Recomendacao } from '../models/recomendacao.model';

@Injectable({ providedIn: 'root' })
export class RecomendacaoService {
  private url = 'http://localhost:8080/api/recomendacoes';

  constructor(private http: HttpClient) {}

  vagasParaAluno(alunoId: number): Observable<Recomendacao[]> {
    return this.http.get<Recomendacao[]>(`${this.url}/aluno/${alunoId}`);
  }

  alunosParaVaga(vagaId: number): Observable<Recomendacao[]> {
    return this.http.get<Recomendacao[]>(`${this.url}/vaga/${vagaId}`);
  }
}
