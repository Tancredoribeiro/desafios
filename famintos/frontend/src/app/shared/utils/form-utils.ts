import { FormGroup, FormControl } from '@angular/forms';

export class FormUtils {

  static validarCamposDoFormulario(formGroup: FormGroup) {
    Object.keys(formGroup.controls).forEach(field => {
      console.log(field);
      const control = formGroup.get(field);
      if (control instanceof FormControl) {
        control.markAsTouched({ onlySelf: true });
      } else if (control instanceof FormGroup) {
        this.validarCamposDoFormulario(control);
      }
    });
  }
}
