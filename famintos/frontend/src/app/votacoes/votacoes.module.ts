import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';
import { VotarComponent } from './votar/votar.component';
import { VotacoesRoutingModule } from './votacoes-routing.module';



@NgModule({
  declarations: [VotarComponent],
  imports: [
    SharedModule,
    VotacoesRoutingModule
  ]
})
export class VotacoesModule { }
