import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassificacaoGeralComponent } from './classificacao-geral.component';

describe('ClassificacaoGeralComponent', () => {
  let component: ClassificacaoGeralComponent;
  let fixture: ComponentFixture<ClassificacaoGeralComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClassificacaoGeralComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClassificacaoGeralComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
