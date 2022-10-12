# Proyecto flujo-efectivo
Aplicación de manejo del flujo de efectivo.

Se desarrollo una aplicación de acuerdo al requerimiento, se utilizaron las siguientes herramientes.
 - Framework Spring-boot
 - Java 8
 - Base de datos H2
 
 Para poder ver los servicios expuestos puede ingresar a:   
```sh
    mvn spring-boot:run
```

Los servicios expuestos son:
1.- Crear cuenta:
  ```sh
    curl --location --request POST 'http://localhost:8080/cuenta/' \
    --header 'Content-Type: application/json' \
    --data-raw '{
      "cliente": "Osmar Hinojosa",
      "fechaRegistro": "2022-10-12T04:01:34.245Z",
      "moneda": "BOB",
      "numeroCuenta": "01-02",
      "saldo": 500
    }'
   ```
        
2.- Realizar un retiro:
  ```sh
    curl --location --request POST 'http://localhost:8080/transaccion/retiro/01-02' \
    --header 'Content-Type: application/json' \
    --data-raw '{
      "descripcion": "Retiro de cuenta",
      "moneda": "BOB",
      "monto": 100
    }'
   ```           
           
3.- Realizar depósito:
  ```sh
    curl --location --request POST 'http://localhost:8080/transaccion/deposito/01-02' \
    --header 'Content-Type: application/json' \
    --data-raw '{
      "descripcion": "Deposito en cuenta",
      "moneda": "BOB",
      "monto": 300
    }'
  ```
           
4.- Consulta de histórico de transacciones por cuenta:
  ```sh
    curl --location --request GET 'http://localhost:8080/transaccion/transacciones/01-02'
  ```
  
5.- Consulta de saldo de una cuenta:
  ```sh
    curl --location --request GET 'http://localhost:8080/cuenta/01-02'
  ```
