import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';
import { VotarComponent } from './votar/votar.component';
import { VotacoesRoutingModule } from './votacoes-routing.module';
import { ClassificacaoComponent } from './classificacao/classificacao.component';
import { ClassificacaoGeralComponent } from './classificacao-geral/classificacao-geral.component';



@NgModule({
  declarations: [VotarComponent, ClassificacaoComponent, ClassificacaoGeralComponent],
  imports: [
    SharedModule,
    VotacoesRoutingModule
  ]
})
export class VotacoesModule { }
