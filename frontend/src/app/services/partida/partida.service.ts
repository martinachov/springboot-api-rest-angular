import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PartidaModel } from 'src/app/model/partida-model.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PartidaService {

  baseUrl = 'api/partidas';

  constructor(private http: HttpClient) { }

  getPartidas(): Observable<PartidaModel[]> {
    return this.http.get<PartidaModel[]>(this.baseUrl);
  }

  getPartidaById(id: any): Observable<PartidaModel>{
    return this.http.get(this.baseUrl + '/' + id);
  }
}
