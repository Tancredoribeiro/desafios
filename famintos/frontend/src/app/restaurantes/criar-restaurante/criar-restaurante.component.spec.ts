import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CriarRestauranteComponent } from './criar-restaurante.component';

describe('CriarRestauranteComponent', () => {
  let component: CriarRestauranteComponent;
  let fixture: ComponentFixture<CriarRestauranteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CriarRestauranteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CriarRestauranteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
