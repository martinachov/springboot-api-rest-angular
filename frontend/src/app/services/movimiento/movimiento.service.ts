import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MovimientoModel } from 'src/app/model/movimiento-model.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MovimientoService {

  baseUrl = 'api/partidas';

  constructor(private http: HttpClient) { }

  getMovimientosByPartidaId(id: any): Observable<MovimientoModel[]>{
    return this.http.get<MovimientoModel[]>(this.baseUrl + '/' + id + '/movimientos');
  }
}
