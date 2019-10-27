import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { MenubarModule } from 'primeng/menubar';
import { InputTextModule } from 'primeng/inputtext';
import { PanelModule } from 'primeng/panel';
import { MessageModule } from 'primeng/message';
import { ToastModule } from 'primeng/toast';
import { ButtonModule } from 'primeng/button';
import {TableModule} from 'primeng/table';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {DialogModule} from 'primeng/dialog';
import { ConfirmarPessoaComponent } from './component/confirmar-pessoa/confirmar-pessoa.component';

@NgModule({
  declarations: [ConfirmarPessoaComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    MenubarModule,
    InputTextModule,
    PanelModule,
    MessageModule,
    ToastModule,
    ButtonModule,
    TableModule,
    DialogModule
  ],
  exports: [
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    MenubarModule,
    InputTextModule,
    PanelModule,
    MessageModule,
    ToastModule,
    ButtonModule,
    TableModule,
    ConfirmDialogModule,
    DialogModule,
    ConfirmarPessoaComponent
  ]
})
export class SharedModule { }
