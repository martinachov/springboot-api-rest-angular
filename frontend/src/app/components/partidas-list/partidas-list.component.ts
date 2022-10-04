import { Component, OnInit } from '@angular/core';
import { MovimientoModel } from 'src/app/model/movimiento-model.model';
import { PartidaModel } from 'src/app/model/partida-model.model';
import { MovimientoService } from 'src/app/services/movimiento/movimiento.service';
import { PartidaService } from 'src/app/services/partida/partida.service';

@Component({
  selector: 'app-partidas-list',
  templateUrl: './partidas-list.component.html',
  styleUrls: ['./partidas-list.component.css']
})
export class PartidasListComponent implements OnInit {

  constructor(private partidaService: PartidaService, private movimientoService: MovimientoService) { }

  partidas?: PartidaModel[];
  currentPartida: PartidaModel = {};
  cantidadPartidas?: number = this.partidas?.length;
  movimientos?: MovimientoModel[];

  ngOnInit(): void {
    this.getPartidas();
  }

  getPartidas(): void {
    this.partidaService.getPartidas()
      .subscribe({
        next: (resp) => {
          this.partidas = resp;
          this.cantidadPartidas = this.partidas.length;
          console.log(resp);
        },
        error: (exception) => console.log(exception)
      });
  }

  getPartidaById(id: any): void {
    this.partidaService.getPartidaById(id)
      .subscribe({
        next: (resp) => {
          this.currentPartida = resp;
          console.log(resp);
        },
        error: (exception) => console.log(exception)
      });
  }

  buscarMovimientosDePartida(partida: PartidaModel) {
    this.movimientoService.getMovimientosByPartidaId(partida.id)
      .subscribe(
        (resp) => {
          this.movimientos = resp;
          console.log(resp);
        }
      );
    this.toggleMovimientosDePartida(partida);
  }

  toggleMovimientosDePartida(partida: PartidaModel) {
    if (this.partidas != undefined) {
      if (this.currentPartida != partida) {
        this.currentPartida = partida;
      } else {
        this.currentPartida = {};
      }
    }
  }

  isPartidaSeleccionada(partida: PartidaModel): boolean {
    let hayPartidaSeleccionada: boolean = this.currentPartida != undefined;
    let isPartidaActualSeleccionada: boolean = this.currentPartida == partida;
    return hayPartidaSeleccionada && isPartidaActualSeleccionada;
  }
}
