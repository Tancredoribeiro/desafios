import { NgModule } from '@angular/core';
import { CriarRestauranteComponent } from './criar-restaurante/criar-restaurante.component';
import { EditarRestauranteComponent } from './editar-restaurante/editar-restaurante.component';
import { ListarRestauranteComponent } from './listar-restaurante/listar-restaurante.component';
import { SharedModule } from '../shared/shared.module';
import { RestauranteFormComponent } from './restaurante-form/restaurante-form.component';
import { RestauranteCrudBaseComponent } from './restaurante-crud-base/restaurante-crud-base.component';
import { RestauranteService } from '../services/restaurante.service';
import { VotoService } from '../services/voto.service';
import { RestaurantesRoutingModuleModule } from './restaurantes-routing-module.module';



@NgModule({
  declarations: [
    CriarRestauranteComponent,
    EditarRestauranteComponent,
    ListarRestauranteComponent,
    RestauranteFormComponent,
    RestauranteCrudBaseComponent],
  imports: [
    SharedModule,
    RestaurantesRoutingModuleModule
  ],
  providers: []
})
export class RestaurantesModule { }
