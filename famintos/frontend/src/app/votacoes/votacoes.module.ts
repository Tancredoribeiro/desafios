import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';
import { VotacoesRoutingModule } from './votacoes-routing.module';
import { ClassificacaoComponent } from './classificacao/classificacao.component';
import { ClassificacaoGeralComponent } from './classificacao-geral/classificacao-geral.component';



@NgModule({
  declarations: [ClassificacaoComponent, ClassificacaoGeralComponent],
  imports: [
    SharedModule,
    VotacoesRoutingModule
  ]
})
export class VotacoesModule { }
