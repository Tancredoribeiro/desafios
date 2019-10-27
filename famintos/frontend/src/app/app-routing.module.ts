import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ListarRestauranteComponent } from './restaurantes/listar-restaurante/listar-restaurante.component';
import { CriarRestauranteComponent } from './restaurantes/criar-restaurante/criar-restaurante.component';
import { EditarRestauranteComponent } from './restaurantes/editar-restaurante/editar-restaurante.component';
import { VotarComponent } from './votacoes/votar/votar.component';

const routes: Routes = [
  { path: 'restaurantes', component: ListarRestauranteComponent },
  { path: 'restaurantes/criar', component: CriarRestauranteComponent },
  { path: 'restaurantes/editar/:id', component: EditarRestauranteComponent },
  { path: 'votacoes/votar', component: VotarComponent }
];


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
