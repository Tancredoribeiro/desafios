import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-confirmar-pessoa',
  templateUrl: './confirmar-pessoa.component.html',
  styleUrls: ['./confirmar-pessoa.component.css']
})
export class ConfirmarPessoaComponent implements OnInit {

  @Input() formulario: FormGroup;
  @Output() submitted = new EventEmitter<FormGroup>();
  @Output() cancelado = new EventEmitter();

  constructor(public fb: FormBuilder) { }

  ngOnInit() {
    this.formulario = this.fb.group({
      userName: ['', Validators.required],
      senha: ['', Validators.required]
    });
  }

  submitForm() {
    console.log('Formulario submetido:', this.formulario);
    this.submitted.emit(this.formulario);
  }

  cancelar() {
    this.cancelado.emit();
  }

  verificarRequired(campo: string) {
    return (
      this.formulario.get(campo) &&
      this.formulario.get(campo).hasError('required') &&
      (this.formulario.get(campo).touched || !this.formulario.get(campo).dirty)
    );
  }

  get botaoDesabilitado() {
    return this.formulario && !this.formulario.valid;
  }
}
