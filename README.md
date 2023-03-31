# parcial2_AREP

Se realizo un programa que realiza el algoritmo de collatz Secuence, el cual recibe un numero y revisa si es impar realiza la funcion 3n+1 y si es par n/2, esto con el fin de llegar hasta 1, dicho resultado se debe entregar en forma de JSON

### Ejemplo:
entrada = 13
respuesta = 13→40→20→10→5→16→8→4→2→1

Asi deberia mostrarse desde el browser:
```
{
 "operation": "collatzsequence",
 "input":  13,
 "output":  "13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1"
}
```

## Arquitectura

Se realizo el codigo en java y se creo una imagen en docker la cual corriera dicho proyecto, luego de esto, la imagen se subio a docker hub, para finalmente correrla en una instancia EC2 de AWS, para esto se deben abrir los puertos respectivos y descargar el proyecto desde dockerhub a la instancia.

## Desarrollo

se creo un archivo dockerfile el cual llevaba el siguiente contenido

```
FROM openjdk:8

WORKDIR /usrapp/bin

ENV PORT 6000

COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency

CMD ["java","-cp","./classes:./dependency/*","org.example.Server"]
```
Donde `org.example.Server` es la carpeta donde esta el main.
Se corre el comando `mvn clean install` luego creamos la imagen de la siguiente manera `docker build --tag parcial .`.
Para subirla a docker hub corremos el comando `docker tag parcial judibec/parcialhub` y pusheamos `docker push judibel/parcialhub:latest`, antes de esto debimos crear un repositorio con el nombre que vamos a trabajar, en este caso con parcialhub.
Por ultimo creamos la instancia y descargamos el docker diciendo por cual puerto debe correr el archivo

## Pruebas de funcionamiento
docker corriendo en aws
![image](https://user-images.githubusercontent.com/90010884/229213468-f84c1b9a-9890-4dff-ba5a-461d47b8867a.png)
Funcionamiento con la respuesta adecuada
![image](https://user-images.githubusercontent.com/90010884/229213483-4b5b5e95-23ae-4a06-9e7c-ff11b610734d.png)
Caracteristicas de la instancia creada
![image](https://user-images.githubusercontent.com/90010884/229213501-4840b6e7-24db-4599-8d65-970148f352a4.png)
![image](https://user-images.githubusercontent.com/90010884/229213514-b9315939-d3c9-463a-bdad-7109ae5b872d.png)

Link video 
https://www.youtube.com/watch?v=UgyQ8kWr_yw
