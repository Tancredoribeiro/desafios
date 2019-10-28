import { Component, OnInit, Output, Input, EventEmitter } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-pessoa-form',
  templateUrl: './pessoa-form.component.html',
  styleUrls: ['./pessoa-form.component.css']
})
export class PessoaFormComponent implements OnInit {

  @Input() formulario: FormGroup;
  @Output() submitted = new EventEmitter<FormGroup>();
  @Output() cancelado = new EventEmitter();

  constructor() {}

  ngOnInit() {}

  submitForm() {
    console.log('Form Restaurante submetido:', this.formulario);
    this.submitted.emit();
  }

  cancelar() {
    this.cancelado.emit();
  }
  verificarValidTouchedDirty(campo: string) {
    return (
      !this.formulario.get(campo).valid &&
      (this.formulario.get(campo).touched || this.formulario.get(campo).dirty)
    );
  }

  verificarRequired(campo: string) {
    return (
      this.formulario.get(campo).hasError('required') &&
      (this.formulario.get(campo).touched || this.formulario.get(campo).dirty)
    );
  }

  verificarMinLength(campo: string) {
    return (
      this.formulario.get(campo).hasError('minlength') &&
      (this.formulario.get(campo).touched || this.formulario.get(campo).dirty)
    );
  }

}
