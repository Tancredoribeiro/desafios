import { Pessoa } from './pessoa.model';
import { Restaurante } from './restaurante.model';

export class Voto {
  pessoa: Pessoa;
  restaurante: Restaurante;
  data: Date;
}
