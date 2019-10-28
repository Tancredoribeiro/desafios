import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { take } from 'rxjs/operators';
import { Pessoa } from '../models/pessoa.model';

@Injectable({
  providedIn: 'root'
})
export class PessoaService {

  private URI = `${environment.API}/pessoas`;

  constructor(private http: HttpClient) { }

  validar(dados: any) {
    return this.http.post(`${this.URI}/validar`, dados).pipe(take(1));
  }

  criar(pessoa: Pessoa) {
    return this.http.post(this.URI, pessoa).pipe(take(1));
  }
}
