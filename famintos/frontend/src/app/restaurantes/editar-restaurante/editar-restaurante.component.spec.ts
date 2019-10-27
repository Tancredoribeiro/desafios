import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarRestauranteComponent } from './editar-restaurante.component';

describe('EditarRestauranteComponent', () => {
  let component: EditarRestauranteComponent;
  let fixture: ComponentFixture<EditarRestauranteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditarRestauranteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditarRestauranteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
