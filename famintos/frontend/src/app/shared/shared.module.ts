import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { MenubarModule } from 'primeng/menubar';
import { InputTextModule } from 'primeng/inputtext';
import {PasswordModule} from 'primeng/password';
import { PanelModule } from 'primeng/panel';
import { MessageModule } from 'primeng/message';
import { ToastModule } from 'primeng/toast';
import { ButtonModule } from 'primeng/button';
import {TableModule} from 'primeng/table';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {DialogModule} from 'primeng/dialog';
import { ConfirmarPessoaComponent } from './component/confirmar-pessoa/confirmar-pessoa.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { FullCalendarModule } from '@fullcalendar/angular';

@NgModule({
  declarations: [ConfirmarPessoaComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    MenubarModule,
    InputTextModule,
    PasswordModule,
    PanelModule,
    MessageModule,
    ToastModule,
    ButtonModule,
    TableModule,
    DialogModule,
    FontAwesomeModule,
    FullCalendarModule
  ],
  exports: [
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    MenubarModule,
    InputTextModule,
    PasswordModule,
    PanelModule,
    MessageModule,
    ToastModule,
    ButtonModule,
    TableModule,
    ConfirmDialogModule,
    DialogModule,
    ConfirmarPessoaComponent,
    FontAwesomeModule,
    FullCalendarModule
  ]
})
export class SharedModule { }
