import { Component, OnInit } from '@angular/core';

import { Observable, empty } from 'rxjs';
import { catchError } from 'rxjs/operators';
import dayGridPlugin from '@fullcalendar/daygrid';
import allLocales from '@fullcalendar/core/locales-all';



import { VotoService } from 'src/app/services/voto.service';
import { ClassificacaoGeral } from 'src/app/models/classificacao-geral.model';

@Component({
  selector: 'app-classificacao-geral',
  templateUrl: './classificacao-geral.component.html',
  styleUrls: ['./classificacao-geral.component.css'],
  providers: [VotoService]
})
export class ClassificacaoGeralComponent implements OnInit {

  calendarPlugins = [dayGridPlugin];
  classificacoes$: Observable<ClassificacaoGeral[]>;
  allLocales = allLocales;


  constructor(private votoService: VotoService) { }

  ngOnInit() {
    this.classificacoes$ = this.votoService.buscarClassificacaoGeral().pipe(
      catchError(error => {
        console.error(error);
        return empty();
      })
    );
  }

  eventRender(info) {
    console.log(info);
    const vencedorIcon = info.event.extendedProps.vencedor ? '<i class="fa fa-trophy" style="color: green; font-size: 18px;"></i>' : '';
    const customHTML = `<div class="fc-content"> <span class="fc-title">${info.event.title} - Votos:
                         ${info.event.extendedProps.votos} ${vencedorIcon}</span></div>`;
    info.el.innerHTML = customHTML;
  }

}
