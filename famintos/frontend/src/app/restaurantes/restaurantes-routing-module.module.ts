import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListarRestauranteComponent } from './listar-restaurante/listar-restaurante.component';
import { CriarRestauranteComponent } from './criar-restaurante/criar-restaurante.component';
import { EditarRestauranteComponent } from './editar-restaurante/editar-restaurante.component';
import { Routes, RouterModule } from '@angular/router';

const restaurantesRoutes: Routes = [
  { path: '', component: ListarRestauranteComponent },
  { path: 'criar', component: CriarRestauranteComponent },
  { path: 'editar/:id', component: EditarRestauranteComponent },
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(restaurantesRoutes)
  ],
  exports: [RouterModule]
})
export class RestaurantesRoutingModuleModule { }
