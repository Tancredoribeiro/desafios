import { Component, OnInit } from '@angular/core';

import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  titulo = 'frontend';
  items: MenuItem[];

  ngOnInit(): void {
    this.items = [
      {
        label: 'Restaurantes', routerLink: ['/restaurantes'],
      },
      {
        label: 'Votações',
        items: [
          { label: 'Ranking de hoje', routerLink: ['/votacoes/classificacao/hoje'] },
          { label: 'Historico', routerLink: ['/votacoes/classificacao/geral'] },
        ]
      }
    ];
  }
}
