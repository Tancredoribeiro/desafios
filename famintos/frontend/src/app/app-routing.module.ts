import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ClassificacaoComponent } from './votacoes/classificacao/classificacao.component';
import { ClassificacaoGeralComponent } from './votacoes/classificacao-geral/classificacao-geral.component';
import { CriarPessoaComponent } from './pessoas/criar-pessoa/criar-pessoa.component';

const routes: Routes = [

  { path: 'restaurantes',  loadChildren: './restaurantes/restaurantes.module#RestaurantesModule'
    },
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
