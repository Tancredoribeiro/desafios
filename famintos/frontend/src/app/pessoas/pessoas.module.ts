import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';
import { CriarPessoaComponent } from './criar-pessoa/criar-pessoa.component';
import { PessoaFormComponent } from './pessoa-form/pessoa-form.component';



@NgModule({
  declarations: [CriarPessoaComponent, PessoaFormComponent],
  imports: [
    SharedModule
  ]
})
export class PessoasModule { }
