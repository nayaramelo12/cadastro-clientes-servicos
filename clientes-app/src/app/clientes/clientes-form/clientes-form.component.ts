import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { Cliente } from '../cliente';
import { ClientesService } from 'src/app/clientes.service';

@Component({
  selector: 'app-clientes-form',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css']
})
export class ClientesFormComponent implements OnInit {

  cliente: Cliente;
  success: boolean = false;
  errors: String[] = [];
  id: any;

  constructor(private service: ClientesService, private router : Router, private activatedRoute : ActivatedRoute) {
    this.cliente = new Cliente();
   }

  ngOnInit(): void {
    let params: any = this.activatedRoute.params;
    if(params && params.value && params.value.id){
      this.id = params.value.id;
      this.service
        .getClientesById(this.id)
        .subscribe( 
          response => this.cliente = response ,
          errorResponse => this.cliente = new Cliente()
        )
    }
  }

  onSubmit(){
    if(this.id){
      this.service
        .atualizar(this.cliente)
        .subscribe(response => {
          this.success = true;
          this.errors = [];
        }, errorResponse => {
          this.errors = ['Erro ao atualizar o cliente.']
        })
    }else{
      this.service
        .salvar(this.cliente)
        .subscribe( response  => {
          this.success = true;
          this.errors = [];
          this.cliente = response;
        } , errorResponse => {
          this.success = false;
          this.errors = errorResponse.error.errors;
        })
    }
  }

  voltarParaListagem(){
    this.router.navigate(['/clientes/lista']);
  }
}
