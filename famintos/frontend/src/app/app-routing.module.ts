import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ListarRestauranteComponent } from './restaurantes/listar-restaurante/listar-restaurante.component';
import { CriarRestauranteComponent } from './restaurantes/criar-restaurante/criar-restaurante.component';
import { EditarRestauranteComponent } from './restaurantes/editar-restaurante/editar-restaurante.component';
import { VotarComponent } from './votacoes/votar/votar.component';
import { ClassificacaoComponent } from './votacoes/classificacao/classificacao.component';
import { ClassificacaoGeralComponent } from './votacoes/classificacao-geral/classificacao-geral.component';
import { CriarPessoaComponent } from './pessoas/criar-pessoa/criar-pessoa.component';

const routes: Routes = [

  { path: 'restaurantes',  loadChildren: './restaurantes/restaurantes.module#RestaurantesModule'
    },
  { path: 'votacoes/votar', component: VotarComponent },
  { path: 'votacoes/classificacao/hoje', component: ClassificacaoComponent },
  { path: 'votacoes/classificacao/geral', component: ClassificacaoGeralComponent },
  { path: 'pessoas/criar', component: CriarPessoaComponent }
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
