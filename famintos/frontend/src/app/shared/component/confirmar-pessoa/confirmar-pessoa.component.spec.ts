import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmarPessoaComponent } from './confirmar-pessoa.component';

describe('ConfirmarPessoaComponent', () => {
  let component: ConfirmarPessoaComponent;
  let fixture: ComponentFixture<ConfirmarPessoaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfirmarPessoaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmarPessoaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
