import { MovimientoModel } from "./movimiento-model.model";

export class PartidaModel {
	id?: number;
    objetoGasto?: string;
	saf?: string;
	ejercicio?: string;
	montoInicial?: number;
	movimientos?: Array<MovimientoModel>;
}
