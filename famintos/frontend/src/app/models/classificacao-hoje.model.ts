import { Restaurante } from './restaurante.model';

export interface ClassificacaoHoje {
  restaurante: Restaurante;
  votos: number;
  vencedor: boolean;
}
