import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Vaga } from '../models/vaga.model';

@Injectable({ providedIn: 'root' })
export class VagaService {
  private url = 'http://localhost:8080/api/vagas';

  constructor(private http: HttpClient) {}

  listar(): Observable<Vaga[]> {
    return this.http.get<Vaga[]>(this.url);
  }

  buscar(id: number): Observable<Vaga> {
    return this.http.get<Vaga>(`${this.url}/${id}`);
  }

  salvar(vaga: Vaga): Observable<Vaga> {
    return this.http.post<Vaga>(this.url, vaga);
  }

  atualizar(id: number, vaga: Vaga): Observable<Vaga> {
    return this.http.put<Vaga>(`${this.url}/${id}`, vaga);
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}
